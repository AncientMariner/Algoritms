package collinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class FastCollinearPoints {
   private LineSegment[] lineSegments;

   public FastCollinearPoints(Point[] points) {
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
           Set<Point> pointSet = new TreeSet<>();
           for (int i = 0; i < points.length; i++) {
               pointSet.add(points[i]);
           }
           if (pointSet.size() != points.length) {
               throw new IllegalArgumentException("there is a duplicate");
           }
       }
       if (points.length == 1) {
           return; // no way to figure out the collinear point
       }
       Point[] pointsCopy = points.clone();
       processPoints(pointsCopy);
   } // finds all line segments containing 4 or more points

    private void processPoints(Point[] points) {
        final int n = points.length;

        SlopeStore slopeStore = new SlopeStore(n);
        ArrayList<LineSegment> temp = new ArrayList<>(n / 4);

        LineSegment tempSegment = null;
        for (int i = 0; i < n; i++) {
            Arrays.sort(points);
            Arrays.sort(points, i + 1, n, points[i].slopeOrder());

            for (int j = i + 1; j < n; j++) {
                double slopeI2J = points[i].slopeTo(points[j]);

                for (int k = j + 1; k < n; k++) {
                    double slopeI2K = points[i].slopeTo(points[k]);
                    if (slopeI2J != slopeI2K) {
                        continue;
                    }
                    for (int p = k + 1; p < n; p++) {
                        double slopeI2P = points[i].slopeTo(points[p]);
                        if (slopeI2K == slopeI2P) {
                            if (slopeStore.containsSlope(slopeI2J)) {
                                if(slopeStore.containsPointInSlope(slopeI2J, points[i])) {
                                    continue;
                                }
                            }
                            slopeStore.append(slopeI2J, points[p]);
                            tempSegment = new LineSegment(points[i], points[p]);
                        }
                    }
                    if (tempSegment != null) {
                        temp.add(tempSegment);
                        tempSegment = null;
                        slopeStore.append(slopeI2J, points[i], points[j], points[k]);
                    }
                }
            }
        }

        temp.trimToSize();
        lineSegments = new LineSegment[temp.size()];
        temp.toArray(lineSegments);
    }

    private class SlopeStore {
        private final Map<Double, SortedSet<Point>> slopeToPoint;

        private SlopeStore(int expectedSize) {
            slopeToPoint = new HashMap<>(expectedSize);
        }

        public void remember(double slope, Point... point) {
            slopeToPoint.put(slope, new TreeSet<>(Arrays.asList(point)));
        }

        public void append(double slope, Point... point) {
            if (slopeToPoint.containsKey(slope)) {
                slopeToPoint.get(slope).addAll(Arrays.asList(point));
            } else {
                remember(slope, point);
            }
        }

        public boolean containsSlope(double slope) {
            return slopeToPoint.containsKey(slope);
        }

        public boolean containsPointInSlope(double slope, Point point) {
            if (slopeToPoint.containsKey(slope)) {
                for (Point p : slopeToPoint.get(slope)) {
                    if (p.compareTo(point) == 0) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

   public int numberOfSegments() {
       return lineSegments.length;
   } // the number of line segments

   public LineSegment[] segments() {
       return lineSegments.clone();
   } // the line segments


    public static void main(String[] args) {

        // read the N points from a file
        In in = new In("collinear/kw1260.txt");
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            if (segment != null) {
                StdOut.println(segment);
                segment.draw();
            }
        }
        StdOut.println("numberOfSegments = " + collinear.numberOfSegments());
//        StdOut.println(Arrays.toString(points));
    }
}