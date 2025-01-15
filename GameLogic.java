public class GameLogic implements ChessController {

    // instance variables
    private GameDisplay gameDisplay;
    private Board chessBoard;
    private ChessPlayer opponentAI;// computer player
    private boolean isHumanTurn;// validation for player turns

    // constructor
    public GameLogic(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
        this.chessBoard = new Board();
        this.opponentAI = null;
        this.isHumanTurn = true; // player 1 starts first always
    }

    // moves a piece after validating the move and updates the board with the move
    // being made
    public boolean movePiece(Move moveDetails) {

        // getting move details using getters
        int startRow = moveDetails.getStartRow();
        int startCol = moveDetails.getStartCol();
        int destRow = moveDetails.getDestRow();
        int destCol = moveDetails.getDestCol();

        // Get the piece at
        Piece piece = chessBoard.getPiece(startRow, startCol);

        // Check if the piece exists and belongs to the human player
        if (piece == null || Character.isLowerCase(piece.getSymbol())) {
            return false; // false no piece at start position or not human player's piece
        }

        // Check if the move is valid for the piece
        if (!piece.isValidMove(chessBoard, moveDetails)) {
            return false; // Invalid move: piece cannot move to destination
        }

        // Check for pawn promotion
        if (piece instanceof Pawn && piece.isHumanPiece()) {
            Pawn pawn = (Pawn) piece;
            if (pawn.isPromotion(chessBoard, destRow, destCol)) {
                // Prompt the user for promotion piece
                Piece promotionPiece = gameDisplay.promptForPromotionPiece();
                if (promotionPiece == null || promotionPiece instanceof King) {
                    return false; // Invalid promotion: can't promote to a King or null
                }

                // Display promotion message
                gameDisplay.displayMessage("Pawn has been promoted to " + promotionPiece.getPieceType() + ".");

                // Summarize the move with the promotion piece
                gameDisplay.summarizeMove(new Move(startRow, startCol, destRow, destCol), chessBoard);
                // Update the pawn on the board with the chosen promotion piece
                chessBoard.placePiece(promotionPiece, destRow, destCol);
                chessBoard.placePiece(null, startRow, startCol);

                // Toggle player turn
                isHumanTurn = !isHumanTurn;
                return true; // Promotion move successful
            }
        }

        // Calling summarize move from within move piece, it works for my logic design
        gameDisplay.summarizeMove(moveDetails, chessBoard);

        // Make the move
        chessBoard.placePiece(piece, destRow, destCol);
        chessBoard.placePiece(null, startRow, startCol);

        // Toggle player turn
        isHumanTurn = !isHumanTurn;

        return true; // move successful
    }

    // drives the whole game, and controls the flow of method calls from the
    // gamedisplay for input and output
    public void playGame() {

        boolean playAgain = true;

        // Main game loop
        while (playAgain) {
            // Prompt the user for the difficulty level
            int maxDifficulty = 2; // Example maximum difficulty level
            int difficultyLevel = gameDisplay.promptForOpponentDifficulty(maxDifficulty);

            // Create AI instance based on the chosen difficulty level
            if (difficultyLevel == 1) {
                opponentAI = new SimpleAI();
            } else if (difficultyLevel == 2) {
                opponentAI = new AdvancedAI();
            } else {
                // Handle invalid difficulty choice
                gameDisplay.displayMessage("Invalid difficulty choice. Defaulting to Regular mode");
                opponentAI = new SimpleAI(); // Default to Simple AI
            }

            // Reset the board
            reset();

            boolean gameOver = false;
            isHumanTurn = true; // Human player plays first

            // initial game display
            gameDisplay.displayBoard(chessBoard);

            // Inner game loop
            while (!gameOver) {

                // Human player's turn
                Move move = gameDisplay.promptForMove();
                if (move == null) {
                    gameOver = true;
                    break; // Exit the inner loop if the human player forfeits
                }

                // move piece is called to check the validity of the move and update the
                // chessboard if the move is valid
                // if valid move is summarized from within the movePiece method
                boolean validMove = movePiece(move);

                if (!validMove) {
                    gameDisplay.displayMessage("Invalid move! Please try again.");
                    continue;
                }

                gameDisplay.displayBoard(chessBoard);

                // Check if the opponent's king is captured
                if (isKingCaptured()) {
                    gameOver = true;
                    break;
                }

                // AI player's turn, move is made using the AI logic
                Move aiMove = opponentAI.makeMove(chessBoard, move); // Pass the last move made by the human player

                // summarize ai move
                gameDisplay.summarizeMove(aiMove, chessBoard);

                // Make the AI move
                Piece movedPiece = chessBoard.getPiece(aiMove.getStartRow(), aiMove.getStartCol());
                chessBoard.placePiece(movedPiece, aiMove.getDestRow(), aiMove.getDestCol());
                chessBoard.placePiece(null, aiMove.getStartRow(), aiMove.getStartCol());

                gameDisplay.displayBoard(chessBoard);

                // Check if the player's king is captured
                if (isKingCaptured()) {
                    gameOver = true;
                    break;
                }
            }

            // Display the game result
            int winner = checkWinner();
            gameDisplay.gameOver(winner);

            // Prompt the user to play again
            playAgain = gameDisplay.promptPlayAgain();
        }

    }

    public int checkWinner() {
        boolean humanKingAlive = false;
        boolean aiKingAlive = false;

        // Check if the human player's king is captured
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                Piece piece = chessBoard.getPiece(row, col);
                if (piece != null && piece.getSymbol() == 'K') {
                    // Human player's king found
                    humanKingAlive = true;
                    break;
                }
            }
            if (humanKingAlive) {
                break;
            }
        }

        // Check if the AI player's king is captured
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                Piece piece = chessBoard.getPiece(row, col);
                if (piece != null && piece.getSymbol() == 'k') {
                    // AI player's king found
                    aiKingAlive = true;
                    break;
                }
            }
            if (aiKingAlive) {
                break;
            }
        }

        if (humanKingAlive && !aiKingAlive) {
            // Human player wins
            return 1;
        } else if (!humanKingAlive && aiKingAlive) {
            // AI player wins
            return 2;
        } else {
            // game draw
            return 0;
        }
    }

    public void reset() {
        chessBoard = new Board(); // Clear the board
        isHumanTurn = true; // Reset player turn to player 1
    }

    // checking if king is captured to end the game
    public boolean isKingCaptured() {
        boolean humanKingFound = false;
        boolean aiKingFound = false;

        for (int row = 1; row <= chessBoard.getSize(); row++) {
            for (int col = 1; col <= chessBoard.getSize(); col++) {
                Piece piece = chessBoard.getPiece(row, col);
                if (piece != null && piece instanceof King) {
                    if (piece.isHumanPiece()) {
                        humanKingFound = true;
                    } else {
                        aiKingFound = true;
                    }
                }
            }
        }

        return !humanKingFound || !aiKingFound;
    }

}
