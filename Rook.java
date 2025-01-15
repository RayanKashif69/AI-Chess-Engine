public class Rook extends Piece {
    public Rook(char symbol) {
        super(symbol);
    }

    public String getPieceType() {
        return "Rook";
    }
    
    //checking for move validation
    public boolean isValidMove(Board board, Move move) {
        int startRow = move.getStartRow();
        int startCol = move.getStartCol();
        int destRow = move.getDestRow();
        int destCol = move.getDestCol();
    
        // Check if the destination is on the same row or column as the rook
        if (startRow == destRow || startCol == destCol) {
    
            // Check if there are any pieces in between (exclusive)
            if (startRow == destRow) { // Moving horizontally
            
                // Finding the index of start and end pos for columns
                int startColIndex = Math.min(startCol, destCol) + 1;
                int endColIndex = Math.max(startCol, destCol);
    
                // Iterate over the squares between start and end columns
                for (int i = startColIndex; i < endColIndex; i++) {
                    if (!board.isSquareEmpty(startRow, i)) {
                        return false; // There's a piece in between
                    }
                }
            } else { // Moving vertically
    
                // Finding the index of the start and end for rows
                int startRowIndex = Math.min(startRow, destRow) + 1;
                int endRowIndex = Math.max(startRow, destRow);
    
                // Iterate over the squares between start and end rows
                for (int j = startRowIndex; j < endRowIndex; j++) {
                    if (!board.isSquareEmpty(j, startCol)) {
                        return false; // There's a piece in between
                    }
                }
            }
    
            // Destination is either empty or occupied by an opponent's piece
            Piece destPiece = board.getPiece(destRow, destCol);
            if (destPiece != null && destPiece.isHumanPiece() == isHumanPiece()) {
                return false; // Cannot capture own piece
            }
            return board.isSquareEmpty(destRow, destCol) || !destPiece.isHumanPiece();
        }
        return false; // Destination is not on the same row or column 
    }

}
