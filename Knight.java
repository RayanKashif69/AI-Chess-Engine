public class Knight extends Piece {
    
    public Knight(char symbol) {
        super(symbol);//calling parent class constructor
    }

    public String getPieceType(){
        return "Knight";
    }
    
public boolean isValidMove(Board board, Move move) {

    // Extracting the rows and columns for the move using get methods
    int startRow = move.getStartRow();
    int startCol = move.getStartCol();
    int destRow = move.getDestRow();
    int destCol = move.getDestCol();

    // Calculating the perpendicular pattern for the knight's move
    int rowDifference = Math.abs(destRow - startRow);
    int colDifference = Math.abs(destCol - startCol);

    // Check if the destination is empty or contains the opponent's piece
    if ((rowDifference == 2 && colDifference == 1) || (rowDifference == 1 && colDifference == 2)) {
        Piece destPiece = board.getPiece(destRow, destCol);
        
        // Check if the destination square contains a piece of the same player
        if (destPiece != null && destPiece.isHumanPiece() == isHumanPiece()) {
            return false; // Cannot capture own piece
        }
        
        return board.isSquareEmpty(destRow, destCol) || !destPiece.isHumanPiece();
    }
    return false; // False otherwise
}



}

