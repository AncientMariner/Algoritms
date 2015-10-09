package collinear;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;

public class FastCollinearPoints {
   private int numberOfSegments;
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

       List<Double> slopesList = new ArrayList<>();
       for (int i = 0; i < points.length - 1; i++) {
           slopesList.add(points[i].slopeTo(points[i + 1]));
       }
       double[] slopes = new double[slopesList.size()];
       for (int i = 0; i < slopesList.size(); i++) {
           slopes[i] = slopesList.get(i);
       }
       Arrays.sort(slopes);

       lineSegments = new LineSegment[slopes.length];
       for (int i = 1; i < slopes.length - 1; i++) {
          if (slopes[i - 1] == slopes[i]
              && slopes[i] == slopes[i + 1]
              && slopes[i - 1] == slopes[i + 1]) {
              //should it be 3 or 4 points ????
                 if (numberOfSegments < slopes.length) {
                  lineSegments[numberOfSegments++] =
                          new LineSegment(points[i - 1], points[i + 1]);
              }
          }
       }
   } // finds all line segments containing 4 or more points

   public int numberOfSegments() {
       return numberOfSegments;
   } // the number of line segments

   public LineSegment[] segments() {
       LineSegment[] copyOfLineSegments = new LineSegment[numberOfSegments];
       System.arraycopy(lineSegments, 0, copyOfLineSegments, 0, numberOfSegments);
       return copyOfLineSegments;
   } // the line segments
}