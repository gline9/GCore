package gcore.units;

/**
 * class for storing amount of data and easily changing between types, this
 * class has an addition accuracy limitation because of the double being used,
 * the limit is 1 byte addition in a petabyte of room. Any more deviation
 * between those values will result in nothing being added, but that accuracy
 * can be handled. This limit shouldn't be a factor for most practical purposes
 * of the class as such an addition would take forever to accumulate anyways.
 * 
 * @author Gavin
 *
 */
public class DataUnit extends Unit {

	public static final double BITS = 1;
	public static final double BYTES = BITS / 8;
	public static final double KILOBYTES = BYTES / 1024;
	public static final double MEGABYTES = KILOBYTES / 1024;
	public static final double GIGABYTES = MEGABYTES / 1024;
	public static final double TERABYTES = GIGABYTES / 1024;
	public static final double PETABYTES = TERABYTES / 1024;
	public static final double EXABYTES = PETABYTES / 1024;
	public static final double ZETTABYTES = EXABYTES / 1024;
	public static final double YOTTABYTES = ZETTABYTES / 1024;

	public DataUnit(double value, double unit) {
		super(value, unit);
	}

	public DataUnit(DataUnit unit) {
		super(unit);
	}

	public static DataUnit valueOf(double value, double unit) {
		return new DataUnit(value, unit);
	}

	public DataUnit add(DataUnit data) {
		return new DataUnit(data.convertTo(BITS) + convertTo(BITS), BITS);
	}

}
