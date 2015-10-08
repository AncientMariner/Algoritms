package collinear;

import org.junit.Test;

import static org.junit.Assert.*;

public class BruteCollinearPointsTest {
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

    @Test(expected = UnsupportedOperationException.class)
    public void testBigSize() {
        new BruteCollinearPoints(new Point[]{
                new Point(1, 2),
                new Point(12, 2),
                new Point(13, 2),
                new Point(21, 1),
                new Point(15, 2)});
    }


    @Test
    public void testForSizeOne() throws Exception {
        new BruteCollinearPoints(new Point[]{new Point(1, 2)});
    }

    @Test
    public void testSegments() throws Exception {
        Point point11 = new Point(1, 1);
        Point point22 = new Point(2, 2);
        Point point33 = new Point(3, 3);
        Point point44 = new Point(4, 4);
        BruteCollinearPoints bruteCollinearPoints =
                new BruteCollinearPoints(new Point[]{point11, point22, point33, point44});
        assertTrue(bruteCollinearPoints.numberOfSegments() != 0);
        LineSegment[] actualSegments = bruteCollinearPoints.segments();
        assertTrue(actualSegments.length > 0);
        assertEquals("(1, 1) -> (4, 4)", actualSegments[0].toString());
    }

    @Test
    public void testSegmentsNotCollinear() {
        Point point11 = new Point(1, 1);
        Point point22 = new Point(3, 4);
        Point point33 = new Point(5, 9);
        Point point44 = new Point(14, 24);
        BruteCollinearPoints bruteCollinearPoints =
                new BruteCollinearPoints(new Point[]{point11, point22, point33, point44});
        assertTrue(bruteCollinearPoints.numberOfSegments() == 0);
    }
}
