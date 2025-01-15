public interface GameDisplay {
     int promptForOpponentDifficulty(int maxDifficulty);
     Move promptForMove();
     void displayBoard(Board board);
     void summarizeMove(Move lastMove, Board board);
     void gameOver(int winner);
     boolean promptPlayAgain();

    //additional helper methods
     void displayMessage(String message);
     Piece promptForPromotionPiece();
}
