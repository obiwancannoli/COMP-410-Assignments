import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by dougied on 4/16/2017.
 */
public class Main {
    static String[] vertices; // The sorted list of vertex names
    static AdjListNode[] adjList; // The adjacency list
    static int[] inDegrees;

    /*
    * You are to write the following method. You will need to use a stack
    * or queue of integers here (you may use java.util.{Stack,Queue}
    */
    public static String[] topSort() {
        Queue<Integer> queue = new ArrayDeque<>();

        int counter = 0;
        String[] topSorted = new String[vertices.length];
        int index = findVertexInZero(inDegrees);
        while (index != -1) {
            inDegrees[index]--;
            queue.offer(index);
            if (adjList[index] != null) {
                AdjListNode tmp = adjList[index];
                while (tmp != null) {
                    inDegrees[tmp.v]--;
                    tmp = tmp.next;
                }
            }
            index = findVertexInZero(inDegrees);
            counter++;
        }

        if (counter != vertices.length) {
            for (int i = 0; i < topSorted.length; i++) {
                topSorted[i] = null;
            }
        } else {
            for (int i = 0; i < vertices.length; i++) {
                topSorted[i] = vertices[queue.poll()];
            }
        }

        return topSorted;
    }


    private static int findVertexInZero(int[] inDeg) {
        for (int i = 0; i < inDeg.length; i++) {
            if (inDeg[i] == 0) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.err.println("Incorrect number of args passed");
            System.exit(-1);
        }
        Scanner fileIn = new Scanner(new File(args[0]));
        vertices = ReadGraph.readVertices(fileIn);
        adjList = ReadGraph.readEdgesAdjList(fileIn);
        String[] sorted = topSort();

/*
* At this point, "sorted" contains the vertices in topologically-sorted
* order (or all NULLs if the graph is not acyclic
*/

    }
}


