/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.security.types;

/**
 *
 * @author joe1962
 */
public class TYP_RetDLG_password_hashed {
	private byte[] passwordhash;
	private byte[] passwordsalt;
	private boolean butOK;

	/**
	 * @return the passwordhash
	 */
	public byte[] getPasswordhash() {
		return passwordhash;
	}

	/**
	 * @param passwordhash the passwordhash to set
	 */
	public void setPasswordhash(byte[] passwordhash) {
		this.passwordhash = passwordhash;
	}

	/**
	 * @return the passwordsalt
	 */
	public byte[] getPasswordsalt() {
		return passwordsalt;
	}

	/**
	 * @param passwordsalt the passwordsalt to set
	 */
	public void setPasswordsalt(byte[] passwordsalt) {
		this.passwordsalt = passwordsalt;
	}

	/**
	 * @return the butOK
	 */
	public boolean isButOK() {
		return butOK;
	}

	/**
	 * @param butOK the butOK to set
	 */
	public void setButOK(boolean butOK) {
		this.butOK = butOK;
	}

}
