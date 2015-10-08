package collinear;

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
           for (int i = 0; i < points.length; i++) {
               for (int j = 0 + i; j < points.length; j++) {
                   if (i != j && points[i].compareTo(points[j]) == 0) {
                       throw new IllegalArgumentException("there is a duplicate");
                   }
               }
           }
       }
       if (points.length == 1) {
           return; // no way to figure out the collinear point
       }

       double[] slopes = new double[points.length - 1];
       for (int i = 0; i < points.length - 1; i++) {  // += 2 ????
           slopes[i] = points[i].slopeTo(points[i + 1]);
       }
       Arrays.sort(slopes);
       lineSegments = new LineSegment[points.length];
       for (int i = 1; i < slopes.length - 1; i++) {
          if(slopes[i -1] == slopes[i]
          && slopes[i] == slopes[i + 1]
          && slopes[i - 1] == slopes[i + 1]) {
              //should it be 3 or 4 points ????
              if (i + 2 < slopes.length
                      && slopes[i + 1] == slopes[i + 2]) {
                  lineSegments[numberOfSegments++] =
                          new LineSegment(points[i - 1], points[i + 2]);
              }
          }
       }
   } // finds all line segments containing 4 or more points

   public int numberOfSegments() {
       return numberOfSegments;
   } // the number of line segments

   public LineSegment[] segments() {
       return lineSegments;
   } // the line segments
}