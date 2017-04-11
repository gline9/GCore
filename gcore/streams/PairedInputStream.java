package gcore.streams;

import java.io.IOException;
import java.io.InputStream;

/**
 * class for creating an input stream paired with an output stream and nothing
 * in between. See {@link IOPair IOPair} class for construction details.
 * 
 * @author Gavin
 *
 */
public class PairedInputStream extends InputStream {
	// buffer for the two streams
	private IOPair buffer;

	/**
	 * initializer for the paired input stream
	 * 
	 * @param buffer
	 *            the buffer passed by the IOPair constructor
	 */
	public PairedInputStream(IOPair buffer) {
		// sets the buffer to the correct value.
		this.buffer = buffer;
	}

	@Override
	public int read() throws IOException {
		// simply read from the buffer.
		return buffer.read();
	}

}
