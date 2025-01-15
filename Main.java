/**
 * Main.java
 *
 * COMP 2150 SECTION A02
 * INSTRUCTOR Emanuel Weins
 * ASSIGNMENT Assignment 3
 * 
 * @author Mian Rayan Kashif 7964392
 * @version 2024-03-22
 *
 *          // this programs runs a chess game with two simplified AI structures
 *          and human interaction using a GUI
 *          // three interfaces for gamedisplay, chessplayer and chesscontroller
 *          are being implemented
 */

public class Main {
    public static void main(String[] args) {
        // Create an instance of your game display class
        GameDisplay gameDisplay = new TextGameDisplay();

        // Create an instance of your game logic class, passing the game display
        // instance
        GameLogic gameLogic = new GameLogic(gameDisplay);

        // Call the playGame() method on the game logic instance
        gameLogic.playGame();
    }
}
