package collinear;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;

public class BruteCollinearPoints {
    private Point[] points;
    private LineSegment[] lineSegments;
    private int numberOfSegments = 0;

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
        Set<Point> pointSet = new TreeSet<>();
        if (points.length > 1) {
            for (int i = 0; i < points.length; i++) {
                pointSet.add(points[i]);
            }
            if (pointSet.size() != points.length) {
                throw new IllegalArgumentException("there is a duplicate");
            }
        }
        if (points.length == 1) {
            return;
        }
        List<Point> pointList = new ArrayList<>(pointSet);
        this.points = new Point[pointList.size()];
        for (int i = 0; i < pointList.size(); i++) {
            this.points[i] = pointList.get(i);
        }
        Arrays.sort(this.points);

        lineSegments = new LineSegment[this.points.length];

        double slope01, slope02, slope03;

        if (this.points.length == 4) {
            slope01 = this.points[0].slopeTo(this.points[1]);
            slope02 = this.points[0].slopeTo(this.points[2]);
            if (slope01 == slope02)
                slope03 = this.points[0].slopeTo(this.points[3]);
            else
                return;
            if (slope01 == slope02 && slope02 == slope03) {
                if (numberOfSegments < this.points.length) {
                    lineSegments[numberOfSegments++]
                            = new LineSegment(this.points[0], this.points[3]);
                }
            }
        } else {
            for (int i = 0; i < this.points.length; i++) {
                for (int j = 0 + i; j < this.points.length; j++) {
                    for (int k = 0 + j; k < this.points.length; k++) {
                        for (int l = 0 + k; l < this.points.length; l++) {
                            if (i != j && i != k && i != l && j != k && k != l) {
                                    slope01 = this.points[i].slopeTo(this.points[j]);
                                    slope02 = this.points[i].slopeTo(this.points[k]);
                                    if (slope01 == slope02)
                                        slope03 = this.points[i].slopeTo(this.points[l]);
                                    else
                                        continue;
                                    if (slope01 == slope02 && slope02 == slope03) {
                                        if (numberOfSegments < this.points.length) {
                                            lineSegments[numberOfSegments++]
                                                    = new LineSegment(this.points[i],
                                                    this.points[l]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } // finds all line segments containing 4 points

    public LineSegment[] segments() {
        LineSegment[] copyOfLineSegments = new LineSegment[numberOfSegments];
        if(lineSegments != null)
            System.arraycopy(lineSegments, 0, copyOfLineSegments, 0, numberOfSegments);
        return copyOfLineSegments;
    } // the line segments

    public int numberOfSegments() {
        return numberOfSegments;
    } // the number of line segments
}