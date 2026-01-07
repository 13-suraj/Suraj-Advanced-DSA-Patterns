package Tree;

import java.util.Scanner;

public class BinaryTree {
    public static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
            Node left = null;
            Node right = null;
        }
    }

    private Node root;

    public void insert(Scanner scanner) {
        System.out.println("Enter the value of the root element: ");
        root = new Node(scanner.nextInt());
        insert(scanner, root);
    }

    private void insert(Scanner scanner, Node root) {
        System.out.println("Do you want to insert left of " + root.val);
        boolean left = scanner.nextBoolean();
        if(left) {
            System.out.println("Enter the value of the left of " + root.val);
            root.left = new Node(scanner.nextInt());
            insert(scanner, root.left);
        }

        System.out.println("Do you want to insert right of " + root.val);
        boolean right = scanner.nextBoolean();
        if(right) {
            System.out.println("Enter the value of the right of " + root.val);
            root.right = new Node(scanner.nextInt());
            insert(scanner, root.right);
        }
    }

    public void display() {
        display(root, "");
    }
    private void display(Node root, String indent) {
        if(root == null) return;
        System.out.println(root.val + indent);
        display(root.left, indent + "\t");
        display(root.right, indent + "\t");
    }
}
