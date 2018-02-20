/**
 * Created by dougied on 3/30/2017.
 */
public class DDasAVL<K extends Comparable<? super K>, D> implements DD<K, D> {
    private AVLtree<K, D> root;

    public AVLtree <K, D> getRoot() {
        return root;
    }
    public DDasAVL() {
        root = null;
    } //Constructor

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(K k, D d) {
        root = insert(root, k, d);
    }

    public D find(K k) {
        return find(root, k);
    }

    public void remove(K k) {
        root = remove(root, k);
    }

    public int height() {
        return height(root);
    }// Not relevant to DD's -- used for comparing performance with AVL trees

    private AVLtree<K, D> insert(AVLtree<K, D> t, K k, D d) {
        if (t == null) {
            return new AVLtree<K, D>(k, d, null, null, false);
        }

        int compareResult = k.compareTo(t.key);

        if (compareResult < 0) {
            t.left = insert(t.left, k, d);
        } else if (compareResult > 0) {
            t.right = insert(t.right, k, d);
        } else if (compareResult == 0) {
            if(t.isDeleted) {
                t.isDeleted = false;
            }
        }

        return balance(t);
    } // Complete this

    private D find(AVLtree<K, D> t, K k) {
        while (t != null) {
            if (k.compareTo(t.key) < 0) {
                t = t.left;
                if (t.isDeleted) {
                    find(t, k);
                }
            }
            if (k.compareTo(t.key) > 0) {
                t = t.right;
                if (t.isDeleted) {
                    find(t, k);
                }
            } else {
                return t.element; //Match
            }
        }
        return null; //No Match
    }

    private AVLtree<K, D> findMin(AVLtree<K, D> t) {
        if (t == null) {
            return t;
        }
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }//Complete this

    private AVLtree<K, D> remove(AVLtree<K, D> t, K k) {
        if(t == null) {
            return root;
        }
        int compareResult = k.compareTo(t.key);
        if(compareResult == 0) {
            t.isDeleted = true;
        }
        if (compareResult < 0) {
            return remove(t.left, k);
        }
        return remove(t.right, k);
    } //Implement using LAZY DELETE!!

    private int height(AVLtree<K, D> t) {
        return (t == null) ? -1 : t.height;
    }

    private AVLtree<K, D> balance(AVLtree<K, D> t) {
        if (t == null) return t;
        if (height(t.left) - height(t.right) > 1)
            if (height(t.left.left) >= height(t.left.right))
                t = rotateLL(t);  //do an LL rotation
            else
                t = rotateLR(t);  //do an LR rotation
        else if (height(t.right) - height(t.left) > 1)
            if (height(t.right.right) >= height(t.right.left))
                t = rotateRR(t);  //do an RR rotation
            else
                t = rotateRL(t);  //do an RL rotation

        t.height = 1 + Math.max(height(t.left), height(t.right));
        return t;
    }

    /*
    Single rotate binary tree node with left child
     */

    private AVLtree<K, D> rotateLL(AVLtree<K, D> k2) {
        AVLtree<K, D> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /*
    Double rotate binary tree node: first left child
    with its right child; then node k2 with new left child.
     */
    private AVLtree<K, D> rotateLR(AVLtree<K, D> k2) {
        k2.left = rotateRR(k2.left);
        return rotateLL(k2);
    }

    /*
    Single rotate binary tree node with right child
     */

    private AVLtree<K, D> rotateRR(AVLtree<K, D> k2) {
        AVLtree<K, D> k3 = k2.right;
        k2.right = k3.left;
        k3.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k3.height = Math.max(height(k3.right), k2.height) + 1;
        return k3;
    }

    /*
    Double rotate binary tree node: first right child
    with its left child; then node k2 with new right;
     */
    private AVLtree<K, D> rotateRL(AVLtree<K, D> k2) {
        k2.right = rotateLL(k2.right);
        return rotateRR(k2);
    }


}
