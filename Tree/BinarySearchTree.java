package Tree;

public class BinarySearchTree {
    private static class Node {
        private int val;
        private Node left;
        private Node right;
        private int height;

        Node(int val) {
            this.val = val;
        }

        private int getValue() {
            return val;
        }
    }

    private Node root;

    public int height(Node node) {
        if(node == null) return -1;
        return node.height;
    }

    public boolean isEmpty(Node root) {
        return root == null;
    }

    private void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node root, int value) {
        if(root == null) {
            Node node = new Node(value);
            return node;
        }
        if(value < root.getValue()) root.left = insert(root.left, value);
        if(value > root.getValue()) root.right = insert(root.right, value);

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        return root;
    }

    public boolean balanced() {
        return balanced(root);
    }

    private boolean balanced(Node root) {
        if(root == null) return true;
        return Math.abs(height(root.left) - height(root.right)) <= 1 && balanced(root.right) && balanced(root.right);
    }

    public void populate(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            this.insert(nums[i]);
        }
    }

    public void display() {
        display(this.root, "Root Node: ");
    }

    private void display(Node root, String details) {
        if(root == null) return;
        System.out.println(details + root.val);
        display(root.left, "Left Child of " + root.val + " : ");
        display(root.right, "Right Child of " + root.val + " : ");
    }

//    public void populate(int[] nums) {
//        insertion(0, nums.length - 1, nums);
//    }

    public void insertionSorted(int l, int r, int[] nums) {
        if (l > r) return;
        int mid = l + (r - l) / 2;
        this.insert(nums[mid]);
        insertionSorted(l, mid, nums);
        insertionSorted(mid + 1, r, nums);
    }
}
