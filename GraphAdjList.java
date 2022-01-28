
// Antonio Artini
// R00147013
import java.util.LinkedList;

/**
 * Graph implementation that uses Adjacency Lists to store edges. It contains
 * one linked-list for every vertex i of the graph. The list for i contains one
 * instance of VertexAdjList for every vertex that is adjacent to i. For
 * directed graphs, if there is an edge from vertex i to vertex j then there is
 * a corresponding element in the adjacency list of node i (only). For
 * undirected graphs, if there is an edge between vertex i and vertex j, then
 * there is a corresponding element in the adjacency lists of *both* vertex i
 * and vertex j. The edges are not sorted; they contain the adjacent nodes in
 * *order* of edge insertion. In other words, for a graph, the node at the head
 * of the list of some vertex i corresponds to the edge involving i that was
 * added to the graph least recently (and has not been removed, yet).
 */

public class GraphAdjList implements Graph {

	// ATTRIBUTES:
	int vertices, edges;
	boolean directed;
	LinkedList<Edge> arr[];
	// TO-DO

	/*
	 * CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no
	 * edges. It initializes the array of adjacency edges so that each list is
	 * empty.
	 */

	public GraphAdjList(int V, boolean directed) {
		// TO-DO
		this.vertices = V;
		this.directed = directed;
		this.edges = 0;
		this.arr = new LinkedList[V];
		// Initialize our array of linked lists/ list of edges
		for (int i = 0; i < V; i++) {
			arr[i] = new LinkedList<Edge>();
		}
	}

	// 1. IMPLEMENTATION METHOD numVerts:
	public int numVerts() {
		// The number of vertices is equal to the length of the array of linked lists
		return this.arr.length;
	}

	// 2. IMPLEMENTATION METHOD numEdges:
	public int numEdges() {
		// We increment the edges attribute in the addEdge() function.
		// Here wwe just return the edges attribute
		return this.edges;
	}

	// 3. IMPLEMENTATION METHOD addEdge:
	public void addEdge(int v1, int v2, int w) {
		try {
			// directed
			if (this.directed) {
				// If an edge exists between v1 and v2
				if (hasEdge(v1, v2)) {
					// We go through all the edge connections for v1 until we find the edge with
					// vertex v2
					for (int i = 0; i < arr[v1].size(); i++) {
						Edge egg = (Edge) this.arr[v1].get(i);
						// If the vertex for an edge is equal to v2,
						// we simply change the weight of the edge and break out of the loop.
						if (egg.getVertex() == v2) {
							egg.setWeight(w);
							break;
						}
					}
				}
				// If the this edge doesn't already exists we create a new edge with vertex v2
				// and weight w then increment our edges attribute.
				else {
					Edge edge = new Edge(v2, w);
					this.arr[v1].add(edge);
					edges++;
				}
			} else {
				// For undirected
				// If an edge exists between v1 and v2
				if (hasEdge(v1, v2) || hasEdge(v2, v1)) {
					// We go through all the edge connections for v1 until we find the edge with
					// vertex v2 then set the weight to w
					for (int i = 0; i < arr[v1].size(); i++) {
						Edge egg = (Edge) this.arr[v1].get(i);
						if (egg.getVertex() == v2) {
							egg.setWeight(w);
							break;
						}
					}
					// We go through all the edge connections for v2 until we find the edge with
					// vertex v1 then set the weight to w
					for (int i = 0; i < arr[v2].size(); i++) {
						Edge egg = (Edge) this.arr[v2].get(i);
						if (egg.getVertex() == v1) {
							egg.setWeight(w);
							break;
						}
					}
				}
				// If the edge connection doesn't exist then we create edges then add them to
				// the v1 and v2 linked lists
				else {
					Edge egg = new Edge(v2, w);
					Edge egg2 = new Edge(v1, w);
					this.arr[v1].add(egg);
					this.arr[v2].add(egg2);
					edges++;
				}
			}

		} catch (Exception e) {
			System.err.println(e + " : Couldn't add edge. Vertex doesn't exist");
		}
	}

	// 4. IMPLEMENTATION METHOD removeEdge:
	public void removeEdge(int v1, int v2) {
		try {
			if (this.directed) {
				// If the edge exists
				if (hasEdge(v1, v2)) {
					// We go through the array of edge connections for v1 until we find an edge with
					// vertex v2, then remove the edge from the linked list and decrement the edges
					// attribute
					for (int i = 0; i < arr[v1].size(); i++) {
						Edge egg = (Edge) this.arr[v1].get(i);
						if (egg.getVertex() == v2) {
							this.arr[v1].remove(i);
							edges--;
							break;
						}
					}
				} else {
					System.out.println("Edge doesn't exist");
				}
			} else {
				// If an edge exists between v1 and v2 or v2 and v1
				if (hasEdge(v1, v2) || hasEdge(v2, v1)) {
					// We go through all the edges in v1. When we get the edge with vertex v2 we
					// remove it from the list and decrement the edges attirbute
					for (int i = 0; i < arr[v1].size(); i++) {
						Edge egg = (Edge) this.arr[v1].get(i);
						if (egg.getVertex() == v2) {
							this.arr[v1].remove(i);
							edges--;
							break;
						}
					}
					// We go through all the edges in v2. When we get the edge with vertex v2 we
					// remove it from the list
					for (int i = 0; i < arr[v2].size(); i++) {
						Edge egg = (Edge) this.arr[v2].get(i);
						if (egg.getVertex() == v1) {
							this.arr[v2].remove(i);
							break;
						}
					}

				}
			}

		} catch (Exception e) {
			System.err.println(e + " : Out of bounds, edge doesn't exist");
		}

	}

