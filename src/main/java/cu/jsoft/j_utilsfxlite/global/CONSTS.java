/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfxlite.global;

/**
 *
 * @author joe1962
 */
public class CONSTS {
	public static final int RET_CANCEL = 0;			//Cancel button return status code...
	public static final int RET_OK = 1;				//OK button return status code...
	public static final int RET_NO = 0;				//No button return status code...
	public static final int RET_YES = 1;			//Yes button return status code...

	public static final String NEW_LINE = System.getProperty("line.separator");
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	public static final String PATH_SEPARATOR = System.getProperty("path.separator");
	public static final String FILE_ENCODING = System.getProperty("file.encoding");
	public static final String EMPTY_STRING = "";
	public static final String SPACE = " ";
	public static final String TAB = "\t";
	public static final String SINGLE_QUOTE = "'";
	public static final String PERIOD = ".";
	public static final String DOUBLE_QUOTE = "\"";

	// These are my own:
	public static final boolean PASSES = true;
	public static final boolean FAILS = false;

	// These are my own:
	public static final boolean SUCCESS = true;
	public static final boolean FAILURE = false;

	// LED constants:
	public static final int DB_RED = 0;
	public static final int DB_YELLOW = 1;
	public static final int DB_GREEN = 2;
	public static final int DefaultTimerDuration = 3000;

	// RESOURCE CONSTANTS:
	public static final String URL_LEDs = "/LEDS/";
	public static final String URL_LEDs16 = URL_LEDs + "16/";
	public static final String URL_LEDs32 = URL_LEDs + "32/";
	public static final String URL_LEDs22 = URL_LEDs + "22/";
	public static final String URL_LED16Red = URL_LEDs16 + "red.png";
	public static final String URL_LED16Yellow = URL_LEDs16 + "yellow.png";
	public static final String URL_LED16Green = URL_LEDs16 + "green.png";
	public static final String URL_LED22Red = URL_LEDs22 + "red.png";
	public static final String URL_LED22Yellow = URL_LEDs22 + "yellow.png";
	public static final String URL_LED22Green = URL_LEDs22 + "green.png";
	public static final String URL_LED32Red = URL_LEDs32 + "red.png";
	public static final String URL_LED32Yellow = URL_LEDs32 + "yellow.png";
	public static final String URL_LED32Green = URL_LEDs32 + "green.png";
	public static final String URL_Icons_Lib = "/icons/";
	public static final String URL_Sounds_Lib = "/sounds/";
	public static final String BeepFileName = "error.wav";
	public static final String URL_SoundsError = URL_Sounds_Lib + BeepFileName;
	
}
