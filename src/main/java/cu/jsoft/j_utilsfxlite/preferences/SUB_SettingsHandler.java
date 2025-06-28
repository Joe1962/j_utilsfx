/*
 * Copyright Joe1962
 */
package cu.jsoft.j_utilsfxlite.preferences;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 *
 * @author Joe1962
 */
public class SUB_SettingsHandler {

	public static boolean writeSettingForClass(TYP_Setting MySetting, Class<?> MyClass, boolean IsDebug) {
		Preferences MyPrefs;

		MyPrefs = Preferences.userNodeForPackage(MyClass).node(MySetting.getHeading());

		//echoln("SUB_SettingsHandler.writeSettingForClass: Calling class = " + MyClass.getName() + ", Heading = " + MySetting.getHeading() + ", Key = " + MySetting.getKey() + ", Value = " + MySetting.getValue(), IsDebug, false);

		MyPrefs.put(MySetting.getKey(), (String) MySetting.getValue());
		return true;
	}

	public static boolean writeSetting(TYP_Setting MySetting, String PathName, boolean IsDebug) {
		Preferences MyUserRoot;
		Preferences MyPrefs;

		MyUserRoot = Preferences.userRoot();
		MyPrefs = MyUserRoot.node(PathName + "/" + MySetting.getHeading());

		//echoln("SUB_SettingsHandler.writeSetting: " + PathName + "/" + MySetting.getHeading() + ", Key = " + MySetting.getKey() + ", Value = " + MySetting.getValue(), IsDebug, false);

		MyPrefs.put(MySetting.getKey(), (String) MySetting.getValue());
		return true;
	}

	public static void flushSetting(String PathName, boolean IsDebug) throws BackingStoreException {
		Preferences MyUserRoot;
		Preferences MyPrefs;

		MyUserRoot = Preferences.userRoot();
		MyPrefs = MyUserRoot.node(PathName);

		MyPrefs.sync();
	}

	public static String readSettingForClass(TYP_Setting MySetting, Class<?> MyClass, boolean IsDebug) {
		Preferences MyPrefs;

		MyPrefs = Preferences.userNodeForPackage(MyClass).node(MySetting.getHeading());
		String tmpStr = MyPrefs.get(MySetting.getKey(), (String) MySetting.getDefValue());

		//echoln("SUB_SettingsHandler.readSettingForClass: Calling class = " + MyClass.getName() + ", Heading = " + MySetting.getHeading() + ", Key = " + MySetting.getKey() + ", Value = " + tmpStr, IsDebug, false);

		return tmpStr;
	}

	public static String readSetting(TYP_Setting MySetting, String PathName, boolean IsDebug) {
		Preferences MyUserRoot;
		Preferences MyPrefs;

		MyUserRoot = Preferences.userRoot();
		MyPrefs = MyUserRoot.node(PathName + "/" + MySetting.getHeading());
		String tmpStr = MyPrefs.get(MySetting.getKey(), (String) MySetting.getDefValue());

		//echoln("SUB_SettingsHandler.readSetting: " + PathName + "/" + MySetting.getHeading() + ", Key = " + MySetting.getKey() + ", Value = " + tmpStr, IsDebug, false);

		return tmpStr;
	}

	public static boolean existsSettingForClass(TYP_Setting MySetting, Class<?> MyClass, boolean IsDebug) throws BackingStoreException {
		Preferences MyPrefs;

		MyPrefs = Preferences.userNodeForPackage(MyClass).node(MySetting.getHeading());

		//echoln("SUB_SettingsHandler.existsSetting: Calling class = " + MyClass.getName() + ", Heading = " + MySetting.getHeading() + ", Key = " + MySetting.getKey() + ", Value = " + MySetting.getValue(), IsDebug, false);

		return MyPrefs.nodeExists(MySetting.getKey());
	}

	public static boolean existsSetting(TYP_Setting MySetting, String PathName, boolean IsDebug) throws BackingStoreException {
		Preferences MyUserRoot;
		Preferences MyPrefs;

		MyUserRoot = Preferences.userRoot();
		MyPrefs = MyUserRoot.node(PathName + "/" + MySetting.getHeading());

		//echoln("SUB_SettingsHandler.existsSetting: " + PathName + "/" + MySetting.getHeading() +  ", Key = " + MySetting.getKey() +  ", Value = " + MySetting.getValue(), IsDebug, false);
		return MyPrefs.nodeExists(MySetting.getKey());
	}

