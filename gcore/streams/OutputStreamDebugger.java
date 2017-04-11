package gcore.streams;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamDebugger extends OutputStream {

	// output stream to debut
	private final OutputStream out;

	/**
	 * initializer that takes an output stream to debug as its arguments.
	 * 
	 * @param out
	 *            stream to debug
	 */
	public OutputStreamDebugger(OutputStream out) {
		this.out = out;
	}

	/**
	 * writes the data to the output stream but first prints out what the data
	 * is.
	 * 
	 * @param data
	 *            data to write
	 * @throws IOException
	 */
	@Override
	public void write(int data) throws IOException {
		System.out.println(data);
		out.write(data);
	}

	@Override
	public void flush() throws IOException {
		out.flush();
	}

	@Override
	public void close() throws IOException {
		out.close();
	}

}
