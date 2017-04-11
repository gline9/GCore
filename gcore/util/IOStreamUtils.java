package gcore.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * utility class for common stream operations.
 * 
 * @author Gavin
 *
 */
public final class IOStreamUtils {
	// make non-instantiable
	private IOStreamUtils() {}

	/**
	 * copies the given input stream into the given output stream, streams still
	 * need to be closed afterwards.
	 * 
	 * @param in
	 *            input stream to read from
	 * @param out
	 *            output stream to write to
	 * @throws IOException
	 */
	public static void copyStreams(InputStream in, OutputStream out) throws IOException {
		byte[] bytes = new byte[8128];
		int numBytes;
		while ((numBytes = in.read(bytes)) != -1) {
			out.write(bytes, 0, numBytes);
		}
		out.flush();
	}
}
