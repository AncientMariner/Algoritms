package collinear;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class BruteCollinearPointsTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testBasic() {
        assertNotNull(new BruteCollinearPoints(new Point[]{new Point(1, 2)}));
    }

    @Test(expected = NullPointerException.class)
    public void testNegative() {
        new BruteCollinearPoints(null);
    }

    @Test(expected = NullPointerException.class)
    public void testNegativePoints1() {
        new BruteCollinearPoints(new Point[]{});
    }

    @Test(expected = NullPointerException.class)
    public void testNegativePoints2() {
        new BruteCollinearPoints(new Point[]{null});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicate() {
        new BruteCollinearPoints(new Point[]{new Point(1, 2), new Point(2, 1), new Point(1, 2)});
    }

    @Test
    public void testNumberOfSegments() throws Exception {
    }

    @Test
    public void testSegments() throws Exception {
    }
}
