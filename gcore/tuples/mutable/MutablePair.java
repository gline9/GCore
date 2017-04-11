package gcore.tuples.mutable;

public class MutablePair<A, B> {

	private A first;
	private B second;

	public MutablePair() {}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

	public void setFirst(A first) {
		this.first = first;
	}

	public void setSecond(B second) {
		this.second = second;
	}

	public String toString() {
		return first.toString() + "," + second.toString();
	}
}
