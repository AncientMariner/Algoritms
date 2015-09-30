package datatype;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        deque.addFirst(new Deque().new Node());
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 1);
    }

    @Test
    public void testSize() throws Exception {
        deque.addFirst(new Deque().new Node());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 1);
    }

    @Test
    public void testAddFirstTwice() {
        deque.addFirst(new Deque().new Node());
        deque.addFirst(new Deque().new Node());
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 2);
    }


    @Test
    public void testAddLast() {
        deque.addLast(new Deque().new Node());
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 1);
    }

    @Test
    public void testAddLastTwice() {
        deque.addLast(new Deque().new Node());
        deque.addLast(new Deque().new Node());
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 2);
    }

    @Test
    public void testAddFirstAddLast() {
        deque.addFirst(new Deque().new Node());
        deque.addLast(new Deque().new Node());
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 2);
    }

    @Test
    public void testAddLastAddFirst() {
        deque.addLast(new Deque().new Node());
        deque.addFirst(new Deque().new Node());
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 2);
    }

    @Test
    public void testAddCoupleOfElements() {
        deque.addLast(new Deque().new Node());
        deque.addFirst(new Deque().new Node());
        deque.addLast(new Deque().new Node());
        deque.addLast(new Deque().new Node());
        deque.addFirst(new Deque().new Node());
        deque.addFirst(new Deque().new Node());
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 6);
    }

    @Test
    public void testRemoveFirst() {
        Deque.Node itemToAdd = new Deque().new Node();
        deque.addFirst(itemToAdd);

        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 1);

        Object obj = deque.removeFirst();
        assertNotNull(obj);

        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testRemoveLast() {
        Deque.Node itemToAdd = new Deque().new Node();
        deque.addLast(itemToAdd);

        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 1);

        Object obj = deque.removeLast();
        assertNotNull(obj);

        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullToTheBeginnning() {
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullToTheEnd() {
        deque.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromEmpty() {
        deque.removeFirst();
    }

    @Test
    public void testIteratorNotNull() {
        assertNotNull(deque.iterator());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveInIterator() {
        deque.iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNext() {
        deque.iterator().next();
    }

    @Test
    public void testEugenesIterator() {
        deque.addFirst("item1");
        deque.addLast("item2");
        deque.addFirst("item3");
        deque.addLast("item4");
        deque.addLast("item5");
        deque.addFirst("item6");
        deque.addFirst("item7");

        Deque.Iterator it =  deque.iterator();

        assertTrue(it.hasNext());
        assertEquals("item7", it.next());

        assertTrue(it.hasNext());
        assertEquals("item6", it.next());

        assertTrue(it.hasNext());
        assertEquals("item3", it.next());

        assertTrue(it.hasNext());
        assertEquals("item1", it.next());

        assertTrue(it.hasNext());
        assertEquals("item2", it.next());

        assertTrue(it.hasNext());
        assertEquals("item4", it.next());

        assertTrue(it.hasNext());
        assertEquals("item5", it.next());

        assertFalse(it.hasNext());
    }

}
