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
        randomizedQueue.enqueue("123");
        assertTrue(randomizedQueue.size() != 0);
        assertFalse(randomizedQueue.isEmpty());
        assertTrue(randomizedQueue.size() == 1);
    }

    @Test
    public void testEnqueueTwoElements() {
        randomizedQueue.enqueue("erty");
        randomizedQueue.enqueue("ewrw");
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

    @Test
    public void testSample() {
        randomizedQueue.enqueue("test");
        assertFalse(randomizedQueue.isEmpty());
        Object sample = randomizedQueue.sample();
        assertEquals("test", sample);
    }

    @Test(expected = NoSuchElementException.class)
    public void testSampleNegative() {
        randomizedQueue.sample();
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueNegative() {
        randomizedQueue.dequeue();
    }

    @Test
    public void testDeque() {
        randomizedQueue.enqueue("test");
        assertFalse(randomizedQueue.isEmpty());
        Object dequeue = randomizedQueue.dequeue();
        assertEquals("test", dequeue);
        assertTrue(randomizedQueue.isEmpty());
    }

    @Test
    public void testDequeTwoElements() {
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test");
        randomizedQueue.enqueue("test1");
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        assertFalse(randomizedQueue.isEmpty());
        Object dequeue = randomizedQueue.dequeue();
        assertTrue(dequeue.toString().startsWith("test"));
        assertFalse(randomizedQueue.isEmpty());
        assertTrue(randomizedQueue.size() == 7);
    }

    @Test
    public void testArrayCopy() {
        int[] ar1 = {1, 2, 3, 4, 5};
        int[] ar2 = new int[4];

        System.arraycopy(ar1, 3, ar2, 1, 2);
        for (int i = 0; i < ar2.length; i++) {
            System.out.println(ar2[i]);
        }




    }

}
