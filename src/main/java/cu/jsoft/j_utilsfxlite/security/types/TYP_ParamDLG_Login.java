/*
 * Copyright Joe1962
 */
package cu.jsoft.j_utilsfxlite.security.types;

import java.util.Objects;

/**
 *
 * @author joe1962
 */
public class TYP_ParamDLG_Login {
	private String name;
	private boolean admin;
	private String password;

	public TYP_ParamDLG_Login() {
	}

	public TYP_ParamDLG_Login(String name, boolean admin, String password) {
		this.name = name;
		this.admin = admin;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 89 * hash + Objects.hashCode(this.name);
		hash = 89 * hash + (this.admin ? 1 : 0);
		hash = 89 * hash + Objects.hashCode(this.password);
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
		final TYP_ParamDLG_Login other = (TYP_ParamDLG_Login) obj;
		if (this.admin != other.admin) {
			return false;
		}
		if (!Objects.equals(this.name, other.name)) {
			return false;
		}
		return Objects.equals(this.password, other.password);
	}

}
