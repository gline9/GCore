package gcore.tuples.unordered;

public class UnorderedQuintuple<A> extends UnorderedQuadruple<A> {

	protected final A e;

	public UnorderedQuintuple(A a, A b, A c, A d, A e) {
		super(a, b, c, d);
		this.e = e;
	}

	public boolean contains(A a) {
		if (a == null && e == null)
			return true;
		if (super.contains(a))
			return true;
		if (this.e != null && this.e.equals(a))
			return true;

		return false;
	}

	public int hashCode() {
		return super.hashCode() ^ (e == null ? 0 : e.hashCode());
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		UnorderedQuintuple<A> other = (UnorderedQuintuple<A>) obj;

		return other.contains(a) && other.contains(b) && other.contains(c) && other.contains(d) && other.contains(e)
				&& contains(other.a) && contains(other.b) && contains(other.c) && contains(other.d)
				&& contains(other.e);
	}

}