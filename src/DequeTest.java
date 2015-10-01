import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
        deque.addFirst("blabla");
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 1);
    }

    @Test
    public void testSize() throws Exception {
        deque.addFirst("blabla");
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 1);
    }

    @Test
    public void testAddFirstTwice() {
        deque.addFirst("blabla");
        deque.addFirst("blabla");
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 2);
    }


    @Test
    public void testAddLast() {
        deque.addLast("blabla");
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 1);
    }

    @Test
    public void testAddLastTwice() {
        deque.addLast("blabla");
        deque.addLast("blabla");
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 2);
    }

    @Test
    public void testAddFirstAddLast() {
        deque.addFirst("blabla");
        deque.addLast("blabla");
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 2);
    }

    @Test
    public void testAddLastAddFirst() {
        deque.addLast("blabla");
        deque.addFirst("blabla");
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 2);
    }

    @Test
    public void testAddCoupleOfElements() {
        deque.addLast("blabla");
        deque.addFirst("blabla");
        deque.addLast("blabla");
        deque.addLast("blabla");
        deque.addFirst("blabla");
        deque.addFirst("blabla");
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 6);
    }

    @Test
    public void testRemoveFirst() {
        deque.addFirst("blabla");

        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 1);

        Object obj = deque.removeFirst();
        assertNotNull(obj);
        assertEquals("blabla", obj);

        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testRemoveLast() {
        deque.addLast("blabla");

        assertFalse(deque.isEmpty());
        assertTrue(deque.size() != 0);
        assertTrue(deque.size() == 1);

        Object obj = deque.removeLast();
        assertNotNull(obj);
        assertEquals("blabla", obj);

        assertTrue(deque.isEmpty());
        assertTrue(deque.size() == 0);
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

        Iterator iterator = deque.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("item7", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("item6", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("item3", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("item1", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("item2", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("item4", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("item5", iterator.next());

        assertFalse(iterator.hasNext());
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
    public void testMixed() {
        deque.addLast("9");
        deque.addFirst("1");
        deque.addLast("8");
        deque.addFirst("2");

        Object o = deque.removeLast();
        assertEquals((String)o, "8");
//        deque.removeFirst();
        Iterator iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "2");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "1");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "9");
    }

}
