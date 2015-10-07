package collinear;

public class BruteCollinearPoints {
   public BruteCollinearPoints(Point[] points) {
       if (points == null || points.length == 0) {
           throw new NullPointerException("points are null");
       }
       if (points.length > 0) {
           for (int i = 0; i < points.length; i++) {
               if (points[i] == null) {
                   throw new NullPointerException("points are null");
               }
           }
       }
       if (points.length > 1) {
           for (int i = 0; i < points.length; i++) {
               for (int j = 0 + i; j < points.length; j++) {
                   if (points[i].compareTo(points[j]) == 0) {
                       throw new IllegalArgumentException("there is a duplicate");
                   }
               }
           }
       }

   } // finds all line segments containing 4 points

   public int numberOfSegments() {
       return -1;
   } // the number of line segments

   public LineSegment[] segments() {
       return null;
   } // the line segments
}