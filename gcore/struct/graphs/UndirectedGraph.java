package gcore.struct.graphs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import gcore.tuples.unordered.UnorderedPair;

public class UndirectedGraph<E> extends AbstractGraph<E> {

	// store all vertices in a hash set
	HashSet<E> vertices = new HashSet<>();

	// store the edges as an unordered pair in a hash set
	HashSet<UnorderedPair<E>> edges = new HashSet<>();

	public UndirectedGraph() {
		vertices = new HashSet<>();
		edges = new HashSet<>();
	}

	protected UndirectedGraph(HashSet<E> vertices, HashSet<UnorderedPair<E>> edges) {
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
		UnorderedPair<E> edge = new UnorderedPair<>(vertexX, vertexY);

		// return if the created edge is in the hash set
		return edges.contains(edge);
	}

	@Override
	public boolean add(E vertex) {
		// just add the vertex to the hash set
		return vertices.add(vertex);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean remove(Object vertex) {
		E vert;
		try {
			vert = (E) vertex;
		} catch (ClassCastException e) {
			return false;
		}
		// just remove the vertex from the hash set and all edges that contain
		// the vertex from the edge set
		edges.removeIf(up -> up.contains(vert));
		return vertices.remove(vertex);
	}

	@Override
	public boolean addEdge(E vertexX, E vertexY) {
		// make sure both vertices are in the vertex set and then add the edge
		if (!vertices.contains(vertexX) || !vertices.contains(vertexY))
			return false;
		UnorderedPair<E> edge = new UnorderedPair<>(vertexX, vertexY);
		return edges.add(edge);
	}

	@Override
	public boolean removeEdge(E vertexX, E vertexY) {
		// just remove the edge from the edge set
		UnorderedPair<E> edge = new UnorderedPair<>(vertexX, vertexY);
		return edges.remove(edge);
	}

	public boolean isConnected() {
		// check for trivial situations
		if (vertices.size() == 0)
			return false;

		if (vertices.size() == 1)
			return true;

		// determine if it is connected by using a wave
		HashSet<E> waveFront = new HashSet<>();
		HashSet<E> waveVisited = new HashSet<>();

		// grab the first element for the start of the wave
		E first = vertices.iterator().next();
		

		waveFront.add(first);

		// continue until there are no more elements in the wave front
		while (waveFront.size() > 0) {
			// add all of the wave front elements to the visited elements
			waveVisited.addAll(waveFront);
			
			// if done break out of the while
			if (waveVisited.size() == vertices.size())
				break;

			// grab the iterator from the wave front
			Iterator<E> front = waveFront.iterator();

			// reset the wave front to a new array list
			waveFront = new HashSet<>();

			// loop through all of the elements from the front
			while (front.hasNext()) {
				E next = front.next();

				// grab all of the neighbors to next
				List<E> neighbors = neighbors(next);

				// add them to the wave front if they haven't been visited yet
				for (E neighbor : neighbors) {
					if (!waveVisited.contains(neighbor))
						waveFront.add(neighbor);
				}
			}
		}

		// return whether all of the nodes have been visited or not
		return waveVisited.size() == vertices.size();
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
