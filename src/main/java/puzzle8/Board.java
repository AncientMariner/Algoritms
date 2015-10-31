package puzzle8;

public class Board {
    int[][] blocks;
    int dimension;
    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new IllegalStateException("it is not possible to create empty block");
        }
        this.blocks = blocks;
        dimension = (int) Math.sqrt(blocks.length);
    } // construct a board from an N-by-N array of blocks
      // (where blocks[i][j] = block in row i, column j)

    public int dimension() {
        if (dimension <= 0) {
            throw new IllegalStateException("board was not created");
        }
        return dimension;
    } // board dimension N

    public int hamming() {
        return 0;
    } // number of blocks out of place

    public int manhattan() {
        return 0;
    } // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        return false;
    } // is this board the goal board?

    public Board twin() {
        return null;
    } // a board that is obtained by exchanging any pair of blocks

    public boolean equals(Object y) {
        return false;
    } // does this board equal y?

    public Iterable<Board> neighbors() {
        return null;
    } // all neighboring boards

    public String toString() {
        return "toString";
    } // string representation of this board (in the output format specified below)

    public static void main(String[] args) {
    }// unit tests (not graded)
}