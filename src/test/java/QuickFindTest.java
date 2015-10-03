package test.java;

import main.java.QuickFind;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuickFindTest {
    @Test
    public void testIsConnected() {
        QuickFind qf = new QuickFind(5);
        assertFalse(qf.isConnected(1, 2));
    }

    @Test
    public void testUnion() {
        QuickFind qf = new QuickFind(5);
        assertFalse(qf.isConnected(1, 2));

        qf.union(1, 2);
        assertTrue(qf.isConnected(1, 2));
    }
}
