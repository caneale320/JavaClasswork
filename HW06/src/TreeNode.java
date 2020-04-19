
/**
 * Binary Tree Node Tree node that has two children: left and right
 * 
 * @author can4ku
 * @param <Comparable> The type of data this tree node stores
 */
public class TreeNode<T extends Comparable<T>> {

    /**
     * Reference pointer to the left subtree
     */
    private TreeNode<T> left;

    /**
     * Reference pointer to the right subtree
     */
    private TreeNode<T> right;

    /**
     * Data stored at this node
     */
    private T data;

    /**
     * Default Constructor Creates a binary tree node with null data and null children
     */
    public TreeNode() {
        this(null, null, null);
    }

    /**
     * Data-only Constructor Creates a binary tree node with the given data and null children
     * 
     * @param theData The data to store at this node
     */
    public TreeNode(T theData) {
        this(theData, null, null);
    }

    /**
     * Full Constructor Creates a binary tree node with the given data and child reference pointers
     * 
     * @param theData    The data to store at this node
     * @param leftChild  A reference pointer to the left subtree
     * @param rightChild A reference pointer to the right subtree
     */
    public TreeNode(T theData, TreeNode<T> leftChild, TreeNode<T> rightChild) {
        data = theData;
        left = leftChild;
        right = rightChild;
    }

    /**
     * Left Child/Subtree getter
     * 
     * @return A reference pointer to the root of the left subtree
     */
    public TreeNode<T> getLeft() {
        return left;
    }

    /**
     * Left Child/Subtree Setter
     * 
     * @param left A reference pointer to the new left subtree's root node
     */
    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    /**
     * node side implementation of BinarySearchTree size method, allows for more concise syntax and easier recursion
     * @return int size of the tree
     */
    public int size() {
        int size = 0;
        if (data == null) {
            size = -1;
        }
        if (left != null) {
            int lefSize = left.size();
            size += lefSize;
        }

        if (right != null) {
            int righSize = right.size();
            size += righSize;
        }

        return 1 + size;
    }

    /**
     * Right Child/Subtree getter
     * 
     * @return A reference pointer to the root of the right subtree
     */
    public TreeNode<T> getRight() {
        return right;
    }

    /**
     * Right Child/Subtree Setter
     * 
     * @param left A reference pointer to the new right subtree's root node
     */
    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    /**
     * Get the data at this node
     * 
     * @return The data stored at this node
     */
    public T getData() {
        return data;
    }

    /**
     * Set the data at this node
     * 
     * @param data The data to be stored at this node
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * toString method
     */
    @Override
    public String toString() {
        return this.data.toString();
    }

    /**
     * equals method override
     * @param val
     * @return true if equal, false if not
     */
    
    public boolean equals(T val) {
        if (this.data.equals(val)) {
            return true;
        } else
            return false;
    }

    /**
     * Main method For main method testing, etc
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

    }

    /**
     * node side implementation of BinarySearchTree insert method, allows for more concise syntax and easier recursion
     * @param val value to insert into the tree
     * @return true if successful, false if not
     */
    public boolean insert(T val) {
        // TODO Auto-generated method stub
        if (val == null) {
            return false;
        }

        else if (this.data == null) {
            this.data = val;
            return true;
        }

        else if (val.compareTo(this.data) < 0) {

            if (this.left == null) {
                this.left = (new TreeNode<T>(val));
                return true;
            }

            else {
                return left.insert(val);
            }

        }

        else if (val.compareTo(this.data) > 0) {
            if (this.right == null) {
                this.right = new TreeNode<T>(val);
                return true;
            }

            else {
                return right.insert(val);
            }
        }

        return false;
    }

}
