import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdvancedAI implements ChessPlayer {

    public Move makeMove(Board board, Move lastMove) {
        // creates valid moves
        List<Move> validMoves = validMoves(board);

        // Check if there are any legal moves
        if (validMoves.isEmpty()) {
            // No legal moves available
            return null;
        }

        // creating moves that captures human piece
        List<Move> movesToCapture = findMovesToCapture(board, validMoves);
        if (!movesToCapture.isEmpty()) {
            return movesToCapture.get(0);
        }

        // choose a random if no capturing moves are found
        Random random = new Random();
        int randomIndex = random.nextInt(validMoves.size());

        return validMoves.get(randomIndex);// return the valid move

    }

    // creating validMoves using strategy 2
    public List<Move> validMoves(Board board) {
        // Initialize an empty list to store valid moves
        List<Move> validMoves = new ArrayList<>();

        // Iterate over all pieces on the board
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                Piece piece = board.getPiece(row, col);

                // Check if the piece belongs to the AI player
                if (piece != null && !piece.isHumanPiece()) {
                    // Generate moves for the current piece
                    for (int destRow = 1; destRow <= 8; destRow++) {
                        for (int destCol = 1; destCol <= 8; destCol++) {
                            Move move = new Move(row, col, destRow, destCol);
                            if (piece.isValidMove(board, move)) {
                                validMoves.add(move);
                            }
                        }
                    }
                }
            }
        }

        return validMoves;
    }

    // simple strategy to capture human pieces
    // iterating over the board finding checking if the dest row and column are
    // humanPiece simply
    // add that move to the capturemoves list
    private List<Move> findMovesToCapture(Board board, List<Move> moves) {
        // Create a list to store moves that capture opponent pieces
        List<Move> movesToCapture = new ArrayList<>();

        // Iterate over each move in the provided list of moves
        for (int i = 0; i < moves.size(); i++) {
            // Get the current move
            Move move = moves.get(i);

            // Retrieve the destination row and column of the move
            int destRow = move.getDestRow();
            int destCol = move.getDestCol();

            // Get the piece at the destination position
            Piece capturingPiece = board.getPiece(destRow, destCol);

            // Check if there is a piece at the destination position and if it belongs to
            // the human player
            if (capturingPiece != null && capturingPiece.isHumanPiece()) {
                // If the conditions are met, add the move to the list of capturing moves
                movesToCapture.add(move);
            }
        }

        // Return the list of capturing moves
        return movesToCapture;
    }

}
