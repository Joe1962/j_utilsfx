/*
 * Copyright Joe1962
 */
package cu.jsoft.j_utilsfxlite.security;

import cu.jsoft.j_utilsfxlite.security.types.TYP_AES_Utils;

/**
 *
 * @author Caesar
 */
public class SUB_Protect {

	public SUB_Protect() {
	}

	public String getEncryptedString(String toEncrypt, String theSalt, String theSecKey, byte[] theIV) {
		TYP_AES_Utils MyTyp = CLS_AES_Utils.strEncryptHelper(toEncrypt, theSalt, theSecKey, theIV);
		return MyTyp.getEncryptedStr();
	}

	public String getDecryptedString(String encryptedString, String theSecKey, byte[] theIV) {
		TYP_AES_Utils MyTyp = new TYP_AES_Utils();
		MyTyp.setSecKeyStr(theSecKey);
		MyTyp.setIv(theIV);
		MyTyp.setEncryptedStr(encryptedString);
		return CLS_AES_Utils.strDecrypthelper(MyTyp);
	}

}
