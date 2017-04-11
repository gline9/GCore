package gcore.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import gcore.tuples.Pair;
import gcore.util.StreamUtils;

public final class ArrayUtils {

	private ArrayUtils() {
	}

	/**
	 * Concatenates two arrays Runtime: O(n)
	 * 
	 * @param first
	 *            array that will be in the front
	 * @param second
	 *            array that will be in the back
	 * @return returns a single array that contains all of the elements of first
	 *         and then all of the elements of second.
	 */
	public static <T> T[] concat(T[] first, T[] second) {
		if (second.length == 0)
			return first;
		if (first.length == 0)
			return second;
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

	/**
	 * takes all of the elements of the list and copys them to the returned
	 * array only if their index in pred is true
	 * 
	 * @param pred
	 *            array of booleans for if the element should be copied
	 * @param list
	 *            array of elements to maybe copy
	 * @return returns a list of the elements whose index in pred was true.
	 */
	public static <T> List<T> copyIf(boolean[] pred, T[] list) {
		// check if pred and list are the same size otherwise throw illegal
		// argument exception
		if (pred.length != list.length) {
			throw new IllegalArgumentException("length of pred and list must be the same but given was " + pred.length
					+ ", " + list.length + " respectively");
		}

		List<T> results = StreamUtils

				// zip together the two lists into a stream of pairs
				.zip(Arrays.asList(WrapperUtils.toWrapper(pred)).stream(), Arrays.asList(list).stream(),
						(Boolean b, T i) -> new Pair<>(b, i))

				// filter out the ones that are false
				.filter(p -> p.getFirst())

				// map the pairs to the value
				.map(p -> p.getSecond())

				// collect the values into a list
				.collect(Collectors.toList());

		// return the list.
		return results;
	}

	/**
	 * counts how many times the given element occurs in the given array
	 * 
	 * @param list
	 *            the array to search through
	 * @param element
	 *            the element to search for
	 * @return returns the number of times the element is in the list
	 */
	public static <T> int countOccurences(T[] list, T element) {
		// initialize the count to 0
		int count = 0;

		// loop through the array and increment count every time an element
		// is found
		for (int x = 0; x < list.length; x++) {
			if (list[x].equals(element))
				count++;
		}

		// returns the count
		return count;
	}

	/**
	 * helper function that takes a rugged array and copies it into a new array
	 * of the corresponding xLength and yLength where the maximum entry of the
	 * new array T will be T[xlength-1][ylength-1].
	 * 
	 * @param original
	 *            matrix to copy
	 * @param xlength
	 *            x length of the new matrix
	 * @param ylength
	 *            y length of the new matrix
	 * @return
	 */
	public static <T> T[][] copy2DArray(T[][] original, int xlength, int ylength) {

		// copy the given array into an array that is square and has the correct
		// number of terms.
		T[][] copy = Arrays.copyOf(original, xlength);
		for (int i = 0; i < copy.length; i++) {
			// check for null rows
			if (copy[i] == null) {
				copy[i] = Arrays.copyOf(copy[0], ylength);
				Arrays.fill(copy[i], null);
			}

			// copy each individual row.
			copy[i] = Arrays.copyOf(copy[i], ylength);
		}

		return copy;
	}
}
