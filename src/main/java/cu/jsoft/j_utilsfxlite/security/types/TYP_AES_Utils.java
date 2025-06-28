/*
 * Copyright Joe1962
 */
package cu.jsoft.j_utilsfxlite.security.types;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author joe1962
 */
public class TYP_AES_Utils {
	private SecretKey secKey;
	private String secKeyStr;
	private IvParameterSpec ivSpec;
	private byte[] iv;
	private String encryptedStr;

	public SecretKey getSecKey() {
		return secKey;
	}

	public void setSecKey(SecretKey secKey) {
		this.secKey = secKey;
	}

	public String getSecKeyStr() {
		return secKeyStr;
	}

	public void setSecKeyStr(String secKeyStr) {
		this.secKeyStr = secKeyStr;
	}

	public IvParameterSpec getIvSpec() {
		return ivSpec;
	}

	public void setIvSpec(IvParameterSpec ivSpec) {
		this.ivSpec = ivSpec;
	}

	public byte[] getIv() {
		return iv;
	}

	public void setIv(byte[] iv) {
		this.iv = iv;
	}

	public String getEncryptedStr() {
		return encryptedStr;
	}

	public void setEncryptedStr(String encryptedStr) {
		this.encryptedStr = encryptedStr;
	}

}