	public static String[] getChildNodes(String PathName, boolean IsDebug) throws BackingStoreException {
		Preferences MyUserRoot;
		Preferences MyPrefs;

		MyUserRoot = Preferences.userRoot();
		MyPrefs = MyUserRoot.node(PathName);

		return MyPrefs.childrenNames();
	}

	public static void delNode(String PathName, boolean IsDebug) throws BackingStoreException {
		Preferences MyUserRoot;
		Preferences MyPrefs;

		MyUserRoot = Preferences.userRoot();
		MyPrefs = MyUserRoot.node(PathName);

		MyPrefs.removeNode();
		MyPrefs.flush();
	}

	public static void setWindowGeometryPlusSettingForClass(String MyWindowName, TYP_WindowGeometry MyGeomPlus, Class<?> MyClass, boolean IsDebug) {
//		TYP_Setting MySetting;
//
//		MySetting = new TYP_Setting();
//		MySetting.setHeading("WindowGeometry");
//		MySetting.setKey(MyWindowName + ".Left");
////		MySetting.setValue(Integer.toString(MyBounds.x));
//		MySetting.setValue(Integer.toString(MyGeomPlus.getWGeomRect().x));
//		writeSettingForClass(MySetting, MyClass, IsDebug);
//
//		MySetting.setKey(MyWindowName + ".Top");
//		MySetting.setValue(Integer.toString(MyGeomPlus.getWGeomRect().y));
//		writeSettingForClass(MySetting, MyClass, IsDebug);
//
//		MySetting.setKey(MyWindowName + ".Width");
//		MySetting.setValue(Integer.toString(MyGeomPlus.getWGeomRect().width));
//		writeSettingForClass(MySetting, MyClass, IsDebug);
//
//		MySetting.setKey(MyWindowName + ".Height");
//		MySetting.setValue(Integer.toString(MyGeomPlus.getWGeomRect().height));
//		writeSettingForClass(MySetting, MyClass, IsDebug);
//
//		MySetting.setKey(MyWindowName + ".StateMaximized");
//		if (MyGeomPlus.isStateMaximized()) {
//			MySetting.setValue("true");
//		} else {
//			MySetting.setValue("false");
//		}
//		writeSettingForClass(MySetting, MyClass, IsDebug);
	}

