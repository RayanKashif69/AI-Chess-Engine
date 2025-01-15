public class Pawn extends Piece {
    // Constructor
    public Pawn(char symbol) {
        super(symbol);
    }

    public String getPieceType() {
        return "Pawn";
    }

    public boolean isValidMove(Board board, Move move) {
        int startRow = move.getStartRow();
        int startCol = move.getStartCol();
        int destRow = move.getDestRow();
        int destCol = move.getDestCol();

        // finding if move is valid and in the board
        int boardSize = board.getSize();
        if (destRow < 1 || destRow > boardSize || destCol < 1 || destCol > boardSize) {
            return false; // Move is out of bounds
        }

        // Determine the direction of movement based on the human type
        int direction;
        if (isHumanPiece()) {
            direction = -1; // direction towards human (row 1)
        } else {
            direction = 1; // direction towards AI (row 8)
        }

        // finding movement of pawn based on moving one sq only
        if (destCol == startCol && destRow == startRow + direction) {
            // Check if the destination square is empty
            if (board.isSquareEmpty(destRow, destCol)) {
                return true;
            }
        }

        // checking pawn capturing strategy
        else if (Math.abs(destCol - startCol) == 1 && destRow == startRow + direction) {
            // Check if there is an opponent's piece at the destination square
            Piece destPiece = board.getPiece(destRow, destCol);
            if (!board.isSquareEmpty(destRow, destCol) && destPiece != null
                    && destPiece.isHumanPiece() != isHumanPiece()) {
                return true;
            }
        }

        // Check if the destination square contains a piece belonging to the same player
        else if (!board.isSquareEmpty(destRow, destCol)
                && board.getPiece(destRow, destCol).isHumanPiece() == isHumanPiece()) {
            return false; // Destination square contains own piece
        }

        return false; // Move is invalid
    }

    public boolean isPromotion(Board board, int destRow, int destCol) {
        // For Human Pawn (moving upwards)
        if (this.isHumanPiece()) {
            if (destRow == 1) {// if piece moves towards the top of the board
                return true;
            }
        }
        // For AI Pawn (moving downwards)
        else {
            // Check if the pawn has reached the bottom of the board
            if (destRow == 8) {
                return true;
            }
        }
        return false; // cant promote
    }

}
