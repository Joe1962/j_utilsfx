/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfxlite.subs;

import static cu.jsoft.j_utilsfxlite.subs.SUB_UtilsNotifications.echoln;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 *
 * @author joe1962
 */
public class SUB_UtilsOS {

	public static final File BasePathCPUs = new File("/sys/devices/system/cpu/");

	public static long getTotalMem() {
		return Runtime.getRuntime().totalMemory();
	}

	public static String getUserHome() {
		return System.getProperty("user.home");
	}

	public static String getTempDir() {
		return System.getProperty("java.io.tmpdir");
	}

	public static int getProcessors() {
		return Runtime.getRuntime().availableProcessors();
	}

	public static String getUserName() {
		return System.getProperty("user.name");
	}

	public static String getJavaVersion() {
		return System.getProperty("java.version");
	}

	public static String[] getOS() {
		String[] OpSys = new String[4];
		OpSys[1] = System.getProperty("os.name");
		OpSys[2] = System.getProperty("os.version");
		OpSys[3] = System.getProperty("os.arch");
		OpSys[0] = OpSys[1] + " " + OpSys[2] + " on " + OpSys[3];
		return OpSys;
	}

	public static long getFreeMem() {
		return Runtime.getRuntime().freeMemory();
	}

	public static long getMaxMem() {
		return Runtime.getRuntime().maxMemory();
	}

	public static PrintService[] getPrintServices(boolean DebugMode, boolean ForceEcho) {
		PrintService[] pservices = PrintServiceLookup.lookupPrintServices(null, null);
		int n = 0;
		for (PrintService printService : pservices) {
			echoln("Printservice: " + n++ + " - " + printService.getName(), DebugMode, ForceEcho);
		}
		if (pservices.length > 0) {
			return pservices;
		} else {
			return null;
		}
	}
	
	public static void CopyToClipboard(String strToCopy) {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection strsel = new StringSelection(strToCopy);
		clip.setContents(strsel, strsel);
	}

}
