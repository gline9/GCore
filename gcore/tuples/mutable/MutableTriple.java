package gcore.tuples.mutable;

public class MutableTriple<A, B, C> extends MutablePair<A, B> {
	private C third;

	public MutableTriple() {}

	public C getThird() {
		return third;
	}

	public void setThird(C third) {
		this.third = third;
	}

	public String toString() {
		return super.toString() + "," + third.toString();
	}
}
