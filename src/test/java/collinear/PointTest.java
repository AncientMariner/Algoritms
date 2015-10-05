package collinear;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

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
    public void testSlopeOrderSimilar() throws Exception {
        int x1 = 0; int y1= 0;
        Point point1 = new Point(x1, y1);

        Comparator<Point> pointComparator1 = point1.slopeOrder();

        int x4 = 3; int y4 = 3;
        Point point4 = new Point(x4, y4);

        int x5 = 2; int y5 = 2;
        Point point5 = new Point(x5, y5);

        double slope3 = point1.slopeTo(point5);
        double slope4 = point1.slopeTo(point4);
        assertTrue(slope3 == slope4);
        assertTrue(slope4 == slope3);

        int compare3 = pointComparator1.compare(point4, point5);
        int compare4 = pointComparator1.compare(point5, point4);
        assertTrue(compare3 == 0);
        assertTrue(compare4 == 0);
    }

    @Test
    public void testFirstSlopeIsHigherThanSecond() {
        int x1 = 0; int y1= 0;
        Point point1 = new Point(x1, y1);

        Comparator<Point> pointComparator1 = point1.slopeOrder();

        int x6 = 3; int y6 = 6;
        Point point6 = new Point(x6, y6);

        int x7 = 2; int y7 = 1;
        Point point7 = new Point(x7, y7);

        double slope5 = point1.slopeTo(point6);
        double slope6 = point1.slopeTo(point7);
        assertTrue(slope5 > slope6);
        assertTrue(slope6 < slope5);

        int compare5 = pointComparator1.compare(point6, point7);
        int compare6 = pointComparator1.compare(point7, point6);
        assertTrue(compare5 > 0);
        assertTrue(compare6 < 0);
    }

    @Test
    public void testSecondSlopeIsHigherThanFirst() {
        int x1 = 0; int y1= 0;
        Point point1 = new Point(x1, y1);

        int x2 = 1; int y2 = 1;
        Point point2 = new Point(x2, y2);

        int x3 = 2; int y3 = 3;
        Point point3 = new Point(x3, y3);

        Comparator<Point> pointComparator1 = point1.slopeOrder();

        double slope1 = point1.slopeTo(point2);
        double slope2 = point1.slopeTo(point3);
        assertTrue(slope1 < slope2);
        assertTrue(slope2 > slope1);

        int compare1 = pointComparator1.compare(point2, point3);
        int compare2 = pointComparator1.compare(point3, point2);
        assertTrue(compare1 < 0);
        assertTrue(compare2 > 0);
    }

    @Test
    public void testPositiveInfSlopeOrder() {
        int x1 = 0; int y1= 0;
        Point point1 = new Point(x1, y1);

        Comparator<Point> pointComparator1 = point1.slopeOrder();

        int x9 = 0; int y9 = 5;
        Point point9 = new Point(x9, y9);

        int x10 = 0; int y10 = 6;
        Point point10 = new Point(x10, y10);

        double slope9 = point1.slopeTo(point9);
        double slope10 = point1.slopeTo(point9);
        assertTrue(slope9 == slope10);
        assertTrue(slope10 == slope9);

        double compare9 = pointComparator1.compare(point10, point9);
        double compare10 = pointComparator1.compare(point9, point10);
        assertTrue(compare9 == compare10);
    }

    @Test
    public void testNegativeInfSlopeOrder() {
        int x1 = 0; int y1= 0;
        Point point1 = new Point(x1, y1);

        Comparator<Point> pointComparator1 = point1.slopeOrder();

        int x8 = 0; int y8 = 0;
        Point point8 = new Point(x8, y8);

        double slope7 = point1.slopeTo(point8);
        double slope8 = point1.slopeTo(point8);
        assertTrue(slope7 == slope8);
        assertTrue(slope8 == slope7);

        double compare7 = pointComparator1.compare(point8, point8);
        double compare8 = pointComparator1.compare(point8, point8);
        assertTrue(compare7 == compare8);
    }

    @Test
    public void testDraw() throws Exception {

    }

    @Test
    public void testDrawTo() throws Exception {

    }
}
