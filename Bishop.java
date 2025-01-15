public class Bishop extends Piece {

    // Constructor
    public Bishop(char symbol) {
        super(symbol);
    }

    public String getPieceType() {
        return "Bishop";
    }

    public boolean isValidMove(Board board, Move move) {
        int startRow = move.getStartRow();
        int startCol = move.getStartCol();
        int destRow = move.getDestRow();
        int destCol = move.getDestCol();

        // Calculate the direction of movement
        int rowMovement = Integer.compare(destRow, startRow);
        int colMovement = Integer.compare(destCol, startCol);

        // if move is considered to be diagonal
        if (Math.abs(destRow - startRow) == Math.abs(destCol - startCol)) {
            
            //iterating over each square using direction of movement 
            for (int i = 1; i < Math.abs(destRow - startRow); i++) {
                
                int row = startRow + i * rowMovement;
                int col = startCol + i * colMovement;
                
                //if a sqaure is occupied return false
                if (!board.isSquareEmpty(row, col)) {
                    return false;
                }
            }
            // Destination is either empty or contains an opponent's piece
            Piece destPiece = board.getPiece(destRow, destCol);

            // Check if the destination square contains a piece of the same player
            if (destPiece != null && destPiece.isHumanPiece() == isHumanPiece()) {
                return false; // Cannot capture own piece
            }

            return board.isSquareEmpty(destRow, destCol) || !destPiece.isHumanPiece();
        }
        return false; // Move is not diagonal
    }

}
