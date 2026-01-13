package Graphs;

public class DisjointSet {
    int[] rank;
    int[] parent;
    int[] size;

    public DisjointSet(int n) {
        for(int i = 0; i <= n; i++) {
            rank = new int[n + 1];
            parent = new int[n + 1];
            size = new int[n + 1];
            rank[i] = 0;
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int findPar(int node) {
        if(node == parent[node])
            return node;
        parent[node] = findPar(parent[node]);;
        return parent[node];
    }


    public void unionByRank(int u, int v) {
        int ulp_u = findPar(u);
        int ulp_v = findPar(v);

        if(ulp_u == ulp_v) return;

        if(rank[ulp_u] < rank[ulp_v])
            parent[ulp_u] = ulp_v;
        else if(rank[ulp_u] > rank[ulp_v])
            parent[ulp_v] = ulp_u;
        else {
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findPar(u);
        int ulp_v = findPar(v);

        if(ulp_u == ulp_v) return;

        if(size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        } else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
}
