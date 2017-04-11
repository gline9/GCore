package gcore.struct.lazy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * utility class for lazy lists
 * 
 * @author Gavin
 *
 */
public final class LazySequences {
	private LazySequences() {}

	public static <E> LazySequence<E> construct(Function<Integer, E> lazyList) {
		Function<E, LazyObject<E>> temp = a -> {
			return new LazyObject<>(a);
		};
		return new LazySequence<>(new LazyFunction<E>() {
			Function<Integer, LazyObject<E>> function = temp.compose(lazyList);

			@Override
			public Function<Integer, LazyObject<E>> construct() {
				return function;
			}
		});
	}

	@SafeVarargs
	public static <E> LazySequence<E> constructRecurrent(BiFunction<Integer, LazyValues<E>, E> lazyList, int depth,
			E... seeds) {
		E[] seedCopy = Arrays.copyOf(seeds, seeds.length);
		return new LazySequence<>(new LazyFunction<E>() {

			@Override
			public Function<Integer, LazyObject<E>> construct() {
				LazyValues<E> values = new LazyValues<>(depth, seedCopy);

				Function<Integer, E> temp = a -> {
					if (a < depth)
						return seedCopy[a];
					E value = lazyList.apply(a, values);
					values.acceptValue(value);
					return value;
				};

				Function<E, LazyObject<E>> converter = a -> {
					return new LazyObject<>(a);
				};

				return converter.compose(temp);

			}
		});
	}

	public static <E> LazySequence<E> iterate(E seed, Function<E, E> function) {
		return constructRecurrent((i, v) -> function.apply(v.getPrevious(1)), 1, seed);
	}

	public static LazySequence<Integer> range(int start, int end, int stepSize) {
		return construct(a -> {
			if (a * stepSize < end - start)
				return a * stepSize + start;
			return null;
		});
	}

	public static LazySequence<Integer> range(int start, int end) {
		return range(start, end, 1);
	}

	public static LazySequence<Integer> range(int end) {
		return range(0, end, 1);
	}

	public static LazySequence<Integer> integers() {
		return construct(a -> a);
	}

	public static <E> LazySequence<E> valueOf(List<E> list) {
		final Object[] elements = list.toArray();
		final int size = list.size();
		@SuppressWarnings("unchecked")
		Function<Integer, E> temp = a -> {
			if (a >= size)
				return null;
			else
				return (E) elements[a];
		};
		return LazySequences.construct(temp);
	}

	public static <E> ArrayList<E> realize(int amount, LazySequence<E> sequence) {
		return realize(amount, sequence, true);
	}

	public static <E> ArrayList<E> realize(LazySequence<E> sequence) {
		return realize(Integer.MAX_VALUE, sequence, false);
	}

	private static <E> ArrayList<E> realize(int amount, LazySequence<E> sequence, boolean sizeArray) {
		ArrayList<E> results;

		// check if the array should be sized
		if (sizeArray) {
			results = new ArrayList<>(amount);
		} else {
			results = new ArrayList<>();
		}

		int index = 0;

		// loop through and take the first 'amount' elements of the lazy
		// sequence.
		for (E e : sequence) {
			if (index++ == amount)
				break;
			results.add(e);
		}

		// return the results
		return results;
	}

	public static <E> E reduce(BiFunction<E, E, E> reducer, LazySequence<E> sequence) {
		Iterator<E> iterator = sequence.iterator();

		// check if the sequence is empty, if so return null
		if (!iterator.hasNext())
			return null;

		// grab the first element of the lazy sequence
		E accumulator = iterator.next();

		// loop through and change onto the accumulator
		while (iterator.hasNext()) {
			accumulator = reducer.apply(accumulator, iterator.next());
		}

		// return the accumulator
		return accumulator;
	}
}
