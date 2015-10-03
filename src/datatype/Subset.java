package datatype;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        int numberOfReads = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {

            String item = StdIn.readString();

            randomizedQueue.enqueue(item);
        }

        if (numberOfReads >= 0 && numberOfReads <= randomizedQueue.size()) {
            while (numberOfReads-- > 0) {
                StdOut.println(randomizedQueue.dequeue());
            }
        }
    }
}
