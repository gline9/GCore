package gcore.struct.graphs;

import java.util.HashSet;

import gcore.tuples.Pair;

public final class DirectedGraphs {
	private DirectedGraphs() {}

	@SafeVarargs
	public static <E> DirectedGraph<E> createComplete(E... vertices) {
		DirectedGraph<E> results = new DirectedGraph<>();

		// add all of the vertices to the results
		for (E vertex : vertices) {
			results.add(vertex);
		}

		// add all of the possible edges
		for (int i = 0; i < vertices.length; i++) {
			for (int j = 0; j < vertices.length; j++) {
				if (i != j) {
					results.addEdge(vertices[i], vertices[j]);
					results.addEdge(vertices[j], vertices[i]);
				}
			}
		}

		// return the graph
		return results;
	}

	public static <E> DirectedGraph<E> union(DirectedGraph<E> a, DirectedGraph<E> b) {
		HashSet<E> vertices = new HashSet<>(a.vertices);
		vertices.addAll(b.vertices);

		HashSet<Pair<E, E>> edges = new HashSet<>(a.edges);
		edges.addAll(b.edges);

		return new DirectedGraph<>(vertices, edges);
	}

	public static <E> DirectedGraph<E> intersection(DirectedGraph<E> a, DirectedGraph<E> b) {
		DirectedGraph<E> results = new DirectedGraph<>();

		for (E e : a) {
			if (b.contains(e)) {
				results.add(e);
			}
		}

		for (Pair<E, E> edge : a.edges) {
			if (b.edges.contains(edge)) {
				results.edges.add(edge);
			}
		}

		return results;
	}

	public static <E> DirectedGraph<E> subtract(DirectedGraph<E> a, DirectedGraph<E> b) {
		DirectedGraph<E> copy = new DirectedGraph<>(new HashSet<>(a.vertices), new HashSet<>(a.edges));

		copy.removeAll(b);

		copy.edges.removeAll(b.edges);

		return copy;
	}

	public static <E> DirectedGraph<E> dualJoin(DirectedGraph<E> a, DirectedGraph<E> b) {
		// make sure the vertices are disjoint
		for (E vertex : a) {
			if (b.contains(vertex))
				throw new UnsupportedOperationException("Two graphs must be independant in order to join them!");
		}

		DirectedGraph<E> results = new DirectedGraph<E>();

		results.addAll(a);

		for (E vertexB : b) {
			results.add(vertexB);
			for (E vertexA : a) {
				results.addEdge(vertexA, vertexB);
				results.addEdge(vertexA, vertexB);
			}
		}

		return results;
	}

	public static <E> DirectedGraph<E> directionalJoin(DirectedGraph<E> from, DirectedGraph<E> to) {
		// make sure the vertices are disjoint
		for (E fromVertex : from) {
			if (to.contains(fromVertex))
				throw new UnsupportedOperationException("Two graphs must be independant in order to join them!");
		}

		DirectedGraph<E> results = new DirectedGraph<E>();

		results.addAll(from);

		for (E toVertex : to) {
			results.add(toVertex);
			for (E fromVertex : from) {
				results.addEdge(fromVertex, toVertex);
			}
		}

		return results;
	}
}