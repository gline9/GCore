package gcore.bits;

import java.util.AbstractSequentialList;
import java.util.ListIterator;

/**
 * class representing a sequence of bits in terms of data.
 * 
 * @author Gavin
 *
 */
public class BitSequence extends AbstractSequentialList<Bit> {

	// store the data for the bit sequence as an array of bytes
	private byte[] data;

	// stores which bit in the byte we are pointing at
	private int bitPtr = 0;

	// stores which byte in the array we are pointing at
	private int bytePtr = 0;

	/**
	 * initialize the bit sequence by providing it with the data that it needs
	 * to parse through.
	 * 
	 * @param data
	 *            data for the bit sequence to parse through
	 */
	public BitSequence(byte[] data) {
		this.data = data;
	}

	/**
	 * 
	 * @return the bit that is currently being pointed to in the stream
	 * @since Sep 26, 2016
	 */
	public int ptrLocation() {
		return bytePtr * 8 + bitPtr;
	}

	@Override
	public ListIterator<Bit> listIterator(int ptr) {
		return new Itr(ptr);
	}

	@Override
	public int size() {
		return 8 * data.length;
	}

	private class Itr implements ListIterator<Bit> {

		// stores which bit in the byte we are pointing at
		private int bitPtr = 0;

		// stores which byte in the array we are pointing at
		private int bytePtr = 0;

		public Itr(int location) {
			// set the bit and byte pointers to the correct location
			bytePtr = location / 8;
			bitPtr = location % 8;
		}

		@Override
		public boolean hasNext() {
			return nextIndex() < size();
		}

		@Override
		public boolean hasPrevious() {
			return previousIndex() > -1;
		}

		@Override
		public Bit next() {
			if (bitPtr == 8) {
				bytePtr++;
				bitPtr = 0;
			}
			if (bytePtr == data.length)
				return null;

			Bit results = new Bit(data[bytePtr] & (1 << bitPtr++));
			return results;
		}

		@Override
		public Bit previous() {
			if (bitPtr == 0) {
				bytePtr--;
				bitPtr = 8;
			}
			if (bytePtr == data.length)
				return null;

			Bit results = new Bit(data[bytePtr] & (1 << --bitPtr));
			return results;
		}

		@Override
		public void add(Bit e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int nextIndex() {
			return 8 * bytePtr + bitPtr + 1;
		}

		@Override
		public int previousIndex() {
			return 8 * bytePtr + bitPtr - 1;
		}

		/**
		 * unsupported operation
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		/**
		 * unsupported operation
		 */
		@Override
		public void set(Bit e) {
			throw new UnsupportedOperationException();
		}

	}

}
