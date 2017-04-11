package gcore.tuples;

public class Hexuple<A, B, C, D, E, F> extends Quintuple<A, B, C, D, E> {
	private final F sixth;
	public Hexuple(A first, B second, C third, D fourth, E fifth, F sixth) {
		super(first, second, third, fourth, fifth);
		this.sixth = sixth;
	}
	
	public F getSixth() {
		return sixth;
	}
	
	public boolean equals(Object obj){
		if (obj instanceof Hexuple){
			Hexuple<?, ?, ?, ?, ?, ?> pair = (Hexuple<?, ?, ?, ?, ?, ?>) obj;
			return  super.equals(obj) &&
					pair.getSixth().equals(this.getSixth());
		}
		return false;
	}
	public int hashCode(){
		return sixth.hashCode() + super.hashCode() * 100;
	}
	public String toString(){
		return super.toString() + "," + sixth.toString();
	}
}
