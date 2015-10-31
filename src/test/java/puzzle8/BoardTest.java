package puzzle8;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BoardTest {
    Board board;

    @Test(expected = IllegalStateException.class)
    public void testBoardNegative() {
        board = new Board(null);
    }

    @Test
    public void testDimension() throws Exception {
        board = new Board(new int[][]{{1,2}, {1,2}});
        assertEquals(2, board.dimension());
    }

    @Test
    public void testHamming() throws Exception {

    }

    @Test
    public void testManhattan() throws Exception {

    }

    @Test
    public void testIsGoal() throws Exception {
    }

    @Test
    public void testTwin() throws Exception {
    }

    @Test
    public void testEquals() throws Exception {
    }

    @Test
    public void testNeighbors() throws Exception {
    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testMain() throws Exception {

    }
}
