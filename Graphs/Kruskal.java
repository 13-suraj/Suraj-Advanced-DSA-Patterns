package Algorithms;

import Graphs.DisjointSet;

import java.sql.SQLOutput;
import java.util.*;

public class Kruskal {
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
        Arrays.sort(edges, Comparator.comparingInt((int[] a) -> a[2]));
        DisjointSet ds = new DisjointSet(n);

        int cost = 0;
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if(ds.findPar(u) != ds.findPar(v)) {
                ds.unionBySize(u, v);
                cost += wt;
            }
        }
        return cost;
    }

    public static List<int[]> minimumSpanningTree2(int n, int[][] edges) {
        List<int[]> mst = new ArrayList<>();
        Arrays.sort(edges, Comparator.comparingInt((int[] a) -> a[2]));
        DisjointSet ds = new DisjointSet(n);

        int cost = 0;
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if(ds.findPar(u) != ds.findPar(v)) {
                mst.add(edge);
                ds.unionBySize(u, v);
                cost += wt;
            }
        }
        return mst;
    }
}

//public class DisjointSet {
//    int[] rank;
//    int[] parent;
//    int[] size;
//
//    public DisjointSet(int n) {
//        rank = new int[n + 1];
//        parent = new int[n + 1];
//        size = new int[n + 1];
//        for(int i = 0; i <= n; i++) {
//            rank[i] = 0;
//            parent[i] = i;
//            size[i] = 1;
//        }
//    }
//
//    public int findPar(int node) {
//        if(node == parent[node])
//            return node;
//        parent[node] = findPar(parent[node]);;
//        return parent[node];
//    }
//
//
//    public void unionByRank(int u, int v) {
//        int ulp_u = findPar(u);
//        int ulp_v = findPar(v);
//
//        if(ulp_u == ulp_v) return;
//
//        if(rank[ulp_u] < rank[ulp_v])
//            parent[ulp_u] = ulp_v;
//        else if(rank[ulp_u] > rank[ulp_v])
//            parent[ulp_v] = ulp_u;
//        else {
//            parent[ulp_v] = ulp_u;
//            rank[ulp_u]++;
//        }
//    }
//
//    public void unionBySize(int u, int v) {
//        int ulp_u = findPar(u);
//        int ulp_v = findPar(v);
//
//        if(ulp_u == ulp_v) return;
//
//        if(size[ulp_u] < size[ulp_v]) {
//            parent[ulp_u] = ulp_v;
//            size[ulp_v] += size[ulp_u];
//        } else {
//            parent[ulp_v] = ulp_u;
//            size[ulp_u] += size[ulp_v];
//        }
//    }
//}