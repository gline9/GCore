package gcore.tuples;

public class Triple<A, B, C> extends Pair<A, B>{
	private final C third;
	public Triple(A first, B second, C third){
		super(first, second);
		this.third = third;
	}
	public C getThird(){
		return third;
	}
	public boolean equals(Object obj){
		if (obj instanceof Quadruple) return false;
		if (obj instanceof Triple){
			Triple<?, ?, ?> pair = (Triple<?, ?, ?>) obj;
			return  super.equals(obj) &&
					pair.getThird().equals(this.getThird());
		}
		return false;
	}
	public int hashCode(){
		return this.getThird().hashCode() + 100 * super.hashCode();
	}
	public String toString(){
		return super.toString() + "," + third.toString();
	}
}
