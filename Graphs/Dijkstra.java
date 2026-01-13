package Algorithms;

import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {{0, 1, 4}, {0, 2, 8}, {1, 4, 6}, {2, 3, 2}, {3, 4, 10}};

        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
            adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        System.out.println(Arrays.toString(singleSourceShortestPath(adj, V, 0)));
        System.out.println(shortestPath(adj, V, 1));
    }


    public static List<Integer> shortestPath(List<List<int[]>> adj, int V, int source) {
//        int[] distance = new int[V + 1];
//        int[] parents = new int[V];
        int[] distance = new int[V];
        int[] parents = new int[V];
        for(int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
            parents[i] = i;
        }
        distance[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt((int[] a) -> a[1])
        );
        pq.offer(new int[]{source, 0});

        while(!pq.isEmpty()) {
            int[] temp = pq.poll();
            int node = temp[0];
            int dist = temp[1];
            for(int[] nb : adj.get(node)) {
                int nb_node = nb[0];
                int nb_dist = nb[1];
                if(distance[nb_node] > dist + nb_dist) {
                    distance[nb_node] = dist + nb_dist;
                    pq.offer(new int[]{nb_node, distance[nb_node]});
                    parents[nb_node] = node;
                }
            }
        }
        if(distance[V - 1] == Integer.MAX_VALUE) {
            return Arrays.asList(-1);
        }

        List<Integer> path =  new ArrayList<>();
        int node = V - 1;
        while(parents[node] != node) {
            path.add(node);
            node = parents[node];
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }

    public static int[] singleSourceShortestPath(List<List<int[]>> adj, int V, int source) {
//        int[] distance = new int[V + 1];
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt((int[] a) -> a[1])
        );
        pq.offer(new int[]{source, 0});

        while(!pq.isEmpty()) {
            int[] temp = pq.poll();
            int node = temp[0];
            int dist = temp[1];
            for(int[] nb : adj.get(node)) {
                int nb_node = nb[0];
                int nb_dist = nb[1];
                if(distance[nb_node] > dist + nb_dist) {
                    distance[nb_node] = dist + nb_dist;
                    pq.offer(new int[]{nb_node, distance[nb_node]});
                }
            }
        }
        return distance;
    }
}
