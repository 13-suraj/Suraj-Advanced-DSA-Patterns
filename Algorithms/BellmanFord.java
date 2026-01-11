package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFord {
    // Bellman-Ford Algorithm is used for Shortest Path of a Single source to all other nodes like Dijkstra Algorithm does.
    // But Dijkstra fails if negative weights or negative cycles is present in a graph.
    // Bellman-Ford algorithm works for negative weights, and it also detects that if there any negative cycle is present or not.

    // Time Complexity = O(V x E)    [V = No. of nodes, E = No. of edges]
    // Space Complexity = O(V)

    // Use adjacency list for Dijkstra, TopoSort, BFS, DFS
    // Use 2D-array of edges or undirectedEdges for Bellman-Ford
    // Although I provided 3rd algorithm which uses adjacency list for Bellman-Ford in case if you need.


    // Used given edges[][] 2D array for Directed Weighted Graph
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e8);
        dist[src] = 0;

        for(int i = 0; i < V - 1; i++) {
            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if(dist[u] != 1e8 && dist[v] > dist[u] + wt) {
                    dist[v] = dist[u] + wt;
                }
            }
        }
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if(dist[u] != 1e8 && dist[v] > dist[u] + wt) {
                return new int[]{-1};
            }
        }
        return dist;
    }


    // Used edges[][] --> undirectedEdges[][] for Undirected Weighted Graph
    public int[] bellmanFord2(int V, int[][] edges, int src) {
        List<int[]> edgeList = new ArrayList<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            // Add both directions
            edgeList.add(new int[]{u, v, wt});
            edgeList.add(new int[]{v, u, wt});
        }
        // Convert back to 2D array if needed
        int[][] undirectedEdges = edgeList.toArray(new int[edgeList.size()][]);

        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e8);
        dist[src] = 0;

        for(int i = 0; i < V - 1; i++) {
            for(int[] edge : undirectedEdges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if(dist[u] != 1e8 && dist[v] > dist[u] + wt) {
                    dist[v] = dist[u] + wt;
                }
            }
        }
        for(int[] edge : undirectedEdges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if(dist[u] != 1e8 && dist[v] > dist[u] + wt) {
                return new int[]{-1};
            }
        }
        return dist;
    }


    // Used Adjacency List for Undirected Weighted Graph
    public int[] bellmanFord3(int V, int[][] edges, int src) {
        // Step 1: Build adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add(new int[]{v, wt});
            adj.get(v).add(new int[]{u, wt});
        }
        // Step 2: Initialize distances
        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e8);
        dist[src] = 0;

        // Step 3: Relax edges V-1 times
        for (int i = 0; i < V - 1; i++) {
            for (int u = 0; u < V; u++) {
                for (int[] nbr : adj.get(u)) {
                    int v = nbr[0];
                    int wt = nbr[1];
                    if (dist[u] != 1e8 && dist[v] > dist[u] + wt) {
                        dist[v] = dist[u] + wt;
                    }
                }
            }
        }
        // Step 4: Check for negative cycles
        for (int u = 0; u < V; u++) {
            for (int[] nbr : adj.get(u)) {
                int v = nbr[0];
                int wt = nbr[1];
                if (dist[u] != 1e8 && dist[v] > dist[u] + wt) {
                    return new int[]{-1};
                }
            }
        }
        return dist;
    }
}