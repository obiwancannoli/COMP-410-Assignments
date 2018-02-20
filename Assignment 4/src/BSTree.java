/**
 * Created by dougied on 3/31/2017.
 */
public class BSTree<K extends Comparable<? super K>, D> {
    K key;
    D data;

    BSTree<K,D> left, right;
    public BSTree(K k, D d, BSTree<K,D> l, BSTree<K,D> r){key=k;data=d;left=l;right=r;}
}
