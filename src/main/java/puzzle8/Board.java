package puzzle8;

import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

public class Board {
    final int[][] blocks;
    int dimension;
    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new IllegalStateException("it is not possible to create empty board");
        }
        this.blocks = blocks;
        dimension = (int) Math.sqrt(blocks.length * blocks[0].length);
    } // construct a board from an N-by-N array of blocks
      // (where blocks[i][j] = block in row i, column j)

    public int dimension() {
        if (dimension <= 0) {
            throw new IllegalStateException("board was not created");
        }
        return dimension;
    } // board dimension N

    public int hamming() {
        int hammingCount = 0;
        for (int[] block : blocks) {
            for (int i = 0; i < block.length; i++) {
                if (block[i] != 0 && block[i] != i + 1) {
                    hammingCount++;
                }
            }
        }
        return hammingCount; // number or moves, how to count ?
    } // number of blocks out of place

    public int manhattan() {
        int verticalCounter;
        int horizontalCounter;
        int manhattan = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != i * j + j + 1) {
                    horizontalCounter = blocks[i][j] % dimension - 1;
                    verticalCounter = blocks[i][j] / dimension;
                    manhattan += (verticalCounter + horizontalCounter);
                }
            }
        }
        return manhattan;
    } // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] != 0) {
                    if(blocks[i][j] == i * j + j + 1)
                        return true;
                }
            }
        }
        return false;
    } // is this board the goal board?

    public Board twin() {
        int left = 0;
        int lDimensionI = 0;
        int lDimensionJ = 0;
        outLeft:
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] != 0) {
                    left = blocks[i][j];
                    lDimensionI = i;
                    lDimensionJ = j;
                    break outLeft;
                }
            }
        }
        int right = 0;
        int rDimensionI = 0;
        int rDimensionJ = 0;
        outRight:
        for (int i = dimension - 1; i >= 0; i--) {
            for (int j = dimension - 1; j >= 0; j--) {
                if (blocks[i][j] != 0) {
                    right = blocks[i][j];
                    rDimensionI = i;
                    rDimensionJ = j;
                    break outRight;
                }
            }
        }
        Board anotherBoard = new Board(Arrays.copyOf(blocks, blocks.length));
        anotherBoard.blocks[lDimensionI][lDimensionJ] = right;
        anotherBoard.blocks[rDimensionI][rDimensionJ] = left;

        return anotherBoard;
    } // a board that is obtained by exchanging any pair of blocks

    public boolean equals(Object y) {
        Board board = (Board) y;
        int [][] objectToCompare = board.blocks;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] != objectToCompare[i][j]) {
                    return false;
                }
            }
        }
        return true;
    } // does this board equal y?

    public Iterable<Board> neighbors() {
        Collection<Board> iterable = new Stack<>();
        iterable.add(twin());
        return iterable;
    } // all neighboring boards

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result.append(blocks[i][j] + " ");
            }
            result.append("\n");
        }
        return result.toString();
    } // string representation of this board (in the output format specified below)

    public static void main(String[] args) {
    }// unit tests (not graded)
}