import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int size;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    } // construct an empty randomized queue

    public boolean isEmpty() {
        return size == 0;
    } // is the queue empty?

    public int size() {
        return size;
    } // return the number of items on the queue

    public void enqueue(Item item) {
        checkForNotNull(item);

        if (size == array.length) resize(2 * array.length);

        array[size++] = item;
    } // add the item

    private void resize(int newSize) {
        if (newSize >= size) {
            Item[] temp = (Item[]) new Object[newSize];
            System.arraycopy(array, 0, temp, 0, size);
            array = temp;
        }
    }

    private void checkForNotNull(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    public Item sample() {
        checkForEmptiness();
        return array[StdRandom.uniform(size)];
    } // return (but do not remove) a random item

    public Item dequeue() {
        checkForEmptiness();

        int randomIndex = StdRandom.uniform(size);

        Item item = array[randomIndex];

        int numMoved = size - randomIndex - 1;
        if (numMoved > 0) {
            System.arraycopy(array, randomIndex+1, array, randomIndex, numMoved);
            array[size - 1] = null;
        } else {
            array[randomIndex] = null;
        }

        size--;

        shrinkTheArray();
        return item;
    } // remove and return a random item

    private void shrinkTheArray() {
        if (size > 0 && size == array.length / 4) {
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
        private int current;
        private Item[] itemsToIterate;

        private MyIterator() {
            itemsToIterate = (Item[]) new Object[size];
            System.arraycopy(array, 0, itemsToIterate, 0, size);
            StdRandom.shuffle(itemsToIterate);
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }
            return itemsToIterate[current++];
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