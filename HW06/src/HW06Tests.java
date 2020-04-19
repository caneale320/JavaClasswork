import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class HW06Tests {

    @Test
    public void sizeTest() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode(7));
        tree_level.getRoot().setLeft(new TreeNode(3));
           
        
        assertEquals(tree_level.size(), 2);
    
    }
    
    public void sizeTest2() {
        BinarySearchTree tree_level = new BinarySearchTree();
        
        assertEquals(tree_level.size(), 0);
    
    }
    
    public void heightTest() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode(7));
        tree_level.getRoot().setLeft(new TreeNode(3));
        tree_level.getRoot().setRight(new TreeNode(9));
        tree_level.getRoot().getRight().setRight(new TreeNode(10));
        tree_level.getRoot().getRight().setLeft(new TreeNode(8));
        tree_level.getRoot().getLeft().setRight(new TreeNode(5));
        tree_level.getRoot().getLeft().getRight().setLeft(new TreeNode(4));
        tree_level.getRoot().getLeft().getRight().setRight(new TreeNode(6));
        tree_level.getRoot().getLeft().setLeft(new TreeNode(2));
        tree_level.getRoot().getLeft().getLeft().setLeft(new TreeNode(1));    
        
        assertEquals(tree_level.height(), 4);
    
    }
    
    public void heightTest2() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode(7));
         
        assertEquals(tree_level.height(), 1);
    
    }
    
    public void findTest() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode(7));
        tree_level.getRoot().setLeft(new TreeNode(3));
        tree_level.getRoot().setRight(new TreeNode(9));
        tree_level.getRoot().getRight().setRight(new TreeNode(10));
        tree_level.getRoot().getRight().setLeft(new TreeNode(8));
        tree_level.getRoot().getLeft().setRight(new TreeNode(5));
        tree_level.getRoot().getLeft().getRight().setLeft(new TreeNode(4));
        tree_level.getRoot().getLeft().getRight().setRight(new TreeNode(6));
        tree_level.getRoot().getLeft().setLeft(new TreeNode(2));
        tree_level.getRoot().getLeft().getLeft().setLeft(new TreeNode(1));    
        
        assertTrue(tree_level.find(2));
    
    }
    
    public void findTest2() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode(7));
        tree_level.getRoot().setLeft(new TreeNode(3));
        tree_level.getRoot().setRight(new TreeNode(9));
        tree_level.getRoot().getRight().setRight(new TreeNode(10));
        tree_level.getRoot().getRight().setLeft(new TreeNode(8));
        tree_level.getRoot().getLeft().setRight(new TreeNode(5));
        tree_level.getRoot().getLeft().getRight().setLeft(new TreeNode(4));
        tree_level.getRoot().getLeft().getRight().setRight(new TreeNode(6));
        tree_level.getRoot().getLeft().setLeft(new TreeNode(2));
        tree_level.getRoot().getLeft().getLeft().setLeft(new TreeNode(1));    
        
        assertFalse(tree_level.find(11));
    
    }
    
    public void insertTest() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode(7));
        tree_level.getRoot().setLeft(new TreeNode(3));
        tree_level.getRoot().setRight(new TreeNode(9));
        tree_level.getRoot().getRight().setRight(new TreeNode(10));
        tree_level.getRoot().getRight().setLeft(new TreeNode(8));
        tree_level.getRoot().getLeft().setRight(new TreeNode(5));
        tree_level.getRoot().getLeft().getRight().setLeft(new TreeNode(4));
        tree_level.getRoot().getLeft().getRight().setRight(new TreeNode(6));
        tree_level.getRoot().getLeft().setLeft(new TreeNode(2));
        tree_level.getRoot().getLeft().getLeft().setLeft(new TreeNode(1));    
        
        assertFalse(tree_level.insert(2));
    
    }
    
    public void insertTest2() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode());
           
        
        assertTrue(tree_level.insert("c"));
    
    }
    
    public void inOrderTest() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode(7));
        tree_level.getRoot().setLeft(new TreeNode(3));
        tree_level.getRoot().setRight(new TreeNode(9));
        tree_level.getRoot().getRight().setRight(new TreeNode(10));
        tree_level.getRoot().getRight().setLeft(new TreeNode(8));
        tree_level.getRoot().getLeft().setRight(new TreeNode(5));
        tree_level.getRoot().getLeft().getRight().setLeft(new TreeNode(4));
        tree_level.getRoot().getLeft().getRight().setRight(new TreeNode(6));
        tree_level.getRoot().getLeft().setLeft(new TreeNode(2));
        tree_level.getRoot().getLeft().getLeft().setLeft(new TreeNode(1));    
        
        assertEquals(tree_level.inOrder(), "(1)(2)(3)(4)(5)(6)(7)(8)(9)(10)");
    
    }

    public void inOrderTest2() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode(7));
        
        assertEquals(tree_level.inOrder(), "(2)");
    
    }
    
    public void postOrderTest() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode(7));
        tree_level.getRoot().setLeft(new TreeNode(3));
        tree_level.getRoot().setRight(new TreeNode(9));
        tree_level.getRoot().getRight().setRight(new TreeNode(10));
        tree_level.getRoot().getRight().setLeft(new TreeNode(8));
        tree_level.getRoot().getLeft().setRight(new TreeNode(5));
        tree_level.getRoot().getLeft().getRight().setLeft(new TreeNode(4));
        tree_level.getRoot().getLeft().getRight().setRight(new TreeNode(6));
        tree_level.getRoot().getLeft().setLeft(new TreeNode(2));
        tree_level.getRoot().getLeft().getLeft().setLeft(new TreeNode(1));    
        
        assertEquals(tree_level.postOrder(), "(1)(2)(4)(6)(5)(3)(8)(10)(9)(7)");
    
    }
    
    public void postOrderTest2() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode(7));
        
        assertEquals(tree_level.postOrder(), "(2)");
    
    }
    
    public void toStringTest() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode(7));
        tree_level.getRoot().setLeft(new TreeNode(3));
        tree_level.getRoot().setRight(new TreeNode(9));
        tree_level.getRoot().getRight().setRight(new TreeNode(10));
        tree_level.getRoot().getRight().setLeft(new TreeNode(8));
        tree_level.getRoot().getLeft().setRight(new TreeNode(5));
        tree_level.getRoot().getLeft().getRight().setLeft(new TreeNode(4));
        tree_level.getRoot().getLeft().getRight().setRight(new TreeNode(6));
        tree_level.getRoot().getLeft().setLeft(new TreeNode(2));
        tree_level.getRoot().getLeft().getLeft().setLeft(new TreeNode(1));    
        
        assertEquals(tree_level.toString(), "(1)(2)(3)(4)(5)(6)(7)(8)(9)(10)");
    
    }
    
    public void toStringTest2() {
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode(7));
       
        assertEquals(tree_level.toString(), "(2)");
    
    }
    
    public void fromListTest() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode());

        assertTrue(tree_level.buildFromList(list));
    
    }
    
    public void fromListTest2() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode());

        assertFalse(tree_level.buildFromList(list));
    
    }
    
    public void fromListTest3() {
        ArrayList list = new ArrayList();

        BinarySearchTree tree_level = new BinarySearchTree(new TreeNode());

        assertFalse(tree_level.buildFromList(list));
    
    }
    
    
}
