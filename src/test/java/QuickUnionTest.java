package test.java;

import main.java.QuickUnion;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuickUnionTest {
    @Test
    public void testIsConnected() throws Exception {
        QuickUnion qu = new QuickUnion(3);
        assertFalse(qu.isConnected(1, 2));
    }

    @Test
    public void testUnion() throws Exception {
        QuickUnion qu = new QuickUnion(3);
        assertFalse(qu.isConnected(1, 2));
        qu.union(1, 2);
        assertTrue(qu.isConnected(1, 2));

    }
}
