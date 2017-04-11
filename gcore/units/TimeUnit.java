package gcore.units;

public class TimeUnit extends Unit {

	// static variables to hold conversion ratios for all of the different kind
	// of time units. This is to reduce code size and number of methods for the
	// future.

	public static final double NANOSECONDS = 1;
	public static final double MICROSECONDS = NANOSECONDS / 1000;
	public static final double MILLISECONDS = MICROSECONDS / 1000;
	public static final double SECONDS = MILLISECONDS / 1000;
	public static final double MINUTES = SECONDS / 60;
	public static final double HOURS = MINUTES / 60;
	public static final double DAYS = HOURS / 24;
	public static final double YEARS = DAYS / 365.24219907407D;
	public static final double DECADES = YEARS / 10;
	public static final double CENTURIES = DECADES / 10;
	public static final double MILLENIA = CENTURIES / 10;

	public TimeUnit(double value, double unit) {
		super(value, unit);
	}

	public TimeUnit(TimeUnit time) {
		super(time);
	}

	public static TimeUnit valueOf(double value, double unit) {
		// just return a new time unit with the given value and unit
		return new TimeUnit(value, unit);
	}

	public TimeUnit add(TimeUnit t) {
		return new TimeUnit(t.convertTo(NANOSECONDS) + convertTo(NANOSECONDS), NANOSECONDS);
	}

	public TimeUnit subtract(TimeUnit t) {
		return new TimeUnit(convertTo(NANOSECONDS) - t.convertTo(NANOSECONDS), NANOSECONDS);
	}

	public TimeUnit interval(int numberOfIntervals) {
		return new TimeUnit(convertTo(NANOSECONDS) / numberOfIntervals, NANOSECONDS);
	}
}
