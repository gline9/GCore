package gcore.util;

/**
 * Utility class to convert primitive arrays to their cooresponding wrapper
 * class arrays for use in the ArrayUtils methods
 * 
 * @author Gavin
 *
 */
public final class WrapperUtils {
	// uninstantiable class
	private WrapperUtils() {
	}

	/**
	 * converts an int array to an Integer array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns an Integer array with same size and all values
	 */
	public static Integer[] toWrapper(int[] array) {
		// initialize resulting array
		Integer[] results = new Integer[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = new Integer(array[i]);
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a double array to a Double array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a Double array with same size and all values
	 */
	public static Double[] toWrapper(double[] array) {
		// initialize resulting array
		Double[] results = new Double[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = new Double(array[i]);
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a long array to a Long array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a Long array with same size and all values
	 */
	public static Long[] toWrapper(long[] array) {
		// initialize resulting array
		Long[] results = new Long[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = new Long(array[i]);
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a char array to a Character array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a Character array with same size and all values
	 */
	public static Character[] toWrapper(char[] array) {
		// initialize resulting array
		Character[] results = new Character[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = new Character(array[i]);
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a boolean array to a Boolean array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a Boolean array with same size and all values
	 */
	public static Boolean[] toWrapper(boolean[] array) {
		// initialize resulting array
		Boolean[] results = new Boolean[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = new Boolean(array[i]);
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a byte array to a Byte array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a Byte array with same size and all values
	 */
	public static Byte[] toWrapper(byte[] array) {
		// initialize resulting array
		Byte[] results = new Byte[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = new Byte(array[i]);
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a short array to a Short array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a Short array with same size and all values
	 */
	public static Short[] toWrapper(short[] array) {
		// initialize resulting array
		Short[] results = new Short[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = new Short(array[i]);
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a float array to a Float array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a Float array with same size and all values
	 */
	public static Float[] toWrapper(float[] array) {
		// initialize resulting array
		Float[] results = new Float[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = new Float(array[i]);
		}

		// returns the results array
		return results;
	}

	/**
	 * converts an Integer array to an int array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns an int array with same size and all values
	 */
	public static int[] toPrimitive(Integer[] array) {
		// initialize resulting array
		int[] results = new int[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = array[i];
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a Double array to a double array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a double array with same size and all values
	 */
	public static double[] toPrimitive(Double[] array) {
		// initialize resulting array
		double[] results = new double[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = array[i];
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a Long array to a long array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a long array with same size and all values
	 */
	public static long[] toPrimitive(Long[] array) {
		// initialize resulting array
		long[] results = new long[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = array[i];
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a Character array to a char array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a char array with same size and all values
	 */
	public static char[] toPrimitive(Character[] array) {
		// initialize resulting array
		char[] results = new char[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = array[i];
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a Boolean array to a boolean array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a boolean array with same size and all values
	 */
	public static boolean[] toPrimitive(Boolean[] array) {
		// initialize resulting array
		boolean[] results = new boolean[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = array[i];
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a Byte array to a byte array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a byte array with same size and all values
	 */
	public static byte[] toPrimitive(Byte[] array) {
		// initialize resulting array
		byte[] results = new byte[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = array[i];
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a Short array to a short array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a short array with same size and all values
	 */
	public static short[] toPrimitive(Short[] array) {
		// initialize resulting array
		short[] results = new short[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = array[i];
		}

		// returns the results array
		return results;
	}

	/**
	 * converts a Float array to a float array
	 * 
	 * @param array
	 *            array to convert
	 * @return returns a float array with same size and all values
	 */
	public static float[] toPrimitive(Float[] array) {
		// initialize resulting array
		float[] results = new float[array.length];

		// loop through given array and add each element
		for (int i = 0; i < array.length; i++) {
			results[i] = array[i];
		}

		// returns the results array
		return results;
	}
}
