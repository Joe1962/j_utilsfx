/*
 * Copyright Joe1962
 */
package cu.jsoft.j_utilsfxlite.security.types;

import java.util.Objects;

/**
 *
 * @author joe1962
 */
public class TYP_DecryptedActivationKey {
	private String theID;
	private String theDate;

	public TYP_DecryptedActivationKey() {
	}

	public TYP_DecryptedActivationKey(String theID, String theDate) {
		this.theID = theID;
		this.theDate = theDate;
	}

	public String getTheID() {
		return theID;
	}

	public void setTheID(String theID) {
		this.theID = theID;
	}

	public String getTheDate() {
		return theDate;
	}

	public void setTheDate(String theDate) {
		this.theDate = theDate;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 13 * hash + Objects.hashCode(this.theID);
		hash = 13 * hash + Objects.hashCode(this.theDate);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TYP_DecryptedActivationKey other = (TYP_DecryptedActivationKey) obj;
		if (!Objects.equals(this.theID, other.theID)) {
			return false;
		}
		return Objects.equals(this.theDate, other.theDate);
	}

}
