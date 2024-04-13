import java.util.Arrays;
import java.util.Stack;

public class Kosaraju {
    
    private Graph g=null; // Graph instance for Kosaraju's algorithm
    
    public Kosaraju(Graph g){
        this.g=g;
    }
    
    // Depth First Search (DFS) utility function for Kosaraju's algorithm
    void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        // System.out.print(v + " ");
        // Traverse through adjacent vertices of v
        for (Integer i : g.adj[v]) {
            // If adjacent vertex i is not visited, recursively call DFSUtil
            if (!visited[i]) {
                DFSUtil(i, visited);
            }
        }
    }

    // Method to get the transpose of the graph (reverse all edges)
    Graph getTranspose() {
        Graph gT = new Graph(g.V); // Create a new graph with the same number of vertices
        // Traverse through all vertices of the original graph
        for (int v = 0; v < gT.V; v++) {
            // Traverse through all adjacent vertices of vertex v in the original graph
            for (Integer i : g.adj[v]) {
                gT.adj[i].add(v); // Add reverse edge from i to v in the transpose graph
            }
        }
        return gT;
    }

    // Method to fill the stack with vertices based on their finish times during DFS
    void fillOrder(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        // Recursively traverse through all vertices reachable from v
        for (Integer i : g.adj[v]) {
            // If adjacent vertex i is not visited, recursively call fillOrder
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }
        // Push vertex v onto the stack after all its adjacent vertices are processed
        stack.push(v);
    }

    // Method to print Strongly Connected Components (SCCs) using Kosaraju's algorithm
    void printSCCs() {
        Stack<Integer> stack = new Stack<>(); // Stack to store vertices based on finish times
        boolean[] visited = new boolean[g.V]; // Array to keep track of visited vertices
        // Step 1: Fill the stack with vertices based on their finish times (in the original graph)
        for (int i = 0; i < g.V; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }
        // Step 2: Get the transpose of the graph
        g = getTranspose();
        Arrays.fill(visited, false); // Reset visited array for the reversed graph
        // Step 3: Process vertices in the stack to print SCCs (in the reversed graph)
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                // Perform DFS from vertex v in the reversed graph to print SCC
                DFSUtil(v, visited);
                // Separate SCCs (e.g., print newline)
                // System.out.println();
            }
        }
    }
}
