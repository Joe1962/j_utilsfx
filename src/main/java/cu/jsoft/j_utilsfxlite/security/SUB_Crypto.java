/*
 * Copyright Joe1962
 */
package cu.jsoft.j_utilsfxlite.security;

import static cu.jsoft.j_utilsfxlite.subs.SUB_UtilsNotifications.echoln;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author joe1962
 */
public class SUB_Crypto {

	@Deprecated
	public static String MD5Encrypt(String MyString) throws NoSuchAlgorithmException {
		// THIS IS DEPRECATED, USE getEncryptedPassword...

		MessageDigest md;

		md = MessageDigest.getInstance("MD5");
		byte[] buffer = MyString.getBytes();
		//byte[] buffer = MyString.getBytes(StandardCharsets.UTF_8);
		md.update(buffer);
		byte[] md5 = md.digest();
		BigInteger bi = new BigInteger(1, md5);
		String output = bi.toString(16);
		//System.out.println(output);
		return output;
	}

	@Deprecated
	public static boolean PasswordValidate(String MyEncryption, String MyEncodedPasswordToValidate, char[] MyPassword, boolean isDebug) {
		// THIS IS DEPRECATED, USE getEncryptedPassword...

		switch (MyEncryption) {
			case "MD5":
				String MyMD5 = new String();

				StringBuilder MyPass = new StringBuilder();
				MyPass.append(MyPassword);

				try {
					// Calc md5...
					MyMD5 = MD5Encrypt(MyPass.toString());
				} catch (NoSuchAlgorithmException ex) {
					echoln("NoSuchAlgorithmException when calling MD5Encrypt...", isDebug, false);
					//GLOBAL.logLogger.log(Level.SEVERE, null, ex);
				}

				return MyMD5 == null ? MyEncodedPasswordToValidate == null : MyMD5.equals(MyEncodedPasswordToValidate);
			default:
				return false;
		}
	}

	public static Boolean authenticate(String MyEncryption, String attemptedPassword, byte[] encryptedPassword, byte[] salt)
		throws NoSuchAlgorithmException, InvalidKeySpecException {
		switch (MyEncryption) {
			case "MD5":
				CLS_MD5 MyMD5 = new CLS_MD5();
				return MyMD5.authenticate(attemptedPassword, encryptedPassword);
			case "PBKDF2":
				CLS_PBKDF2 MyPBKDF2 = new CLS_PBKDF2();
				return MyPBKDF2.authenticate(attemptedPassword, encryptedPassword, salt);
			default:
				return null;
		}
	}

	public static Boolean authenticate(String MyEncryption, char[] attemptedPassword, byte[] encryptedPassword, byte[] salt)
		throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		switch (MyEncryption) {
			case "MD5":
				CLS_MD5 MyMD5 = new CLS_MD5();
				return MyMD5.authenticate(attemptedPassword, encryptedPassword);
			case "PBKDF2":
				CLS_PBKDF2 MyPBKDF2 = new CLS_PBKDF2();
				return MyPBKDF2.authenticate(attemptedPassword, encryptedPassword, salt);
			default:
				return null;
		}
	}

	public static byte[] getEncryptedPassword(String MyEncryption, String password, byte[] salt)
		throws NoSuchAlgorithmException, InvalidKeySpecException {
		switch (MyEncryption) {
			case "MD5":
				CLS_MD5 MyMD5 = new CLS_MD5();
				return MyMD5.getEncryptedPassword(password);
			case "PBKDF2":
				CLS_PBKDF2 MyPBKDF2 = new CLS_PBKDF2();
				return MyPBKDF2.getEncryptedPassword(password, salt);
			default:
				return null;
		}
	}

	public static byte[] getEncryptedPassword(String MyEncryption, char[] password, byte[] salt)
		throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		switch (MyEncryption) {
			case "MD5":
				CLS_MD5 MyMD5 = new CLS_MD5();
				return MyMD5.getEncryptedPassword(password);
			case "PBKDF2":
				CLS_PBKDF2 MyPBKDF2 = new CLS_PBKDF2();
				return MyPBKDF2.getEncryptedPassword(password, salt);
			default:
				return null;
		}
	}

	public static byte[] generateSalt(String MyEncryption) throws NoSuchAlgorithmException {
		switch (MyEncryption) {
			case "MD5":
				CLS_MD5 MyMD5 = new CLS_MD5();
				return null;			// MD5 does not use salt...
			case "PBKDF2":
				CLS_PBKDF2 MyPBKDF2 = new CLS_PBKDF2();
				return MyPBKDF2.generateSalt();
			default:
				return null;
		}
	}

	public static String convertByteArrayToString(byte[] MyByteArray) {
		BigInteger bi = new BigInteger(1, MyByteArray);
		return bi.toString(16);
	}

	public static byte[] convertStringToByteArray(String MyString) throws UnsupportedEncodingException {
		//return MyString.getBytes(StandardCharsets.UTF_8);
		return MyString.getBytes(StandardCharsets.US_ASCII);
	}

	public static byte[] convertCharArrayToByteArray(char[] MyCharArray) throws UnsupportedEncodingException {
		if (MyCharArray != null) {
//			return new String(MyCharArray).getBytes(StandardCharsets.UTF_8);
			return new String(MyCharArray).getBytes(StandardCharsets.US_ASCII);
		} else {
			return null;
		}
	}

	public static byte[] convertstringToBytesUTFCustom(String str) {
		char[] buffer = str.toCharArray();
		byte[] b = new byte[buffer.length << 1];
		for (int i = 0; i < buffer.length; i++) {
			int bpos = i << 1;
			b[bpos] = (byte) ((buffer[i] & 0xFF00) >> 8);
			b[bpos + 1] = (byte) (buffer[i] & 0x00FF);
		}
		return b;
	}

}
