public class Queen extends Piece {

    // Constructor
    public Queen(char symbol) {
        super(symbol);
    }

    public String getPieceType() {
        return "Queen";
    }

    public boolean isValidMove(Board board, Move move) {
        int startRow = move.getStartRow();
        int startCol = move.getStartCol();
        int destRow = move.getDestRow();
        int destCol = move.getDestCol();

        // Check if the move is within the bounds of the board
        int boardSize = board.getSize();
        if (destRow < 1 || destRow > boardSize || destCol < 1 || destCol > boardSize) {
            return false; // Move is out of bounds
        }

        // Calculate the direction of movement
        int rowMovement = Integer.compare(destRow, startRow);
        int colMovement = Integer.compare(destCol, startCol);

        // Check if the move is horizontal, vertical, or diagonal
        if (startRow == destRow || startCol == destCol
                || Math.abs(destRow - startRow) == Math.abs(destCol - startCol)) {
            // Check if the move is along a row or column
            if (startRow == destRow || startCol == destCol) {
                // Check each square in the path
                if (startRow == destRow) { // Moving horizontally same as rook

                    int minStartCol = Math.min(startCol, destCol) + 1;
                    int minEndCol = Math.max(startCol, destCol);

                    for (int col = minStartCol; col < minEndCol; col++) {
                        if (!board.isSquareEmpty(startRow, col)) {
                            return false; // There's a piece in between
                        }
                    }
                } else { // Moving vertically same as rook
                    int minStartCol = Math.min(startRow, destRow) + 1;
                    int minEndCol = Math.max(startRow, destRow);
                    for (int row = minStartCol; row < minEndCol; row++) {
                        if (!board.isSquareEmpty(row, startCol)) {
                            return false; // There's a piece in between
                        }
                    }
                }
            } else { // Diagonal movement same as bishop movement
                // Check each square in the diagonal path
                for (int i = 1; i < Math.abs(destRow - startRow); i++) {
                    int row = startRow + i * rowMovement;
                    int col = startCol + i * colMovement;
                    // If any square in the diagonal path is occupied, return false
                    if (!board.isSquareEmpty(row, col)) {
                        return false;
                    }
                }
            }
            // Destination piece
            Piece destPiece = board.getPiece(destRow, destCol);

            // Check if the destination square contains a piece of the same player
            if (destPiece != null && destPiece.isHumanPiece() == isHumanPiece()) {
                return false; // Cannot capture own piece
            }

            return board.isSquareEmpty(destRow, destCol) || !destPiece.isHumanPiece();
        }
        return false; // Move is not along a row, column, or diagonal
    }

}
