package gcore.struct.graphs;

import java.util.HashSet;

import gcore.tuples.unordered.UnorderedPair;

public final class UndirectedGraphs {

	private UndirectedGraphs() {}

	@SafeVarargs
	public static <E> UndirectedGraph<E> createComplete(E... vertices) {
		UndirectedGraph<E> results = new UndirectedGraph<>();

		// add all of the vertices to the results
		for (E vertex : vertices) {
			results.add(vertex);
		}

		// add all of the possible edges
		for (int i = 0; i < vertices.length; i++) {
			for (int j = i + 1; j < vertices.length; j++) {
				results.addEdge(vertices[i], vertices[j]);
			}
		}

		// return the graph
		return results;
	}

	@SafeVarargs
	public static <E> UndirectedGraph<E> createPath(E... vertices) {
		// make sure the vertex list isn't empty
		if (vertices.length == 0)
			return new UndirectedGraph<E>();

		UndirectedGraph<E> results = new UndirectedGraph<>();

		// add in the first vertex as a seed
		results.add(vertices[0]);

		// loop through the vertex list and build the path
		for (int i = 1; i < vertices.length; i++) {
			results.add(vertices[i]);
			results.addEdge(vertices[i - 1], vertices[i]);
		}

		// return the results
		return results;
	}

	@SafeVarargs
	public static <E> UndirectedGraph<E> createCycle(E... vertices) {
		// make sure the list isn't empty
		if (vertices.length == 0)
			return new UndirectedGraph<E>();

		// get the path made from this
		UndirectedGraph<E> results = createPath(vertices);

		// check if the size is 1
		if (vertices.length == 1)
			return results;

		// loop the last element back to the front
		results.addEdge(vertices[vertices.length - 1], vertices[0]);

		// return the resulting graph
		return results;
	}

	@SafeVarargs
	public static <E> UndirectedGraph<E> createNull(E... vertices) {
		// create the graph
		UndirectedGraph<E> results = new UndirectedGraph<>();

		// add all of the vertices and return the results
		for (E vertex : vertices) {
			results.add(vertex);
		}

		return results;
	}

	public static <E> UndirectedGraph<E> union(UndirectedGraph<E> a, UndirectedGraph<E> b) {
		HashSet<E> vertices = new HashSet<>(a.vertices);
		vertices.addAll(b.vertices);

		HashSet<UnorderedPair<E>> edges = new HashSet<>(a.edges);
		edges.addAll(b.edges);

		return new UndirectedGraph<>(vertices, edges);
	}

	public static <E> UndirectedGraph<E> intersection(UndirectedGraph<E> a, UndirectedGraph<E> b) {
		UndirectedGraph<E> results = new UndirectedGraph<>();

		for (E e : a) {
			if (b.contains(e)) {
				results.add(e);
			}
		}

		for (UnorderedPair<E> edge : a.edges) {
			if (b.edges.contains(edge)) {
				results.edges.add(edge);
			}
		}

		return results;
	}

	public static <E> UndirectedGraph<E> subtract(UndirectedGraph<E> a, UndirectedGraph<E> b) {
		UndirectedGraph<E> copy = new UndirectedGraph<>(new HashSet<>(a.vertices), new HashSet<>(a.edges));

		copy.removeAll(b);

		copy.edges.removeAll(b.edges);

		return copy;
	}

	public static <E> UndirectedGraph<E> join(UndirectedGraph<E> a, UndirectedGraph<E> b) {
		// make sure the vertices are disjoint
		for (E vertex : a) {
			if (b.contains(vertex))
				throw new UnsupportedOperationException("Two graphs must be independant in order to join them!");
		}

		UndirectedGraph<E> results = new UndirectedGraph<E>();

		results.addAll(a);
		results.edges.addAll(a.edges);

		for (E vertexB : b) {
			results.add(vertexB);
			for (E vertexA : a) {
				results.addEdge(vertexA, vertexB);
			}
		}

		return results;
	}

}
