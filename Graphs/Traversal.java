package Graphs;

import java.util.*;

public class Traversal {
    public static void main(String[] args) {
        int n = 9, m = 9;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        adj.get(1).add(2);
        adj.get(1).add(6);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(2).add(4);
        adj.get(6).add(1);
        adj.get(6).add(7);
        adj.get(6).add(9);
        adj.get(3).add(2);
        adj.get(4).add(2);
        adj.get(4).add(5);
        adj.get(7).add(6);
        adj.get(7).add(8);
        adj.get(9).add(6);
        adj.get(5).add(4);
        adj.get(5).add(8);
        adj.get(8).add(5);
        adj.get(8).add(7);

//        for(int i = 1; i <= n; i++) {
//            for(int j = 0; j < adj.get(i).size(); j++) {
//                System.out.print(adj.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }

        System.out.println((Arrays.toString(bfs(adj, n, 1).toArray())));

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        List<Integer> dfs = new ArrayList<>();
        dfs(1, visited, adj, dfs);
        System.out.println((Arrays.toString(dfs.toArray())));
    }

    public static void dfs(int start, boolean[] visited, ArrayList<ArrayList<Integer>> adj, List<Integer> list) {
        visited[start] = true;
        list.add(start);

        for(int neighbor : adj.get(start)) {
            if(!visited[neighbor]) {
                dfs(neighbor, visited, adj, list);
            }
        }
    }

    public static List<Integer> bfs(ArrayList<ArrayList<Integer>> adj, int n, int start) {
        List<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        while(!q.isEmpty()) {
            int node = q.poll();
            bfs.add(node);
            for(int neighbor : adj.get(node)) {
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
        return bfs;
    }



}
