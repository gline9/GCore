package gcore.streams;

import java.io.IOException;
import java.io.OutputStream;

/**
 * class for creating an output stream paired with an input stream and nothing
 * in between. See {@link IOPair IOPair} class for construction details.
 * 
 * @author Gavin
 *
 */
public class PairedOutputStream extends OutputStream {
	// buffer for the stream.
	private IOPair buffer;

	/**
	 * initializer for the paired output stream.
	 * 
	 * @param buffer
	 *            the buffer the output stream uses.
	 */
	public PairedOutputStream(IOPair buffer) {
		// initialize the buffer
		this.buffer = buffer;
	}

	@Override
	public void write(int data) throws IOException {
		// simply write to the buffer.
		buffer.write(data);
	}

}
