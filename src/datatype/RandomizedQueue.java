package datatype;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first, last;
    private int sizeCounter;

    public RandomizedQueue() {
    } // construct an empty randomized queue

    public boolean isEmpty() {
        return first == null;
    } // is the queue empty?

    public int size() {
        return sizeCounter;
    } // return the number of items on the queue
    public void enqueue(Item item) {
        checkForNotNull(item);
        Node oldLast = last;
        last = new Node();
        last.value = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        sizeCounter++;
    } // add the item

    private void checkForNotNull(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

//    public Item dequeue() {} // remove and return a random item
//    public Item sample() {} // return (but do not remove) a random item
    @Override
    public Iterator<Item> iterator() {
        return new MyIterator();
    } // return an independent iterator over items in random order

    class Node {
        private Node next;
        private Item value;
    }

    class MyIterator implements java.util.Iterator {
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }
            Item item = current.value;
            current = current.next;
            return item;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException("It is impossible "
                    + "to remove via iterator");
        }
    }

    public static void main(String[] args) {} // unit testing
}


/**
 * The order of two or more iterators to the same randomized queue
 * must be mutually independent; each iterator must maintain its
 * own random order.
 *
NoSuchElementException if the client attempts to sample or dequeue an item
 from an empty randomized queue;
*/
