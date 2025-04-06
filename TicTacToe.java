package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    private char[][] board;// Holds the 3x3 game board
    static Scanner input = new Scanner(System.in);
    private char currentPlayer = 'X';
    private boolean gameValid = false; // Indicates if the game has been won
    private boolean boardValid = false; // Indicates if the board is full
    // Creates a new empty game board

    public TicTacToe() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' '; // Fills all cells with spaces
            }
        }
    }

    // Prints the game board to the console
    public void printBoard() {

        for (int i = 0; i < 3; i++) {
            System.out.println(" ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print("|"); // Adds a separator between cells
                }
            }
            System.out.println(" ");
            if (i < 2) {
                System.out.print("------"); // Draws a line between rows
            }

        }
    }

    // Takes a move from the player and updates the board
    public void makeMove() {
        boolean validMove = false;
        do {
            System.out.println("Player " + currentPlayer + " Do your move!");
            System.out.print("Enter the row number: ");
            int row = input.nextInt();
            row -= 1;
            System.out.print("Enter the column number: ");
            int col = input.nextInt();
            col -= 1;
            // Checks if the move is valid
            if (row > -1 && row < 3 && col > -1 && col < 3 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                validMove = true;
                // Switches the player
                if (currentPlayer == 'X') {
                    currentPlayer = 'O';
                } else {
                    currentPlayer = 'X';
                }
            }
            if (!validMove) {
                System.out.println("Your move is invalid!");
            }
        } while (!validMove);// Continues until a valid move is made

    }

    // Checks if there is a winner on the board
    public boolean checkWinner() {
        // Horizontal check
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        // Vertical check
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        // Diagonal check (top-left to bottom-right)
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        // Diagonal check (top-right to bottom-left)
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false; // Returns false if no winner is found
    }

    // Checks if the board is completely full
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Returns false if any cell is empty
                }
            }
        }
        return true; // Returns true if no empty cells remain
    }

    // Starts and manages the game
    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe();
        // Continues until the game is won or the board is full
        while (true) {
            while (!ttt.gameValid && !ttt.boardValid) {
                ttt.printBoard();
                ttt.makeMove();

                ttt.gameValid = ttt.checkWinner();
                ttt.boardValid = ttt.isBoardFull();
            }
            // Evaluates the game outcome
            if (ttt.checkWinner()) {
                // Determines the winning player (reversed due to switch after move)
                if (ttt.currentPlayer == 'X') {
                    ttt.currentPlayer = 'O';
                } else {
                    ttt.currentPlayer = 'X';
                }
                System.out.println("\n-*-*-*-*-*-*-*-*-");
                System.out.println("Congratulations! Player " + ttt.currentPlayer + " has won!!!");

            } else {
                System.out.println("Game ended in a tie!");
            }
            ttt.printBoard(); // Shows the final board state
            // Question to continue
            System.out.println("\n Do you want to play again? y/n: ");
            String wantToPlay = input.next();
            if (wantToPlay.equals("n") || wantToPlay.equals("no")) {
                System.out.println("Thank you for playing our game!!!");
                break;
            }
        }
        input.close();
    }
}