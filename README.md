# AI Chess Game

This is a Java-based implementation of a chess game featuring two AI players with different difficulty levels. The game allows you to play chess against an AI opponent, and it includes logic for handling the rules of chess, such as piece movement, capturing, and pawn promotion.

## Features

- **Two AI Difficulty Levels**:
  - **SimpleAI**: Makes random moves by selecting a random piece and generating a random valid move.
  - **AdvancedAI**: Implements a simple strategy to prioritize capturing human pieces. If no capturing moves are found, it chooses a random valid move from the available options.

- **Piece Movement Logic**: All chess pieces (King, Queen, Rook, Knight, Bishop, Pawn) have their movement logic implemented according to the official rules of chess.

- **Pawn Promotion**: Pawns are automatically promoted to queens upon reaching the promotion rank (8th rank for white pawns, 1st rank for black pawns).

## How to Run the Code

### Prerequisites
- Ensure that you have **Java** installed on your system. You can verify this by running:


### Compilation and Execution

1. **Compiling the Code**:
 - To compile the Java code, open a terminal or command prompt, navigate to the directory where your `Main.java` file is located, and run:
   ```bash
   javac Main.java
   ```

2. **Running the Program**:
 - After compilation, you can run the program by executing the following command:
   ```bash
   java Main
   ```

 - The program will start, and you'll be prompted to play the game.

## AI Logic

### SimpleAI:
The **SimpleAI** is designed to make moves completely randomly. It picks a random piece from the AI’s available pieces and generates a random valid move for that piece. This AI doesn't consider any strategy or tactics and simply moves pieces without attempting to capture the opponent’s pieces or control the board.

- **Random Piece Selection**: A random piece is selected from the AI’s available pieces.
- **Random Move**: A random valid move is selected for the chosen piece.

### AdvancedAI:
The **AdvancedAI** has a basic strategy aimed at capturing the human player's pieces. The AI will prioritize moves that allow it to capture an opponent’s piece. If no capture moves are available, the AI will select a random valid move from the remaining available options.

- **Capture Priority**: The AI checks for moves that would result in capturing a human piece, and it prioritizes these moves over other random moves.
- **Random Move**: If no capturing moves are found, the AI picks a random valid move from the available options.

## Piece Movement Logic

The game follows the standard chess movement rules for each type of piece. Here’s an overview of how each piece is handled:

- **King**: Moves one square in any direction.
- **Queen**: Moves any number of squares in any direction—vertically, horizontally, or diagonally.
- **Rook**: Moves any number of squares horizontally or vertically.
- **Bishop**: Moves diagonally for any number of squares.
- **Knight**: Moves in an "L" shape: two squares in one direction and then one square perpendicular to that, or one square in one direction and then two squares perpendicular to that. Knights can jump over other pieces.
- **Pawn**: Moves one square forward but captures one square diagonally. Pawns can move two squares forward from their starting position. If a pawn reaches the 8th rank (for white) or the 1st rank (for black), it is automatically promoted to a queen.

## Pawn Promotion
- Pawns that reach the opponent’s back rank (8th rank for white pawns, 1st rank for black pawns) are automatically promoted to a queen. This is implemented for both the AI and human players.

## Code Structure

The project consists of 17 Java files, which include the core game logic for handling the game state, piece movements, and AI decision-making.

- **Main.java**: Contains the entry point for the game and manages the overall game flow.
- **Board.java**: Represents the chessboard, handles the placement of pieces, and validates moves.
- **Piece.java**: A base class for all chess pieces. It provides methods for checking valid moves.
- **Specific Piece Classes (King.java, Queen.java, etc.)**: These classes extend the `Piece` class and implement the specific movement logic for each type of piece.
- **Move.java**: Represents a move in the game, storing the start and destination coordinates.
- **AI Classes (SimpleAI.java, AdvancedAI.java)**: These classes implement the logic for the Simple and Advanced AI players. They decide which move the AI should make based on the current game state.

## How to Play

1. When you run the program, the game will start in a console window.
2. The AI will take its turn based on the difficulty level selected.
3. You will play as the human player, and you will need to make your moves based on the current game state.
4. The game continues until one player wins, or a draw occurs.

## Testing

The code has been tested thoroughly on CS Aviary systems, ensuring that all pieces move as expected, pawn promotion works correctly, and AI players make valid moves. The program works as expected without any known issues.

## Files Included

- 17 Java files implementing the core game logic.
- 1 README file (this document).

## Notes

- The code is modular, allowing easy modification and extension. You can easily add more sophisticated AI strategies or change the game rules.
- The AI difficulty is determined by the `SimpleAI` and `AdvancedAI` classes. You can modify or add new difficulty levels by adjusting the `makeMove` method and introducing more complex strategies.

## Conclusion

Thank you for reviewing this project! I hope you enjoy playing this chess game powered by AI. If you have any feedback or suggestions for improvement, feel free to reach out. Happy playing!

