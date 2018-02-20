/**
 * Created by dougied on 3/30/2017.
 */
public interface DD<K extends Comparable<? super K>, D>{
    public void insert(K k, D d);
    public D find(K k);
    public void remove(K k);
}
