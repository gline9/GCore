package gcore.tuples;

public class Pair<A, B> {
	private final A first;
	private final B second;
	public Pair(A first, B second){
		this.first = first;
		this.second = second;
	}
	public A getFirst(){
		return first;
	}
	public B getSecond(){
		return second;
	}
	public boolean equals(Object obj){
		if (obj instanceof Triple) return false;
		if (obj instanceof Pair){
			Pair<?, ?> pair = (Pair<?, ?>) obj;
			return pair.getFirst().equals(this.getFirst()) && pair.getSecond().equals(this.getSecond());
		}
		return false;
	}
	public int hashCode(){
		return this.getFirst().hashCode() + 100 * this.getSecond().hashCode();
	}
	public String toString(){
		return first.toString() + "," + second.toString();
	}
}
