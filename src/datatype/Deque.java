package datatype;

public class Deque// implements Iterable<Item>
{
    public Deque() {

    } // construct an empty deque

    public boolean isEmpty() {
        return false;
    } // is the deque empty?

    public int size() {
        return 0;
    } // return the number of items on the deque
//    public void addFirst(Item item) {}          // add the item to the front
//    public void addLast(Item item) {}          // add the item to the end
//    public Item removeFirst() {}               // remove and return the item from the front
//    public Item removeLast() {}                // remove and return the item from the end
//    public Iterator<Item> iterator() {}        // return an iterator over items in order from front to end




    public static void main(String[] args) {
    }   // unit testing
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