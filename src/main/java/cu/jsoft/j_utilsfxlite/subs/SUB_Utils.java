/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfxlite.subs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joe1962
 */
public class SUB_Utils {

	public static String whoCalledMe() {
		String retVal = "";
		//The constructor for Throwable has a native function that fills the stack trace.
		java.lang.StackTraceElement[] trace = (new Throwable()).getStackTrace();
		//Once you have the trace you can pick out information you need.
		if (trace.length >= 2) {
			retVal = trace[1].getClassName() + "."
				+ trace[1].getMethodName() + "()";
		}

		return retVal;
	}

	public static void ProcLaunch(String MyCommand) throws IOException {
		Process child = Runtime.getRuntime().exec(MyCommand);

//		StringBuilder MySB = new StringBuilder();
//		try (InputStream in = child.getInputStream()) {
//			int c;
//			while ((c = in.read()) != -1) {
//				MySB.append((char)c);
//			}
//		}
	}

	public static int ProcLaunchWait(String MyCommand) throws IOException, InterruptedException {
		Process child = Runtime.getRuntime().exec(MyCommand);
		return child.waitFor();
	}

	public static String AutoUnitsFreq(int clockfreq) {
		// Return drive/partition size in pretty format as "value;unit" string:
		//private Temp AS Integer
		//
		//' TODO: complete this with KHz, Hz, etc.
		//IF clockfreq < 1000000 THEN			// MegaHertz:
		//   RETURN Format$(clockfreq / 1000, "###,###,###") & " MHz"
		//ELSE			// GigaHertz:
		//   RETURN Format$(clockfreq / 1000000, "####,###,###.000") & " GHz"
		//END IF

		return "";
	}

	public static int AutoUnitsFreqRev(String clockfreqPretty) {
		//Convert pretty format back to drive/partition size
		//
		//' TODO: complete this with KHz, Hz, etc.
		//IF InStr(clockfreqPretty, "GHz") > 0 THEN
		//   RETURN Val(clockfreqPretty) * 1000000
		//ELSE IF InStr(clockfreqPretty, "MHz") > 0 THEN
		//   RETURN Val(clockfreqPretty) * 1000
		//ENDIF

		return 0;
	}

	public static void ERROR_exit(byte errtype, boolean err_crit, String errmesg, String filename) {	//OPTIONAL String filename) {
		//SELECT CASE errtype
		//CASE 0   'File read error
		//   IF err_crit THEN
		//      Message.Error("Error reading: " & filename & gb.NewLine & "Nothing else to do here, so I'll just exit...")
		//   ELSE
		//      Message.Error("Error reading: " & filename & gb.NewLine)
		//   ENDIF
		//CASE 255 'Misc. error, supply own message
		//   IF err_crit THEN
		//      Message.Error(errmesg & " " & filename & gb.NewLine & gb.NewLine & "Nothing else to do here, so I'll just exit...")
		//   ELSE
		//      Message.Error(errmesg)
		//   ENDIF
		//END SELECT
		//
		//IF err_crit THEN
		//   FMain.butExit_Click
		//   QUIT
		//ENDIF
	}

	public static void setupBeep(String BeepFilePath, boolean DebugFlag) {
		InputStream input = null;
		int size = 0;

		File MyBeepFile = new File(BeepFilePath);
		try {
			// TODO: convert this to read a resource file:
			input = new FileInputStream(MyBeepFile);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(SUB_Utils.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static boolean isInteger(Object MyTestSubject) {
		try {
			int MyInt = (int) MyTestSubject;
		} catch (Exception e) {			// ClassCastException or NumberFormatException ???
			return false;
		}
		return true;
	}

	public static boolean isString(Object MyTestSubject) {
		try {
			String MyStr = (String) MyTestSubject;
		} catch (Exception e) {			// ClassCastException or NumberFormatException ???
			return false;
		}
		return true;
	}

	public static boolean isBigDecimal(Object MyTestSubject) {
		try {
			BigDecimal MyBD = (BigDecimal) MyTestSubject;
		} catch (Exception e) {			// ClassCastException or NumberFormatException ???
			return false;
		}
		return true;
	}

	public static boolean isDouble(Object MyTestSubject) {
		try {
			Double MyDouble = (Double) MyTestSubject;
		} catch (Exception e) {			// ClassCastException or NumberFormatException ???
			return false;
		}
		return true;
	}

}
