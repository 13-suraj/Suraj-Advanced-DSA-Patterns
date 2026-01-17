package Algorithms;
import  java.util.*;

public class Kosaraju {
    public int countStrongConnectedComponents(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < n; i++) {
            if(!visited[i]) dfs(i, adj, visited, stack);
        }

        ArrayList<ArrayList<Integer>> adjT = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < n; i++) {
            adjT.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < n; i++) {
            visited[i] = false;
            for(int it : adj.get(i)) {
                adjT.get(it).add(i);
            }
        }

        int scc = 0;
        while(!stack.isEmpty()) {
            int node = stack.pop();
            if(!visited[node]) {
                scc++;
                dfs2(node, adjT, visited);
            }
        }
        return scc;
    }

    private void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for(int nb_node : adj.get(node)) {
            if(!visited[nb_node]) {
                dfs(nb_node, adj, visited, stack);
            }
        }
        stack.add(node);
    }

    private void dfs2(int node, ArrayList<ArrayList<Integer>> adjT, boolean[] visited) {
        visited[node] = true;
        for(int nb_node : adjT.get(node)) {
            if(!visited[nb_node]) {
                dfs2(nb_node, adjT, visited);
            }
        }
    }




    public List<List<Integer>> getStronglyConnectedComponents(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        // Step 1: Get finishing times in a stack
        for(int i = 0; i < n; i++) {
            if(!visited[i]) dfs(i, adj, visited, stack);  // Normal dfs traversal
        }

        // Step 2: Transpose the graph
        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
        for(int i = 0; i < n; i++) adjT.add(new ArrayList<>());

        for(int i = 0; i < n; i++) {
            visited[i] = false; // Reset visited for Step 3
            for(int it : adj.get(i)) {
                adjT.get(it).add(i);
            }
        }

        // Step 3: Process stack and collect individual SCCs
        List<List<Integer>> result = new ArrayList<>();
        while(!stack.isEmpty()) {
            int node = stack.pop();
            if(!visited[node]) {
                List<Integer> currentSCC = new ArrayList<>();
                collectDFS(node, adjT, visited, currentSCC);
                result.add(currentSCC);
            }
        }
        return result;
    }

    private void collectDFS(int node, ArrayList<ArrayList<Integer>> adjT, boolean[] visited, List<Integer> currentSCC) {
        visited[node] = true;
        currentSCC.add(node);
        for(int neighbor : adjT.get(node)) {
            if(!visited[neighbor]) {
                collectDFS(neighbor, adjT, visited, currentSCC);
            }
        }
    }
}
