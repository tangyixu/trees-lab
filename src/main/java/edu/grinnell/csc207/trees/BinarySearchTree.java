package edu.grinnell.csc207.trees;

import java.util.List;
import java.util.ArrayList;

/**
 * A binary tree that satisifies the binary search tree invariant.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    ///// From the reading

    /**
     * A node of the binary search tree.
     */
    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        /**
         * @param value the value of the node
         * @param left the left child of the node
         * @param right the right child of the node
         */
        Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * @param value the value of the node
         */
        Node(T value) {
            this(value, null, null);
        }
    }

    private Node<T> root;

    /**
     * Constructs a new empty binary search tree.
     */
    public BinarySearchTree() {}

    private int sizeH(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeH(node.left) + sizeH(node.right);
        }
    }

    /**
     * @return the number of elements in the tree
     */
    public int size() {
        return sizeH(root);
    }

    private Node<T> insertH(T value, Node<T> root) {
        if (root == null) {
            return new Node<T>(value);
        } else {
            if (value.compareTo(root.value) < 0) {
                root.left = insertH(value, root.left);
            } else {
                root.right = insertH(value, root.right);
            }
            return root;
        }
    }

    /**
     * @param value the value to add to the tree
     */
    public void insert(T value) {
        root = insertH(value, root);
    }

    ///// Part 1: Traversals

    /**
     * @return the elements of this tree collected via an in-order traversal
     */
    public List<T> toListInorder() {
        List<T> result = new ArrayList<>();
        Node<T> cur = this.root;
        inorderHelper(result, cur);
        return result;
    }

    public void inorderHelper(List<T> result, Node<T> cur) {
        if (cur == null) {
            return;
        }
        inorderHelper(result, cur.left);
        result.add(cur.value);
        inorderHelper(result, cur.right);
    }

    /**
     * @return the elements of this tree collected via a pre-order traversal
     */
    public List<T> toListPreorder() {
        List<T> result = new ArrayList<>();
        Node<T> cur = this.root;
        preorderHelper(result, cur);
        return result;
    }

    public void preorderHelper(List<T> result, Node<T> cur) {
        if (cur == null) {
            return;
        }
        result.add(cur.value);
        preorderHelper(result, cur.left);
        preorderHelper(result, cur.right);
    }

    /**
     * @return the elements of this tree collected via a post-order traversal
     */
    public List<T> toListPostorder() {
        List<T> result = new ArrayList<>();
        Node<T> cur = this.root;
        postorderHelper(result, cur);
        return result;
    }

    public void postorderHelper(List<T> result, Node<T> cur) {
        if (cur == null) {
            return;
        }
        postorderHelper(result, cur.left);
        postorderHelper(result, cur.right);
        result.add(cur.value);
    }

    ///// Part 2: Contains

    /**
     * @param value the value to search for
     * @return true iff the tree contains <code>value</code>
     */
    public boolean contains(T value) {
        boolean result = containsh(root, value);
        System.out.println(result);
        return result;
    }

    public boolean containsh(Node<T> cur, T value) {
        System.out.println("cur" + cur.value);
        System.out.println("if" + value.compareTo(cur.value));
        if (cur == null) {
            return false;
        }
        if (value.compareTo(cur.value) == 0) {
            System.out.println("return true");
            return true;
        } else {
            if (cur.left != null) {
                if (!containsh(cur.left, value))
            }
            if (cur.right != null) {
                return containsh(cur.right, value);
            }

        }

    }

    ///// Part 3: Pretty Printing

    /**
     * @return a string representation of the tree obtained via an pre-order traversal in the form:
     *         "[v0, v1, ..., vn]"
     */
    public String toStringPreorder() {
        throw new UnsupportedOperationException();
    }

    ///// Part 4: Deletion

    /*
     * The three cases of deletion are: 1. (TODO: fill me in!) 2. (TODO: fill me in!) 3. (TOOD: fill
     * me in!)
     */

    /**
     * Modifies the tree by deleting the first occurrence of <code>value</code> found in the tree.
     *
     * @param value the value to delete
     */
    public void delete(T value) {
        throw new UnsupportedOperationException();
    }
}
