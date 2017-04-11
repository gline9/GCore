package gcore.tuples.unordered;

public class UnorderedTriple<A> extends UnorderedPair<A> {

	protected final A c;

	public UnorderedTriple(A a, A b, A c) {
		super(a, b);
		this.c = c;
	}

	public boolean contains(A a) {
		if (a == null && c == null)
			return true;
		if (super.contains(a))
			return true;
		if (this.c != null && this.c.equals(a))
			return true;

		return false;
	}

	public int hashCode() {
		return super.hashCode() ^ (c == null ? 0 : c.hashCode());
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		UnorderedTriple<A> other = (UnorderedTriple<A>) obj;

		return other.contains(a) && other.contains(b) && other.contains(c) && contains(other.a) && contains(other.b)
				&& contains(other.c);
	}

}
