public class Main {

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        // Mr. Albinson, I made a method that adds values from an area because I didn't want to write so much
        // it is on lines 32 - 38 in BinaryTree.java
        bt = bt.add(new int[]{6,4,3,7,8,1,2,9,5});
        System.out.println(bt.containsNode(6));
        bt.delete(6);
        System.out.println(bt.containsNode(6));
        bt.add(6);
        System.out.println(bt.containsNode(6));
        bt.traverseInOrder();
        System.out.println();
        bt.traversePreOrder();
        System.out.println();
        bt.traversePostOrder();
    }
}
