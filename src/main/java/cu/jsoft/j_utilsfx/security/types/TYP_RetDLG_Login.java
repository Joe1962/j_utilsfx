/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.security.types;

/**
 *
 * @author Joe1962
 */
public class TYP_RetDLG_Login {
	private boolean matched = false;
	private boolean cancelled = false;
	private boolean changeDB = false;

	public boolean isMatched() {
		return matched;
	}

	public void setMatched(boolean matched) {
		this.matched = matched;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public boolean isChangeDB() {
		return changeDB;
	}

	public void setChangeDB(boolean changeDB) {
		this.changeDB = changeDB;
	}

	public TYP_RetDLG_Login() {
	}

	public TYP_RetDLG_Login(boolean matched, boolean cancelled, boolean changeDB) {
		this.matched = matched;
		this.cancelled = cancelled;
		this.changeDB = changeDB;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 59 * hash + (this.matched ? 1 : 0);
		hash = 59 * hash + (this.cancelled ? 1 : 0);
		hash = 59 * hash + (this.changeDB ? 1 : 0);
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
		final TYP_RetDLG_Login other = (TYP_RetDLG_Login) obj;
		if (this.matched != other.matched) {
			return false;
		}
		if (this.cancelled != other.cancelled) {
			return false;
		}
		return this.changeDB == other.changeDB;
	}

}
