/*
 * Copyright Joe1962
 */
package cu.jsoft.j_utilsfxlite.preferences;


/**
 *
 * @author Joe1962
 */
public class TYP_WindowGeometry {
	private double WindowLeft;
	private double WindowTop;
	private double WindowWidth;
	private double WindowHeight;
	private boolean WindowMaximized;

	public TYP_WindowGeometry() {
	}

	public TYP_WindowGeometry(double WindowLeft, double WindowTop, double WindowWidth, double WindowHeight, boolean WindowMaximized) {
		this.WindowLeft = WindowLeft;
		this.WindowTop = WindowTop;
		this.WindowWidth = WindowWidth;
		this.WindowHeight = WindowHeight;
		this.WindowMaximized = WindowMaximized;
	}

	public double getWindowLeft() {
		return WindowLeft;
	}

	public void setWindowLeft(double WindowLeft) {
		this.WindowLeft = WindowLeft;
	}

	public double getWindowTop() {
		return WindowTop;
	}

	public void setWindowTop(double WindowTop) {
		this.WindowTop = WindowTop;
	}

	public double getWindowWidth() {
		return WindowWidth;
	}

	public void setWindowWidth(double WindowWidth) {
		this.WindowWidth = WindowWidth;
	}

	public double getWindowHeight() {
		return WindowHeight;
	}

	public void setWindowHeight(double WindowHeight) {
		this.WindowHeight = WindowHeight;
	}

	public boolean isWindowMaximized() {
		return WindowMaximized;
	}

	public void setWindowMaximized(boolean WindowMaximized) {
		this.WindowMaximized = WindowMaximized;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 71 * hash + (int) (Double.doubleToLongBits(this.WindowLeft) ^ (Double.doubleToLongBits(this.WindowLeft) >>> 32));
		hash = 71 * hash + (int) (Double.doubleToLongBits(this.WindowTop) ^ (Double.doubleToLongBits(this.WindowTop) >>> 32));
		hash = 71 * hash + (int) (Double.doubleToLongBits(this.WindowWidth) ^ (Double.doubleToLongBits(this.WindowWidth) >>> 32));
		hash = 71 * hash + (int) (Double.doubleToLongBits(this.WindowHeight) ^ (Double.doubleToLongBits(this.WindowHeight) >>> 32));
		hash = 71 * hash + (this.WindowMaximized ? 1 : 0);
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
		final TYP_WindowGeometry other = (TYP_WindowGeometry) obj;
		if (Double.doubleToLongBits(this.WindowLeft) != Double.doubleToLongBits(other.WindowLeft)) {
			return false;
		}
		if (Double.doubleToLongBits(this.WindowTop) != Double.doubleToLongBits(other.WindowTop)) {
			return false;
		}
		if (Double.doubleToLongBits(this.WindowWidth) != Double.doubleToLongBits(other.WindowWidth)) {
			return false;
		}
		if (Double.doubleToLongBits(this.WindowHeight) != Double.doubleToLongBits(other.WindowHeight)) {
			return false;
		}
		return this.WindowMaximized == other.WindowMaximized;
	}

}
