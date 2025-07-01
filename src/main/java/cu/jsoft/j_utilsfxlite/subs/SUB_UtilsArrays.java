/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfxlite.subs;

import java.util.ArrayList;

/**
 *
 * @author joe1962
 */
public class SUB_UtilsArrays {

	/**
	 *
	 * @param cadena the value of cadena
	 * @return 
	 */
	public static Integer[] str2intArray(String cadena) {
		cadena = cadena.replace("{", "").replace("}", "").trim();
		if (cadena.isEmpty()) {
			return null;
		} else {
			String[] cadArr;
			cadArr = cadena.split(",");
			int i0 = 0;
			Integer[] intArray = new Integer[cadArr.length];
			for (String cad : cadArr) {
				intArray[i0++] = Integer.parseInt(cad);
			}
			return intArray;
		}
	}

	/**
	 *
	 * @param arr the value of arr
	 * @return 
	 */
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

	/**
	 *
	 * @param MyString the value of MyString
	 * @return 
	 */
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
			Integer[] intArray = new Integer[strArray.length];
			for (String item : strArray) {
				intArray[n++] = Integer.parseInt(item);
			}
			return intArray;
		}
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @return 
	 */
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
			int[] intArray = new int[strArray.length];
			for (String item : strArray) {
				intArray[n++] = Integer.parseInt(item);
			}
			return intArray;
		}
	}

	/**
	 *
	 * @param arr the value of arr
	 * @return 
	 */
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

	/**
	 *
	 * @param arr the value of arr
	 * @param codi the value of codi
	 * @return 
	 */
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

	/**
	 *
	 * @param arr the value of arr
	 * @return 
	 */
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

	/**
	 *
	 * @param arr the value of arr
	 * @return 
	 */
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

	/**
	 *
	 * @param arr the value of arr
	 * @return 
	 */
	public static String array2Str(Boolean[] arr) {
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

	/**
	 *
	 * @param MyString the value of MyString
	 * @return 
	 */
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

	/**
	 *
	 * @param arrString the value of arrString
	 * @param separator the value of separator
	 * @return 
	 */
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

	/**
	 *
	 *
	 * @param arr tipo ArrayList
	 * @param find tipo String, es la cadena a buscar
	 * @param ifind tipo int, es la columna donde buscar esa cadena
	 * @param ireturn tipo int, es la columna donde esta el valor a devolver,
	 * @return el valor de la columna a devolver, siempre va a ser un String, si
	 * se necesita otro formato lo convertiremos del otro lado...
	 */
	public static String getArrayListValue(ArrayList arr, String find, int ifind, int ireturn) {
		String sret = "-1";
		for (Object item : arr) {
			if (((ArrayList) item).get(ifind).toString().equals(find)) {
				sret = ((ArrayList) item).get(ireturn).toString();
				break;
			}
		}
		return sret;
	}

	/**
	 *
	 *
	 * @param arr tipo ArrayList
	 * @param find tipo int, es el valor a buscar
	 * @param ifind tipo int, es la columna donde buscar esa cadena
	 * @param ireturn tipo int, es la columna donde esta el valor a devolver,
	 * @return el valor de la columna a devolver, siempre va a ser un String, si
	 * se necesita otro formato lo convertiremos del otro lado...
	 */
	public static String getArrayListValue(ArrayList arr, int find, int ifind, int ireturn) {
		String sret = "-1";
		for (Object item : arr) {
			if (((ArrayList) item).get(ifind).toString().equals(String.valueOf(find))) {
				sret = ((ArrayList) item).get(ireturn).toString();
				break;
			}
		}
		return sret;
	}
	
}
