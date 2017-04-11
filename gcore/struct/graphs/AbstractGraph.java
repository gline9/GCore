package gcore.struct.graphs;

import java.util.AbstractSet;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGraph<E> extends AbstractSet<E> {

	public abstract int graphOrder();

	public abstract int graphSize();

	public abstract boolean contains(Object vertex);

	public abstract boolean adjacent(E vertexX, E vertexY);

	public abstract boolean add(E vertex);

	public abstract boolean remove(Object vertex);

	public abstract boolean addEdge(E vertexX, E vertexY);

	public abstract boolean removeEdge(E vertexX, E vertexY);

	public List<E> neighbors(E start) {
		LinkedList<E> results = new LinkedList<>();

		// loop through all of the vertices and find the ones that are
		// connected to the start vertex, this will also hold for self
		// connecting vertices.
		for (E vertex : this) {
			if (adjacent(start, vertex)) {
				results.add(vertex);
			}
		}

		// return the list of vertices that were found
		return results;
	}

	public final int size() {
		return graphOrder();
	}

}
