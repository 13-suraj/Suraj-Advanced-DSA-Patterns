package SegmentTree;

public class SGTree {
    int[] seg;
    int n;

    // Constructor
    public SGTree(int n) {
        this.n = n;
        seg = new int[4 * n + 1];
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

    // Query wrapper
    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }
    private int query(int ind, int low, int high, int l, int r) {
        if (r < low || l > high) return Integer.MAX_VALUE; // no overlap
        if (low >= l && high <= r) return seg[ind];        // complete overlap

        int mid = low + (high - low) / 2;
        int left = query(2 * ind + 1, low, mid, l, r);
        int right = query(2 * ind + 2, mid + 1, high, l, r);
        return Math.min(left, right);
    }

    // Update wrapper
    public void update(int i, int val) {
        update(0, 0, n - 1, i, val);
    }
    private void update(int ind, int low, int high, int i, int val) {
        if (low == high) {
            seg[ind] = val;
            return;
        }
        int mid = low + (high - low) / 2;
        if (i <= mid) update(2 * ind + 1, low, mid, i, val);
        else update(2 * ind + 2, mid + 1, high, i, val);
        seg[ind] = Math.min(seg[2 * ind + 1], seg[2 * ind + 2]);
    }



    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 3, 1, 7};
        SGTree st = new SGTree(arr.length);

        st.build(arr);

        System.out.println(st.query(1, 4)); // min in range [1,4] → 1
        st.update(3, 0);                    // arr[3] = 0
        System.out.println(st.query(1, 4)); // min in range [1,4] → 0
    }
}