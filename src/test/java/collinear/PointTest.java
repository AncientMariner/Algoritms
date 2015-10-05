package collinear;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PointTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testBasicBehaviour() {
        Point point;
        int x = 0;
        int y = 0;
        point = new Point(x, y);

        assertNotNull(point);
        assertEquals("(" + x + ", " + y + ")", point.toString());
    }


    @Test
    public void testDraw() throws Exception {

    }

    @Test
    public void testDrawTo() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testCompareTo() throws Exception {
        int x0 = 0;
        int y0 = 0;
        int x1 = 1;
        int y1 = 1;
        int x2 = 1;
        int y2 = 0;
        Point point1 = new Point(x0, y0);
        Point point2 = new Point(x1, y1);
        Point point3 = new Point(x2, y2);

        int negativeResult = point1.compareTo(point2);
        assertTrue(negativeResult < 0);

        int anotherNegativeResult = point1.compareTo(point2);
        assertTrue(anotherNegativeResult < 0);

        assertTrue(point1.compareTo(point1) == 0);
        assertTrue(point2.compareTo(point1) > 0);
        assertTrue(point3.compareTo(point1) > 0);
    }

    @Test
    public void testSlopeTo() throws Exception {

    }

    @Test
    public void testSlopeOrder() throws Exception {

    }
}
