package gcore.streams;

import java.io.IOException;
import java.io.InputStream;

/**
 * class to print out every byte that has been read from the input stream to
 * test what is being received.
 * 
 * @author Gavin
 *
 */
public class InputStreamDebugger extends InputStream {

	// input stream to debug
	private final InputStream in;

	/**
	 * gets the input stream and then sets the debugging input stream to it
	 * 
	 * @param in
	 *            input stream to debug
	 */
	public InputStreamDebugger(InputStream in) {
		this.in = in;
	}

	/**
	 * reads the input stream like normal but instead prints out the results of
	 * it.
	 */
	@Override
	public int read() throws IOException {
		int read = in.read();
		System.out.println(read);
		return read;
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

}
