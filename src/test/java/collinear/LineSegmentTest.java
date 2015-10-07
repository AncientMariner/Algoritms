package collinear;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class LineSegmentTest {
    @Test
    public void testDraw() throws Exception {
        LineSegment lineSegment = new LineSegment(new Point(1, 2), new Point(3, 4));
        assertNotNull(lineSegment);
    }

    @Test
    public void testToString() throws Exception {
        LineSegment lineSegment = new LineSegment(new Point(1, 2), new Point(3, 4));
        assertEquals("(1, 2) -> (3, 4)", lineSegment.toString());

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testHashCode() throws Exception {
        new LineSegment(new Point(1, 2), new Point(3, 4)).hashCode();
    }
}
