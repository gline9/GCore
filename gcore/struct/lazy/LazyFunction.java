package gcore.struct.lazy;

import java.util.function.Function;

abstract class LazyFunction<E> {
	public abstract Function<Integer, LazyObject<E>> construct();
}
