/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.security;

import static cu.jsoft.j_utilsfx.security.SUB_Crypto.generateSalt;
import static cu.jsoft.j_utilsfx.security.SUB_Crypto.getEncryptedPassword;
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
