package gcore.streams;

import java.io.IOException;
import java.io.InputStream;

import gcore.counter.BitCounter;
import gcore.units.DataUnit;

/**
 * input stream that keeps track of how much data has gone through using a
 * {@link BitCounter}. How to use it is to put it in-between your input stream
 * and the input stream you will grab data from. For instance socket ->
 * CountingInputStream -> DataInputStream.
 * 
 * @author Gavin
 *
 */
public class CountingInputStream extends InputStream {
	private final InputStream in;
	private final BitCounter count;

	/**
	 * instantiate by giving an input stream for it to count from
	 * 
	 * @param in
	 *            input stream to read from
	 */
	public CountingInputStream(InputStream in) {
		this.in = in;
		count = new BitCounter();
	}

	@Override
	public int read() throws IOException {
		count.incrementBytesBy(1);
		return in.read();
	}

	@Override
	public void close() throws IOException {
		in.close();
	}

	@Override
	public void reset() throws IOException {
		in.reset();
	}

	@Override
	public boolean markSupported() {
		return in.markSupported();
	}

	@Override
	public int available() throws IOException {
		return in.available();
	}

	@Override
	public void mark(int i) {
		in.mark(i);
	}

	/**
	 * resets how many bits have been read
	 * 
	 * @since Jan 28, 2017
	 */
	public void resetCounter() {
		count.reset();
	}

	/**
	 * counts how many bits have been received in a long form
	 * 
	 * @return number of bits read by input stream
	 * @since Jan 28, 2017
	 */
	public long getBitsReceived() {
		return count.getBits();
	}

	/**
	 * counts how much data has been received from the input stream
	 * 
	 * @return {@link DataUnit} variable containing amount of data
	 * @since Jan 28, 2017
	 */
	public DataUnit getDataReceived() {
		return count.getData();
	}
}
