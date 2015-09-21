import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationStatsTest {
    @Test
    public void test() {
        PercolationStats percolationStats;
        for (int i = 1; i < 1000; i++) {
            percolationStats = new PercolationStats(i, 2);
            double mean = percolationStats.mean();
            double stddev = percolationStats.stddev();
            double min = percolationStats.confidenceLo();
            double max = percolationStats.confidenceHi();
            System.out.println();
            StdOut.println("mean " + mean);
            StdOut.println("stddev " + stddev);
            StdOut.println("95% confidence interval = " + min + " - " + max);
            percolationStats = null;
        }
    }
}