
// Antonio Artini
// R00147013
import java.util.LinkedList;

/*
 *  Implementation of the interface Graph with adjacency matrix.
*/

public class GraphAdjMatrix implements Graph {

    // ATTRIBUTES:
    // TO-DO
    int vertex, edges;
    boolean directed;
    int[][] array;

    // CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges
    public GraphAdjMatrix(int V, boolean directed) {
        this.directed = directed;
        this.edges = 0;
        // We initialise the matrix with column size and row size equal to V
        this.array = new int[V][V];
    }

    // 1. IMPLEMENTATION METHOD numVerts:
    public int numVerts() {
        // The number of vertices is equal to the length of the our matrix
        return array.length;
    }

    // 2. IMPLEMENTATION METHOD numEdges:
    public int numEdges() {
        // We simply return the edges attribute which is incremented in the addEdge()
        // function
        return this.edges;
    }

    // 3. IMPLEMENTATION METHOD addEdge:
    public void addEdge(int v1, int v2, int w) {
        try {
            // directed
            if (this.directed) {
                // If the element at v1,v2 is 0 we change it to w and increment edges
                if (array[v1][v2] == 0) {
                    this.array[v1][v2] = w;
                    this.edges++;
                }
                // If the element at v1,v2 isn't 0 we change it to w
                if (array[v1][v2] != 0) {
                    this.array[v1][v2] = w;

                }
            }
            // undirected
            else {
                // If the element at v1,v2 or v2,v1 is 0 we change it to w and increment edges
                if ((array[v1][v2] == 0) || (array[v2][v1] == 0)) {
                    this.array[v1][v2] = w;
                    this.array[v2][v1] = w;
                    // if (v1 == v2) {
                    //// If v1 is equal to v2 we first decrement edges as to avoid double counting
                    // this.edges--;
                    // }
                    this.edges++;
                } else {
                    // If the elements at v1, v2 and v2,v1 are not 0 then we just change the weights
                    // of said elements
                    this.array[v1][v2] = w;
                    this.array[v2][v1] = w;
                }

            }
        } catch (Exception e) {
            System.err.println(e + " : Out of bounds");
        }

    }

    // 4. IMPLEMENTATION METHOD removeEdge:
    public void removeEdge(int v1, int v2) {
        try {
            // directed
            if (this.directed) {
                // If directed we just change the weight at v1,v2 to 0 and decrement edges
                this.array[v1][v2] = 0;
                this.edges--;
            }
            // undirectred
            else {
                // If undirected we just change the weight at v1,v2 and v2,v1 to 0 and decrement
                // edges
                this.array[v1][v2] = 0;
                this.array[v2][v1] = 0;
                this.edges--;
            }

        } catch (Exception e) {
            System.err.println(e + " : Out of bounds");
        }

    }

    // 5. IMPLEMENTATION METHOD hasEdge:
    public boolean hasEdge(int v1, int v2) {
        // directed
        try {
            if (this.directed) {
                // If the element at v1,v2 = 0 then the edge doesn't exist
                if (array[v1][v2] != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                // If the elements at v1,v2 = 0 and v2,v1 = 0then the edge doesn't exist

                if ((array[v1][v2] != 0) & (array[v2][v1] != 0)) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (Exception e) {
            System.err.println(e + ": Out of bounds");
            return false;
        }

    }

    // 6. IMPLEMENTATION METHOD getWeightEdge:
    public int getWeightEdge(int v1, int v2) {
        try {
            // We just return the element at v1,v2
            // This works the same for undirected as it does for directed
            return array[v1][v2];

        } catch (Exception e) {
            System.err.println(e + " : Out of bounds");
            return -1;
        }

    }

    // 7. IMPLEMENTATION METHOD getNeighbors:
    public LinkedList getNeighbors(int v) {
        // directed
        LinkedList<Edge> linkedList = new LinkedList<Edge>();
        try {
            // We go through each element in row v
            for (int i = 0; i < numVerts(); i++) {
                // If the element at v,i is not 0 we make a new edge with vertex i and weight
                // equal to the element at v,i
                if (array[v][i] != 0) {
                    Edge edge = new Edge(i, array[v][i]);
                    linkedList.add(edge);
                }
            }
            return linkedList;
        } catch (Exception e) {
            System.err.println(e + " : ");
            return linkedList;
        }

    }

    // 8. IMPLEMENTATION METHOD getDegree:
    public int getDegree(int v) {
        // TO-DO

        try {
            int sum = 0;
            // directed
            if (this.directed) {
                for (int i = 0; i < numVerts(); i++) {
                    // outs
                    if (this.array[v][i] != 0) {
                        sum += 1;
                    }
                    // ins
                    if (this.array[i][v] != 0) {
                        sum += 1;
                    }
                }
            }
            // undirected
            else {
                for (int i = 0; i < (numVerts() / 2) + 1; i++) {
                    if ((this.array[v][i] != 0) & (this.array[i][v] != 0)) {
                        sum += 1;
                    }
                }
            }

            return sum;
        } catch (Exception e) {
            System.err.println(e + " : Out of bounds");
            return -1;
        }

    }

    // 9. IMPLEMENTATION METHOD toString:
    public String toString() {
        // TO-DO
        String graph = "";
        if (this.directed) {
            for (int i = 0; i < numVerts(); i++) {
                for (int j = 0; j < numVerts(); j++) {
                    if (array[i][j] != 0) {
                        graph += i + " ====" + array[i][j] + "=====> " + j + "\n";
                    }

                }
            }
        } else {
            for (int i = 0; i < numVerts(); i++) {
                for (int j = 0; j < (numVerts() / 2) + 1; j++) {
                    if (array[i][j] != 0) {
                        graph += i + " ====" + array[i][j] + "==== " + j + "\n";
                    }
                }
            }
        }

        return graph;
    }
}