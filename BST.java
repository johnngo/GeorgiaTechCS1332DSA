
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is already in
     * the tree, then nothing should be done (the duplicate shouldn't get added, and
     * size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null to BST.");
        }

        root = _add(root, data);
    }

    private BSTNode<T> _add(BSTNode<T> node, T data) {
        if (node == null) {
            size++;
            return new BSTNode<T>(data);
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(_add(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(_add(node.getRight(), data));
        }

        return node;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider: 1: The node containing the data is a leaf (no
     * children). In this case, simply remove it. 2: The node containing the data
     * has one child. In this case, simply replace it with its child. 3: The node
     * containing the data has 2 children. Use the SUCCESSOR to replace the data.
     * You should use recursion to find and remove the successor (you will likely
     * need an additional helper method to handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that was
     * stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null from BST.");
        }

        ArrayList<T> dataAndSuccessor = new ArrayList<>(2);
        dataAndSuccessor.add(null);
        dataAndSuccessor.add(null);
        root = _remove(root, data, dataAndSuccessor);

        return dataAndSuccessor.get(0);
    }
    
    private BSTNode<T> _remove(BSTNode<T> node, T data, ArrayList<T> dataAndSuccessor) {
        if (node == null) {
            throw new NoSuchElementException("Cannot remove from BST: data not found.");
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(_remove(node.getLeft(), data, dataAndSuccessor));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(_remove(node.getRight(), data, dataAndSuccessor));
        } else {
            dataAndSuccessor.set(0, node.getData());
            size--;
    
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else {
                node.setRight(_removeSuccessor(node.getRight(), dataAndSuccessor));
                node.setData(dataAndSuccessor.get(1));
            }
        }

        return node;
    }

    private BSTNode<T> _removeSuccessor(BSTNode<T> node, ArrayList<T> dataAndSuccessor) {
        if (node.getLeft() == null) {
            dataAndSuccessor.set(1, node.getData());
            return node.getRight();
        } else {
            node.setLeft(_removeSuccessor(node.getLeft(), dataAndSuccessor));
        }

        return node;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since you
     * have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since you
     * have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

}