	// 5. IMPLEMENTATION METHOD hasEdge:
	public boolean hasEdge(int v1, int v2) {
		boolean flag = false;
		if (this.directed) {
			// We go through all the edges in v1. When we get the edge with vertex v2 we
			// set flag to true.
			for (int i = 0; i < this.arr[v1].size(); i++) {
				Edge egg = (Edge) this.arr[v1].get(i);
				if (egg.getVertex() == v2) {
					flag = true;
					break;
				} else {
					flag = false;
				}

			}
		} else {
			// We go through all the edges in v1. When we get the edge with vertex v2 we
			// set flag to true.
			for (int i = 0; i < this.arr[v1].size(); i++) {
				Edge egg = (Edge) this.arr[v1].get(i);
				if (egg.getVertex() == v2) {
					flag = true;
					break;
				} else {
					flag = false;
				}

			}
			// We go through all the edges in v2. When we get the edge with vertex v1 we
			// set flag to true.
			for (int i = 0; i < this.arr[v2].size(); i++) {
				Edge egg = (Edge) this.arr[v2].get(i);
				if (egg.getVertex() == v1) {
					flag = true;
					break;
				} else {
					flag = false;
				}

			}
		}

		return flag;
	}

	// 6. IMPLEMENTATION METHOD getWeightEdge:
	public int getWeightEdge(int v1, int v2) {
		try {
			int res = 0;
			// We go through all the edges in v1. When we get the edge with vertex v2 we
			// set res equal to the weight of the edge
			for (int i = 0; i < arr[v1].size(); i++) {
				Edge egg = (Edge) this.arr[v1].get(i);
				if (egg.getVertex() == v2) {
					res = egg.getWeight();
					break;
				}
			}
			return res;
		} catch (Exception e) {
			System.err.println(e);
			return -1;
		}
	}

	// 7. IMPLEMENTATION METHOD getNeighbors:
	public LinkedList getNeighbors(int v) {
		try {
			// We return the elements/edges connected to v.
			return arr[v];
		} catch (Exception e) {
			System.err.println(e + " : Vertex doesn't exist");
			return new LinkedList<>();
		}
	}

	// 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) {
		try {
			int res = 0;
			// directed
			if (this.directed) {
				// We go through all the edges in v. If an edge exists
				// we increment res.
				// outs
				for (int i = 0; i < this.arr[v].size(); i++) {
					if (hasEdge(v, arr[v].get(i).getVertex())) {
						res += 1;
					}

				}
				// We go through all the vertices represented as i. When we find the edge with a
				// vertex equal to v we increment res.
				// ins
				for (int i = 0; i < this.arr.length; i++) {
					for (int j = 0; j < this.arr[i].size(); j++) {
						if (this.arr[i].get(j).getVertex() == v) {
							res += 1;
						}
					}
				}
			} else {
				// We go through all the edges in v. If an edge exists
				// we increment res.
				for (int i = 0; i < this.arr[v].size(); i++) {
					if (hasEdge(v, arr[v].get(i).getVertex())) {
						res += 1;
					}
				}
			}

			return res;
		} catch (Exception e) {
			System.err.println(e);
			return -1;

		}
	}

	// 9. IMPLEMENTATION METHOD toString:
	public String toString() {
		// TO-DO
		try {
			String res = "";
			if (this.directed) {
				for (int i = 0; i < this.arr.length; i++) {
					for (int j = 0; j < this.arr[i].size(); j++) {
						Edge vert = (Edge) this.arr[i].get(j);
						if (hasEdge(i, vert.getVertex())) {
							res += i + " === " + vert.getWeight() + "===> " + vert.getVertex() + "\n";
						}

					}
				}
			} else {
				for (int i = 0; i < (this.arr.length / 2); i++) {
					for (int j = 0; j < this.arr[i].size(); j++) {
						Edge vert = (Edge) this.arr[i].get(j);
						res += i + " ===" + vert.getWeight() + "=== " + vert.getVertex() + "\n";
					}
				}
			}
			return res;
		} catch (Exception e) {
			System.err.println(e);
			return "";
		}

	}

}
