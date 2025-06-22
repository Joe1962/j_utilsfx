/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu.jsoft.j_utilsfxlite.subs;

import java.math.BigDecimal;

/**
 *
 * @author joe1962
 */
public class SUB_UtilsNums {

	/**
	 *
	 * @param sNumber the value of sNumber
	 * @return 
	 */
	public static String CleanUpNumString2String(String sNumber) {
		String tmpStr = sNumber.replace(",", "");
		tmpStr = tmpStr.replace(" ", "");
		tmpStr = tmpStr.replace("$", "");
		// TODO: make universal => check leftmost and rightmost chars for non-numeric...
		return tmpStr;
	}

	/**
	 *
	 * @param MyString the value of MyString
	 * @return 
	 */
	public static String String2Curr(String MyString) {
		// needs to have 2 decimal places...!!!
		// Remove spaces:
		MyString = MyString.replace(" ", "");
		// Last 2 chars are Cents:
		String tmpCents = MyString.substring(MyString.length() - 2, MyString.length());
		// The rest towards the left are Pesos:
		String tmpPesos = MyString.substring(0, MyString.length() - 2);
		// Remove leading zeroes:
		tmpPesos = SUB_UtilsStrings.CustomLeftTrim(tmpPesos, "0");
		if ("".equals(tmpPesos)) {
			tmpPesos = "0";
		}
		//tmpStr = "$" + tmpPesos + "." + tmpCents;
		MyString = tmpPesos + "." + tmpCents;
		return MyString;
	}

	/**
	 *
	 * @param Hex the value of Hex
	 * @param NumBytes the value of NumBytes
	 * @return 
	 */
	public static byte[] Int2DecByte(int Hex, int NumBytes) {
		byte[] RD = new byte[NumBytes];
		String sHex = String.format("%06d", Hex);
		for (int n = 0; n < RD.length; n++) {
			RD[n] = (byte) Short.parseShort(sHex.substring(n * 2, (n * 2) + 2), 16);
		}
		return RD;
	}

	/**
	 *
	 * @param MyBigDec the value of MyBigDec
	 * @param MyInt the value of MyInt
	 * @return 
	 */
	public static BigDecimal MultBigDecByInt(BigDecimal MyBigDec, int MyInt) {
		String tmpStr = String.valueOf(MyInt);
		Double tmpDbl = Double.parseDouble(tmpStr);
		return MyBigDec.multiply(BigDecimal.valueOf(tmpDbl));
	}

	/**
	 *
	 * @param sNumber the value of sNumber
	 * @return 
	 */
	public static BigDecimal CleanUpNumString2BigDec(String sNumber) {
		String tmpStr = sNumber.replace(",", "");
		tmpStr = tmpStr.replace(" ", "");
		tmpStr = tmpStr.replace("$", "");
		return new BigDecimal(tmpStr);
	}

	/**
	 *
	 * @param BigDec the value of BigDec
	 * @param NumBytes the value of NumBytes
	 * @return 
	 */
	public static byte[] BigDec2DecByte(BigDecimal BigDec, int NumBytes) {
		byte[] RD = new byte[NumBytes];
		String sHex = String.format("%07.2f", BigDec).replace(".", "");
		for (int n = 0; n < RD.length; n++) {
			RD[n] = (byte) Short.parseShort(sHex.substring(n * 2, (n * 2) + 2), 16);
		}
		return RD;
	}
	
}
