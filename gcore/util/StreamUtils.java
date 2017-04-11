package gcore.util;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * stream util class for java 8's stream API.
 * 
 * @author Gavin
 *
 */
public class StreamUtils {

	// non-instantiable
	private StreamUtils() {}

	/**
	 * zips two streams together
	 * 
	 * @param streamA
	 * @param streamB
	 * @param zipper
	 * @return streams zipped using the collectors to a single stream.
	 */
	public static <A, B, C> Stream<C> zip(Stream<A> streamA, Stream<B> streamB, BiFunction<A, B, C> zipper) {
		// turn the streams into iterators
		Iterator<A> iteratorA = streamA.iterator();
		Iterator<B> iteratorB = streamB.iterator();
		
		// make a new iterator that will apply the zipper function on the two streams as it is passed in.
		final Iterator<C> iteratorC = new Iterator<C>() {
			@Override
			public boolean hasNext() {
				return iteratorA.hasNext() && iteratorB.hasNext();
			}

			@Override
			public C next() {
				return zipper.apply(iteratorA.next(), iteratorB.next());
			}
		};
		
		// if either of the two streams are parallel the resulting stream should also be parallel
		boolean parallel = streamA.isParallel() || streamB.isParallel();
		
		// return the conversion of the iterator to a finite stream
		return iteratorToFiniteStream(iteratorC, parallel);
	}

	/**
	 * converts an iterator to a stream
	 * 
	 * @param iterator
	 *            iterator to convert
	 * @param parallel
	 *            whether the stream should be made parallel or not
	 * @return a stream from the iterator.
	 */
	public static <T> Stream<T> iteratorToFiniteStream(Iterator<T> iterator, boolean parallel) {
		Iterable<T> iterable = () -> iterator;
		return StreamSupport.stream(iterable.spliterator(), parallel);
	}

}
