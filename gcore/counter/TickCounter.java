package gcore.counter;

/**
 * class to keep track of ticks, difference between it and {@link BasicCounter}
 * is the lack of an increment by function.
 * 
 * @author Gavin
 *
 */
public class TickCounter extends Counter {
	private volatile long count;

	public TickCounter() {}

	@Override
	public void increment() {
		count++;
	}

	@Override
	public int getCount() {
		return (int) count;
	}

	/**
	 * used to convert value to long
	 * @return the long value of the counter
	 * @since Jan 28, 2017
	 */
	public long getLongCount() {
		return count;
	}

	@Override
	public void reset() {
		count = 0;
	}

	public boolean equals(Object obj) {
		if (obj != null && obj.getClass().equals(TickCounter.class)) {
			TickCounter counter = (TickCounter) obj;
			return (counter.getLongCount() == getLongCount());
		}
		return false;
	}

	public int hashCode() {
		return getCount();
	}

	public String toString() {
		return String.valueOf(count);
	}

}
