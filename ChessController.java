public interface ChessController {
    boolean movePiece(Move moveDetails);

    void reset();

    void playGame();

    // additional helper methods
    int checkWinner(); // determing whos the winner based on checking which king is still alive

    boolean isKingCaptured(); // checking if king is captured to end the game
}