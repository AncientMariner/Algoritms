package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PercolationE {

    private final WeightedQuickUnionUF model;
    private final boolean [] [] grid;
    private final int n;
    private final int topNode;
    private final int bottomNode;

    private int openSiteCounter;
    // create size-by-size grid, with all sites blocked
    public PercolationE(int N)  {
        if(N <= 0) {
            throw new IllegalArgumentException("N must be greater than ZERO");
        }
        n = N;
        grid = new boolean [N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
        model = new WeightedQuickUnionUF(N * N + 2);
        topNode = N * N;
        bottomNode = N * N + 1;
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        if(i < 0 || i > n || j < 0 || j > n) {
            throw new IndexOutOfBoundsException("i and j must be greater than ZERO and less than N");
        }
        //System.out.println(String.format("Open i=%s, j=%s", i, j));
        if(isOpen(i, j)) {
            return;
        }
        grid[i][j] = true;
        openSiteCounter++;
        int current = i * n + j;
        if(i == 0) {
            model.union(topNode, current);
        }
        if(i == n - 1) {
            model.union(bottomNode, current);
        }

        if(i > 0 && isOpen(i - 1, j)) {
            int up = getIndex(i - 1, j);
            model.union(current, up);
        }
        if(i < n -1 && isOpen(i + 1, j)) {
            int down = getIndex(i + 1, j);
            model.union(current, down);
        }
        if(j > 0 && isOpen(i, j - 1)) {
            int left = getIndex(i, j - 1);
            model.union(current, left);
        }
        if(j < n -1 && isOpen(i, j + 1)) {
            int right = getIndex(i, j + 1);
            model.union(current, right);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if(i < 0 || i > n || j < 0 || j > n) {
            throw new IndexOutOfBoundsException("i and j must be greater than ZERO and less than N");
        }
        return grid[i][j];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        if(!isOpen(i, j)) {
            return false;
        }
        return model.connected(getIndex(i, j), topNode);
    }

    // does the system percolate?
    public boolean percolates() {
        return model.connected(topNode, bottomNode);
    }

    private int getIndex(int i, int j) {
        if(i < 0 || i > n || j < 0 || j > n) {
            throw new IndexOutOfBoundsException("i and j must be greater than ZERO and less than N");
        }
        return i * n + j;
    }

    private void printGrid() {
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j]) {
                    System.out.print(grid[i][j] + "\t");
                } else {
                    System.out.print(grid[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    // test client (optional)
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        System.setOut(new PrintStream(new FileOutputStream("output.log")));
        for(int i = 0; i < 10; i++) {
            final int n = 5;
            PercolationE percolation = new PercolationE(n);
            while(!percolation.percolates()) {
                //System.out.println("-------------");
                percolation.open(StdRandom.uniform(n), StdRandom.uniform(n));
                //percolation.printGrid();
                //Thread.sleep(5000);
                //System.out.println("\n");
            }
            System.out.println("openSiteCounter = " + percolation.openSiteCounter);
            percolation.printGrid();
            System.out.println();
        }
    }
}