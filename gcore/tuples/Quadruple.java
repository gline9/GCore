package gcore.tuples;

public class Quadruple<A, B, C, D> extends Triple<A, B, C>{
	private final D fourth;
	public Quadruple(A first, B second, C third, D fourth){
		super(first, second, third);
		this.fourth = fourth;
	}
	public D getFourth(){
		return fourth;
	}
	public boolean equals(Object obj){
		if (obj instanceof Quintuple) return false;
		if (obj instanceof Quadruple){
			Quadruple<?, ?, ?, ?> pair = (Quadruple<?, ?, ?, ?>) obj;
			return  super.equals(obj) &&
					pair.getFourth().equals(this.getFourth());
		}
		return false;
	}
	public int hashCode(){
		return fourth.hashCode() + 100 * super.hashCode();
	}
	public String toString(){
		return super.toString() + "," + fourth.toString();
	}
}
