package gcore.bits;

/**
 * util class for bit sequences
 * 
 * @author Gavin
 *
 */
public final class BitSequences {
	// private so you can't instantiate it
	private BitSequences() {}

	/**
	 * creates a bit sequence that is the bits of a long
	 * @param value long value to use for its bits
	 * @return a corresponding bit sequence
	 * @since Jan 28, 2017
	 */
	public BitSequence valueOf(long value) {
		return new BitSequence(
				new byte[] { (byte) (value & 0x00000000000000FFL), (byte) ((value & 0x000000000000FF00L) >> 8),
						(byte) ((value & 0x0000000000FF0000L) >> 16), (byte) ((value & 0x00000000FF000000L) >> 24),
						(byte) ((value & 0x000000FF00000000L) >> 32), (byte) ((value & 0x00000FF000000000L) >> 40),
						(byte) ((value & 0x00FF000000000000L) >> 48), (byte) ((value & 0xFF00000000000000L) >> 56) });
	}
	
	/**
	 * creates a bit sequence that is the bits of an int
	 * @param value integer value to use for its bits
	 * @return a corresponding bit sequence
	 * @since Jan 28, 2017
	 */
	public BitSequence valueOf(int value) {
		return new BitSequence(new byte[] { (byte) (value & 0x000000FF), (byte) ((value & 0x0000FF00) >> 8),
				(byte) ((value & 0x00FF0000) >> 16), (byte) ((value & 0xFF000000) >> 24) });
	}

	public BitSequence valueOf(short value) {
		return new BitSequence(new byte[] { (byte) (value & 0x00FF), (byte) ((value & 0xFF00) >> 8) });
	}

	public BitSequence valueOf(char value) {
		return valueOf((short) value);
	}

	public BitSequence valueOf(String value) {
		return new BitSequence(value.getBytes());
	}
}
