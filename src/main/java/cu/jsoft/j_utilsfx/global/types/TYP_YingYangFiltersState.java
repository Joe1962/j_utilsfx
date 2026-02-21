/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.global.types;

/**
 *
 * @author joe1962
 */
public class TYP_YingYangFiltersState {
	private boolean yingFilterState = true;
	private boolean yangFilterState = true;

	public TYP_YingYangFiltersState() {
	}

	public TYP_YingYangFiltersState(boolean ying, boolean yang) {
		this.yingFilterState = ying;
		this.yangFilterState = yang;
	}

	public boolean isYingFilterState() {
		return yingFilterState;
	}

	public void setYingFilterState(boolean yingFilterState) {
		this.yingFilterState = yingFilterState;
	}

	public boolean isYangFilterState() {
		return yangFilterState;
	}

	public void setYangFilterState(boolean yangFilterState) {
		this.yangFilterState = yangFilterState;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 11 * hash + (this.yingFilterState ? 1 : 0);
		hash = 11 * hash + (this.yangFilterState ? 1 : 0);
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
		final TYP_YingYangFiltersState other = (TYP_YingYangFiltersState) obj;
		if (this.yingFilterState != other.yingFilterState) {
			return false;
		}
		return this.yangFilterState == other.yangFilterState;
	}

}
