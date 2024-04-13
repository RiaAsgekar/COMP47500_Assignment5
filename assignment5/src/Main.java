import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Graph for storing trip data
        Graph g = new Graph(84); // Assuming 84 stations
        
        // Path to the CSV file containing trip data
        String csvFile = "D:\\College\\Advanced Data Structures in Java\\assignment5\\src\\201508_trip_data.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isFirstLine = true; // Flag to skip header row
            // Read each line from the CSV file
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header row
                }
                
                // Split the line into parts using comma as the delimiter
                String[] parts = line.split(",");

                // Extract start and end stations
                int startStation = Integer.parseInt(parts[4]); // Start terminal station number column index
                int endStation = Integer.parseInt(parts[7]); // End terminal station number column index
                // Add edge between start and end stations
                g.addEdge(startStation - 2, endStation - 2); // Subtract 2 to convert station numbers to 0-based indices
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print table header for performance comparison
        System.out.printf("| %-10s | %-10s | %-10s |%n", "Iteration", "Tarjan", "Kosaraju");
        
        // Run both algorithms multiple times and measure their execution time
        for(int i = 0; i < 10; i++) {
            // Kosaraju's Algorithm
            Kosaraju obj1 = new Kosaraju(g);
            long startTime = System.nanoTime();
            obj1.printSCCs(); // Find SCCs using Kosaraju's algorithm
            long endTime = System.nanoTime();
            long kosarajuTime = endTime - startTime;

            // Tarjan's Algorithm
            Tarjan obj2 = new Tarjan(g);
            startTime = System.nanoTime();
            obj2.SCC(); // Find SCCs using Tarjan's algorithm
            endTime = System.nanoTime();
            long tarjanTime = endTime - startTime;

            // Print execution time for both algorithms
            System.out.printf("| %-10d | %-10d | %-10d |%n", i + 1, tarjanTime, kosarajuTime);
        }
    }
}
