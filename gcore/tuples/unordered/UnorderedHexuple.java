package gcore.tuples.unordered;

public class UnorderedHexuple<A> extends UnorderedQuintuple<A> {

	protected final A f;

	public UnorderedHexuple(A a, A b, A c, A d, A e, A f) {
		super(a, b, c, d, e);
		this.f = f;
	}

	public boolean contains(A a) {
		if (a == null && f == null)
			return true;
		if (super.contains(f))
			return true;
		if (this.f != null && this.f.equals(a))
			return true;

		return false;
	}

	public int hashCode() {
		return super.hashCode() ^ (f == null ? 0 : f.hashCode());
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		UnorderedHexuple<A> other = (UnorderedHexuple<A>) obj;

		return other.contains(a) && other.contains(b) && other.contains(c) && other.contains(d) && other.contains(e)
				&& other.contains(f) && contains(other.a) && contains(other.b) && contains(other.c) && contains(other.d)
				&& contains(other.e) && contains(other.f);
	}

}