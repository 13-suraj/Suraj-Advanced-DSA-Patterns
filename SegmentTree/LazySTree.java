package SegmentTree;

public class LazySTree {
    int[] seg, lazy;
    int n;

    // Constructor
    public LazySTree(int n) {
        this.n = n;
        seg = new int[4 * n + 1];
        lazy = new int[4 * n + 1];
    }

    // Build wrapper
    public void build(int[] arr) {
        build(0, 0, n - 1, arr);
    }

    private void build(int ind, int low, int high, int[] arr) {
        if (low == high) {
            seg[ind] = arr[low];
            return;
        }
        int mid = low + (high - low) / 2;
        build(2 * ind + 1, low, mid, arr);
        build(2 * ind + 2, mid + 1, high, arr);
        seg[ind] = Math.min(seg[2 * ind + 1], seg[2 * ind + 2]);
    }

    // Range update wrapper
    public void updateRange(int l, int r, int val) {
        updateRange(0, 0, n - 1, l, r, val);
    }

    private void updateRange(int ind, int low, int high, int l, int r, int val) {
        // First, resolve any pending lazy updates
        if (lazy[ind] != 0) {
            seg[ind] += lazy[ind];
            if (low != high) {
                lazy[2 * ind + 1] += lazy[ind];
                lazy[2 * ind + 2] += lazy[ind];
            }
            lazy[ind] = 0;
        }

        // No overlap
        if (r < low || l > high) return;

        // Complete overlap
        if (low >= l && high <= r) {
            seg[ind] += val;
            if (low != high) {
                lazy[2 * ind + 1] += val;
                lazy[2 * ind + 2] += val;
            }
            return;
        }

        // Partial overlap
        int mid = low + (high - low) / 2;
        updateRange(2 * ind + 1, low, mid, l, r, val);
        updateRange(2 * ind + 2, mid + 1, high, l, r, val);
        seg[ind] = Math.min(seg[2 * ind + 1], seg[2 * ind + 2]);
    }

    // Range query wrapper
    public int queryRange(int l, int r) {
        return queryRange(0, 0, n - 1, l, r);
    }

    private int queryRange(int ind, int low, int high, int l, int r) {
        // Resolve pending lazy updates
        if (lazy[ind] != 0) {
            seg[ind] += lazy[ind];
            if (low != high) {
                lazy[2 * ind + 1] += lazy[ind];
                lazy[2 * ind + 2] += lazy[ind];
            }
            lazy[ind] = 0;
        }

        // No overlap
        if (r < low || l > high) return Integer.MAX_VALUE;

        // Complete overlap
        if (low >= l && high <= r) return seg[ind];

        // Partial overlap
        int mid = low + (high - low) / 2;
        int left = queryRange(2 * ind + 1, low, mid, l, r);
        int right = queryRange(2 * ind + 2, mid + 1, high, l, r);
        return Math.min(left, right);
    }



    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 3, 1, 7};
        LazySTree st = new LazySTree(arr.length);

        st.build(arr);

        System.out.println(st.queryRange(1, 4)); // min in [1,4] → 1
        st.updateRange(2, 5, 3);                 // add +3 to range [2,5]
        System.out.println(st.queryRange(1, 4)); // min in [1,4] → 4
    }
}
