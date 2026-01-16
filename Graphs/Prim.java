package Algorithms;

import java.util.*;

public class Prim {
    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {
                {0, 1, 10},
                {0, 2, 6},
                {0, 3, 5},
                {1, 3, 15},
                {2, 3, 4}
        };

        System.out.println(minimumSpanningTree(4, edges));

        List<int[]> list = minimumSpanningTree2(4, edges);
        list.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    public static int minimumSpanningTree(int n, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];
            adj.get(u).add(new int[] {v, wt});
            adj.get(v).add(new int[] {u, wt});
        }

        boolean [] visited = new boolean[n];
        int minCost = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt((int[] a) -> a[0])
        );
        pq.offer(new int[] {0, 0});

        while(!pq.isEmpty()) {
            int[] temp = pq.poll();
            int node = temp[1];

            if(visited[node]) continue;
            visited[node] = true;

            int wt = temp[0];
            minCost += wt;

            for(int[] nb : adj.get(node)) {
                int nb_node = nb[0], nb_wt = nb[1];
                if(!visited[nb_node])
                    pq.offer(new int[]{nb_wt, nb_node});
            }
        }
        return minCost;
    }

    public static List<int[]> minimumSpanningTree2(int n, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];
            adj.get(u).add(new int[] {v, wt});
            adj.get(v).add(new int[] {u, wt});
        }

        boolean [] visited = new boolean[n];
        List<int[]> mst = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt((int[] a) -> a[0])
        );
        pq.offer(new int[] {0, 0, -1});

        while(!pq.isEmpty()) {
            int[] temp = pq.poll();
            int node = temp[1];

            if(visited[node]) continue;
            visited[node] = true;

            int wt = temp[0];
            int parent = temp[2];

            if(parent != -1) mst.add(new int[] {parent, node, wt});

            for(int[] nb : adj.get(node)) {
                int nb_node = nb[0], nb_wt = nb[1];
                if(!visited[nb_node])
                    pq.offer(new int[]{nb_wt, nb_node, node});
            }
        }
        return mst;
    }
}

