/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.jsoft.j_utilsfxlite.global;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author joe1962
 */
public class CLS_WavPlayer {

	private static Clip MyClip;

	public static void doInit(AudioInputStream myBeep, boolean DebugFlag) throws UnsupportedAudioFileException, IOException, LineUnavailableException, IllegalArgumentException {
		MyClip = AudioSystem.getClip();
		MyClip.open(myBeep);
	}

	public static void play() {
		if (!MyClip.isActive() && FLAGS.isBEEPAVAILABLE()) {
			MyClip.setFramePosition(0);
			MyClip.start();
		}
	}

	public static void doClose() {
		MyClip.stop();
		MyClip.close();
	}

	public static boolean isNotNull() {
		return MyClip != null;
	}

}
