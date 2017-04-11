package gcore.tuples.unordered;

public class UnorderedPair<A> {
	protected final A a;
	protected final A b;

	public UnorderedPair(A a, A b) {
		this.a = a;
		this.b = b;
	}

	public boolean contains(A a) {
		if (a == null && (this.a == null || this.b == null))
			return true;
		if (this.a != null && this.a.equals(a))
			return true;
		if (this.b != null && this.b.equals(a))
			return true;

		return false;
	}

	public int hashCode() {
		return (a == null ? 0 : a.hashCode()) ^ (b == null ? 0 : b.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		UnorderedPair<A> other = (UnorderedPair<A>) obj;

		return other.contains(a) && other.contains(b) && contains(other.a) && contains(other.b);
	}

}
