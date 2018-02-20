import java.util.Arrays;
import java.util.Scanner;
/**
 * Created by dougied on 4/15/2017.
 */
public class ReadGraph {
    static String[] storedVertices;

    public static String[] readVertices(Scanner fileIn) {
        int numOfVertices = fileIn.nextInt();
        String[] verticesList = new String[numOfVertices];
        for (int i = 0; i < numOfVertices; i++) {
            verticesList[i] = fileIn.next();
        }
        Arrays.sort(verticesList);
        storedVertices = verticesList;
        return verticesList;
    }

    public static AdjListNode[] readEdgesAdjList(Scanner fileIn) {

        AdjListNode[] adjList = new AdjListNode[storedVertices.length];
        Main.inDegrees = new int[Main.vertices.length];

        while(fileIn.hasNext()) {
            String source = fileIn.next();
            String dest = fileIn.next();
            int weight = fileIn.nextInt();
            int i = Arrays.binarySearch(storedVertices, source);   //returns index of vertex the edge leads out of
            int j = Arrays.binarySearch(storedVertices, dest);   //returns index of vertex to which the edge leads to
            Main.inDegrees[j]++;
            if (adjList[i] == null) {
                adjList[i] = new AdjListNode(j, weight, null); //fileIn.nextInt() picks up the weight
            } else {
                AdjListNode temp = adjList[i];
                adjList[i] = new AdjListNode(j, weight, temp);
                while (temp.next != null) {
                    temp = temp.next;
                    temp.next = new AdjListNode(j, weight, null);
                }
            }

        }

        return adjList;
    }
}
