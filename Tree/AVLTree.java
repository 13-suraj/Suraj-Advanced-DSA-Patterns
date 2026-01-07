package Tree;

public class AVLTree {
    public class Node {
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

    public int height() {
        return height(root);
    }

    public int height(Node node) {
        if(node == null) return -1;
        return node.height;
    }

    public boolean isEmpty(Node root) {
        return root == null;
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node root, int value) {
        if(root == null) {
            Node node = new Node(value);
            return node;
        }
        if(value < root.val) root.left = insert(root.left, value);
        if(value > root.val) root.right = insert(root.right, value);

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        return rotate(root);
    }

    private Node rotate(Node root) {
        if(height(root.left) - height(root.right) > 1) {   // Left Heavy
            if(height(root.left.left) - height(root.left.right) > 0) {
                return rightRotate(root);
            }
            if(height(root.left.left) - height(root.left.right) < 0) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        if(height(root.left) - height(root.right) < -1) {   // Right Heavy
            if(height(root.right.left) - height(root.right.right) < 0) {
                return leftRotate(root);
            }
            if(height(root.right.left) - height(root.right.right) > 0) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }

        return root;
    }

    private Node leftRotate(Node root) {
        Node c = root.right;
        Node t = c.left;

        c.left = root;
        root.right = t;

        root.height = Math.max(height(root.left), height(root.right) + 1);
        c.height = Math.max(height(c.left), height(c.right) + 1);

        return c;
    }

    private Node rightRotate(Node root) {
        Node c = root.left;
        Node t = c.right;

        c.right = root;
        root.left = t;

        root.height = Math.max(height(root.left), height(root.right) + 1);
        c.height = Math.max(height(c.left), height(c.right) + 1);

        return c;
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
}

