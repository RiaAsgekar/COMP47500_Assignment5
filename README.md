# COMP47500 Assignment 5

The main objective of this assignment seems to be comparing Tarjan’s and Kosaraju’s algorithms for graph processing. These algorithms are widely used for various graph-related tasks, such as finding strongly connected components. This comparison likely involves assessing factors like time complexity, memory usage, and overall efficiency to determine the strengths and weaknesses of each algorithm in different scenarios.

Content: 

Kosaraju.java: It implements Kosaraju's algorithm for finding Strongly Connected Components (SCCs) in a directed graph. 

Tarjan.java: This file implements Tarjan's algorithm for finding SCCs in a directed graph. The main functionality of the algorithm is encapsulated in two methods SSCUtil and SCC, which together allow efficient identification of SCCs.

Graph.java: It serves as a foundational building block for creating and manipulating directed graphs in Java using an adjacency list representation.

Main.java: It reads trip data from a CSV file 201508_trip_data.csv. For each trip in the CSV file, the start and end terminal stations are extracted and used to add an edge between the corresponding vertices in the graph. It then processes trip data, constructs a graph representing the trip connections between stations, and compares the performance of two algorithms for finding Strongly Connected Components in the graph.


Usage: Simply run the Main class. It will read data from the csv file, construct graphs and evaluate the performances of Kosaraju and Tarjan's algorithms. 

This README provides an overview of the provided code files, their functionalities, and the purpose of the experimentation conducted within the Main.java class.





Overall, this repository provides an environment for exploring and analyzing graph algorithms, particularly focusing on the comparison between Tarjan’s and Kosaraju’s algorithms. The provided files serve as the foundation for implementing and executing these comparisons within a Java environment.
