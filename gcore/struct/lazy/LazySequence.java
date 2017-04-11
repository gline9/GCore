package gcore.struct.lazy;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.function.Function;

public class LazySequence<E> extends AbstractCollection<E> {

	private final LazyFunction<E> lazyFunction;

	protected LazySequence(LazyFunction<E> lazyList) {
		lazyFunction = lazyList;
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	@Override
	public int size() {
		int size = 0;
		for (@SuppressWarnings("unused")
		E e : this) {
			size++;
		}
		return size;
	}

	public <F> LazySequence<F> map(Function<E, F> map) {
		Function<LazyObject<E>, LazyObject<F>> temp = a -> {
			return a.map(map);
		};
		return new LazySequence<F>(new LazyFunction<F>() {
			@Override
			public Function<Integer, LazyObject<F>> construct() {
				return temp.compose(lazyFunction.construct());
			}
		});
	}

	public LazySequence<E> filter(Function<E, Boolean> filter) {
		Function<LazyObject<E>, LazyObject<E>> temp = a -> {
			return a.filter(filter);
		};
		return new LazySequence<E>(new LazyFunction<E>() {
			@Override
			public Function<Integer, LazyObject<E>> construct() {
				return temp.compose(lazyFunction.construct());
			}
		});
	}

	public LazySequence<E> take(int amount) {
		return new LazySequence<>(new LazyFunction<E>() {

			@Override
			public Function<Integer, LazyObject<E>> construct() {
				Iterator<E> iterator = iterator();
				return a -> {
					if (a < amount && iterator.hasNext()) {
						return new LazyObject<>(iterator.next());
					} else {
						return new LazyObject<>(null);
					}
				};
			}

		});
	}

	private class Itr implements Iterator<E> {

		private Function<Integer, LazyObject<E>> function;

		public Itr() {
			function = lazyFunction.construct();
			next = function.apply(0);
		}

		private int ptr = 1;

		private LazyObject<E> next;

		private boolean nextEvaluated = true;

		@Override
		public boolean hasNext() {

			// check if the next has been evaluated, if it has and it is null
			// return false
			if (nextEvaluated) {
				if (next.isTerminal())
					return false;

				return true;
			}

			do {
				// advance the pointer
				advancePointer();

				// get the next element
				evaluateNext();

				// make sure the element passes the filter
			} while ((!next.isTerminal()) && next.wasFiltered());

			// return true if it is not terminal
			return !next.isTerminal();
		}

		@Override
		public E next() {

			do {
				// get the next element
				evaluateNext();

				// advance the pointer
				advancePointer();

				// make sure the element passes the filter
			} while ((!next.isTerminal()) && next.wasFiltered());

			// check if the next is terminal
			if (next.isTerminal())
				return null;

			// if not just return the data
			return next.getData();
		}

		private void evaluateNext() {
			// make sure the last hasn't been evaluated yet
			if (!nextEvaluated) {
				next = function.apply(ptr++);
				nextEvaluated = true;
			}
		}

		private void advancePointer() {
			// make sure the next isn't terminal
			if (next.isTerminal()) {
				nextEvaluated = true;
			} else {
				nextEvaluated = false;
			}
		}

	}

}
