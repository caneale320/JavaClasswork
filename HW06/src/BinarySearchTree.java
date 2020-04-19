import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Search Tree Class The head class for a binary search tree implementation.
 * sources: https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/, https://www.techiedelight.com/deletion-from-bst/, http://www.algolist.net/Data_structures/Binary_search_tree/Removal 
 * @author can4ku
 * @param <Comparable> Type of data to store in the binary tree
 */
public class BinarySearchTree<T extends Comparable<T>> {

    /**
     * A reference pointer to the root of the tree
     */
    private TreeNode<T> root;

    /**
     * Default constructor Creates a binary tree object with null root note (empty tree)
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Constructor Creates a binary tree object with the given node as root
     * 
     * @param newRoot The root of the tree
     */
    public BinarySearchTree(TreeNode<T> newRoot) {
        this.root = newRoot;
    }

    /**
     * Get the root of the tree
     * 
     * @return The root of the tree
     */
    public TreeNode<T> getRoot() {
        return root;
    }

    /**
     * Set the root of the tree
     * 
     * @param root The new root of this tree
     */
    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    /**
     * Find if an element exists Checks to see if the value val appears in the tree (recursively). Returns true if it
     * appears and false otherwise.
     * 
     * @param val The value to find
     * @return True if the tree contains the value, false otherwise
     */
    public boolean find(T val) {
        // If tree is empty
        if (root == null) {
            return false;
        } else if (root.equals(val)) {
            return true;
        }

        // Initialize empty queue.
        Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();

        // Do level order traversal starting from root
        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNode<T> temp = queue.poll();
            if (temp.getLeft() != null) {
                if (!(temp.getLeft().equals(val))) {
                    queue.add(temp.getLeft());
                }

                else if (temp.getLeft().equals(val)) {
                    return true;
                }
            }

            // Enqueue right child
            if (temp.getRight() != null) {
                if (!temp.getRight().equals(val)) {
                    queue.add(temp.getRight());
                }

                else if (temp.getRight().equals(val)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param val the value to insert into a tree node in the correct location
     * @return true if successful, false if not
     */
    public boolean insert(T val) {

        if (this.root == null) {
            this.root = new TreeNode<T>(val);
            return true;
        }

        else if (this.root != null) {
            return this.root.insert(val);
        } else
            return false;

    }

    /**
     * Delete an element from the tree Deletes val from the tree if it appears, returning true on success and false
     * otherwise
     * 
     * @param val The value to delete
     * @return True on success, false otherwise
     */
    public boolean delete(T val) {
        if (this.deleteNode(this.root, val) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * helper method to find the minimum node in a tree starting at a given node
     * @param curr current root node to start search from (parent node)
     * @return the minimum node in the tree
     */
    public static TreeNode minimumKey(TreeNode curr) {
        while (curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        return curr;
    }

    /**
     *  function to search through a BST and delete the specified node, restructuring the tree as needed
     * @param root node to start search at, root node of the tree
     * @param key key (value) to remove from the tree
     * @return the node which was removed
     */
    public TreeNode deleteNode(TreeNode<T> root, T key) {
        // pointer to store parent node of current node
        TreeNode<T> parent = null;

        // start with root node
        TreeNode<T> curr = root;

        // search key in BST and set its parent pointer
        while (curr != null && curr.getData().compareTo(key) != 0) {
            // update parent node as current node
            parent = curr;

            // if given key is less than the current node, go to left subtree
            // else go to right subtree
            if (key.compareTo(curr.getData()) < 0) {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }
        }

        // return if key is not found in the tree
        if (curr == null) {
            return root;
        }

        // Case 1: node to be deleted has no children i.e. it is a leaf node
        if (curr.getLeft() == null && curr.getRight() == null) {
            // if node to be deleted is not a root node, then set its
            // parent left/right child to null
            if (!curr.equals(root)) {
                if (parent.getLeft().equals(curr)) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }
            // if tree has only root node, delete it and set root to null
            else {
                root = null;
            }
        }

        // Case 2: node to be deleted has two children
        else if (curr.getLeft() != null && curr.getRight() != null) {
            // find its in-order successor node
            TreeNode<T> successor = minimumKey(curr.getRight());

            // store successor value
            T val = successor.getData();

            // recursively delete the successor. Note that the successor
            // will have at-most one child (right child)
            deleteNode(root, successor.getData());

            // Copy the value of successor to current node
            curr.setData(val);
        }

        // Case 3: node to be deleted has only one child
        else {
            // find child node
            TreeNode<T> child = (curr.getLeft() != null) ? curr.getLeft() : curr.getRight();

            // if node to be deleted is not a root node, then set its parent
            // to its child
            if (curr != root) {
                if (curr.equals(parent.getLeft())) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
            }

            // if node to be deleted is root node, then set the root to child
            else {
                root = child;
            }
        }

        return root;
    }

    /**
     * Build from a list Build the tree from the given list, overwriting any tree data previously stored in this tree.
     * Should read from beginning to end of the list and repeatedly call insert() to build the tree.
     * 
     * @param list The list from which to build the tree
     * @return True if successfully built, false otherwise
     */
    public boolean buildFromList(ArrayList<T> list) {
        BinarySearchTree<T> output = new BinarySearchTree<T>();
        for (T item : list) {
            if (!output.insert(item)) {
                return false;
            }
        }
        this.root = output.root;

        return true;
    }

    /**
     * toString method
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.inOrder();
    }


    /**
     * method to recursively find the size, or number of nodes in the tree
     * @return int size, number of nodes
     */
    public int size() {
        // If tree is empty
        if (root == null) {
            return 0;
        }

        return this.root.size();

    }
    
    /**
     * method to implement maxdepth, finds total height of the tree
     * @return int describing height of the tree
     */
    public int height() {
        return this.maxDepth(this.root);
    }

    /**
     * helper method to determine the depth of a tree by recursively searching both sides
     * @param node to begin searching the tree
     * @return int representing the maximum depth of the tree on nay given side
     */
    int maxDepth(TreeNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.getLeft());
            int rDepth = maxDepth(node.getRight());

            /* use the larger one */
            if (lDepth > rDepth) {
                return (lDepth + 1);
            } else {
                return (rDepth + 1);
            }
        }
    }

    /**
     * inOrder display of nodes, with parenthesis between each node
     * 
     * @param node is the root of the tree/subtree
     * @return an inorder String of the tree
     */
    public String inorder(TreeNode<T> node) {
        String displayNodes = "";
        if (node != null) {
            displayNodes = displayNodes + this.inorder(node.getLeft());
            displayNodes = displayNodes + "(" + node.toString() + ")";
            displayNodes = displayNodes + this.inorder(node.getRight());
        }
        return displayNodes;
    }

    /**
     * implements a version of inorder requiring no parameters, allowing for easier recursion
     * @return see inorder
     */
    public String inOrder() {
        return this.inorder(this.getRoot());
    }

    /**
     * implements postOrder sort using an argument for easier recursion
     * @param node to begin sort at
     * @return postorder list of all the nodes
     */
    public String postorder(TreeNode<T> node) {
        String displayNodes = "";
        if (node != null) {
            displayNodes = displayNodes + this.postorder(node.getLeft());
            displayNodes = displayNodes + this.postorder(node.getRight());
            displayNodes = displayNodes + "(" + node.toString() + ")";

        }
        return displayNodes;
    }

    /**
     * argument free implementation of postorder
     * @return see postorder
     */
    public String postOrder() {
        return this.postorder(this.getRoot());
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(new TreeNode<Integer>(1));
        bst.root.setLeft(new TreeNode<Integer>(2));
        bst.root.setRight(new TreeNode<Integer>(3));
        bst.root.getRight().setLeft(new TreeNode<Integer>(3));

        System.out.println(bst.size());
        System.out.println(bst.inOrder());
    }
}
