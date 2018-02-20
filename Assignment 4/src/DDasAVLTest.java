import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

/**
 * Created by dougied on 3/31/2017.
 */
public class DDasAVLTest {
    DDasAVL<Double,Double> tree = new DDasAVL<Double,Double>();
    AVLtree root;

    @Test
    public void testInsert() {
        tree.makeEmpty();

        root = tree.getRoot();
        assertNull(root);

        tree.insert(2.0, 2.0);
        tree.insert(1.0, 1.0);
        tree.insert(3.0, 3.0);

        root = tree.getRoot();
        //screw this.
    }

    @Test
    public void testRotationLL() {
        tree.makeEmpty();
        tree.insert(3.0, 3.0);
        tree.insert(2.0, 2.0);
        tree.insert(1.0, 1.0);
        root = tree.getRoot();

        traverseTree(root);
        assertAVLtreeNode(root, 2.0);
        assertAVLtreeNode(root.left, 1.0);
        assertAVLtreeNode(root.right, 3.0);

        tree.makeEmpty();
        tree.insert(7.0, 7.0);
        tree.insert(8.0, 8.0);
        tree.insert(3.0, 3.0);
        tree.insert(2.0, 2.0);
        tree.insert(4.0, 4.0);
        tree.insert(1.0, 1.0);
        root = tree.getRoot();

        traverseTree(root);
        assertAVLtreeNode(root, 3.0);
        assertAVLtreeNode(root.left, 2.0);
        assertAVLtreeNode(root.left.left, 1.0);
        assertAVLtreeNode(root.right, 7.0);
        assertAVLtreeNode(root.right.left, 4.0);
        assertAVLtreeNode(root.right.right, 8.0);
    }

    @Test
    public void testRotationRR() {
        tree.makeEmpty();
        tree.insert(1.0, 1.0);
        tree.insert(2.0, 2.0);
        tree.insert(3.0, 3.0);
        root = tree.getRoot();

        traverseTree(root);
        assertAVLtreeNode(root, 2.0);
        assertAVLtreeNode(root.left, 1.0);
        assertAVLtreeNode(root.right, 3.0);
    }

    @Test
    public void testRotationLR() {
        tree.makeEmpty();
        tree.insert(7.0, 7.0);
        tree.insert(8.0, 8.0);
        tree.insert(3.0, 3.0);
        tree.insert(2.0, 2.0);
        tree.insert(5.0, 5.0);
        tree.insert(6.0, 6.0); // use either 4.0 or 6.0
        root = tree.getRoot();

        traverseTree(root);
        assertAVLtreeNode(root, 5.0);
        assertAVLtreeNode(root.left, 3.0);
        assertAVLtreeNode(root.left.left, 2.0);
        assertAVLtreeNode(root.right, 7.0);
        assertAVLtreeNode(root.right.left, 6.0);
        assertAVLtreeNode(root.right.right, 8.0);
    }

    @Test
    public void testRotationRL() {
        tree.makeEmpty();
        tree.insert(4.0, 4.0);
        tree.insert(3.0, 3.0);
        tree.insert(8.0, 8.0);
        tree.insert(6.0, 6.0);
        tree.insert(9.0, 9.0);
        tree.insert(5.0, 5.0); // use either 5.0 or 7.0
        root = tree.getRoot();

        traverseTree(root);
        assertAVLtreeNode(root, 6.0);
        assertAVLtreeNode(root.left, 4.0);
        assertAVLtreeNode(root.left.left, 3.0);
        assertAVLtreeNode(root.left.right, 5.0);
        assertAVLtreeNode(root.right, 8.0);
        assertAVLtreeNode(root.right.right, 9.0);
    }

    /**
     * brute force tests all your rotations
     */
    @Test
    public void testIfIsProperAVLtree() {
        for( int i = 0; i < 5; i++) {
            tree.makeEmpty();

            for( int j = 0; j < 100; j++) {
                Double rand = ThreadLocalRandom.current().nextDouble(100);
                tree.insert(rand, rand);
                traverseTree(tree.getRoot());
            }
        }
    }

    private int traverseTree(AVLtree t) {
        if(t == null)
            return -1;

        if( t.left != null && t.left.key.compareTo(t.key) > 0)
            fail("a left child is greater than its parent");
        if( t.right != null && t.key.compareTo(t.right.key) > 0)
            fail("a right child is smaller than its parent");

        int heightLeft = traverseTree(t.left);
        int heightRight = traverseTree(t.right);
        if( Math.abs(heightLeft - heightRight) > 2)
            fail("height mismatch");

        return 1 + Math.max(heightLeft, heightRight);
    }

    private void assertAVLtreeNode(AVLtree t, Double key) {
        if(t == null)
            fail("node doesn't exist when it should");

        if(t.key.compareTo(key) != 0)
            fail("rotation performed incorrectly");
    }


}