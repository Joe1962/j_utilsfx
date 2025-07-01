/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfxlite.subs;

import cu.jsoft.j_utilsfxlite.global.CLS_WavPlayer;
import static cu.jsoft.j_utilsfxlite.global.FLAGS.isBEEP;
import static cu.jsoft.j_utilsfxlite.global.FLAGS.isBEEPAVAILABLE;
import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author joe1962
 */
public class SUB_UtilsNotifications {
	
	/**
	 *
	 * @param Thingymajig the value of Thingymajig
	 * @param DebugMode the value of DebugMode
	 * @param ForceEcho the value of ForceEcho
	 */
	public static void echo(Object Thingymajig, boolean DebugMode, boolean ForceEcho) {
		if (DebugMode) {
			System.out.print(String.valueOf(Thingymajig));
		} else {
			if (ForceEcho) {
				System.out.print(String.valueOf(Thingymajig));
			}
		}
	}

	/**
	 *
	 * @param Thingymajig the value of Thingymajig
	 * @param DebugMode the value of DebugMode
	 * @param ForceEcho the value of ForceEcho
	 */
	public static void echoln(Object Thingymajig, boolean DebugMode, boolean ForceEcho) {
		if (DebugMode) {
			System.out.println(String.valueOf(Thingymajig));
		} else {
			if (ForceEcho) {
				System.out.println(String.valueOf(Thingymajig));
			}
		}
	}

	/**
	 *
	 * @param MyComment the value of MyComment
	 * @param DebugMode the value of DebugMode
	 * @param ForceEcho the value of ForceEcho
	 */
	public static void echoClassMethodComment(String MyComment, boolean DebugMode, boolean ForceEcho) {
		//The constructor for Throwable has a native function that fills the stack trace.
		StackTraceElement[] trace = (new Throwable()).getStackTrace();
		echoln(trace[1].getClassName() + "." + trace[1].getMethodName() + "()" + ": " + MyComment, DebugMode, ForceEcho);
	}

	public static void doBeep() {
		//echoln("beep...", false, false);
		//Toolkit.getDefaultToolkit().beep();
		//hj.utils.CLS_WavPlayer_old.getMyClip().setFramePosition(0);
		if (isBEEP() & isBEEPAVAILABLE()) {
			CLS_WavPlayer.play();
		}
	}

	public static void closeBeep() {
		if (CLS_WavPlayer.isNotNull()) {
			CLS_WavPlayer.doClose();
		}
	}

	public static void setupBeep(boolean DebugFlag) throws UnsupportedAudioFileException, IOException, LineUnavailableException, IllegalArgumentException {
		BufferedInputStream myStream = new BufferedInputStream(SUB_Utils.class.getClassLoader().getResourceAsStream("sounds/error.wav"));
		AudioInputStream myBeep = AudioSystem.getAudioInputStream(myStream);
		CLS_WavPlayer.doInit(myBeep, DebugFlag);
	}

}
