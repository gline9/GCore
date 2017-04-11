package gcore.struct.lazy;

import java.util.function.Function;

class LazyObject<E> {

	private E data;

	private boolean good = true;

	private boolean terminal = false;

	public LazyObject(E data) {
		this.data = data;
		if (data == null)
			terminal = true;
	}

	public E getData() {
		return data;
	}

	public LazyObject<E> filter(Function<E, Boolean> filter) {
		if (!good || terminal){
			return this;
		}else{
			good = filter.apply(data);
			return this;
		}
	}

	public <F> LazyObject<F> map(Function<E, F> function) {
		LazyObject<F> results;
		
		if (!good) {
			results = new LazyObject<F>(null);
			results.terminal = false;
			results.good = false;
		}else if (terminal){
			results = new LazyObject<F>(null);
		}else{
			results = new LazyObject<F>(function.apply(data));
		}
		
		return results;
	}

	public boolean isTerminal() {
		return terminal;
	}

	public boolean wasFiltered() {
		return !good;
	}
}
