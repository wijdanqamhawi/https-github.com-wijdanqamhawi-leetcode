class Solution {

    private int[][] matrix;
    private int[][] memo;
    private int rows;
    private int columns;

    private final int[][] directions = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        rows = matrix.length;
        columns = matrix[0].length;

        memo = new int[rows][columns];

        int longest = 0;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                longest = Math.max(longest, dfs(row, column));
            }
        }

        return longest;
    }

    private int dfs(int row, int column) {
        if (memo[row][column] != 0) {
            return memo[row][column];
        }

        int best = 1;

        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextColumn = column + direction[1];

            if (
                nextRow >= 0 &&
                nextRow < rows &&
                nextColumn >= 0 &&
                nextColumn < columns &&
                matrix[nextRow][nextColumn] > matrix[row][column]
            ) {
                best = Math.max(best, 1 + dfs(nextRow, nextColumn));
            }
        }

        memo[row][column] = best;
        return best;
    }
}