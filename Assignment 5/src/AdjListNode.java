/**
 * Created by dougied on 4/15/2017.
 */
public class AdjListNode {
    int v; //The vertex to which the edge leads
    int w; //The weight of the edge
    AdjListNode next;

    public AdjListNode(int i, int j, AdjListNode n) {//Constructor
        v = i;
        w = j;
        next = n;
    }

    public int getV() {
       return v;
    }
}
