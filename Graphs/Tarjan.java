package Algorithms;
import java.util.*;

public class Tarjan {

    // Tarjan's Algorithm :-  This algorithm is used for finding bridges(critical edges), Articulation Point(Node/Vertex) in a graph.

    // What is bridge or critical edge?
    // -> Bridge or critical edge in a graph is the edge which can split the graph into two different components
    //    if we remove it from the edge.

    // What is an Articulation Point?
    // -> Articulation Point in a graph is that node/vertex which can split the graph into two different components
    //    if we remove it from the edge.

    // There can be multiple bridges and articulation points in a graph. 


    // Bridges Detection
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




    
    // Articulatuion Points Detection
    public ArrayList<Integer> articulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        int[] tin = new int[V];
        int[] low = new int[V];
        boolean[] mark = new boolean[V];
        
        for(int i = 0; i < V; i++) {
            if(!visited[i]) dfs(i, -1, visited, tin, low, mark, adj);
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            if(mark[i]) ans.add(i);
        }
        
        if(ans.size() == 0) ans.add(-1);
        return ans;
    }
    
    private int timer = 1; 
    private void dfs(int node, int parent, boolean[] visited, int[] tin, int[] low, boolean[] mark, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;
        tin[node] = low[node] = timer;
        timer++;
        int child = 0;
        for(int nb_node : adj.get(node)) {
            if(nb_node == parent) continue;
            if(!visited[nb_node]) {
                dfs(nb_node, node, visited, tin, low, mark, adj);
                low[node] = Math.min(low[node], low[nb_node]);
                
                if(low[nb_node] >= tin[node] && parent != -1) {
                    mark[node] = true;
                }
                child++;
            } else {
                low[node] = Math.min(low[node], tin[nb_node]);
            }
        }
        if(child > 1 && parent == -1) {
            mark[node] = true;
        }
    }
}