	public static void setWindowGeometryPlusSetting(String MyWindowName, TYP_WindowGeometry MyGeomPlus, String MyAppTitle, boolean IsDebug) {
//		TYP_Setting MySetting;
//		String MyPrefsPath = "hj/" + MyAppTitle;
//
//		MySetting = new TYP_Setting();
//		MySetting.setHeading("WindowGeometry");
//		MySetting.setKey(MyWindowName + ".Left");
////		MySetting.setValue(Integer.toString(MyBounds.x));
//		MySetting.setValue(Integer.toString(MyGeomPlus.getWGeomRect().x));
//		writeSetting(MySetting, MyPrefsPath, IsDebug);
//
//		MySetting.setKey(MyWindowName + ".Top");
//		MySetting.setValue(Integer.toString(MyGeomPlus.getWGeomRect().y));
//		writeSetting(MySetting, MyPrefsPath, IsDebug);
//
//		MySetting.setKey(MyWindowName + ".Width");
//		MySetting.setValue(Integer.toString(MyGeomPlus.getWGeomRect().width));
//		writeSetting(MySetting, MyPrefsPath, IsDebug);
//
//		MySetting.setKey(MyWindowName + ".Height");
//		MySetting.setValue(Integer.toString(MyGeomPlus.getWGeomRect().height));
//		writeSetting(MySetting, MyPrefsPath, IsDebug);
//
//		MySetting.setKey(MyWindowName + ".StateMaximized");
//		if (MyGeomPlus.isStateMaximized()) {
//			MySetting.setValue("true");
//		} else {
//			MySetting.setValue("false");
//		}
//		writeSetting(MySetting, MyPrefsPath, IsDebug);
	}

//	public static void setWindowGeometrySettingForClass(String MyWindowName, Rectangle MyWinRect, int MyWinState, Class<?> MyClass, boolean IsDebug) {
//		TYP_Setting MySetting = new TYP_Setting();
//
//		MySetting.setHeading("WindowGeometry");
//		if (MyWinState != JFrame.MAXIMIZED_BOTH) {
//			MySetting.setKey(MyWindowName + ".Left");
//			//MySetting.setValue(Integer.toString(MyBounds.x));
//			MySetting.setValue(Integer.toString(MyWinRect.x));
//			writeSettingForClass(MySetting, MyClass, IsDebug);
//
//			MySetting.setKey(MyWindowName + ".Top");
//			MySetting.setValue(Integer.toString(MyWinRect.y));
//			writeSettingForClass(MySetting, MyClass, IsDebug);
//
//			MySetting.setKey(MyWindowName + ".Width");
//			MySetting.setValue(Integer.toString(MyWinRect.width));
//			writeSettingForClass(MySetting, MyClass, IsDebug);
//
//			MySetting.setKey(MyWindowName + ".Height");
//			MySetting.setValue(Integer.toString(MyWinRect.height));
//			writeSettingForClass(MySetting, MyClass, IsDebug);
//
//			MySetting.setKey(MyWindowName + ".StateMaximized");
//			MySetting.setValue("false");
//		} else {
//			MySetting.setKey(MyWindowName + ".StateMaximized");
//			MySetting.setValue("true");
//		}
//
//		writeSettingForClass(MySetting, MyClass, IsDebug);
//	}

//	public static void setWindowGeometrySetting(String MyWindowName, Rectangle MyWinRect, int MyWinState, String MyAppTitle, boolean IsDebug) {
//		TYP_Setting MySetting = new TYP_Setting();
//		String MyPrefsPath = "hj/" + MyAppTitle;
//
//		MySetting.setHeading("WindowGeometry");
//		if (MyWinState != JFrame.MAXIMIZED_BOTH) {
//			MySetting.setKey(MyWindowName + ".Left");
//			//MySetting.setValue(Integer.toString(MyBounds.x));
//			MySetting.setValue(Integer.toString(MyWinRect.x));
//			writeSetting(MySetting, MyPrefsPath, IsDebug);
//
//			MySetting.setKey(MyWindowName + ".Top");
//			MySetting.setValue(Integer.toString(MyWinRect.y));
//			writeSetting(MySetting, MyPrefsPath, IsDebug);
//
//			MySetting.setKey(MyWindowName + ".Width");
//			MySetting.setValue(Integer.toString(MyWinRect.width));
//			writeSetting(MySetting, MyPrefsPath, IsDebug);
//
//			MySetting.setKey(MyWindowName + ".Height");
//			MySetting.setValue(Integer.toString(MyWinRect.height));
//			writeSetting(MySetting, MyPrefsPath, IsDebug);
//
//			MySetting.setKey(MyWindowName + ".StateMaximized");
//			MySetting.setValue("false");
//		} else {
//			MySetting.setKey(MyWindowName + ".StateMaximized");
//			MySetting.setValue("true");
//		}
//
//		writeSetting(MySetting, MyPrefsPath, IsDebug);
//	}

//	public static TYP_WindowGeometry getWindowGeometrySetting(String MyWindowName, String MyAppTitle, boolean IsDebug) {
//		TYP_Setting MySetting = new TYP_Setting();
//		TYP_WindowGeometry MyGeomPlus = new TYP_WindowGeometry();
//		Rectangle MyRect = new Rectangle();
//		String MyPrefsPath = "hj/" + MyAppTitle;
//
//		MySetting.setHeading("WindowGeometry");
//		MySetting.setKey(MyWindowName + ".Left");
//		MySetting.setDefValue("0");
//		MyRect.x = Integer.valueOf((String) readSetting(MySetting, MyPrefsPath, IsDebug));
//
//		MySetting.setKey(MyWindowName + ".Top");
//		MySetting.setDefValue("0");
//		MyRect.y = Integer.valueOf((String) readSetting(MySetting, MyPrefsPath, IsDebug));
//
//		MySetting.setKey(MyWindowName + ".Width");
//		MySetting.setDefValue("580");
//		MyRect.width = Integer.valueOf((String) readSetting(MySetting, MyPrefsPath, IsDebug));
//
//		MySetting.setKey(MyWindowName + ".Height");
//		MySetting.setDefValue("450");
//		MyRect.height = Integer.valueOf((String) readSetting(MySetting, MyPrefsPath, IsDebug));
//
//		MySetting.setKey(MyWindowName + ".StateMaximized");
//		MySetting.setDefValue("false");
//		MySetting.setValue(readSetting(MySetting, MyPrefsPath, IsDebug));
//		if ("true".equals(MySetting.getValue())) {
//			MyGeomPlus.setStateMaximized(true);
//		} else {
//			MyGeomPlus.setStateMaximized(false);
//		}
//
//		MyGeomPlus.setWGeomRect(MyRect);
//		return MyGeomPlus;
//	}

//	public static TYP_WindowGeometry getWindowGeometrySettingForClass(String MyWindowName, Class<?> MyClass, boolean IsDebug) {
//		TYP_Setting MySetting = new TYP_Setting();
//		TYP_WindowGeometry MyGeomPlus = new TYP_WindowGeometry();
//		Rectangle MyRect = new Rectangle();
//
//		MySetting.setHeading("WindowGeometry");
//		MySetting.setKey(MyWindowName + ".Left");
//		MySetting.setDefValue("0");
//		MyRect.x = Integer.valueOf((String) readSettingForClass(MySetting, MyClass, IsDebug));
//
//		MySetting.setKey(MyWindowName + ".Top");
//		MySetting.setDefValue("0");
//		MyRect.y = Integer.valueOf((String) readSettingForClass(MySetting, MyClass, IsDebug));
//
//		MySetting.setKey(MyWindowName + ".Width");
//		MySetting.setDefValue("580");
//		MyRect.width = Integer.valueOf((String) readSettingForClass(MySetting, MyClass, IsDebug));
//
//		MySetting.setKey(MyWindowName + ".Height");
//		MySetting.setDefValue("450");
//		MyRect.height = Integer.valueOf((String) readSettingForClass(MySetting, MyClass, IsDebug));
//
//		MySetting.setKey(MyWindowName + ".StateMaximized");
//		MySetting.setDefValue("false");
//		MySetting.setValue(readSettingForClass(MySetting, MyClass, IsDebug));
//		if ("true".equals(MySetting.getValue())) {
//			MyGeomPlus.setStateMaximized(true);
//		} else {
//			MyGeomPlus.setStateMaximized(false);
//		}
//
//		MyGeomPlus.setWGeomRect(MyRect);
//		return MyGeomPlus;
//	}

//	public static void loadGeom(String MyWindowName, JFrame MyFrame, String MyAppTitle, boolean IsDebug) {
//		TYP_WindowGeometry MyGeomPlus = getWindowGeometrySetting(MyWindowName, MyAppTitle, IsDebug);
//		if (MyGeomPlus.isStateMaximized()) {
//			MyFrame.setExtendedState(MAXIMIZED_BOTH);
//		} else {
//			MyFrame.setBounds(MyGeomPlus.getWGeomRect());
//		}
//	}

//	public static void saveGeom(String MyWindowName, Rectangle MyWinRect, int MyWinState, String MyAppTitle, boolean IsDebug) {
//		// Save window pos and size.
//		// Same as calling setWindowGeometrySetting() directly, but fits nicer as counterpart of loadGeom.
//		setWindowGeometrySetting(MyWindowName, MyWinRect, MyWinState, MyAppTitle, IsDebug);
//	}

//	public static void LoadGeomForClass(String MyWindowName, JFrame MyFrame, Class<?> MyClass, boolean IsDebug) {
//		TYP_WindowGeometry MyGeomPlus = getWindowGeometrySettingForClass(MyWindowName, MyClass, IsDebug);
//		if (MyGeomPlus.isStateMaximized()) {
//			MyFrame.setExtendedState(MAXIMIZED_BOTH);
//		} else {
//			MyFrame.setBounds(MyGeomPlus.getWGeomRect());
//		}
//	}

}
