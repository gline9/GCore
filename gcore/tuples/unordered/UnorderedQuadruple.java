package gcore.tuples.unordered;

public class UnorderedQuadruple<A> extends UnorderedTriple<A> {

	protected final A d;

	public UnorderedQuadruple(A a, A b, A c, A d) {
		super(a, b, c);
		this.d = d;
	}

	public boolean contains(A a) {
		if (a == null && d == null)
			return true;
		if (super.contains(a))
			return true;
		if (this.d != null && this.d.equals(a))
			return true;

		return false;
	}

	public int hashCode() {
		return super.hashCode() ^ (d == null ? 0 : d.hashCode());
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		UnorderedQuadruple<A> other = (UnorderedQuadruple<A>) obj;

		return other.contains(a) && other.contains(b) && other.contains(c) && other.contains(d) && contains(other.a)
				&& contains(other.b) && contains(other.c) && contains(other.d);
	}

}