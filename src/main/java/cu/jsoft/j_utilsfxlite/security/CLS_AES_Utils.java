/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfxlite.security;

import cu.jsoft.j_utilsfxlite.security.types.TYP_AES_Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author joe1962
 */
public class CLS_AES_Utils {

	public static TYP_AES_Utils strEncryptHelper(String MyPlainString, String MySalt) {
		TYP_AES_Utils MyTyp = new TYP_AES_Utils();
		
		// Get AES secret key:
		try {
			MyTyp.setSecKey(getKeyFromPassword(MyPlainString, MySalt));
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidKeySpecException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		}

		MyTyp.setSecKeyStr(SecretKeyToString(MyTyp.getSecKey()));

		// Get initialization vector (iv):
		MyTyp.setIv(generateIv());
		MyTyp.setIvSpec(generateIvParameterSpec(MyTyp.getIv()));

		// Get encrypted password:
		try {
			MyTyp.setEncryptedStr(encrypt("AES/CBC/PKCS5Padding", MyPlainString, MyTyp.getSecKey(), MyTyp.getIvSpec()));
		} catch (NoSuchPaddingException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidAlgorithmParameterException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidKeyException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (BadPaddingException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalBlockSizeException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		}

		return MyTyp;
	}

	public static TYP_AES_Utils strEncryptHelper(String MyPlainString, String MySalt, String SecKeyStr, byte[] MyIV) {
		TYP_AES_Utils MyTyp = new TYP_AES_Utils();
		MyTyp.setSecKey(StringToSecretKey(SecKeyStr));
		MyTyp.setSecKeyStr(SecKeyStr);
		// Get initialization vector parameter spec:
		MyTyp.setIv(MyIV);
		MyTyp.setIvSpec(generateIvParameterSpec(MyIV));

		// Get encrypted password:
		try {
			MyTyp.setEncryptedStr(encrypt("AES/CBC/PKCS5Padding", MyPlainString, MyTyp.getSecKey(), MyTyp.getIvSpec()));
		} catch (NoSuchPaddingException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidAlgorithmParameterException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidKeyException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (BadPaddingException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalBlockSizeException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		}

		return MyTyp;
	}

	public static String strDecrypthelper(TYP_AES_Utils MyTyp) {
		String retString = "";

		MyTyp.setIvSpec(generateIvParameterSpec(MyTyp.getIv()));

		try {
			retString = decrypt("AES/CBC/PKCS5Padding", MyTyp.getEncryptedStr(), StringToSecretKey(MyTyp.getSecKeyStr()), MyTyp.getIvSpec());
		} catch (NoSuchPaddingException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidAlgorithmParameterException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidKeyException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (BadPaddingException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalBlockSizeException ex) {
			Logger.getLogger(CLS_AES_Utils.class.getName()).log(Level.SEVERE, null, ex);
		}

		return retString;
	}

	private static String encrypt(String algorithm, String input, SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] cipherText = cipher.doFinal(input.getBytes());
		return Base64.getEncoder().encodeToString(cipherText);
	}

	private static String decrypt(String algorithm, String cipherText, SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(plainText);
	}

	private static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(n);
		SecretKey key = keyGenerator.generateKey();
		return key;
	}

	private static SecretKey getKeyFromPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		return secret;
	}

	private static byte[] generateIv() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		return iv;
	}

	private static IvParameterSpec generateIvParameterSpec(byte[] iv) {
		return new IvParameterSpec(iv);
	}

	public static void encryptFile(String algorithm, SecretKey key, IvParameterSpec iv, File inputFile, File outputFile) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		FileInputStream inputStream = new FileInputStream(inputFile);
		FileOutputStream outputStream = new FileOutputStream(outputFile);
		byte[] buffer = new byte[64];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			byte[] output = cipher.update(buffer, 0, bytesRead);
			if (output != null) {
				outputStream.write(output);
			}
		}
		byte[] outputBytes = cipher.doFinal();
		if (outputBytes != null) {
			outputStream.write(outputBytes);
		}
		inputStream.close();
		outputStream.close();
	}

	public static void decryptFile(String algorithm, SecretKey key, IvParameterSpec iv, File encryptedFile, File decryptedFile) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		FileInputStream inputStream = new FileInputStream(encryptedFile);
		FileOutputStream outputStream = new FileOutputStream(decryptedFile);
		byte[] buffer = new byte[64];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			byte[] output = cipher.update(buffer, 0, bytesRead);
			if (output != null) {
				outputStream.write(output);
			}
		}
		byte[] output = cipher.doFinal();
		if (output != null) {
			outputStream.write(output);
		}
		inputStream.close();
		outputStream.close();
	}

	private static SealedObject encryptObject(String algorithm, Serializable object, SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IOException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		SealedObject sealedObject = new SealedObject(object, cipher);
		return sealedObject;
	}

	private static Serializable decryptObject(String algorithm, SealedObject sealedObject, SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, ClassNotFoundException, BadPaddingException, IllegalBlockSizeException, IOException {
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		Serializable unsealObject = (Serializable) sealedObject.getObject(cipher);
		return unsealObject;
	}

	private static String encryptPasswordBased(String plainText, SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
	}

	private static String decryptPasswordBased(String cipherText, SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
	}

	private static String SecretKeyToString(SecretKey mAesKey) {
		//SecretKeySpec to String 
		byte[] byteaes = mAesKey.getEncoded();
		return Base64.getEncoder().encodeToString(byteaes);
	}

	private static SecretKey StringToSecretKey(String mAesKey_string) {
		//String to SecretKeySpec
		byte[] aesByte = Base64.getDecoder().decode(mAesKey_string);
		return new SecretKeySpec(aesByte, "AES");
	}

}
