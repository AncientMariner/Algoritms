package datatype;

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

        return null;
    } // remove and return a random item

    private void checkForEmptiness() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
    }

    @Override
    public Iterator<Item> iterator() {
//        return new MyIterator();
        return null;
    } // return an independent iterator over items in random order

    class Node {
        private Node next;
        private Item value;
    }

//    class MyIterator implements java.util.Iterator {
//        enqueue
//        private Node current = first;
//        @Override
//        public boolean hasNext() {
//            return current != null;
//        }
//        @Override
//        public Item next() {
//            if (!hasNext()) {
//                throw new NoSuchElementException("No more items to return");
//            }
//            Item item = current.value;
//            current = current.next;
//            return item;
//        }
//        @Override
//        public void remove() {
//            throw new UnsupportedOperationException("It is impossible "
//                    + "to remove via iterator");
//        }
//    }

    public static void main(String[] args) {} // unit testing
}


/**
 * The order of two or more iterators to the same randomized queue
 * must be mutually independent; each iterator must maintain its
 * own random order.
 *
*/
