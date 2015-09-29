package datatype;

import java.util.Iterator;

public class Deque implements Iterable<Deque.Item> {
    private Item first, last;
    int sizeCounter;

    public Deque() {
    } // construct an empty deque

    public boolean isEmpty() {
        return first == null;
    } // is the deque empty?

    public int size() {
        return sizeCounter;
    } // return the number of items on the deque

    @Override
    public Iterator<Item> iterator() {
        return null;
    }// return an iterator over items in order from front to end

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            first = last = item;
        }

        Item oldFirst = first;
        first = new Item();
        first.value = item;
        first.next = oldFirst;

        sizeCounter++;
    }          // add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

    }          // add the item to the end
//    public Item removeFirst() {}               // remove and return the item from the front
//    public Item removeLast() {}                // remove and return the item from the end

    public static void main(String[] args) {
    }   // unit testing

    class Item {
        Item next;
        Item previous;
        Object value;
    }
}





/*
* Corner cases.
 * Throw a java.lang.NullPointerException if the client attempts to add a null item;
 *
 * throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque;
 *
 * throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator;
 *
 * throw a java.util.NoSuchElementException if the client calls the next() method in the iterator
 *  and there are no more items to return.


*
* */