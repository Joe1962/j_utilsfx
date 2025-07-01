/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfxlite.subs;

import static cu.jsoft.j_utilsfxlite.global.CONSTS.EMPTY_STRING;
import static cu.jsoft.j_utilsfxlite.subs.SUB_UtilsOS.getOS;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 *
 * @author joe1962
 */
public class SUB_UtilsFileIO {

	public static boolean FileExists(File MyFile) {
		return MyFile.exists();
	}

	public static boolean FileExists(String MyFile) {
		return new File(MyFile).exists();
	}

	public static boolean FileIsReadable(File MyFile) {
		return MyFile.canRead();
	}

	public static boolean FileIsReadable(String MyFile) {
		return new File(MyFile).canRead();
	}

	public static boolean FileIsWritable(File MyFile) {
		return MyFile.canWrite();
	}

	public static boolean FileIsWritable(String MyFile) {
		return new File(MyFile).canWrite();
	}

	public static boolean FileDelete(File MyFile) {
		if (FileIsWritable(MyFile)) {
			try {
				MyFile.delete();
			} catch (Exception ex) {
				// Couldn't delete for whatever reason...
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	public static File getTempFile(String strPattern, String strSuffix) throws IOException {
		// Create tmpFile file.
		File tmpFile = File.createTempFile(strPattern, "." + strSuffix);

		// Delete tmpFile file when program exits.
		tmpFile.deleteOnExit();

		return tmpFile;
	}

	public static ArrayList<String> readLinesFromFile(File MyFile) throws FileNotFoundException {
		String sreadline = EMPTY_STRING;
		ArrayList<String> areadline = null;

		BufferedReader freadline = new BufferedReader(new FileReader(MyFile));
		try {
			if (MyFile.length() < 16384) {
				areadline = (ArrayList<String>) Files.readAllLines(MyFile.toPath(), StandardCharsets.US_ASCII);
//			} else {
//				while ((sreadline = freadline.readLine()) != null) {
//					areadline.add(sreadline);
//				}
//				while (freadline.ready()) {
//					//sreadline = freadline.readLine();
//					areadline.add(freadline.readLine());
//				}
			}
		} catch (IOException ex) {
			Logger.getLogger(SUB_UtilsFileIO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				freadline.close();
			} catch (IOException ex) {
				Logger.getLogger(SUB_UtilsFileIO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return areadline;
	}

	public static String readFileToString(Path path, Charset MyCharset) throws IOException {
		return Files.readString(path, MyCharset);
	}

	public static void writeStringToFileWithSysEnc(File tmpFile, String strTemp) throws IOException {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(tmpFile))) {
			out.write(strTemp);
			out.close();
		}
	}

	public static void writeStringToFileWithEnc(File tmpFile, String strTemp, String strEnc) throws UnsupportedEncodingException, FileNotFoundException, IOException {
	// strENC can be US-ASCII, ISO-8859-1, UTF-8, UTF-16, etc...
		try (Writer out = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream(tmpFile), strEnc))) {
			out.write(strTemp);
		}
	}

	public static ArrayList<String> getDrives() throws IOException {
		File MyDir[];
		ArrayList<String> DriveList = new ArrayList<>();

		String[] DrivesPossible = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] MyOS = SUB_UtilsOS.getOS();
		if (MyOS[0].startsWith("Linux")) {
			for (String strDriveLetter : DrivesPossible) {
				StringBuilder MySB = new StringBuilder();
				String retStr = EMPTY_STRING;
				//String command = "/sbin/udevadm info --query=property --name=/dev/sd" + strDriveLetter + " | grep ID_SERIAL_SHORT";
				//String command = "/sbin/udevadm info --query=property --name=/dev/sd" + strDriveLetter;
				String command = getUdevadm() + " info --query=property --name=/dev/sd" + strDriveLetter;
				Process child = Runtime.getRuntime().exec(command);
				try (InputStream in = child.getInputStream()) {
					int c;
					while ((c = in.read()) != -1) {
						MySB.append((char)c);
					}
				}
				//System.out.println("/dev/sd" + strDriveLetter + ": " + MySB);
				//if (MySB.indexOf("ID_SERIAL_SHORT") > 0) {
				if (MySB.length() > 64) {
					DriveList.add("/dev/sd" + strDriveLetter);
				}
			}
		} else if (MyOS[0].startsWith("Windows")) {
			for (String strDriveLetter : DrivesPossible) {
				File MyDOSDrive = new File(strDriveLetter + ":");
				if (MyDOSDrive.isDirectory()) {
					DriveList.add(MyDOSDrive.getPath());
				}
			}
//			String DrivesPossible = "CDEFGHIJKLMNOPQRSTUVWXYZ";
//			String tmpDrive;
//			File MyDOSDirs;
//			for (int n = 0; n < DrivesPossible.length(); n++) {
//				tmpDrive = DrivesPossible.substring(n, n + 1);
//				MyDOSDirs = new File(tmpDrive + ":");
//				if (MyDOSDirs.isDirectory()) {
//					DriveList.add(MyDOSDirs.getPath());
//				}
//			}
		} else {
			// UNKNOWN OS...
			DriveList = null;
		}
		return DriveList;
	}

	public static boolean WriteByteArray(File outFile, byte[] HC, boolean ForAppend) {
		try {
			OutputStream output = null;
			try {
				output = new BufferedOutputStream(new FileOutputStream(outFile, ForAppend));
				output.write(HC);
			} finally {
				try {
					// Couldn't write byte array for whatever reason...
					if (!(output == null)) {
						output.close();
					}
				} catch (IOException ex) {
					// Couldn't close BufferedOutputStream for whatever reason...
					return false;
				}
			}
		} catch (FileNotFoundException ex) {
			//echoln("File not found.", false, false);
			return false;
		} catch (IOException ex) {
			//echoln(ex, false, false);
			return false;
		}
		return true;
	}

	public static long CheckSum(String MyFile) {
		try {
			// Compute Adler-32 checksum
			CheckedInputStream cis = new CheckedInputStream(
			//new FileInputStream(MyFile), new Adler32());
			new FileInputStream(MyFile), new CRC32());
			byte[] tempBuf = new byte[128];
			while (cis.read(tempBuf) >= 0) {
			}
			long checksum = cis.getChecksum().getValue();
			return checksum;
		} catch (IOException e) {
			//echoln(e.getMessage(), false, false);
			return 0;
		}
	}

	public static String GetID_HDD(String HDD) throws IOException, InterruptedException {
		StringBuilder MySB = new StringBuilder();
		String retStr = EMPTY_STRING;

		String[] MyOS = SUB_UtilsOS.getOS();
		if (MyOS[0].startsWith("Linux")) {
			// Execute udevadm:
			String Udevadm = getUdevadm();
			ProcessBuilder builder = new ProcessBuilder();
			//builder.command(Udevadm, "info", "--query=property", "--name=" + HDD, "|", "grep", "ID_SERIAL_SHORT");
			builder.command(Udevadm, "info", "--query=property", "--name=" + HDD, "--property=ID_SERIAL_SHORT");
			Process child = builder.start();
			try (InputStream in = child.getInputStream()) {
				int c;
				while ((c = in.read()) != -1) {
					MySB.append((char)c);
				}
			}
			if (MySB.length() > 0 && MySB.charAt(MySB.length() - 1) == '\n') {
				 MySB.deleteCharAt(MySB.length() - 1);
			}
			int subStart = MySB.indexOf("ID_SERIAL_SHORT=");
			int subEnd = MySB.length();
			//retStr = MySB.substring(subStart + 16, subEnd - 1);
			//retStr = MySB.substring(subStart + 16);
			retStr = MySB.delete(subStart, 16).toString();
		} else if (MyOS[0].startsWith("Windows")) {
			try {
				File file = File.createTempFile("realhowto",".vbs");
				file.deleteOnExit();
				try (FileWriter fw = new java.io.FileWriter(file)) {
					String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
							 +"Set colDrives = objFSO.Drives\n"
							 +"Set objDrive = colDrives.item(\"" + HDD.charAt(0) + "\")\n"
							 +"Wscript.Echo objDrive.SerialNumber";  // see note
					fw.write(vbs);
				}
				Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
				try (BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream()))) {
					String line;
					while ((line = input.readLine()) != null) {
						retStr += line;
					}
				}
			} catch(IOException e){
				//
			}
			retStr = retStr.trim();
		} else {
			// UNKNOWN OS...
		}
		return retStr;
	}

	public static String GetID_HDD_Carlitos(String HDD, boolean oldKey) throws IOException {
		StringBuilder MySB = new StringBuilder();
		String retStr = EMPTY_STRING;

		String[] MyOS = getOS();
		if (MyOS[0].startsWith("Linux")) {
			String Udevadm = getUdevadm();
			if (Udevadm==null)
				throw new IOException("Fatal Error: I can't find udevadm!");
			// Execute udevadm:
			String[] cmd = {
					"sh",
					"-c",
					Udevadm + " info --query=property --name=/dev/sda | grep ID_SERIAL_SHORT"
			};
			Process child = Runtime.getRuntime().exec(cmd);
			try (InputStream in = child.getInputStream()) {
				int c;
				while ((c = in.read()) != -1) {
					MySB.append((char) c);
				}
			}
			int subStart = MySB.indexOf("ID_SERIAL_SHORT=");
			if (subStart > -1) {
				// int subEnd = MySB.indexOf("ID_TYPE=");
				retStr = MySB.substring(subStart + 16);
			} else { //Raspberry Pi 3 and Raspberry Pi 400 CASES
				String[] cmd2 = {
						"sh",
						"-c",
						Udevadm + " info --query=property --name=/dev/sda | grep ID_PART_TABLE_UUID"
				};
				Process child2 = Runtime.getRuntime().exec(cmd2);
				try (InputStream in = child2.getInputStream()) {
					int c;
					while ((c = in.read()) != -1) {
						MySB.append((char) c);
					}
				}
				subStart = MySB.indexOf("ID_PART_TABLE_UUID=");
				if (subStart > 0) {
					retStr = MySB.substring(subStart + 19);
				}
			}
		} else if (MyOS[0].startsWith("Windows")) {
			try {
				File file = File.createTempFile("realhowto", ".vbs");
				file.deleteOnExit();
				try (FileWriter fw = new java.io.FileWriter(file)) {
					String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
							+ "Set colDrives = objFSO.Drives\n"
							+ "Set objDrive = colDrives.item(\"" + HDD.charAt(0) + "\")\n"
							+ "Wscript.Echo objDrive.SerialNumber";  // see note
					fw.write(vbs);
				}
				Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
				try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
					String line;
					while ((line = input.readLine()) != null) {
						retStr += line;
					}
				}
			} catch (IOException e) {
				//
			}
			retStr = retStr.trim();
		} else if (MyOS[0].startsWith("Mac OS")) {
			// Execute udevadm:
			//MsgInfoOK(null, "is MacOS!", "TESTING!");
			String command = "/usr/sbin/diskutil info -all";
			//MsgInfoOK(null, "Command: " + command, "TESTING!");
			Process child = Runtime.getRuntime().exec(command);
			try (InputStream in = child.getInputStream()) {
				int c;
				while ((c = in.read()) != -1) {
					MySB.append((char) c);
				}
			}
			//MsgInfoOK(null, "MySB.toString(): " + MySB.toString(), "TESTING!");
			//int subStart = MySB.indexOf("ID_SERIAL_SHORT=");
			int subStart = MySB.indexOf("UUID:");
			//MsgInfoOK(null, "subStart: " + subStart, "TESTING!");
			if (subStart > 0) { //Mac OS X 10.14.6
				retStr = MySB.substring(subStart);
				//MsgInfoOK(null, "retStr after subStart: " + retStr, "TESTING!");
				int subEnd = retStr.indexOf("Disk");
				//MsgInfoOK(null, "subEnd: " + subEnd, "TESTING!");
				retStr = retStr.substring(5, subEnd - 1);
			} else { //Otros casos (Desconocidos)
				//OJO: Tener en cuenta otras versiones de Mac OS
			}
			retStr = retStr.toString().replaceAll("-", "").trim();
			//MsgInfoOK(null, "retStr: " + retStr, "TESTING!");
		} else {
			// UNKNOWN OS...
		}
		if (oldKey) return retStr;
		String retNew = EMPTY_STRING;
		if (!retStr.trim().isEmpty()) {
			try {
				retNew = Integer.toHexString(Integer.parseInt(retStr)).toUpperCase();
			} catch (NumberFormatException e) {
				retNew = retStr.toUpperCase();
			}
			//MsgInfoOK(null, "retNew: " + retNew + "... Now going to Check Hash!", "TESTING!");
			//Fix Hash if it's greater than 8
			String retFixed = retNew;
			String retTemp = "";
			while (retFixed.length() > 8) {
				retTemp = "";
				//MsgInfoOK(null, "Hash is " + retFixed.length() + " digits length... Fixing!", "TESTING!");
				boolean cpy = false;
				for (int rf = 0; rf < retFixed.length(); rf++) {
					if (cpy) retTemp += retFixed.charAt(rf);
					cpy = !cpy;
				}
				retFixed = retTemp;
			}
			//MsgInfoOK(null, "Hash is " + retFixed.length() + " digits length now!", "TESTING!");
			if (retFixed.length()<8) {
				int cn = 8 - retFixed.length();
				retFixed += retNew.substring(0, cn);
			}
			retNew = retFixed;
		}
		return retNew;
	}

	public static String getUdevadm() throws IOException {
		// check for location and availability of udevadm.
		// Returns string with location if executable.
		// Returns null if not.

		ArrayList<String> commandResult = new ArrayList<>();

		// Try 'which' to find udevadm:
		String command = "which -a udevadm";
		Process child = Runtime.getRuntime().exec(command);

		DataInputStream in = new DataInputStream(child.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = br.readLine()) != null) {
			commandResult.add(line);
		}

		for (String commandResultLine : commandResult) {
			File f = new File(commandResultLine);
			if (f.exists() && f.isFile() && f.canExecute()) {
				return commandResultLine;
			}
		}

		return null;
	}

	public static byte[] getBytesFromFile(File file) throws IOException {
		byte[] FileByteArray;
		try (InputStream iStream = new FileInputStream(file)) {
			long length = file.length();
			if (length > Integer.MAX_VALUE) {
				// File iStream too large
			}	FileByteArray = new byte[(int) length];
			int offset = 0;
			int numRead = 0;
			while (offset < FileByteArray.length && (numRead = iStream.read(FileByteArray, offset, FileByteArray.length - offset)) >= 0) {
				offset += numRead;
			}
				if (offset < FileByteArray.length) {
				throw new IOException("Could not completely read file " + file.getName());
			}
		}
		return FileByteArray;
	}

	public static ByteBuffer getBufferFromFile(File file) throws IOException {
		ByteBuffer FileByteBuffer;
		byte[] FileByteArray;
		try (InputStream iStream = new FileInputStream(file)) {
			long FileSize = file.length();
			if (FileSize > Integer.MAX_VALUE) {
				// File iStream too large
			}
			FileByteArray = new byte[(int) FileSize];
			FileByteBuffer = ByteBuffer.allocate(FileByteArray.length);
			int offset = 0;
			int numRead = 0;
			while (offset < FileByteArray.length && (numRead = iStream.read(FileByteArray, offset, FileByteArray.length - offset)) >= 0) {
				offset += numRead;
			}
			if (offset < FileByteArray.length) {
				throw new IOException("Could not completely read file " + file.getName());
			}
		}

		// Copy FileByteArray to ByteBuffer and return it:
		FileByteBuffer.put(FileByteArray);
		// Release FileByteArray:
		FileByteArray = null;

		return FileByteBuffer;
	}

	public static long getFileSize(File file) throws IOException {
		// Get the size of the file:
		long FileSize = file.length();
		if (FileSize > Integer.MAX_VALUE) {
			// File iStream too large
			return -1;
		} else {
			return FileSize;
		}
	}

	public static String getJarPath(Class<?> MyClass) throws URISyntaxException {
		CodeSource codeSource = MyClass.getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());
		return jarFile.getParentFile().getPath();
	}

}

