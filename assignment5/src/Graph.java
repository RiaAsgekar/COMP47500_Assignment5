import java.util.*;

public class Graph {
    public int V; // Number of vertices in the graph
    public LinkedList<Integer>[] adj; // Array of linked lists to store adjacency lists for each vertex

    @SuppressWarnings("unchecked")
    Graph(int V) { // Constructor to initialize the graph with V vertices
        this.V = V;
        adj = new LinkedList[V]; // Initialize array for adjacency lists
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>(); // Initialize each adjacency list
        }
    }

    void addEdge(int v, int w) { // Method to add an edge between vertices v and w
        adj[v].add(w); // Add w to the adjacency list of v (representing an edge from v to w)
    }
}
