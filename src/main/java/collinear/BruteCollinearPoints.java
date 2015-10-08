package collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private Point[] points;
    private LineSegment[] lineSegments;
    private int numberOfSegments;

    public BruteCollinearPoints(Point[] points) {
        if (checkForCornerCases(points)) return;

        double slope01;
        double slope12;
        double slope23;
        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) != 0) {
                pointList.add(points[i]);
            }
        }
        if (points[points.length - 1].compareTo(points[0]) != 0) {
            pointList.add(points[points.length - 1]);
        }
        this.points = new Point[pointList.size()];
        for (int i = 0; i < pointList.size(); i++) {
            this.points[i] = pointList.get(i);
        }

        Arrays.sort(this.points);
        lineSegments = new LineSegment[this.points.length];

        Point max = this.points[0];
        for (int i = 0; i < this.points.length; i++) {
            for (int j = 0 + i; j < this.points.length; j++) {
                for (int k = 0 + j; k < this.points.length; k++) {
                    for (int l = 0 + k; l < this.points.length; l++) {
                        if (i != j && i != k && i != l
                                && j != k && j != l
                                && k != l) {
                            slope01 = this.points[i].slopeTo(this.points[j]);
                            slope12 = this.points[j].slopeTo(this.points[k]);
                            slope23 = this.points[k].slopeTo(this.points[l]);
                            if (slope01 == slope12 && slope12 == slope23
                             && slope01 == slope23) {
                                if (numberOfSegments < this.points.length) {
                                    if (max.compareTo(this.points[l]) < 0) {
                                        max = this.points[l];
                                        lineSegments[numberOfSegments++]
                                        = new LineSegment(this.points[i], max);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } // finds all line segments containing 4 points

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
            for (int i = 0; i < points.length; i++) {
                for (int j = 0 + i; j < points.length; j++) {
                    if (i != j && points[i].compareTo(points[j]) == 0) {
                        throw new IllegalArgumentException("there is a duplicate");
                    }
                }
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