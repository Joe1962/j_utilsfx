/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.global;

/**
 *
 * @author joe1962
 */
public class FLAGS {

	// GENERAL FLAGS:
	private static Boolean SingleInstance;
	private static boolean BEEPAVAILABLE;
	private static boolean BEEP;
	private static boolean DEBUG;
	private static boolean TESTING;

	// ERROR FLAGS:
	private static boolean FLAG_UNKNOWN_OS = false;

	public static Boolean getSingleInstance() {
		return SingleInstance;
	}

	public static void setSingleInstance(Boolean aSingleInstance) {
		SingleInstance = aSingleInstance;
	}

	public static boolean isBEEPAVAILABLE() {
		return BEEPAVAILABLE;
	}

	public static void setBEEPAVAILABLE(boolean aBEEPAVAILABLE) {
		BEEPAVAILABLE = aBEEPAVAILABLE;
	}

	public static boolean isBEEP() {
		return BEEP;
	}

	public static void setBEEP(boolean aBEEP) {
		BEEP = aBEEP;
	}

	public static boolean isDEBUG() {
		return DEBUG;
	}

	public static void setDEBUG(boolean aDEBUG) {
		DEBUG = aDEBUG;
	}

	public static boolean isTESTING() {
		return TESTING;
	}

	public static void setTESTING(boolean aTESTING) {
		TESTING = aTESTING;
	}

	public static boolean isFLAG_UNKNOWN_OS() {
		return FLAG_UNKNOWN_OS;
	}

	public static void setFLAG_UNKNOWN_OS(boolean aFLAG_UNKNOWN_OS) {
		FLAG_UNKNOWN_OS = aFLAG_UNKNOWN_OS;
	}

}
