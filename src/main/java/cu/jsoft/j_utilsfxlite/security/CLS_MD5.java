/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfxlite.security;

import static cu.jsoft.j_utilsfxlite.security.SUB_Crypto.convertCharArrayToByteArray;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 *
 * @author joe1962
 */
@Deprecated
public class CLS_MD5 {

	public boolean authenticate(String attemptedPassword, byte[] encryptedPassword) throws NoSuchAlgorithmException {
		// Calc md5...
		byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword);

		// Authentication succeeds if encrypted password that the user entered
		// is equal to the stored hash
		return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
	}

	public boolean authenticate(char[] attemptedPassword, byte[] encryptedPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// Calc md5...
		byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword);

		// Authentication succeeds if encrypted password that the user entered
		// is equal to the stored hash
		return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
	}

	public byte[] getEncryptedPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		//byte[] buffer = password.getBytes();
//		byte[] buffer = password.getBytes(StandardCharsets.UTF_8);
		byte[] buffer = password.getBytes(StandardCharsets.US_ASCII);
		md.update(buffer);
		return md.digest();
	}

	public byte[] getEncryptedPassword(char[] password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] buffer = convertCharArrayToByteArray(password);
		md.update(buffer);
		return md.digest();
	}

}
