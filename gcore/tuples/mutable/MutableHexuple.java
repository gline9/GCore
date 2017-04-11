package gcore.tuples.mutable;

public class MutableHexuple<A, B, C, D, E, F> extends MutableQuintuple<A, B, C, D, E> {
	private F sixth;

	public MutableHexuple() {}

	public F getSixth() {
		return sixth;
	}

	public void setSixth(F sixth) {
		this.sixth = sixth;
	}

	public String toString() {
		return super.toString() + "," + sixth.toString();
	}
}