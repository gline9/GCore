package gcore.tuples;

public class Quintuple<A, B, C, D, E> extends Quadruple<A, B, C, D>{
	private final E fifth;
	public Quintuple(A first, B second, C third, D fourth, E fifth){
		super(first, second, third, fourth);
		this.fifth = fifth;
	}
	public E getFifth(){
		return fifth;
	}
	public boolean equals(Object obj){
		if (obj instanceof Hexuple) return false;
		if (obj instanceof Quintuple){
			Quintuple<?, ?, ?, ?, ?> pair = (Quintuple<?, ?, ?, ?, ?>) obj;
			return  super.equals(obj) &&
					pair.getFifth().equals(this.getFifth());
		}
		return false;
	}
	public int hashCode(){
		return fifth.hashCode() + super.hashCode() * 100;
	}
	public String toString(){
		return super.toString() + "," + fifth.toString();
	}
}
