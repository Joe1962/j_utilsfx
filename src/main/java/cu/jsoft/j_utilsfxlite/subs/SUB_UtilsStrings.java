/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu.jsoft.j_utilsfxlite.subs;

import static cu.jsoft.j_utilsfxlite.global.CONSTS.SPACE;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author joe1962
 */
public class SUB_UtilsStrings {

	/**
	 *
	 * @param MyString the value of MyString
	 * @param LineLength the value of LineLength
	 * @return 
	 */
	public static String CenterString(String MyString, int LineLength) {
		//String strTrimmed = CustomTrim(MyString, CONSTS.SPACE);
		String strTrimmed = MyString;
		int iWhiteSpace = LineLength - strTrimmed.length();
		String sWhiteSpace = StringRepeater(SPACE.charAt(0), iWhiteSpace / 2);
		return sWhiteSpace + strTrimmed + sWhiteSpace;
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @param MyChar the value of MyChar
	 * @return 
	 */
	public static String CustomTrim(String MyString, String MyChar) {
		// Trim all consecutive ocurrences of MyChar from both ends of MyString:
		MyString = CustomLeftTrim(MyString, MyChar);
		MyString = CustomRightTrim(MyString, MyChar);
		return MyString;
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @return 
	 */
	public static String ReverseString(String MyString) {
		StringBuilder MyStringBuff = new StringBuilder(MyString);
		MyString = MyStringBuff.reverse().toString();
		return MyString;
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @return 
	 */
	public static ArrayList<String> StringCutter(String MyString) {
		//Cuts strings to ArrayList by \n:
		ArrayList<String> MyStringLines = new ArrayList<>();
		// Split by /n:
		MyStringLines.addAll(Arrays.asList(MyString.split("\n")));
		return MyStringLines;
	}

	/**
	 *
	 * @param WrapString the value of WrapString
	 * @param WrapPos the value of WrapPos
	 * @return 
	 */
	public static String WordWrapHTML(String WrapString, int WrapPos) {
		//Prepare variables
		String rsm = WrapString;
		boolean gotspace;
		boolean gotfeed;
		//Jump to characters to add line feeds
		int pos = WrapPos;
		while (pos < rsm.length()) {
			//Progressivly go backwards until next space
			int bf = pos - WrapPos; //What is the stop point
			gotspace = false;
			gotfeed = false;
			//Find space just before to avoid cutting words
			for (int ap = pos; ap > bf; ap--) {
				if (String.valueOf(rsm.charAt(ap)).equals(" ") == true && gotspace == false) {
					//Is it a space?
					//Insert line feed and compute position variable
					gotspace = true;
					pos = ap; //Go to position
				} else if (String.valueOf(rsm.charAt(ap)).equals("/") == true && gotspace == false) {
					//Is it a slash?
					gotspace = true;
					pos = ap; //Go to position
				} else if (String.valueOf(rsm.charAt(ap)).equals("\n") == true && gotfeed == false) {
					//If it is a line feed, go to it
					pos = ap; //Go to position
					gotfeed = true;
				}
			}
			//Got no feed? Append a line feed to the appropriate place
			if (gotfeed == false) {
				if (gotspace == false) {
					rsm = new StringBuffer(rsm).insert(pos, "<br>").toString();
				} else {
					rsm = new StringBuffer(rsm).insert(pos + 1, "<br>").toString();
				}
			}
			//Increment position by WrapPos and restart loop
			pos += (WrapPos + 1);
		}
		//Return the result
		return rsm;
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @param MyChar the value of MyChar
	 * @return 
	 */
	public static String CustomRightTrim(String MyString, String MyChar) {
		// Trim all consecutive ocurrences of MyChar from the right (trailing) end of MyString:
		MyString = ReverseString(MyString);
		MyString = CustomLeftTrim(MyString, MyChar);
		MyString = ReverseString(MyString);
		return MyString;
	}

	/**
	 *
	 * @param WrapString the value of WrapString
	 * @param WrapPos the value of WrapPos
	 * @return 
	 */
	public static String WordWrap(String WrapString, int WrapPos) {
		//Prepare variables
		String rsm = WrapString;
		boolean gotspace;
		boolean gotfeed;
		//Jump to characters to add line feeds
		int pos = WrapPos;
		while (pos < rsm.length()) {
			//Progressivly go backwards until next space
			int bf = pos - WrapPos; //What is the stop point
			gotspace = false;
			gotfeed = false;
			//Find space, slash or linefeed just before to avoid cutting words
			for (int ap = pos; ap > bf; ap--) {
				if (String.valueOf(rsm.charAt(ap)).equals(" ") == true && gotspace == false) {
					//Is it a space?
					//Insert line feed and compute position variable
					gotspace = true;
					pos = ap; //Go to position
				} else if (String.valueOf(rsm.charAt(ap)).equals("/") == true && gotspace == false) {
					//Is it a slash?
					gotspace = true;
					pos = ap; //Go to position
				} else if (String.valueOf(rsm.charAt(ap)).equals("\n") == true && gotfeed == false) {
					//If it is a line feed, go to it
					pos = ap; //Go to position
					gotfeed = true;
				}
			}
			//Got no feed? Append a line feed to the appropriate place
			if (gotfeed == false) {
				if (gotspace == false) {
					rsm = new StringBuffer(rsm).insert(pos, "\n").toString();
				} else {
					rsm = new StringBuffer(rsm).insert(pos + 1, "\n").toString();
				}
			}
			//Increment position by WrapPos and restart loop
			pos += (WrapPos + 1);
		}
		//Return the result
		return rsm;
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @param CellLength the value of CellLength
	 * @return 
	 */
	public static String PadStringLeft(String MyString, int CellLength) {
		String strTrimmed = CustomTrim(MyString, SPACE);
		//String strTrimmed = MyString;
		int iWhiteSpace = CellLength - strTrimmed.length();
		String sWhiteSpace = StringRepeater(SPACE.charAt(0), iWhiteSpace);
		return sWhiteSpace.concat(strTrimmed);
	}

	/**
	 *
	 * @param MyLeftString the value of MyLeftString
	 * @param MyRightString the value of MyRightString
	 * @param LineLength the value of LineLength
	 * @return 
	 */
	public static String LeftAndRightAlignStrings(String MyLeftString, String MyRightString, int LineLength) {
		//String strTrimmedLeft = CustomTrim(MyLeftString, CONSTS.SPACE);
		//String strTrimmedRight = CustomTrim(MyRightString, CONSTS.SPACE);
		String strTrimmedLeft = MyLeftString;
		if ("LSA DORIA".equals(strTrimmedLeft)) {
			String ttt = "ttt";
		}
		String strTrimmedRight = MyRightString;
		int iWhiteSpace = LineLength - strTrimmedLeft.length() - strTrimmedRight.length();
		String sWhiteSpace = StringRepeater(SPACE.charAt(0), iWhiteSpace);
		return strTrimmedLeft.concat(sWhiteSpace.concat(strTrimmedRight));
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @param LineLength the value of LineLength
	 * @return 
	 */
	public static String RightAlignString(String MyString, int LineLength) {
		//String strTrimmed = CustomTrim(MyString, CONSTS.SPACE);
		String strTrimmed = MyString;
		int iWhiteSpace = LineLength - strTrimmed.length();
		String sWhiteSpace = StringRepeater(SPACE.charAt(0), iWhiteSpace);
		return sWhiteSpace.concat(strTrimmed);
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @param LineLength the value of LineLength
	 * @return 
	 */
	public static String LeftAlignString(String MyString, int LineLength) {
		//String strTrimmed = CustomTrim(MyString, CONSTS.SPACE);
		String strTrimmed = MyString;
		int iWhiteSpace = LineLength - strTrimmed.length();
		String sWhiteSpace = StringRepeater(SPACE.charAt(0), iWhiteSpace);
		return strTrimmed.concat(sWhiteSpace);
	}

	/**
	 *
	 * @param Repeater the value of Repeater
	 * @param Repetitions the value of Repetitions
	 * @return 
	 */
	public static String StringRepeater(char Repeater, int Repetitions) {
		StringBuilder MyStringBuff = new StringBuilder(Repetitions);
		for (int i = 0; i < Repetitions; i++) {
			MyStringBuff.append(Repeater);
		}
		return MyStringBuff.toString();
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @param LineLength the value of LineLength
	 * @return 
	 */
	public static ArrayList<String> StringCutterWW(String MyString, int LineLength) {
		//Cuts strings to ArrayList by WordWrap:
		ArrayList<String> MyStringLines = new ArrayList<>();
		ArrayList<String> MyStringLinesTrimmed = new ArrayList<>();
		// WordWrap then split by /n:
		String MyCutterString = WordWrap(MyString, LineLength);
		//MyStringLines.addAll(Arrays.asList(MyCutterString.split("\n")));
		MyStringLines.addAll(StringCutter(MyCutterString));
		// Remove extraneous empty spaces at end of strings:
		for (String MyStringLine : MyStringLines) {
			String strTemp = CustomRightTrim(MyStringLine, SPACE);
			MyStringLinesTrimmed.add(strTemp);
			//System.out.println(MyStringLine.length() + " - " + strTemp.length());
		}
		return MyStringLinesTrimmed;
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @param CellLength the value of CellLength
	 * @return 
	 */
	public static String PadStringRight(String MyString, int CellLength) {
		String strTrimmed = CustomTrim(MyString, SPACE);
		//String strTrimmed = MyString;
		int iWhiteSpace = CellLength - strTrimmed.length();
		String sWhiteSpace = strTrimmed;
		return sWhiteSpace.concat(StringRepeater(SPACE.charAt(0), iWhiteSpace));
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @return 
	 */
	public static String RemoveSpaces(String MyString) {
		// Remove spaces:
		return MyString.replace(" ", "");
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @param MyChar the value of MyChar
	 * @return 
	 */
	public static String CustomLeftTrim(String MyString, String MyChar) {
		// Trim all consecutive ocurrences of MyChar from the left (leading) end of MyString:
		for (int n = 0; n < MyString.length(); n++) {
			if (!MyChar.equals(MyString.substring(n, n + 1))) {
				MyString = MyString.substring(n);
				break;
			} else {
				// check for all same as MyChar:
				if (n == MyString.length() - 1) {
					MyString = "";
				}
			}
		}
		return MyString;
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @return 
	 */
	public static String RemoveDupSpaces(String MyString) {
		// Remove duplicate spaces:
		String tmpStr = "";
		int MyLen = MyString.length();
		do {
			tmpStr = MyString.replace("  ", " ");
			if (tmpStr.length() < MyLen) {
				MyLen = tmpStr.length();
				MyString = tmpStr;
			} else {
				break;
			}
			// Keep going as long as string length is getting smaller...
		} while (true);
		return tmpStr;
	}
	
}
