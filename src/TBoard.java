public class TBoard {
    private static final int ROW = 3;
    private static final int COL = 3;
    private String[][] board;
    private int moveCount;
    private boolean currentPlayer; // true = X, false = O

    public TBoard() {
        board = new String[ROW][COL];
        createGridPanel();
    }

    public void createGridPanel() {
        moveCount = 0;
        currentPlayer = true; // Start with player X
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }

    public boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    public void makeMove(int row, int col) {
        board[row][col] = getCurrentPlayerSymbol();
        moveCount++;
        currentPlayer = !currentPlayer; // Switch player after move
    }

    public String getCurrentPlayerSymbol() {
        return currentPlayer ? "X" : "O";
    }

    public boolean isWin(String currentPlayerSymbol) {
        String player = currentPlayer ? "O" : "X"; // Check last move's player
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    public boolean isTie() {
        if (moveCount < 7) return false;
        // Check for tie logic
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].equals(" ")) return false;
            }
        }
        return true;
    }

    private boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }
}