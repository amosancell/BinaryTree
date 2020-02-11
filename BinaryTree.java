import java.util.Queue;
import java.util.LinkedList;

public class BinaryTree {

    Node root;

    /*
     rules for insertion starting from the root node:
        *if the new node's value is lower than the current node's, we go to the left child
        *if the new node's value is greater than the current node's, we go to the right child
        *when the current node is null, we've reached a leaf node and we can insert the new node in that position
     */
    private Node addRecursive(Node current, int value) {
        if(current == null) {
            return new Node(value);
        }
        if(value < current.value) {
            current.left = addRecursive(current.left,value);
        }
        else if(value > current.value) {
            current.right = addRecursive(current.right,value);
        }
        return current;
    }

    // add a value to a binary tree
    public void add(int value) {
        root = addRecursive(root,value);
    }

    // add an array of values in order
    public BinaryTree add(int[] vals) {
        BinaryTree bt = new BinaryTree();
        for(int val : vals) {
            bt.add(val);
        }
        return bt;
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if(current == null) {
            return false;
        }
        if(value == current.value) {
            return true;
        }
        return value < current.value ? containsNodeRecursive(current.left,value) : containsNodeRecursive(current.right, value);
    }

    // checks if a value is in a binary tree
    public boolean containsNode(int value) {
        return containsNodeRecursive(root,value);
    }

    // helper which finds the smallest value in the binary tree
    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    private Node deleteRecursive(Node current, int value) {
        if(current == null) {
            return null;
        }
        if(value == current.value) {
            if(current.left == null && current.right == null) {
                return null;
            }
            if(current.right == null) {
                return current.left;
            }
            if(current.left == null) {
                return current.right;
            }
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right,smallestValue);
            return current;
        }
        if(value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right,value);
        return current;
    }

    // method to delete a value from a binary tree
    public void delete(int value) {
        root = deleteRecursive(root,value);
    }

    // traverse a binary tree in order
    public void traverseInOrderHelper(Node node) {
        if(node != null) {
            traverseInOrderHelper(node.left);
            System.out.print(" " + node.value);
            traverseInOrderHelper(node.right);
        }
    }
    public void traverseInOrder() {
        traverseInOrderHelper(root);
    }

    /* traverse the tree going:
        * first visit the root
        * then visit the left subtree
        * then visit the right subtree
     */
    public void traversePreOrderHelper(Node node) {
        if(node != null) {
            System.out.print(" " + node.value);
            traversePreOrderHelper(node.left);
            traversePreOrderHelper(node.right);
        }
    }
    public void traversePreOrder() {
        traversePreOrderHelper(root);
    }

    // same as above pre order but visit right subtree first
    public void traversePostOrderHelper(Node node) {
        if(node != null) {
            traversePostOrderHelper(node.left);
            traversePostOrderHelper(node.right);
            System.out.print(" " + node.value);
        }
    }
    public void traversePostOrder() {
        traversePostOrderHelper(root);
    }

    /* breadth first search
        * visits every node on each level before moving down
     */
    public void traverseLevelOrder() {
        if(root == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while(!nodes.isEmpty()) {
            Node node = nodes.remove();
            System.out.print(" " + node.value);
            if(node.left != null) {
                nodes.add(node.left);
            }
            if(node.right != null) {
                nodes.add(node.right);
            }
        }
    }


    // the tests that the tutorial showed
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        // the check to make sure containsNode works
        System.out.println(true);
        System.out.println(true);
        System.out.println(false);
        System.out.println(bt.containsNode(6));
        System.out.println(bt.containsNode(4));
        System.out.println(bt.containsNode(1));

        // the check to make sure delete works
        System.out.println("\n\n"+true);
        System.out.println(false);
        System.out.println(bt.containsNode(9));
        bt.delete(9);
        System.out.println(bt.containsNode(9));

        // check the traversing algorithms
        System.out.println("\n\n");
        System.out.print("in order: ");
        bt.traverseInOrder();
        System.out.print("\npre order: ");
        bt.traversePreOrder();
        System.out.print("\npost order: ");
        bt.traversePostOrder();
        System.out.print("\nbreadth first traversal: ");
        bt.traverseLevelOrder();
    }

}
