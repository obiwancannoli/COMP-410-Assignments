import java.util.Random;

/**
 * Created by dougied on 3/31/2017.
 */
public class PerformanceTester<K extends Comparable<? super K>, D> {
    public static void main(String[] args) {
        int N = 5000;
        Random rnd = new Random();
        int arrayOfKeys[] = new int[N];
        int arrayOfData[] = new int[N];
        for (int i = 0; i < N; i++) {
            arrayOfKeys[i] = rnd.nextInt();
            arrayOfData[i] = rnd.nextInt();
        }
        DDasAVL avlDD = new DDasAVL();
        DDasBST bstDD = new DDasBST();

        long startTime, endTime;
        startTime = System.nanoTime();
        for (int i = 0; i < N; i++) {
            avlDD.insert(arrayOfKeys[i], arrayOfData[i]);
        }
        endTime = System.nanoTime();
        System.out.println("Took " + ((endTime - startTime) / 1000000) + " time units");

        startTime = System.nanoTime();
        for (int i = 0; i < N; i++) {
            bstDD.insert(arrayOfKeys[i], arrayOfData[i]);
        }
        endTime = System.nanoTime();
        System.out.println("Took " + ((endTime - startTime) / 1000000) + " time units");
    }
}
