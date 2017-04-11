package gcore.tuples.mutable;

public class MutableQuadruple<A, B, C, D> extends MutableTriple<A, B, C> {
	private D fourth;

	public MutableQuadruple() {}

	public D getFourth() {
		return fourth;
	}

	public void setFourth(D fourth) {
		this.fourth = fourth;
	}

	public String toString() {
		return super.toString() + "," + fourth.toString();
	}
}
