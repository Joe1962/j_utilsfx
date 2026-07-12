/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.utils.fx;

import com.sun.javafx.PlatformUtil;

/**
 *
 * @author joe1962
 */
public class UtilsFX_OS {

	public OSTYPE getFXOS() {
		if (PlatformUtil.isLinux()) {
			return OSTYPE.LINUX;
		} else if (PlatformUtil.isWindows()) {
			return OSTYPE.WINDOWS;
		} else if (PlatformUtil.isMac()) {
			return OSTYPE.MACOS;
		} else {
			return OSTYPE.UNKNOWN;
		}
	}

	public enum OSTYPE {
		LINUX,
		WINDOWS,
		MACOS,
		UNKNOWN
	}

}
