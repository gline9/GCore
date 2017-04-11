package gcore.tuples.mutable;

public class MutableQuintuple<A, B, C, D, E> extends MutableQuadruple<A, B, C, D> {
	private E fifth;

	public MutableQuintuple() {}

	public E getFifth() {
		return fifth;
	}

	public void setFifth(E fifth) {
		this.fifth = fifth;
	}

	public String toString() {
		return super.toString() + "," + fifth.toString();
	}
}