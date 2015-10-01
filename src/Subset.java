import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String[] args) {
        RandomizedQueue randomizedQueue = new RandomizedQueue();
        int numberOfReads = Integer.parseInt(args[0]);

        while(!StdIn.isEmpty()) {
            String item = StdIn.readString();

            randomizedQueue.enqueue(item);
        }

        while (numberOfReads-- > 0) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
