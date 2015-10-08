package collinear;

import java.util.*;

public class BruteCollinearPoints {
    private Point[] points;
    private LineSegment[] lineSegments;
    private int numberOfSegments;

    public BruteCollinearPoints(Point[] points) {
        if (checkForCornerCases(points)) return;

        List<Point> pointList = identifyUniquePoints(points);

        this.points = new Point[pointList.size()];
        for (int i = 0; i < pointList.size(); i++) {
            this.points[i] = pointList.get(i);
        }
        Arrays.sort(this.points);

        lineSegments = new LineSegment[this.points.length];

        double slope01, slope02, slope03 = -1;
        Point prevJ = this.points[0], prevK = prevJ, prevL = prevK;

        for (int i = 0; i < this.points.length; i++) {
            for (int j = 0 + i; j < this.points.length; j++) {
                for (int k = 0 + j; k < this.points.length; k++) {
                    for (int l = 0 + k; l < this.points.length; l++) {
                        if(i < j
                                && i < k && i < l
                                                 && j < k && j < l
                                                                   && k < l) {
                            if (prevJ.compareTo(this.points[j]) != 0
                                    && prevK.compareTo(this.points[k]) != 0
                                    && prevL.compareTo(this.points[l]) != 0) {
                                slope01 = this.points[i].slopeTo(this.points[j]);
                                slope02 = this.points[i].slopeTo(this.points[k]);
                                if(slope01 == slope02)
                                    slope03 = this.points[i].slopeTo(this.points[l]);
                                if (slope01 == slope02 && slope02 == slope03 && slope01 == slope03) {
                                    if (numberOfSegments < this.points.length) {
                                        lineSegments[numberOfSegments++]
                                                = new LineSegment(this.points[i], this.points[l]);
                                        prevJ = this.points[j];
                                        prevK = this.points[k];
                                        prevL = this.points[l];
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } // finds all line segments containing 4 points

    private List<Point> identifyUniquePoints(Point[] points) {
        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) != 0) {
                pointList.add(points[i]);
            }
        }
        if (points[points.length - 1].compareTo(points[0]) != 0) {
            pointList.add(points[points.length - 1]);
        }
        return pointList;
    }

    private boolean checkForCornerCases(Point[] points) {
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
            return true;
        }
        return false;
    }

    public LineSegment[] segments() {
        LineSegment[] copyOfLineSegments = new LineSegment[numberOfSegments];
        System.arraycopy(lineSegments, 0, copyOfLineSegments, 0, numberOfSegments);
        return copyOfLineSegments;
    } // the line segments

    public int numberOfSegments() {
        return numberOfSegments;
    } // the number of line segments
}