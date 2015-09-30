package datatype;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class RandomizedQueueTest {
    RandomizedQueue randomizedQueue;
    @Before
    public void setUp() {
        randomizedQueue = new RandomizedQueue();
    }

    @Test
    public void testBasic() {
        assertNotNull(randomizedQueue);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(randomizedQueue.isEmpty());
    }

    @Test
    public void testSize() {
        assertTrue(randomizedQueue.isEmpty());
        assertTrue(randomizedQueue.size() == 0);
    }

    @Test(expected = NullPointerException.class)
    public void testEnqueueNull() {
        randomizedQueue.enqueue(null);
    }

    @Test
    public void testEnqueue() {
        randomizedQueue.enqueue(randomizedQueue.new Node());
        assertTrue(randomizedQueue.size() != 0);
        assertFalse(randomizedQueue.isEmpty());
        assertTrue(randomizedQueue.size() == 1);
    }

    @Test
    public void testEnqueueTwoElements() {
        randomizedQueue.enqueue(randomizedQueue.new Node());
        randomizedQueue.enqueue(randomizedQueue.new Node());
        assertTrue(randomizedQueue.size() != 0);
        assertFalse(randomizedQueue.isEmpty());
        assertTrue(randomizedQueue.size() == 2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveInIterator() {
        randomizedQueue.iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNext() {
        randomizedQueue.iterator().next();
    }

    @Test
    public void testIteratorNotNull() {
        assertNotNull(randomizedQueue.iterator());
    }

    


}
