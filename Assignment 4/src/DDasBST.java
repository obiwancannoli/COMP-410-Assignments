/**
 * Created by dougied on 3/31/2017.
 */
public class DDasBST<K extends Comparable<? super K>, D> implements DD<K,D>{
    private BSTree<K,D> root;
    public DDasBST(){root = null;} //Constructor
    public void makeEmpty(){root = null;}
    public boolean isEmpty(){return root == null;}
    public void insert(K k, D d){root = insert(root, k, d);}
    public D find(K k){return find(root, k);}
    public void remove(K k){root = remove(root, k);}

    public int height(){// Not relevant to DD's -- used for comparing performance with AVL trees
        return height(root);
    }
    private int height (BSTree<K,D> t){
        if (t==null) return -1;
        return 1 + Math.max(height(t.left), height(t.right));
    }

    private BSTree<K,D> insert(BSTree<K,D> t, K k, D d){
        if (t == null) return new BSTree<K,D>(k, d, null, null);
        int compareResult = k.compareTo(t.key);
        if (compareResult < 0) t.left = insert(t.left, k, d);
        else if (compareResult>0) t.right = insert(t.right, k, d);
        else ;
        return t;
    }
    private D find(BSTree<K,D> t, K k){
        if (t == null) return null;
        int compareResult = k.compareTo(t.key);
        if (compareResult == 0) return t.data;
        if (compareResult < 0) return find(t.left, k);
        return find(t.right, k);
    }
    private BSTree<K,D> findMin(BSTree<K,D> t){//PRE: not null
        if (t.left == null)return t;
        return findMin(t.left);
    }
    private BSTree<K,D> remove(BSTree<K,D> t, K k){
        if (t==null) return null;
        int compareResult = k.compareTo(t.key);
        if (compareResult < 0) t.left = remove(t.left, k);
        else if (compareResult > 0) t.right = remove(t.right, k);
        else{//found
            if (t.left == null && t.right ==null) t = null;
            else if (t.left == null || t.right == null) t =  (t.left == null) ? t.right : t.left;
                //both children exist
            else{
                BSTree<K,D> tmp1 = findMin(t.right);
                t.key = tmp1.key; t.data = tmp1.data;
                t.right = remove(t.right, tmp1.key);
            }
        }
        return t;
    }
}
