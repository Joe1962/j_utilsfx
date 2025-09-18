/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.security;

import cu.jsoft.j_utilsfx.global.CONSTS;
import static cu.jsoft.j_utilsfx.security.SUB_Crypto.MD5Encrypt;
import cu.jsoft.j_utilsfx.security.types.TYP_AES_Utils;
import static cu.jsoft.j_utilsfx.subs.SUB_UtilsFileIO.GetID_HDD;
import static cu.jsoft.j_utilsfx.subs.SUB_UtilsFileIO.getDrives;
import static cu.jsoft.j_utilsfx.subs.SUB_UtilsNotifications.echoln;
import cu.jsoft.j_utilsfx.subs.SUB_UtilsOS;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ini4j.Wini;

/**
 *
 * @author Caesar
 */
public class SUB_Protect {

	public SUB_Protect() {
	}

	public String getEncryptedString(String toEncrypt, String theSalt, String theSecKey, byte[] theIV) {
		TYP_AES_Utils MyTyp = CLS_AES_Utils.strEncryptHelper(toEncrypt, theSalt, theSecKey, theIV);
		return MyTyp.getEncryptedStr();
	}

	public String getDecryptedString(String encryptedString, String theSecKey, byte[] theIV) {
		TYP_AES_Utils MyTyp = new TYP_AES_Utils();
		MyTyp.setSecKeyStr(theSecKey);
		MyTyp.setIv(theIV);
		MyTyp.setEncryptedStr(encryptedString);
		return CLS_AES_Utils.strDecrypthelper(MyTyp);
	}

	// Old stuff to convert to new encryption and JSON preferences (check out ntfy projects):

	private static final String sTail = " designed and programmed by HJSoft";
	public static String[] myModules = {"hj.pos", "hj.rms", "hj.rms.finanzas", "hj.rms.inventory", "hj.rms.pos", "HJ-InterPOS"};

	public static String getRegisterCode(String HDD, int appID) throws IOException, NoSuchAlgorithmException, InterruptedException {
		String tmpCode = "";
		String RegCode;

		String HDDSerial = GetID_HDD(HDD);
		echoln(HDDSerial, false, false);

		int lenHDD = HDDSerial.length();
		String modules = myModules[appID] + sTail;
		int lenaID = modules.length();

		int ix = (lenHDD > lenaID) ? lenHDD : lenaID;

		for (int it = 0; it < ix; it++) {
			tmpCode += (it >= lenHDD) ? HDDSerial.charAt(it % lenHDD) : HDDSerial.charAt(it);
			tmpCode += (it >= lenaID) ? modules.charAt(it % lenaID) : modules.charAt(it);
		}

		RegCode = MD5Encrypt(tmpCode);
		return RegCode;

	}

	public static Boolean isRegistered(String AppName, String module, int AppID) throws InterruptedException {
		Wini ini;
		String RegCode1;
		String RegCode2;

		String slsh = System.getProperty("file.separator");
		String folders = module.replace(".", slsh);

		String myPath = SUB_UtilsOS.getUserHome() + slsh + "." + folders;

		File p = new File(myPath);
		if (!p.exists()) {
			return false;
		}

		File f = new File(myPath + slsh + AppName + ".ini");
		if (!f.exists()) {
			return false;
		}
		try {
			ini = new Wini(f);
			RegCode1 = ini.get("Register", module);
		} catch (IOException ex) {
			Logger.getLogger(SUB_Protect.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}

		ArrayList ADs = null;
		try {
			ADs = getDrives();
		} catch (IOException ex) {
			Logger.getLogger(SUB_Protect.class.getName()).log(Level.SEVERE, null, ex);
		}
		for (int im = 0; im < ADs.size(); im++) {
			try {
				RegCode2 = getRegisterCode(ADs.get(im).toString(), AppID);
			} catch (IOException | NoSuchAlgorithmException ex) {
				Logger.getLogger(SUB_Protect.class.getName()).log(Level.SEVERE, null, ex);
				return false;
			}
			if (RegCode1.equals(RegCode2)) {
				return true;
			}
		}
		return false;
	}

	public static Boolean isOneOfOurDrivesPresent(boolean isDebug) throws InterruptedException {
		String CurrID = CONSTS.EMPTY_STRING;
		ArrayList<String> DriveID = new ArrayList<>();

		DriveID.add("AAAUR56402O40KYH");						// (JJ) Lexar 32GB (L)
		DriveID.add("907952908");							// (JJ) Lexar 32GB (W)
		DriveID.add("5B790A8F7123");							// (JJ) KINGSTON DT1 (L)
		DriveID.add("935639147");								// (JJ) KINGSTON DT1 (W)
		DriveID.add("6C626DBEDEA5EBC120001515");			// (JJ) KINGSTON DT109 (L)
		DriveID.add("-790159306");								// (JJ) KINGSTON DT109 (W)
		DriveID.add("05030483460513");						// (JJ) IOMEGA (L)
		DriveID.add("876434455");								// (JJ) IOMEGA (W)
		DriveID.add("AAGRRA9P68V97GAT");						// (Herbie) Lexar 32GB black (L)
		DriveID.add("1726784714");								// (Herbie) Lexar 32GB black (W)
		DriveID.add("07C104081C74EEC0");						// (Carlitos) PHILIPS UFD (L)
		DriveID.add("816195292");								// (Carlitos) PHILIPS UFD (W)

		ArrayList<String> AvailableDrives = null;
		try {
			AvailableDrives = getDrives();
		} catch (IOException ex) {
			Logger.getLogger(SUB_Protect.class.getName()).log(Level.SEVERE, null, ex);
		}
		for (String AvailableDrive : AvailableDrives) {
			try {
				CurrID = GetID_HDD(AvailableDrive);
				if (isDebug) {
					System.out.println(AvailableDrive + " - " + CurrID);
				}
			} catch (IOException ex) {
				Logger.getLogger(SUB_Protect.class.getName()).log(Level.SEVERE, null, ex);
			}
			for (String ADrive : DriveID) {
				if (ADrive.equals(CurrID)) {
					return true;
				}
			}
		}
		return false;
	}

}
