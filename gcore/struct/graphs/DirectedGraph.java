package gcore.struct.graphs;

import java.util.HashSet;
import java.util.Iterator;

import gcore.tuples.Pair;

public class DirectedGraph<E> extends AbstractGraph<E> {

	// store all vertices in a hash set
	protected final HashSet<E> vertices;

	// store the edges as a pair in a hash set
	protected final HashSet<Pair<E, E>> edges;

	public DirectedGraph() {
		vertices = new HashSet<>();
		edges = new HashSet<>();
	}

	protected DirectedGraph(HashSet<E> vertices, HashSet<Pair<E, E>> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}

	@Override
	public boolean contains(Object vertex) {
		// just return if the hash set contains the vertex
		return vertices.contains(vertex);
	}

	@Override
	public boolean adjacent(E vertexX, E vertexY) {
		// create the edge
		Pair<E, E> edge = new Pair<>(vertexX, vertexY);

		// return if the created edge is in the hash set
		return edges.contains(edge);
	}

	@Override
	public boolean add(E vertex) {
		// just add the vertex to the hash set
		return vertices.add(vertex);
	}

	@Override
	public boolean remove(Object vertex) {
		// just remove the vertex from the hash set and all edges that contain
		// the vertex from the edge set
		edges.removeIf(p -> p.getFirst().equals(vertex) || p.getSecond().equals(vertex));
		return vertices.remove(vertex);
	}

	@Override
	public boolean addEdge(E vertexX, E vertexY) {
		// make sure both vertices are in the vertex set and then add the edge
		if (!vertices.contains(vertexX) || !vertices.contains(vertexY))
			return false;
		Pair<E, E> edge = new Pair<>(vertexX, vertexY);
		return edges.add(edge);
	}

	@Override
	public boolean removeEdge(E vertexX, E vertexY) {
		// just remove the edge from the edge set
		Pair<E, E> edge = new Pair<>(vertexX, vertexY);
		return edges.remove(edge);
	}

	@Override
	public int graphOrder() {
		// return the size of the vertex set
		return vertices.size();
	}

	@Override
	public int graphSize() {
		// return the size of the edge set
		return edges.size();
	}

	@Override
	public Iterator<E> iterator() {
		// return the hash set iterator over the vertices
		return vertices.iterator();
	}

}
