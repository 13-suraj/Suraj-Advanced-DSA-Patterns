package Algorithms;

import java.util.*;
public class Kahn {
    public static void main(String[] args) {
        int[][] edges = {{1,0},{2,0},{3,0}};
        int n = 4;

        System.out.println(topoSortUsingBFS(n, edges));
    }

    public static List<Integer> topoSortUsingBFS(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        int[] inDegree = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j : adj.get(i)) {
                inDegree[j]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(inDegree[i] == 0) q.offer(i);
        }

        List<Integer> topoOrder = new ArrayList<>();
        while(!q.isEmpty()) {
            int node = q.poll();
            topoOrder.add(node);
            for(int nb_node : adj.get(node)) {
                inDegree[nb_node]--;
                if(inDegree[nb_node] == 0) q.offer(nb_node);
            }
        }
        return topoOrder;
    }
}
