package Algorithms;

import java.util.List;

public class FloydWarshall {
    public static int[][] allNodesShortestPaths(int n, List<List<int[]>> adj) {
        int[][] dist = new int[n][n];
        for(int i = 0; i < n;  i++) {
            for(int j = 0; j < n; j++) {
                if(i != j) dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int[] j : adj.get(i)) {
                dist[i][j[0]] = j[1];
            }
        }

        for(int via = 0; via < n; via++) {
            for(int i = 0; i < n; i++) {
                if(dist[i][via] == Integer.MAX_VALUE) continue;
                for(int j = 0; j < n; j++) {
                    if(dist[via][j] == Integer.MAX_VALUE) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
                }
            }
        }

        // Checking for Negative Cycles
        for(int i = 0; i < n; i++) {
            if(dist[i][i] < 0) return null;
        }
        return dist;
    }
}
