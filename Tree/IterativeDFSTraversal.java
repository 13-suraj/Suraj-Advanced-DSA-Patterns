package Tree;

import java.util.*;

import Tree.BinaryTree.Node;

public class IterativeDFSTraversal {
    public List<Integer> inorderTraversal(Node root) {
        List<Integer> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }

    public List<Integer> preorderTraversal(Node root) {
        List<Integer> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                res.add(cur.val);
                stack.push(cur.right);
                cur = cur.left;
            } else {
                cur = stack.pop();
            }
        }
        return res;
    }

    public List<Integer> postorderTraversal(Node root) {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            Node node = stack.peek();
            if (node.right != null && node.right != prev) root = node.right;
            else {
                result.add(node.val);
                prev = stack.pop();
            }
        }
        return result;
    }
}
