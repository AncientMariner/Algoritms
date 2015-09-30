package datatype;

import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable {
    private Node first, last;
    private int sizeCounter;

    public Deque() {
    } // construct an empty deque

    public boolean isEmpty() {
        return first == null || last == null;
    } // is the deque empty?

    public int size() {
        return sizeCounter;
    } // return the number of items on the deque

    public void addFirst(Item item) {
        checkForNotNull(item);
        if (isEmpty()) {
            last = first;
        }
        Node oldFirst = first;
        first = new Node();
        first.value = item;
        first.previous = null;

        if (oldFirst != null) {
            first.next = oldFirst;
            oldFirst.previous = first;
        }

        sizeCounter++;
        if (size() == 1) {
            last = first;
        }
    }          // add the item to the front

    public void addLast(Item item) {
        checkForNotNull(item);
        if (isEmpty()) {
            first = last;
        }
        Node oldLast = last;
        last = new Node();
        last.value = item;
        last.next = null;

        if (oldLast != null) {
            last.previous = oldLast;
            oldLast.next = last;
        }
        sizeCounter++;
        if (size() == 1) {
            first = last;
        }
    }          // add the item to the end

    private void checkForNotNull(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    public Item removeFirst() {
        checkForEmptiness();
        Node itemToReturn = first;

        if (size() > 1) {
            first = first.next;
            first.previous = null;
        } else {
            first = null;
            last = null;
        }
        sizeCounter--;
        return itemToReturn.value;
    }               // remove and return the item from the front

    public Item removeLast() {
        checkForEmptiness();
        Node itemToReturn = last;

        if (size() > 1) {
            last = last.previous;
            last.next = null;
        } else {
            first = null;
            last = null;
        }
        sizeCounter--;
        return itemToReturn.value;
    }                // remove and return the item from the end

    private void checkForEmptiness() {
        if (isEmpty()) {
            throw new NoSuchElementException("It is impossible to remove an item");
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator();
    }// return an iterator over items in order from front to end

    class Node {
        Node next;
        Node previous;
        Item value;
    }

    class Iterator implements java.util.Iterator {
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
            throw new UnsupportedOperationException("It is impossible to remove via iterator");
        }
    }


    public static void main(String[] args) {
    }   // unit testing
}