package edu.grinnell.csc207.trees;

/**
 * The driver for the Trees program.
 */
public class Trees {
    /**
     * The main entry point of the program.
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");

        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.insert(1);
        tree.insert(0);
        tree.insert(7);
        tree.insert(2);
        tree.insert(6);
        System.out.println(tree.contains(0));

    }
}
