public class Board {
    private Piece[][] pieces; // 2D array to represent the grid of squares

    public Board() {
        // Initialize the grid with an empty board
        pieces = new Piece[8][8];

        // Set up the initial board configuration with pieces placed accordingly
        initializeBoard();

    }

    private void initializeBoard() {
        // Place human player's pieces (capital letters)
        placePiece(new Rook('R'), 8, 1);
        placePiece(new Knight('N'), 8, 2);
        placePiece(new Bishop('B'), 8, 3);
        placePiece(new Queen('Q'), 8, 4);
        placePiece(new King('K'), 8, 5);
        placePiece(new Bishop('B'), 8, 6);
        placePiece(new Knight('N'), 8, 7);
        placePiece(new Rook('R'), 8, 8);

        // Place human player's pawns
        for (int col = 1; col <= 8; col++) {
            placePiece(new Pawn('P'), 7, col);
        }

        // Place AI player's pieces (lowercase letters)
        placePiece(new Rook('r'), 1, 1);
        placePiece(new Knight('n'), 1, 2);
        placePiece(new Bishop('b'), 1, 3);
        placePiece(new Queen('q'), 1, 4);
        placePiece(new King('k'), 1, 5);
        placePiece(new Bishop('b'), 1, 6);
        placePiece(new Knight('n'), 1, 7);
        placePiece(new Rook('r'), 1, 8);

        // Place AI player's pawns
        for (int col = 1; col <= 8; col++) {
            placePiece(new Pawn('p'), 2, col);
        }
    }

    // Method to place a piece on the board
    public void placePiece(Piece piece, int row, int col) {
        if (isValidPosition(row, col)) {
            pieces[row - 1][col - 1] = piece;
        }
    }

    // Method to retrieve the piece at a specific position on the board
    public Piece getPiece(int row, int col) {
        if (isValidPosition(row, col)) {
            return pieces[row - 1][col - 1];
        } else {
            return null;
        }
    }

    // Method to check if a position is valid on the board
    private boolean isValidPosition(int row, int col) {
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }

    // Method to check if a square on the board is empty
    public boolean isSquareEmpty(int row, int col) {
        return pieces[row - 1][col - 1] == null;
    }

    // Method to retrieve the size of the board
    public int getSize() {
        return pieces.length;
    }

}
