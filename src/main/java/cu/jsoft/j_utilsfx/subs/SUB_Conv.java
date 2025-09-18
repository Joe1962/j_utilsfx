/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.subs;

import static cu.jsoft.j_utilsfx.subs.SUB_UtilsStrings.CustomLeftTrim;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author joe1962
 */
public class SUB_Conv {
	
	public static String String2Curr(String MyString) {
		// needs to have 2 decimal places...!!!
		// Remove spaces:
		MyString = MyString.replace(" ", "");
		// Last 2 chars are Cents:
		String tmpCents = MyString.substring(MyString.length() - 2, MyString.length());
		// The rest towards the left are Pesos:
		String tmpPesos = MyString.substring(0, MyString.length() - 2);
		// Remove leading zeroes:
		tmpPesos = CustomLeftTrim(tmpPesos, "0");
		if ("".equals(tmpPesos)) {
			tmpPesos = "0";
		}
		//tmpStr = "$" + tmpPesos + "." + tmpCents;
		MyString = tmpPesos + "." + tmpCents;

		return MyString;
	}

	public static String[] strcsv2strArray(String MyString) {
		if (MyString == null || MyString.isEmpty()) {
			return null;
		}
		MyString = MyString.replace("{", "").replace("}", "").trim();
		if (MyString.isEmpty()) {
			return null;
		} else {
			String[] strArray;
			strArray = MyString.split(",");
			return strArray;
		}
	}

	public static String strArray2strCSV(String[] arrString, String separator) {
		StringBuilder retString = new StringBuilder();
		for (String string : arrString) {
			retString.append(string);
			if (separator != null & separator.length() > 0) {
				retString.append(separator);
			} else {
				retString.append(",");
			}
		}
		return retString.substring(0, retString.length() - 1);
	}

	public static Integer[] strcsv2IntegerArray(String MyString) {
		if (MyString == null || MyString.isEmpty()) {
			return null;
		}
		MyString = MyString.replace("{", "").replace("}", "").trim();
		if (MyString.isEmpty()) {
			return null;
		} else {
			String[] strArray;
			strArray = MyString.split(",");
			int n = 0;
			Integer intArray[] = new Integer[strArray.length];
			for (String item : strArray) {
				intArray[n++] = Integer.valueOf(item);
			}
			return intArray;
		}
	}

	public static int[] strcsv2intArray(String MyString) {
		if (MyString == null || MyString.isEmpty()) {
			return null;
		}
		MyString = MyString.replace("{", "").replace("}", "").trim();
		if (MyString.isEmpty()) {
			return null;
		} else {
			String[] strArray;
			strArray = MyString.split(",");
			int n = 0;
			int intArray[] = new int[strArray.length];
			for (String item : strArray) {
				intArray[n++] = Integer.parseInt(item);
			}
			return intArray;
		}
	}

	public static Integer[] str2intArray(String cadena) {
		cadena = cadena.replace("{", "").replace("}", "").trim();
		if (cadena.isEmpty()) {
			return null;
		} else {
			String[] cadArr;
			cadArr = cadena.split(",");
			int i0 = 0;
			Integer intArray[] = new Integer[cadArr.length];
			for (String cad : cadArr) {
				intArray[i0++] = Integer.valueOf(cad);
			}
			return intArray;
		}
	}

	public static String array2Str(Integer[] arr) {
		String cadena = "{";
		if (arr.length > 0) {
			for (int i0 = 0; i0 < arr.length; i0++) {
				cadena += arr[i0].toString() + ",";
			}
			cadena = cadena.substring(0, cadena.length() - 1);
		}
		cadena += "}";
		return cadena;
	}

	public static String array2Str(String[] arr) {
		String cadena = "{";
		if (arr.length > 0) {
			for (int i0 = 0; i0 < arr.length; i0++) {
				cadena += arr[i0] + ",";
			}
			cadena = cadena.substring(0, cadena.length() - 1);
		}
		cadena += "}";
		return cadena;
	}

	public static String array2Str(String[] arr, String header, String footer, String delim) {
		// Takes header, footer and delmiter Strings...
		String cadena = header;
		if (arr.length > 0) {
			for (String arr1 : arr) {
				cadena += arr1 + delim;
			}
			cadena = cadena.substring(0, cadena.length() - 1);
		}
		cadena += footer;
		return cadena;
	}

	public static String intArray2Str(Integer[] arr) {
		String cadena = "{";
		if (arr.length > 0) {
			for (int i0 = 0; i0 < arr.length; i0++) {
				cadena += arr[i0].toString() + ",";
			}
			cadena = cadena.substring(0, cadena.length() - 1);
		}
		cadena += "}";
		return cadena;
	}

	public static String intArray2StrExceptFor(Integer[] arr, int codi) {
		String cadena = "{";
		if (arr.length > 0) {
			for (int ite : arr) {
				if (ite != codi) {
					cadena += ite + ",";
				}
			}
			if (cadena.length() > 1) {
				cadena = cadena.substring(0, cadena.length() - 1);
			}
		}
		cadena += "}";
		return cadena;
	}

	public static String IntegerArray2strCSV(Integer[] arr) {
		String cadena = "{";
		if (arr.length > 0) {
			for (Integer i0 = 0; i0 < arr.length; i0++) {
				cadena += arr[i0].toString() + ",";
			}
			cadena = cadena.substring(0, cadena.length() - 1);
		}
		cadena += "}";
		return cadena;
	}

	public static BigDecimal CleanUpNumString2BigDec(String sNumber) {
		String tmpStr = sNumber.replace(",", "");
		tmpStr = tmpStr.replace(" ", "");
		tmpStr = tmpStr.replace("$", "");
		return new BigDecimal(tmpStr);
	}

	public static String CleanUpNumString2String(String sNumber) {
		String tmpStr = sNumber.replace(",", "");
		tmpStr = tmpStr.replace(" ", "");
		tmpStr = tmpStr.replace("$", "");
		// TODO: make universal => check leftmost and rightmost chars for non-numeric...
		return tmpStr;
	}

	public static String List2Str(List<String> aList, String header, String footer, String delim) {
		return header + String.join(delim, aList) + footer;
	}

	public static String bool2Str(boolean aBool) {
		if (aBool) {
			return "true";
		} else {
			return "false";
		}
	}

	public static boolean Str2bool(String aStr) {
		return "true".equals(aStr);
	}

	public static BigDecimal textToBigDecimalWithNullCheck(String texttoConvert) throws Exception {
		return (texttoConvert == null || texttoConvert.isEmpty()) ? null : new BigDecimal(texttoConvert);
	}

	public static Integer textToIntegerWithNullCheck(String texttoConvert) throws Exception {
		return (texttoConvert == null || texttoConvert.isEmpty()) ? null : Integer.valueOf(texttoConvert);
	}

}

