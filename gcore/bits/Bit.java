package gcore.bits;

/**
 * Class for having a bit variable instead of just a boolean.
 * 
 * @author Gavin
 *
 */
public class Bit {
	private final boolean value;

	/**
	 * boolean value constructor for the bit
	 * 
	 * @param isOne
	 *            whether the bit is one or not
	 */
	public Bit(boolean isOne) {
		value = isOne;
	}

	/**
	 * integer constructor for the bit
	 * 
	 * @param num
	 *            if num == 0 will set the value to zero otherwise the value
	 *            will be 1
	 */
	public Bit(int num) {
		if (num == 0) {
			value = false;
		} else {
			value = true;
		}
	}

	/**
	 * used to get the numerical value of the bit
	 * 
	 * @return a 0 or 1 value for the bit
	 * @since Jan 28, 2017
	 */
	public int getValue() {
		return value ? 1 : 0;
	}

	/**
	 * converts the bit value to a string, equivalent to calling new
	 * Integer(Bit.getValue()).toString()
	 */
	public String toString() {
		return value ? "1" : "0";
	}

}
