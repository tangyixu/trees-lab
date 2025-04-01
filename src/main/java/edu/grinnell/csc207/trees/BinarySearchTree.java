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
    public BinarySearchTree() {
    }

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
 * @return true if the tree contains the value
 */
public boolean contains(T value) {
        return containsH(root, value);
    }

    private boolean containsH(Node<T> cur, T value) {
        if (cur == null) {
            return false;
        }

        int cmp = value.compareTo(cur.value);

        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return containsH(cur.left, value);
        } else {
            return containsH(cur.right, value);
        }
    }

    ///// Part 3: Pretty Printing

    /**
     * @return a string representation of the tree obtained via an pre-order traversal in the form:
     *         "[v0, v1, ..., vn]"
     */
    public String toStringPreorder() {
        StringBuffer buf = new StringBuffer("[");
        List<T> result = this.toListPreorder();
        if (!result.isEmpty()) {
            buf.append(result.get(0));
            for (int i = 1; i < result.size(); i++) {
                buf.append(", ");
                buf.append(result.get(i));
            }
        }
        buf.append("]");
        return buf.toString();
    }

    ///// Part 4: Deletion

    /*
     * The three cases of deletion are:
     1. A node that has no left or right
     1                      1
        2           ->          2
            3
     2. A node that has left or right
     1                      1
        2           ->          3
            3                 4     5
          4    5
     3. A node that has both left or right
     1                      1
        2           ->          2
            3                 4     5
          4    5

     *
     */

    /**
     * Modifies the tree by deleting the first occurrence of <code>value</code> found in the tree.
     *
     * @param value the value to delete
     */
    public void delete(T value) {
        deleteh(value, root);
    }

    public Node<T> deleteh(T value, Node<T> cur) {
        if (cur == null) {
            return null;
        }

        if (value.compareTo(cur.value) < 0) {
            cur.left = deleteh(value, cur.left);
        } else if (value.compareTo(cur.value) > 0) {
            cur.right = deleteh(value, cur.right);
        } else {
            if (cur.left == null && cur.right == null) {
                return null;
            } else if (cur.left == null) {
                return cur.right;
            } else if (cur.right == null) {
                return cur.left;
            } else {
                cur.value = max();
                cur.left = deleteh(cur.value, cur.left);
            }
        }
        return cur;
    }

    public T max() {
        Node<T> cur = root;
        if (cur == null) {
            return null;
        }
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.value;
    }

}
