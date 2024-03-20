//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.

public class TetrisGrid {
    private boolean[][] grid;

    /**
     * Constructs a new instance with the given grid.
     * Does not make a copy.
     *
     * @param grid
     */
    public TetrisGrid(boolean[][] grid) {
        this.grid = grid;
    }

    /**
     * Does row-clearing on the grid (see handout).
     */
    public void clearRows() {
        for (int j = 0; j < grid[0].length; ++j) {
            int thu = 1;
            for (int i = 0; i < grid.length - 1; ++i) {
                if (grid[i][j] != grid[i + 1][j]) {
                    thu = 0;
                    break;
                }
            }
            if (thu == 0) {
                continue;
            } else {
                for (int i = 0; i < grid.length; ++i) {
                    for (int k = j; k < grid[0].length - 1; ++k) {
                        grid[i][k] = grid[i][k + 1];
                    }
                }
                for (int i = 0; i < grid.length; ++i) {
                    grid[i][grid[0].length - 1] = false;
                }
            }
        }
    }

    /**
     * Returns the internal 2d grid array.
     *
     * @return 2d grid array
     */
    boolean[][] getGrid() {
        return grid;
    }
}
