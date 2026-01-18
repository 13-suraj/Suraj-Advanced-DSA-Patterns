package Graphs;

import java.util.*;
public class TopoSort {

    // Topological Sort: Sort Vertex based on their dependencies
    // Works only on Directed Acyclic Graph (DAG)

    public static void main(String[] args) {
        int[][] edges = {{1,0},{2,0},{3,0}};
        int n = 4;

        System.out.println(topoSort(n, edges));
    }

    public static List<Integer> topoSort(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, adj, visited, stack);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while(!stack.isEmpty()) {
            topoOrder.add(stack.pop());
        }
        return topoOrder;
    }

    private static void dfs(int node, List<List<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for(int nb_node : adj.get(node)) {
            if(!visited[nb_node]) {
                dfs(nb_node, adj, visited, stack);
            }
        }
        stack.push(node);
    }
}
