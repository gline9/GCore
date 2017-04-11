package gcore.units;

/**
 * super class for all units so the only necessary implementation is adding
 * conversion ratios for the user and a method of creating new instances of the
 * unit.
 * 
 * @author Gavin
 *
 */
public abstract class Unit {

	private final double value;

	protected Unit(double value, double unit) {
		this.value = value / unit;
	}

	protected Unit(Unit u) {
		value = u.value;
	}

	public final double convertTo(double unit) {
		return value * unit;
	}

}
