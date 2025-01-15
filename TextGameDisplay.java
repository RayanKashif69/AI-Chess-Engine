import java.util.Scanner;

//class defines the gamedisplay interface methods for user interaction
public class TextGameDisplay implements GameDisplay {

    private Scanner scanner;

    public TextGameDisplay() {
        this.scanner = new Scanner(System.in);
    }

    public int promptForOpponentDifficulty(int maxDifficulty) {
        System.out.println("Please select the difficulty level of the opponent:");
        System.out.println("1. Easy");
        System.out.println("2. Hard");

        int difficulty = 0;

        boolean validChoice = false;
        //error checking to see if the choice is valid using a loop
        while (!validChoice) {
            System.out.print("Enter your choice (1 or 2): ");
            if (scanner.hasNextInt()) {
                difficulty = scanner.nextInt();
                if (difficulty >= 1 && difficulty <= maxDifficulty) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + maxDifficulty + ".");
                }
            } else {
                scanner.next(); //handle invalid input
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return difficulty;
    }

    //prompts the user for move 
    public Move promptForMove() {
        System.out.println("Please enter the row of the piece you would like to move, or enter -1 to forfeit:");
        int startRow = scanner.nextInt();
        if (startRow == -1) {
            return null; //user wants to forfeit 
        }

        System.out.println("Please enter the column of the piece you would like to move:");
        int startCol = scanner.nextInt();

        System.out.println("Please enter the row of the destination, or enter -1 to forfeit:");
        int destRow = scanner.nextInt();
        if (destRow == -1) {
            return null; //user wants to forfeit 
        }

        System.out.println("Please enter the column of the destination:");
        int destCol = scanner.nextInt();

        return new Move(startRow, startCol, destRow, destCol);// create a new move with the above move details 
    }

    //displays the current state of the board after each move 
    public void displayBoard(Board board) {
        System.out.println("  1 2 3 4 5 6 7 8");
        System.out.println("------------------");

        for (int i = 1; i <= 8; i++) {

            System.out.print(i + "|");

            for (int j = 1; j <= 8; j++) {

                Piece piece = board.getPiece(i, j);

                char symbol = (piece != null) ? piece.getSymbol() : '-';
                System.out.print(symbol + "|");
            }
            System.out.println("\n------------------");
        }
    }

    //summarize the move details
    public void summarizeMove(Move lastMove, Board board) {

        //getting the move details using getters
        int startRow = lastMove.getStartRow();
        int startCol = lastMove.getStartCol();
        int destRow = lastMove.getDestRow();
        int destCol = lastMove.getDestCol();

        // getting the piece from the board based on the start position of the move
        Piece piece = board.getPiece(startRow, startCol);

        // Check if the piece is not null
        if (piece != null) {
            String pieceName = piece.getPieceType(); //get type of the piece 

            String moveDetails = pieceName + " moved from (" + startRow + ", " + startCol + ") to (" +
                    destRow + ", " + destCol + ").";

            // Check if a piece was captured
            Piece killedPiece = board.getPiece(destRow, destCol);
            if (killedPiece != null) {
                String killedPieceName = killedPiece.getPieceType();
                System.out.println(moveDetails + " " + killedPieceName + " captured.");
            } else {
                System.out.println(moveDetails + " No capture made.");
            }
        }
    }

    public void gameOver(int winner) {
        if (winner == 0) {
            System.out.println("Game over! Draw");
        } else if (winner == 1) {
            System.out.println("Game over! Human player wins.");
        } else if (winner == 2) {
            System.out.println("Game over! Computer wins.");
        } else if (winner == 3) {
            System.out.println("Game over! Human player forfeited.");
        }
    }

    public boolean promptPlayAgain() {
        //returns if user wants to play again by entering yes, false if user enters no
        System.out.println("Would you like to play again? (yes/no)");

        String input = scanner.next().toLowerCase();
        return input.equals("yes");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public Piece promptForPromotionPiece() {
       
        // Display options to the user
        System.out.println("Which piece do you want for Promotion?");
        System.out.println("Q for Queen");
        System.out.println("R for Rook");
        System.out.println("B for Bishop");
        System.out.println("N for Knight");

        // Get user input
        char symbol;
        do {
            System.out.print("Enter the symbol of the piece: ");
            symbol = scanner.next().toUpperCase().charAt(0); // Converting input to uppercase to prevent opponent piece symbols
        } while (symbol != 'Q' && symbol != 'R' && symbol != 'B' && symbol != 'N');

        //switch cases to check the type of the symbol, and creating an object of that piece 
        switch (symbol) {
            case 'Q':
                return new Queen('Q'); 
            case 'R':
                return new Rook('R'); 
            case 'B':
                return new Bishop('B'); 
            case 'N':
                return new Knight('N'); 
            default:
                return null; 
        }
    }

}