package datatype;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DequeTest {
    Deque deque;

    @Before
    public void setUp() {
        deque = new Deque();
    }

    @Test
    public void basicTest() {
        assertNotNull(deque);
    }

    @Test
    public void testForEmptiness() {
        if (!(deque.isEmpty() && deque.size() == 0)) {
            throw new AssertionError();
        }
    }

    @Test
    public void testSizeIsZero() {
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testAddFirst() {
        deque.addFirst(new Deque().new Item());
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 1);
    }

    @Test
    public void testAddFirstTwice() {
        deque.addFirst(new Deque().new Item());
        deque.addFirst(new Deque().new Item());
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 2);
    }

    @Test
    public void testAddLast() {
        deque.addLast(new Deque().new Item());
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 1);
    }


    @Test
    public void testAddLastTwice() {
        deque.addLast(new Deque().new Item());
        deque.addLast(new Deque().new Item());
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 2);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullToTheBeginnning() {
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullToTheEnd() {
        deque.addLast(null);
    }

//    @Test
//    public void testIterator() {
//        assertNotNull(deque.iterator());
//    }

    @Test
    public void testSize() throws Exception {
    }
}
