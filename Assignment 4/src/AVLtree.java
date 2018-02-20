/**
 * Created by dougied on 3/30/2017.
 */
public class AVLtree<K extends Comparable<? super K>, D> {
    K key;
    D element;
    AVLtree<K, D> left;
    AVLtree<K, D> right;
    int height;
    boolean isDeleted;

    public AVLtree(K theKey, D theElement) {
        this(theKey, theElement, null, null, false);
    }

    public AVLtree(K theKey, D theElement, AVLtree<K, D> lt, AVLtree<K, D> rt, boolean deleted) {
        key = theKey;
        element = theElement;
        left = lt;
        right = rt;
        height = 0;
        isDeleted = deleted;
    }
}
