package gcore.units;

public class DistanceUnit extends Unit {

	// static variables to hold conversion ratios for all of the different kind
	// of time units. This is to reduce code size and number of methods for the
	// future.
	public static final double METERS = 1;
	public static final double DECIMETERS = METERS * 10;
	public static final double CENTIMETERS = DECIMETERS * 10;
	public static final double MILLIMETERS = CENTIMETERS * 10;
	public static final double MICROMETERS = MILLIMETERS * 1000;
	public static final double NANOMETERS = MICROMETERS * 1000;
	public static final double PICOMETERS = NANOMETERS * 1000;
	public static final double FEMTOMETERS = PICOMETERS * 1000;
	public static final double ATTOMETERS = FEMTOMETERS * 1000;
	public static final double ZEPTOMETERS = ATTOMETERS * 1000;
	public static final double YOCTOMETERS = ZEPTOMETERS * 1000;
	public static final double DEKAMETERS = METERS / 10;
	public static final double HECTOMETERS = DEKAMETERS / 10;
	public static final double KILOMETERS = HECTOMETERS / 10;
	public static final double MEGAMETERS = KILOMETERS / 1000;
	public static final double GIGAMETERS = MEGAMETERS / 1000;
	public static final double TERAMETERS = GIGAMETERS / 1000;
	public static final double PETAMETERS = TERAMETERS / 1000;
	public static final double EXAMETERS = PETAMETERS / 1000;
	public static final double ZETTAMETERS = EXAMETERS / 1000;
	public static final double YOTTAMETERS = ZETTAMETERS / 1000;
	public static final double LIGHTYEARS = PETAMETERS / 9.46073;
	public static final double PARSECS = PETAMETERS / 30.8568;
	public static final double FEET = METERS * 3.2808399;
	public static final double INCHES = FEET * 12;
	public static final double YARDS = FEET / 3;
	public static final double MILES = FEET / 5280;
	public static final double NAUTICALMILES = METERS / 1852;

	protected DistanceUnit(double value, double unit) {
		super(value, unit);
	}

	public DistanceUnit(DistanceUnit unit) {
		super(unit);
	}

	public static DistanceUnit valueOf(double value, double unit) {
		return new DistanceUnit(value, unit);
	}

}
