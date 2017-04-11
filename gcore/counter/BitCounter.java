package gcore.counter;

import gcore.units.DataUnit;

/**
 * extends the basic counters functionality but adds the ability to use the data
 * as bits
 * 
 * @author Gavin
 *
 */
public class BitCounter extends BasicCounter {

	/**
	 * returns how many bits have been counted
	 * 
	 * @return number of bits
	 * @since Jan 28, 2017
	 */
	public long getBits() {
		return getLongCount();
	}

	/**
	 * gets the number of bits in the DataUnit type for easy conversion
	 * 
	 * @return DataUnit variable containing the number of bits
	 * @since Jan 28, 2017
	 */
	public DataUnit getData() {
		return DataUnit.valueOf(getLongCount(), DataUnit.BITS);
	}

	/**
	 * increments the number of bytes in the counter
	 * @param bytes number of bytes to increment by
	 * @since Jan 28, 2017
	 */
	public void incrementBytesBy(long bytes) {
		incrementBy(8 * bytes);
	}

	/**
	 * increments how much data is in the counter by a number of units
	 * @param value number of units
	 * @param unit unit to use, {@link DataUnit}.
	 * @since Jan 28, 2017
	 */
	public void incrementDataBy(double value, double unit) {
		incrementDataBy(DataUnit.valueOf(value, unit));
	}

	/**
	 * increments the amount of data by a pre given data unit
	 * @param unit data unit that contains how much data to increment by
	 * @since Jan 28, 2017
	 */
	public void incrementDataBy(DataUnit unit) {
		incrementBy((long) unit.convertTo(DataUnit.BITS));
	}

	public boolean equals(Object obj) {
		if (obj != null && obj.getClass().equals(BitCounter.class)) {
			BitCounter counter = (BitCounter) obj;
			return (counter.getLongCount() == getLongCount());
		}
		return false;
	}

}
