package gcore.counter;

/**
 * counter class that extends the nomal counter to give it actual functionality
 * 
 * @author Gavin
 *
 */
public class BasicCounter extends Counter {

	// stores the count for the coutner
	private volatile long count = 0;

	/**
	 * default constructor gives a default count of 0
	 */
	public BasicCounter() {}

	@Override
	public void increment() {
		count++;
	}
	
	/**
	 * increments the count by a set amount
	 * @param x how much to increment by
	 * @since Jan 28, 2017
	 */
	public void incrementBy(long x) {
		count += x;
	}
	@Override
	public int getCount() {
		return (int) count;
	}

	/**
	 * returns the current count of the counter in long form
	 * @return 
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
		if (obj != null && obj.getClass().equals(BasicCounter.class)) {
			BasicCounter counter = (BasicCounter) obj;
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
