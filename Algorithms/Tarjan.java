package Algorithms;
import java.util.*;

public class Tarjan {

    // Tarjan's Algorithm :-  This algorithm is used for finding bridges(critical edges) in a graph.

    // What is bridge or critical edge?
    // -> Bridge or critical edge in a graph is the edge which can split the graph into two different components
    //    if we remove it from the edge.

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for(List<Integer> list : connections) {
            int u = list.get(0), v = list.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] tin = new int[n];
        int[] low = new int[n];
        boolean[] visited = new boolean[n];

        List<List<Integer>> bridges = new ArrayList<>();   // No need to create this bridges list if we have to return count only.
        dfs(0, -1, adj, visited, tin, low, bridges);
        return bridges;
        // return bridges_count;
    }

    private int timer = 1;
    // private bridges_count = 0;
    private void dfs(int node, int parent, List<List<Integer>> adj, boolean[] visited, int[] tin, int[] low, List<List<Integer>> bridges) {
        visited[node] = true;
        tin[node] = low[node] = timer;
        timer++;

        for(int nb_node : adj.get(node)) {
            if(nb_node == parent) continue;
            if(!visited[nb_node]) {
                dfs(nb_node, node, adj, visited, tin, low, bridges);
                low[node] = Math.min(low[node], low[nb_node]);

                if(low[nb_node] > tin[node]) {
                    bridges.add(Arrays.asList(nb_node, node));
                    // bridges_count++;
                }
            } else {
                low[node] = Math.min(low[node], low[nb_node]);
            }
        }
    }
}
