import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array = (Item[]) new Object[1];
    private int N;

    public RandomizedQueue() {
    } // construct an empty randomized queue

    public boolean isEmpty() {
        return N == 0;
    } // is the queue empty?

    public int size() {
        return N;
    } // return the number of items on the queue

    public void enqueue(Item item) {
        checkForNotNull(item);

        if (N == array.length) resize(2 * array.length);

        array[N++] = item;
    } // add the item

    private void resize(int newSize) {
        Item[] temp = (Item[]) new Object[newSize];
        for (int i = 0; i < N; i++)
            temp[i] = array[i];
        array = temp;
    }

    private void checkForNotNull(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    public Item sample() {
        checkForEmptiness();
        int random = StdRandom.uniform(N);
        return array[random];
    } // return (but do not remove) a random item

    public Item dequeue() {
        checkForEmptiness();

        int random = StdRandom.uniform(N);

        Item item = array[random];

        int numMoved = size() - random - 1;
        if (numMoved > 0) {
            for (int i = random; i < N - 1; i++) {
                array[i] = array[i + 1];
            } // System.arraycopy(array, random+1, array, random, numMoved);
        }
        array[--N] = null;

        shrinkTheArray();
        return item;
    } // remove and return a random item

    private void shrinkTheArray() {
        if (N > 0 && N == array.length / 4) {
            resize(array.length / 2);
        }
    }

    private void checkForEmptiness() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyIterator();
    } // return an independent iterator over items in random order

    private class MyIterator implements java.util.Iterator {
        private int random;

        @Override
        public boolean hasNext() {
            if (N == 0) {
                throw new NoSuchElementException("Size is 0, " +
                                                 "there is no elements");
            }
            random = StdRandom.uniform(N);
            return array[random] != null;
        }
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }
            return array[random];
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException("It is impossible "
                    + "to remove via iterator");
        }
    }

    public static void main(String[] args) {
    } // unit testing
}