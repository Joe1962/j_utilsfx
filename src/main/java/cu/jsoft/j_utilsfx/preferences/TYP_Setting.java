/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.preferences;

/**
 *
 * @author Joe1962
 */
public class TYP_Setting {
	private String Heading;
	private String Key;
	private Object Value;
	private Object DefValue;

	public TYP_Setting() {
	}

	public TYP_Setting(String Heading, String Key, Object Value, Object DefValue) {
		this.Heading = Heading;
		this.Key = Key;
		this.Value = Value;
		this.DefValue = DefValue;
	}

	/**
	 * @return the Heading
	 */
	public String getHeading() {
		return Heading;
	}

	/**
	 * @param Heading the Heading to set
	 */
	public void setHeading(String Heading) {
		this.Heading = Heading;
	}

	/**
	 * @return the Key
	 */
	public String getKey() {
		return Key;
	}

	/**
	 * @param Key the Key to set
	 */
	public void setKey(String Key) {
		this.Key = Key;
	}

	/**
	 * @return the Value
	 */
	public Object getValue() {
		return Value;
	}

	/**
	 * @param Value the Value to set
	 */
	public void setValue(Object Value) {
		this.Value = Value;
	}

	/**
	 * @return the DefValue
	 */
	public Object getDefValue() {
		return DefValue;
	}

	/**
	 * @param DefValue the DefValue to set
	 */
	public void setDefValue(Object DefValue) {
		this.DefValue = DefValue;
	}
}
