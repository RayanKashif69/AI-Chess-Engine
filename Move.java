public class Move {
    // instance variables (board coordinates)
    private int startRow;
    private int startCol;
    private int destRow;
    private int destCol;

    // constructor
    public Move(int startRow, int startCol, int destRow, int destCol) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.destRow = destRow;
        this.destCol = destCol;
    }
    
    //getters
    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getDestRow() {
        return destRow;
    }

    public int getDestCol() {
        return destCol;
    }
}
