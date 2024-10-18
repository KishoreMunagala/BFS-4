time;O(M×N)
space;O(M×N)


class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        // Directions for the 8 possible neighboring cells
        int [][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        int m = board.length;
        int n = board[0].length;
        
        // If the click is on a mine, game over. Mark it as 'X'.
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        // Initialize the queue for BFS and start with the clicked cell
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';  // Mark the clicked cell as visited (blank)

        // BFS to explore neighboring cells
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1], dirs);

            // If no mines are found around the current cell, continue BFS
            if (count == 0) {
                for (int[] dir : dirs) {
                    int nr = dir[0] + curr[0]; // new row
                    int nc = dir[1] + curr[1]; // new column
                    if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length &&
                        board[nr][nc] == 'E') {  // Only explore unvisited empty cells ('E')
                        q.add(new int[]{nr, nc});
                        board[nr][nc] = 'B';  // Mark as visited
                    }
                }
            } else {
                // If there are mines nearby, mark the cell with the number of mines
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
        }

        return board;
    }

    // Helper function to count the number of mines surrounding a given cell
    private int countMines(char[][] board, int i, int j, int[][] dirs) {
        int result = 0;
        for (int[] dir : dirs) {
            int nr = dir[0] + i; // new row
            int nc = dir[1] + j; // new column
            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] == 'M') {
                result++;
            }
        }
        return result;
    }
}

time and space in one line