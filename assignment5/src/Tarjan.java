import java.util.Arrays;
import java.util.Stack;

public class Tarjan {
    
    private Graph g=null; // Graph instance to perform SCC on
    private int time = 0; // Variable to keep track of time during traversal
    
    public Tarjan(Graph g){ // Constructor to initialize Tarjan's algorithm with a graph
        this.g=g;
    }
    
    // Tarjan's algorithm to find Strongly Connected Components (SCCs)
    void SCCUtil(int u, int[] disc, int[] low, Stack<Integer> st, boolean[] stackMember) {
        // Initialize discovery time and low value for vertex u
        disc[u] = low[u] = ++time;
        // Push the current vertex onto the stack and mark it as a part of the stack
        st.push(u);
        stackMember[u] = true;
        // Traverse through all adjacent vertices of u
        for (int v : g.adj[u]) {
            // If v is not visited, recursively call SCCUtil for v
            if (disc[v] == -1) {
                SCCUtil(v, disc, low, st, stackMember);
                // Update low value of u based on low value of v
                low[u] = Math.min(low[u], low[v]);
            } 
            // If v is still in the stack, update low value of u based on discovery time of v
            else if (stackMember[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        // If u is a head node, pop vertices from stack until u is reached
        if (low[u] == disc[u]) {
            int w;
            do {
                w = st.pop();
                // Mark the popped vertex as not a part of the stack
                stackMember[w] = false;
                // Print the SCC or do further processing
            } while (w != u);
        }
    }

    // Method to find Strongly Connected Components in the graph
    void SCC() {
        // Arrays to store discovery time, low value, and stack membership of vertices
        int[] disc = new int[g.V]; // Discovery times
        int[] low = new int[g.V]; // Nodes with least discovery times
        boolean[] stackMember = new boolean[g.V]; // Whether node is in the stack
        // Stack to keep track of connected ancestors during traversal
        Stack<Integer> st = new Stack<>();
        // Initialize arrays
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(stackMember, false);
        // Iterate through all vertices of the graph
        for (int i = 0; i < g.V; i++) {
            // If vertex is not visited, call SCCUtil for it
            if (disc[i] == -1) {
                SCCUtil(i, disc, low, st, stackMember);
            }
        }
    }
}
