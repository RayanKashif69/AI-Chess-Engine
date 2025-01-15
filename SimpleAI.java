import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleAI implements ChessPlayer {

    // A move is made completely randomly, the AI randomly picks a piece, makes a
    // random move for it, check if
    // the move is valid, if yes move is returned
    public Move makeMove(Board board, Move lastMove) {
        // create a list of valid moves for the AI player
        List<Move> validMoves = validMoves(board);

        // Check if there are any legal moves
        if (validMoves.isEmpty()) {
            // No valid moves available
            return null;
        }

        // Select a random move from the list of legal moves
        Random random = new Random();
        int randomIndex = random.nextInt(validMoves.size());
        Move aiMove = validMoves.get(randomIndex);

        // Check if the piece is a pawn and eligible for promotion
        Piece piece = board.getPiece(aiMove.getStartRow(), aiMove.getStartCol());
        if (piece instanceof Pawn && !piece.isHumanPiece()
                && ((Pawn) piece).isPromotion(board, aiMove.getDestRow(), aiMove.getDestCol())) {
            // Promote the pawn to a queen
            board.placePiece(new Queen('q'), aiMove.getDestRow(), aiMove.getDestCol());
        }

        return aiMove;
    }

    // create a list of valid moves
    public List<Move> validMoves(Board board) {
        // Initialize an empty list to store valid moves
        List<Move> validMoves = new ArrayList<>();

        // Iterate over all pieces on the board
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                // Get the piece at the current position
                Piece piece = board.getPiece(row, col);

                // Check if the piece is AI piece and exists
                if (piece != null && !piece.isHumanPiece()) {
                    // Generate a random move for the current piece
                    Move randomMove = randomMoves(board, piece, row, col);

                    // Checking if the random move is valid
                    if (randomMove != null && piece.isValidMove(board, randomMove)) {
                        // Adding the valid random move to the list of valid moves
                        validMoves.add(randomMove);
                    }
                }
            }
        }

        // Return the list of valid moves
        return validMoves;
    }

    //creates random moves for the piece, this method is kept private 
    //since its particular to this class and doesnt need to be in the interface
    private Move randomMoves(Board board, Piece piece, int startRow, int startCol) {
        // List to store valid random moves for the piece
        List<Move> validMoves = new ArrayList<>();

        // grab a random piece on the board
        // make a random move for it, checking if that move is valid
        // if yes add that into the valid moves arraylist
        for (int destRow = 1; destRow <= 8; destRow++) {
            for (int destCol = 1; destCol <= 8; destCol++) {
                // Create a move object
                Move move = new Move(startRow, startCol, destRow, destCol);

                // Check if the move is valid for the piece
                if (piece.isValidMove(board, move)) {
                    validMoves.add(move); // Add valid move to the list
                }
            }
        }

        // if the valid moves arraylist is not empty, select a random move from the list
        // of valid moves using thr random class
        if (!validMoves.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(validMoves.size());
            return validMoves.get(randomIndex); // Return random valid move
        }

        return null; // No valid moves found
    }
}
