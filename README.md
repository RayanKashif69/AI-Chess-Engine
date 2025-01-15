# AI Chess Game

This is a chess game implementation where two players can play against each other. The game includes two types of AI players: **SimpleAI** and **AdvancedAI**, each with different strategies for making moves.

## How to Run the Code

1. **Compiling**:
   - To compile the code, use the following command in your terminal:
     ```
     javac Main.java
     ```

2. **Running**:
   - After compiling, run the program with:
     ```
     java Main
     ```

## AI Logic

### SimpleAI:
- The **SimpleAI** makes moves randomly.
- It selects a random piece from its available pieces and generates a random valid move for that piece.

### AdvancedAI:
- The **AdvancedAI** follows a simple strategy focused on capturing the human player's pieces.
- If a capturing move is available, it will prioritize that over other moves.
- If no capturing moves are found, it will select a random valid move.

## Piece Logic

- All piece logic, such as valid movements and capturing, has been implemented according to the chess rules as defined in the assignment prompt.
- **Pawn Promotion** has been implemented for both AI and human players. When a pawn reaches the promotion rank, it is automatically promoted to a queen.

## Additional Information

- The code contains detailed comment blocks explaining the logic behind each function.
- The program has been tested on CS Aviary systems, and it functions as expected.

## Files Included

- 17 Java files containing the core game logic.
- 1 README file (this one).

## Thank You!

Thank you for reviewing my project. I hope you enjoy playing the chess game powered by SimpleAI and AdvancedAI.

