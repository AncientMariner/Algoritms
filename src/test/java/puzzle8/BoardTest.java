package puzzle8;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BoardTest {
    Board board;

    @Test(expected = IllegalStateException.class)
    public void testBoardNegative() {
        board = new Board(null);
    }

    @Test
    public void testDimension() throws Exception {
        board = new Board(new int[][]{{1,0}, {0,1}});
        assertEquals(2, board.dimension());
    }

    @Test
    public void testHamming() throws Exception {
        board = new Board(new int[][] { { 8, 1, 3, 4, 0, 2, 7, 6, 5 } } );
        assertEquals(5, board.hamming());
    }

    @Test
    public void testAnotherHamming() throws Exception {
        board = new Board(new int[][] { { 7, 2, 3, 4, 0, 5, 1, 6, 8 } } );
        assertEquals(5, board.hamming());
    }

    @Test
    public void testManhattan() throws Exception {
        board = new Board(new int[][] { {8, 1, 3}, {4, 0, 2}, {7, 6, 5} } );
        assertEquals(10, board.manhattan());
    }

    @Test
    public void testIsGoal() throws Exception {
        board = new Board(new int[][] { {1, 0, 3}, {4, 5, 6}, {7, 8, 9} } );
        assertTrue(board.isGoal());
    }

    @Test
    public void testTwin() throws Exception {
        board = new Board(new int[][] { {1, 0, 3}, {4, 5, 6}, {7, 8, 9} } );
        assertNotNull(board.twin());
    }

    @Test
    public void testEquals() throws Exception {
        board = new Board(new int[][] { {1, 0, 3}, {4, 5, 6}, {7, 8, 9} } );
        Board anotherSimilarBoard = new Board(new int[][]{{1, 0, 3}, {4, 5, 6}, {7, 8, 9}});
        assertTrue(board.equals(anotherSimilarBoard));
    }

    @Test
    public void testEqualsNegative() throws Exception {
        board = new Board(new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} } );
        Board anotherSimilarBoard = new Board(new int[][]{{1, 0, 3}, {4, 5, 6}, {7, 8, 9}});
        assertFalse(board.equals(anotherSimilarBoard));
    }

    @Test
    public void testNeighbors() throws Exception {
        board = new Board(new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} } );
        assertNotNull(board.neighbors());
    }

    @Test
    public void testToString() throws Exception {
        board = new Board(new int[][]{{1, 0, 3}, {4, 5, 6}, {7, 8, 9}});
        assertEquals("1 0 3 \n4 5 6 \n7 8 9 \n", board.toString());
    }
}
