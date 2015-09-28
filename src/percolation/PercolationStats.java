package percolation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Used to perform Monte-Carlo simulation upon N x N matrix
 */
public class PercolationStats {
    // threshold factor
    private static final double THRESHOLD_FACTOR = 1.96;
    //array of the threshold values
    private double[] threshold;
    //number of experiments
    private int numberOFExperiments;

    /**
     * performs independent experiments on an size-by-size grid
     * @param size number of grid
     * @param numberOfExperiments number of experiments
     */
    public PercolationStats(int size, int numberOfExperiments) {
        if (size <= 0 || numberOfExperiments <= 0) {
            throw new IllegalArgumentException("Please check the size " +
                    "and number of experiments");
        }

        threshold = new double[numberOfExperiments];
        this.numberOFExperiments = numberOfExperiments;

        for (int i = 0; i < numberOfExperiments; i++) {
            int openCounter = calculate(size);
            threshold[i] = (double) openCounter / (size * size);
        }
    }

    /**+
     * calculates the percolation of the grid
     * @param n size of the grid
     * @return number of opened positions
     */
    private int calculate(final int n) {
        Percolation percolation = new Percolation(n);
        int openCounter = 0;

        while (!percolation.percolates()) {
            int x = StdRandom.uniform(1, n + 1);
            int y = StdRandom.uniform(1, n + 1);

            if (!percolation.isOpen(x, y)) {
               percolation.open(x, y);
               openCounter++;
            }
        }
        return openCounter;
    }

    /**
     * calculates the percolation threshold
     * @return value of the threshold
     */
    public double mean() {
        return StdStats.mean(threshold);
    }

    /**
     * calculates the sample standard deviation of percolation threshold,
     * sharpness of the threshold
     * @return value of the sharpness of the threshold
     */
    public double stddev() {
        if (numberOFExperiments == 1) {
            return Double.NaN;
        }
        return StdStats.stddev(threshold);
    }

    /**
     * low endpoint of 95% confidence interval
     * @return value of the low endpoint interval
     */
    public double confidenceLo() {
        return mean() - (THRESHOLD_FACTOR * stddev()
                / Math.sqrt(numberOFExperiments));
    }

    /**
     * high endpoint of 95% confidence interval
     * @return value of the high endpoint interval
     */
    public double confidenceHi() {
        return mean() + (THRESHOLD_FACTOR * stddev()
                / Math.sqrt(numberOFExperiments));
    }

    /**
     *  test client
     * @param args values to enter
     */
    public static void main(String[] args) {
        StdOut.println("Please state the number of experiments ");
        int numberOfExperiments = StdIn.readInt();
        StdOut.println("Please state the size of area ");
        int size = StdIn.readInt();

        PercolationStats percolationStats = new PercolationStats(size, numberOfExperiments);
        double mean = percolationStats.mean();
        double stddev = percolationStats.stddev();
        double min = percolationStats.confidenceLo();
        double max = percolationStats.confidenceHi();

        StdOut.println("mean " + mean);
        StdOut.println("stddev " + stddev);
        StdOut.println("95% confidence interval = " + min + " - " + max);
    }
}
