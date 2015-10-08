package collinear;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class FastCollinearPointsTest {
    @Test
    public void testBasic() {
        assertNotNull(new FastCollinearPoints(new Point[]{new Point(1, 2)}));
    }

    @Test(expected = NullPointerException.class)
    public void testNegative1() {
        new FastCollinearPoints(new Point[]{null});
    }

    @Test(expected = NullPointerException.class)
    public void testNegative2() {
        new FastCollinearPoints(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegative3() {
        new FastCollinearPoints(new Point[]{new Point(1, 2), new Point(31, 2), new Point(1, 2)});
    }


    @Test
    public void testForSizeOne() throws Exception {
        new FastCollinearPoints(new Point[]{new Point(1, 2)});
    }


    @Test
    public void testNumberOfSegments() throws Exception {

    }

    @Test
    public void testSegments() throws Exception {

    }
}
