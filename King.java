public class King extends Piece {

    // Constructor
    public King(char symbol) {
        super(symbol);
    }

    public String getPieceType() {
        return "King";
    }

    @Override
    public boolean isValidMove(Board board, Move move) {
        // getting move details
        int startRow = move.getStartRow();
        int startCol = move.getStartCol();
        int destRow = move.getDestRow();
        int destCol = move.getDestCol();

        // king can only move 1 square in each direction getting the difference of the
        // movement
        int rowDiff = Math.abs(destRow - startRow);
        int colDiff = Math.abs(destCol - startCol);

        // if any of the conditons is true then the is considered to be true
        if ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 1)) {
            // Destination is either empty or contains an opponent's piece
            Piece destPiece = board.getPiece(destRow, destCol);

            // Check if the destination square contains a piece of the same player
            if (destPiece != null && destPiece.isHumanPiece() == isHumanPiece()) {
                return false; // Cannot capture own piece
            }

            return board.isSquareEmpty(destRow, destCol) || !destPiece.isHumanPiece();
        }
        return false; // Move is not within one square in any direction
    }

}
