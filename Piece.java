public abstract class Piece {
    //protected instance field common to all sub classes 
    protected char symbol; // Symbol representing the type of the piece on the chessboard
    
    //constructo
    public Piece(char symbol) {
        this.symbol = symbol;
    }
    
    //getter
    public char getSymbol() {
        return symbol;
    }
    
    //to check if piece is human or no
    public boolean isHumanPiece() {
        // Check if the type is uppercase
        return Character.isUpperCase(symbol);
    }

    // abstract methods impplemented by the sub classes 
    public abstract boolean isValidMove(Board board, Move move);
    public abstract String getPieceType();

}