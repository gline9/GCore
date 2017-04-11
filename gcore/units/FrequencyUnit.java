package gcore.units;

public class FrequencyUnit extends Unit {

	// static variables to hold conversion ratios for all of the different kind
	// of time units. This is to reduce code size and number of methods for the
	// future.
	public static final double PER_YEAR = 1;
	public static final double PER_DAY = PER_YEAR / 365.24219907407D;
	public static final double PER_HOUR = PER_DAY / 24;
	public static final double PER_MINUTE = PER_HOUR / 60;
	public static final double PER_SECOND = PER_MINUTE / 60;
	public static final double PER_MILLISECOND = PER_SECOND / 1000;
	public static final double PER_MICROSECOND = PER_MILLISECOND / 1000;
	public static final double PER_NANOSECOND = PER_MICROSECOND / 1000;
	public static final double PER_DECADE = PER_YEAR * 10;
	public static final double PER_CENTURY = PER_DECADE * 10;
	public static final double PER_MILLENNIUM = PER_CENTURY * 10;

	public FrequencyUnit(double value, double unit) {
		super(value, unit);
	}

	public FrequencyUnit(FrequencyUnit unit) {
		super(unit);
	}

	public static FrequencyUnit valueOf(double value, double unit) {
		return new FrequencyUnit(value, unit);
	}

	public TimeUnit getDelay() {
		return TimeUnit.valueOf(1.0 / convertTo(PER_YEAR), TimeUnit.YEARS);
	}

}
