import java.util.*;
import java.io.*;

public class Graph {
    private int V;
    private LinkedList<Integer>[] adj;
    
    private int time = 0;

    @SuppressWarnings("unchecked")
	Graph(int V) { //constructor
        this.V = V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>(); //initialise adjacency lists for each vertex
        }
    }

    void addEdge(int v, int w) { //function to add edges
        adj[v].add(w);
    }

    void DFSUtil(int v, boolean[] visited) { //kosaraju's - dfs traversal
        visited[v] = true;
       // System.out.print(v + " ");
        for (Integer i : adj[v]) { //iterate through adjacent vertices
            if (!visited[i]) {
                DFSUtil(i, visited); //recursive call
            }
        }
    }

    Graph getTranspose() { // kosaraju's
        Graph g = new Graph(V);
        for (int v = 0; v < V; v++) {
            for (Integer i : adj[v]) {
                g.adj[i].add(v); //adds reverse edges to get transpose graph
            }
        }
        return g;
    }

    void fillOrder(int v, boolean[] visited, Stack<Integer> stack) { //kosaraju's - fills stack based on finish times
        visited[v] = true;
        for (Integer i : adj[v]) { //dfs
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }
        stack.push(v);
    }

    void printSCCs() { // kosaraju's
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }
        Graph gr = getTranspose();
        Arrays.fill(visited, false); //reset visited array for reversed graph
        while (!stack.isEmpty()) { //process stack vertices
            int v = stack.pop();
            if (!visited[v]) {
                gr.DFSUtil(v, visited);
              //  System.out.println();
            }
        }
    }

    void SCCUtil(int u, int[] disc, int[] low, Stack<Integer> st, boolean[] stackMember) { //tarjan's
        disc[u] = low[u] = ++time; //discovery time and low values are initialised
        st.push(u);
        stackMember[u] = true;
        for (int v : adj[u]) { //traversing adjacent vertices
            if (disc[v] == -1) { // if not visited
                SCCUtil(v, disc, low, st, stackMember); //checks if subtree has connection to ancestors
                low[u] = Math.min(low[u], low[v]);
            } else if (stackMember[v]) {
                low[u] = Math.min(low[u], disc[v]); //update if v is still in the stack
            }
        }
        if (low[u] == disc[u]) { //head node found
            int w;
            do { //pop until stack is empty
                w = st.pop();
               // System.out.print(w + " ");
                stackMember[w] = false;
            } while (w != u);
           // System.out.println();
        }
    }

    void SCC() { // tarjan's 
        int[] disc = new int[V]; //discovery times
        int[] low = new int[V]; //nodes with least discovery times
        boolean[] stackMember = new boolean[V];//whether node is in the stack
        Stack<Integer> st = new Stack<>();//connected ancestors
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(stackMember, false);
        for (int i = 0; i < V; i++) {
            if (disc[i] == -1) {//if not visited 
                SCCUtil(i, disc, low, st, stackMember);
            }
        }
    }
    
    public static void main(String[] args) {
        // Graph for testing
        Graph g = new Graph(84);
        
        String csvFile = "D:\\College\\Advanced Data Structures in Java\\assignment5\\src\\201508_trip_data.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isFirstLine = true; // Flag to skip header row
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header row
                }
                
                String[] parts = line.split(",");

                // Extract start and end stations
                int startStation = Integer.parseInt(parts[4]); // start terminal station number column index
                int endStation = Integer.parseInt(parts[7]); // end terminal station number column index
                // Add edge between start and end stations
                g.addEdge(startStation - 2, endStation - 2); // Subtract 2 to convert station numbers to 0-based indices
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Performance comparison
        System.out.printf("| %-10s | %-10s | %-10s |%n", "Iteration", "Tarjan", "Kosaraju");
        
        for(int i=0;i<10;i++) {
        	// Kosaraju's Algorithm
            long startTime = System.nanoTime();
            g.printSCCs();
            long endTime = System.nanoTime();
            long kosarajuTime = endTime - startTime;

            // Tarjan's Algorithm
            startTime = System.nanoTime();
            g.SCC();
            endTime = System.nanoTime();
            long tarjanTime = endTime - startTime;

            System.out.printf("| %-10d | %-10d | %-10d |%n", i + 1, tarjanTime, kosarajuTime);
       
        }
    }
}
