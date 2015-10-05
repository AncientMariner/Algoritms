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
    }


    @Test
    public void testDraw() throws Exception {

    }

    @Test
    public void testDrawTo() throws Exception {

    }

    @Test
    public void testToString() throws Exception {
        Point point;
        int x = 0;
        int y = 0;
        point = new Point(x, y);

        assertNotNull(point);
        assertEquals("(" + x + ", " + y + ")", point.toString());
    }

    @Test
    public void testCompareTo() throws Exception {
        int x1 = 0; int y1= 0;
        Point point1 = new Point(x1, y1);

        int x2 = 1; int y2 = 1;
        Point point2 = new Point(x2, y2);

        int x3 = 1; int y3 = 0;
        Point point3 = new Point(x3, y3);

        int negativeResult = point1.compareTo(point2);
        assertTrue(negativeResult < 0);

        int anotherNegativeResult = point1.compareTo(point2);
        assertTrue(anotherNegativeResult < 0);

        assertTrue(point1.compareTo(point1) == 0);
        assertTrue(point2.compareTo(point2) == 0);
        assertTrue(point3.compareTo(point3) == 0);
        assertTrue(point2.compareTo(point1) > 0);
        assertTrue(point2.compareTo(point3) > 0);
        assertTrue(point3.compareTo(point2) < 0);
        assertTrue(point3.compareTo(point1) > 0);
        assertTrue(point1.compareTo(point3) < 0);
    }

    @Test
    public void testSlopeTo() throws Exception {
        int x1 = 0; int y1= 0;
        Point point1 = new Point(x1, y1);

        int x2 = 1; int y2 = 1;
        Point point2 = new Point(x2, y2);

        int x3 = 2; int y3 = 3;
        Point point3 = new Point(x3, y3);

        double expectedSlopeP1P2 = (((double) (y2 - y1)) / ((double) (x2 - x1)));
        double expectedSlopeP2P1 = ((double) (y1 - y2) / ((double) (x1 - x2)));

        double expectedSlopeP2P3 = ((double) (y3 - y2) / ((double) (x3 - x2)));
        double expectedSlopeP3P2 = ((double) (y2 - y3) / ((double) (x2 - x3)));

        double expectedSlopeP1P3 = ((double) (y3 - y1) / ((double) (x3 - x1)));
        double expectedSlopeP3P1 = ((double) (y1 - y3) / ((double) (x1 - x3)));

        assertEquals(expectedSlopeP1P2, point1.slopeTo(point2), 0.001);
        assertEquals(expectedSlopeP2P1, point2.slopeTo(point1), 0.001);
        assertEquals(point1.slopeTo(point2), point2.slopeTo(point1), 0.001);

        assertEquals(expectedSlopeP3P1, point3.slopeTo(point1), 0.001);
        assertEquals(expectedSlopeP1P3, point1.slopeTo(point3), 0.001);
        assertEquals(point1.slopeTo(point3), point3.slopeTo(point1), 0.001);

        assertEquals(expectedSlopeP2P3, point2.slopeTo(point3), 0.001);
        assertEquals(expectedSlopeP3P2, point3.slopeTo(point2), 0.001);
        assertEquals(point3.slopeTo(point2), point2.slopeTo(point3), 0.001);
    }

    @Test
    public void testSlopeCornerCases() {
        int x1 = 0; int y1= 0;
        Point point1 = new Point(x1, y1);

        int x2 = 1; int y2 = 1;
        Point point2 = new Point(x2, y2);

        int x3 = 1; int y3 = 0;
        Point point3 = new Point(x3, y3);

        double delta = 0.0001;

        double positiveZero1 = point1.slopeTo(point3);
        double positiveZero2 = point3.slopeTo(point1);
        assertEquals(positiveZero1, positiveZero2, delta);
        assertEquals(positiveZero2, positiveZero1, delta);

        double positiveInfinity1 = point2.slopeTo(point3);
        double positiveInfinity2 = point3.slopeTo(point2);
        assertEquals(Double.POSITIVE_INFINITY, point3.slopeTo(point2), delta);
        assertEquals(Double.POSITIVE_INFINITY, point2.slopeTo(point3), delta);
        assertEquals(positiveInfinity1, positiveInfinity2, delta);
        assertEquals(positiveInfinity2, positiveInfinity1, delta);

        double negativeInfinity1 = point1.slopeTo(point1);
        double negativeInfinity2 = point2.slopeTo(point2);
        double negativeInfinity3 = point3.slopeTo(point3);
        assertEquals(negativeInfinity1, negativeInfinity2, delta);
        assertEquals(negativeInfinity2, negativeInfinity1, delta);
        assertEquals(negativeInfinity1, negativeInfinity3, delta);
        assertEquals(negativeInfinity3, negativeInfinity1, delta);
        assertEquals(negativeInfinity2, negativeInfinity3, delta);
        assertEquals(negativeInfinity3, negativeInfinity2, delta);
    }


    @Test
    public void testSlopeOrder() throws Exception {

    }
}
