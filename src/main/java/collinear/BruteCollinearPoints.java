package collinear;

public class BruteCollinearPoints {
    private Point[] points;
    private LineSegment[] lineSegments;
    private int numberOfSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null || points.length == 0) {
            throw new NullPointerException("there are no points");
        }
        if (points.length > 0) {
            for (int i = 0; i < points.length; i++) {
                if (points[i] == null) {
                    throw new NullPointerException("one of the points is null");
                }
            }
        }
        if (points.length > 1) {
            for (int i = 0; i < points.length; i++) {
                for (int j = 0 + i; j < points.length; j++) {
                    if (i != j && points[i].compareTo(points[j]) == 0) {
                        throw new IllegalArgumentException("there is a duplicate");
                    }
                }
            }
        }
        if (points.length > 4) {
            throw new UnsupportedOperationException("size is too big");
        }
        if (points.length == 1) {
            return; // no way to figure out the collinear point
        }

        this.points = points;
    } // finds all line segments containing 4 points

    public LineSegment[] segments() {
        double slope01;
        double slope12;
        double slope23;
        lineSegments = new LineSegment[points.length];

        for (int i = 0; i < points.length; i++) {
            for (int j = 0 + i; j < points.length; j++) {
                for (int k = 0 + j; k < points.length; k++) {
                    for (int l = 0 + k; l < points.length; l++) {
                        if (i != j && i != k && i != l
                                && j != k && j != l
                                && k != l) {
                            slope01 = points[i].slopeTo(points[j]);
                            slope12 = points[j].slopeTo(points[k]);
                            slope23 = points[k].slopeTo(points[l]);
                            if (slope01 == slope12
                                    && slope12 == slope23
                                    && slope01 == slope23) {
                                lineSegments[numberOfSegments++] =
                                        new LineSegment(points[i], points[l]);
                            }
                        }
                    }
                }
            }
        } // should this transformation be in the constructor ???


        return this.lineSegments;
    } // the line segments

    public int numberOfSegments() {
        return numberOfSegments;
    } // the number of line segments
}