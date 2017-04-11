package gcore.streams;

import java.io.IOException;
import java.io.OutputStream;

import gcore.counter.BitCounter;
import gcore.units.DataUnit;

/**
 * output stream that keeps track of how much data has gone through using a
 * {@link BitCounter}. How to use it is to put it in-between your output stream
 * and the output stream you write data to. For instance DataOutputStream ->
 * CountingOutputStream -> Socket
 * 
 * @author Gavin
 *
 */
public class CountingOutputStream extends OutputStream {

	private final OutputStream out;
	private final BitCounter count;

	/**
	 * initialize it by giving the output stream it will write to
	 * 
	 * @param out
	 *            output stream to write to
	 */
	public CountingOutputStream(OutputStream out) {
		this.out = out;
		this.count = new BitCounter();
	}

	@Override
	public void write(int b) throws IOException {
		count.incrementBytesBy(1);
		out.write(b);
	}

	@Override
	public void flush() throws IOException {
		out.flush();
	}

	@Override
	public void close() throws IOException {
		out.close();
	}

	/**
	 * reset the counter of how much data has gone through
	 * 
	 * @since Jan 28, 2017
	 */
	public void resetCounter() {
		count.reset();
	}

	/**
	 * get how many bits it has sent in the long form
	 * 
	 * @return number of bits sent by output stream
	 * @since Jan 28, 2017
	 */
	public long getBitsSent() {
		return count.getBits();
	}

	/**
	 * get how much data has been sent.
	 * 
	 * @return amount of data sent by Output Stream in form of {@link DataUnit}
	 * @since Jan 28, 2017
	 */
	public DataUnit getDataSent() {
		return count.getData();
	}
}
