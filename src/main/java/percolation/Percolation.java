package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation class. Backed by Weighted Quick Union model
 * which used to store connection between open sites
 */
public class Percolation {
    /**
     * stores open sites
     */
    private final boolean[][] grid;
    /**
     * connection between open sites
     */
    private final WeightedQuickUnionUF model;
    /**
     * virtual top element
     */
    private final int topVirtualElement;
    /**
     * virtual bottom element
     */
    private final int bottomVirtualElement;
    /**
     * beginning index
     */
    private int begin = 1;
    /**
     * ending index
     */
    private int end;

    /**
     * creates N-by-N grid, with all sites blocked
     * @param n - size of the grid
     */
    public Percolation(int n) {
        if (n <= 0) {
           throw new IllegalArgumentException("Size could not be 0 " +
                                                   "or less than 0");
        }
        end = n + 1;

        topVirtualElement = end * end;
        bottomVirtualElement = end * end + 1;
        grid = new boolean[end][end];
        model = new WeightedQuickUnionUF(end * end + 2);
    }

    /**
     * opens an element in grid
     * @param i - row
     * @param j - column
     */
    public void open(final int i, final int  j) {
        checkBoundary(i, j);

        if (!isOpen(i, j)) {
            grid[i][j] = true;
        }

        connectVirtualTop(i, j);
        connectVirtualBottom(i, j);

        connectLeftNeighbour(i, j);
        connectRightNeighbour(i, j);
        connectTopNeighbour(i, j);
        connectBottomNeighbour(i, j);
    }

    /**
     * checks whether element is opened
     * @param i - row
     * @param j - column
     * @return true if opened
     */
    public boolean isOpen(final int i, final int j) {
        checkBoundary(i, j);
        return grid[i][j];
    }

    /**
     * checks whether grid percolates
     * @return true if percolates
     */
    public boolean percolates() {
        return model.connected(topVirtualElement, bottomVirtualElement);
    }

    /**
     * checks whether element is full
     * @param i - row
     * @param j - column
     * @return true is is full
     */
    public boolean isFull(final int i, final int j) {
        checkBoundary(i, j);
        if (isOpen(i, j)) {
            return model.connected(model.find(getAbsoluteCoordinate(i, j)), topVirtualElement);
        }
        return false;
    }

    /**
     * connects current element to the virtual top element
     * @param i - row
     * @param j - column
     */
    private void connectVirtualTop(final int  i, final int j) {
        int current = getAbsoluteCoordinate(i, j);
        if (i == begin) {
            model.union(current, topVirtualElement);
        }
    }

    /**
     * connects current element to the virtual bottom element
     * @param i - row
     * @param j - column
     */
    private void connectVirtualBottom(final int  i, final int  j) {
        int current = getAbsoluteCoordinate(i, j);
        if (i == end - 1) {
            model.union(current, bottomVirtualElement);
        }
    }

    /**
     * checks whether left neighbour is existed
     * and connects it to the current element
     * @param i - row
     * @param j - column
     */
    private void connectLeftNeighbour(final int i, final int j) {
        int current = getAbsoluteCoordinate(i, j);
        if (j > begin && isOpen(i, j - 1)) {
            model.union(current, model.find(checkNeighbour(i, j - 1)));
        }
    }

    /**
     * checks whether right neighbour is existed
     * and connects it to the current element
     * @param i - row
     * @param j - column
     */
    private void connectRightNeighbour(final int i, final int j) {
        int current = getAbsoluteCoordinate(i, j);
        if (j < end - 1 && isOpen(i, j + 1)) {
            model.union(current, model.find(checkNeighbour(i, j + 1)));
        }
    }

    /**
     * checks whether top neighbour is existed
     * and connects it to the current element
     * @param i - row
     * @param j - column
     */
    private void connectTopNeighbour(final int i, final int j) {
        int current = getAbsoluteCoordinate(i, j);
        if (i > begin && isOpen(i - 1, j)) {
            model.union(current, model.find(checkNeighbour(i - 1, j)));
        }
    }

    /**
     * checks whether bottom neighbour is existed
     * and connects it to the current element
     * @param i - row
     * @param j - column
     */
    private void connectBottomNeighbour(final int i, final int j) {
        int current = getAbsoluteCoordinate(i, j);
        if (i < end - 1 && isOpen(i + 1, j)) {
            model.union(current, model.find(checkNeighbour(i + 1, j)));
        }
    }

    /**
     * gets an absolute coordinate from i and j
     * @param i - row
     * @param j - column
     * @return value of the absolute coordinate
     */
    private int getAbsoluteCoordinate(final int i, final int j) {
        return i * end + j;
    }


    /**
     * checks neighbour is existing element
     * @param i - row
     * @param j - column
     * @return absolute coordinate of the neighbour
     */
    private int checkNeighbour(final int i, final int j) {
        checkBoundary(i, j);
        return getAbsoluteCoordinate(i, j);
    }

    /**
     * checks boundary of the element is not violated
     * @param i - row
     * @param j - column
     */
    private void checkBoundary(final int i, final int j) {
        if (i < begin || i > end || j < begin || j > end) {
            throw new IndexOutOfBoundsException("Index is out of bounds "
                                                 + i + " " + j);
        }
    }
}

