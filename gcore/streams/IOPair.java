package gcore.streams;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * class to create a pair of linked input and output streams.
 * 
 * @author Gavin
 *
 */
public class IOPair {
	// buffer to hold the values of the streams.
	private byte[] buffer = new byte[10];

	// pointer to where the current element to read lies
	private int readPointer = 0;

	// pointer to where the current element to write lies
	private int writePointer = 0;

	// output stream for the pair
	private OutputStream out;

	// input stream for the pair
	private InputStream in;

	// key for notification of data being available.
	private Object dataKey = new Object();

	/**
	 * initializer for the IOPair class
	 */
	public IOPair() {
		// initialize the input and output streams for the class.
		out = new PairedOutputStream(this);
		in = new PairedInputStream(this);
	}

	/**
	 * adds data to the buffer, only the first byte of data in the int will be
	 * read the rest is ignored.
	 * 
	 * @param data
	 *            data to be written to internal buffer.
	 */
	public synchronized void write(int data) {
		// convert the data to a byte and then add to the buffer.
		byte writeData = (byte) data;
		buffer[writePointer++] = writeData;

		// if the buffer is out of write room.
		if (writePointer == buffer.length) {

			// if the read pointer is catching up to the write pointer move
			// array instead of resizing.
			if (readPointer * 2 > writePointer) {

				// how far to shift the array
				int arrayShift = readPointer;

				// reset the read and write pointers
				writePointer = writePointer - readPointer;
				readPointer = 0;

				// shift the array
				for (int i = 0; i < writePointer; i++) {
					buffer[i] = buffer[arrayShift + i];
				}
			} else {
				// if the read pointer isn't catching up to the write pointer
				// expand the array.
				buffer = Arrays.copyOf(buffer, 3 * (buffer.length / 2) + 2);
			}
		}
		synchronized (dataKey) {
			dataKey.notifyAll();
		}
	}

	/**
	 * reads data from the buffer will block if no data is available.
	 * 
	 * @return
	 */
	public int read() {
		// if no data is available wait for data
		while (readPointer == writePointer) {
			synchronized (dataKey) {
				try {
					dataKey.wait();
				} catch (InterruptedException e) {}
			}
		}

		// once their is data read and increment read pointer and filter out the
		// last bytes so there isn't an integer cast.
		return buffer[readPointer++] & 0xFF;
	}

	/**
	 * returns the output stream paired to this buffer.
	 * 
	 * @return
	 */
	public OutputStream getOutputStream() {
		return out;
	}

	/**
	 * returns the input stream paired to this buffer.
	 * 
	 * @return
	 */
	public InputStream getInputStream() {
		return in;
	}

}
