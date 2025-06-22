/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.jsoft.j_utilsfxlite.security;

import static cu.jsoft.j_utilsfxlite.security.SUB_Crypto.generateSalt;
import static cu.jsoft.j_utilsfxlite.security.SUB_Crypto.getEncryptedPassword;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author joe1962
 */
public class SUB_PasswordHandler {

	public static byte[] genEntitySalt() throws NoSuchAlgorithmException {
		return generateSalt("PBKDF2");
	}

	public static byte[] genEntityPass(char[] MyPass) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		return getEncryptedPassword("PBKDF2", MyPass, genEntitySalt());
	}

	public static byte[] genEntityPass(char[] MyPass, byte[] MySalt) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		return getEncryptedPassword("PBKDF2", MyPass, MySalt);
	}

}
