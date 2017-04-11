package gcore.struct.lazy;

import java.util.ArrayList;
import java.util.Arrays;

public class LazyValues<E> {

	private final ArrayList<E> preValues;

	private final E[] seeds;

	private final int size;

	private int ptr = 0;

	@SafeVarargs
	public LazyValues(int size, E... seeds) {
		// make sure there are the correct number of seeds for the sequence
		if (size != seeds.length)
			throw new IllegalArgumentException(
					"Must have the same number of seeds as the distance of look back in a lazy sequence!");

		// set the values for the lazy values
		this.size = size;
		preValues = new ArrayList<E>(size);
		this.seeds = Arrays.copyOf(seeds, seeds.length);
		for (E seed : this.seeds) {
			preValues.add(seed);
		}
	}

	protected void acceptValue(E value) {

		// add the value to the next open spot
		preValues.set(ptr++, value);
		
		// mod over the pointer
		ptr %= size;
	}

	public E getPrevious(int lookBack) {
		if (lookBack > size || lookBack < 1)
			throw new IndexOutOfBoundsException(
					"Invalid back lookup distance: " + lookBack + " Maximum valid distance is " + size);
		
		return preValues.get(Math.floorMod(ptr - lookBack + 1, size));
	}

}
