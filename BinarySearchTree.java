import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    private class TreeNode {
        int value;
        TreeNode parent;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            value = val;
            parent = left = right = null;
        }
    }

    private TreeNode root = null;

    BinarySearchTree() {

    }

    BinarySearchTree(int rootValue) {
        this.root = new TreeNode(rootValue);
        root.parent = null;
        root.left = null;
        root.right = null;
    }

    private void insertNode(TreeNode current, int newValue) {
        if (newValue == current.value)
            return;
        if (newValue < current.value) {
            if (current.left != null) {
                insertNode(current.left, newValue);
            } else {
                current.left = new TreeNode(newValue);
                current.left.parent = current;
            }
        } else {
            if (current.right != null) {
                insertNode(current.right, newValue);
            } else {
                current.right = new TreeNode(newValue);
                current.right.parent = current;
            }
        }
    }

    public void insertTreeNode(int newValue) {
        if (this.root == null)
            this.root = new TreeNode(newValue);
        else
            insertNode(this.root, newValue);
    }

    private TreeNode findValue(int target) {
        TreeNode current = this.root;
        while (current != null && target != current.value) {
            if (target < current.value)
                current = current.left;
            else
                current = current.right;
        }
        return current;
    }

    private void removeNode(TreeNode node) {
        if (root == null || node == null)
            return;

        // Deleting a leaf node
        if (node.left == null && node.right == null) {
            if (node.parent == null)
                this.root = null;
            else if (node.parent.left == node)
                node.parent.left = null;
            else
                node.parent.right = null;
            return;
        }

        // Deleting a node with one child
        if (node.left == null || node.right == null) {
            TreeNode child = node.left;
            if (node.left == null)
                child = node.right;

            child.parent = node.parent;
            if (node.parent == null)
                root = child;
            else if (node.parent.left == node)
                node.parent.left = child;
            else
                node.parent.right = child;
            return;
        }

        // Deleting a node with two children
        TreeNode successor = node.right;
        while (successor != null)
            successor = successor.left;

        removeNode(successor);

        if (node.parent == null)
            root = successor;
        else if (node.parent.left == node)
            node.parent.left = successor;
        else
            node.parent.right = successor;

        successor.parent = node.parent;

        successor.left = node.left;
        node.left.parent = successor;

        successor.right = node.right;
        if (node.right != null)
            node.right.parent = successor;

    }

    public void removeTreeNode(int value) {
        TreeNode node = findValue(value);
        removeNode(node);
    }

    public List<Integer> inorderTraversal() {
        class Wrapper {
            ArrayList<Integer> values = new ArrayList<>();

            public void wrapperInorderTraversal(TreeNode node) {
                if (node != null) {
                    wrapperInorderTraversal(node.left);
                    this.values.add(node.value);
                    wrapperInorderTraversal(node.right);
                }
            }
        }
        Wrapper runner = new Wrapper();
        runner.wrapperInorderTraversal(this.root);
        return runner.values;
    }

}