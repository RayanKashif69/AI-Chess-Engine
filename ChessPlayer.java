import java.util.List;

public interface ChessPlayer {
    public Move makeMove(Board board, Move lastMove);

    // additional helper method
    public List<Move> validMoves(Board board);
}
