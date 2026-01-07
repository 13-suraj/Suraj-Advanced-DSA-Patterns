package Tree;

import java.util.ArrayList;
import java.util.List;
import Tree.BinaryTree.Node;

public class RecursiveDFSTraversal {
    private ArrayList<Integer> res1;
    private ArrayList<Integer> res2;
    private ArrayList<Integer> res3;
    private ArrayList<ArrayList<Integer>> ans;

    public List<ArrayList<Integer>> inorderTraversal(Node root) {
        res1 = new ArrayList<>();
        res2 = new ArrayList<>();
        res3 = new ArrayList<>();
        ans = new ArrayList<>();
        inorder(root);
        preorder(root);
        postorder(root);
        ans.add(res1);
        ans.add(res2);
        ans.add(res3);
        return ans;
    }

    private void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        res1.add(node.val);
        inorder(node.right);
    }

    private void preorder(Node node) {
        if (node == null) return;
        res2.add(node.val);
        preorder(node.left);
        preorder(node.right);
    }

    private void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        res3.add(node.val);
    }
}
