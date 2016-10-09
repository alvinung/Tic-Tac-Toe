package alvin.example.com.tictactoe;

/**
 * Created by Alvin on 4/24/2016.
 */

import java.util.Random;

public class GameLogic {

    private char playBoard[];
    private final static int BOARD_SIZE = 9;
    private Random randMove;

    public static final char REAL_PLAYER = 'X';
    public static final char PLAYER1 = 'X';
    public static final char COM_PLAYER = 'O';
    public static final char PLAYER2 = 'O';
    public static final char BLANK_SPACE = ' ';

    public static int getBoardSize() {
        return BOARD_SIZE;
    }

    // Initialize Board
    public GameLogic() {

        playBoard = new char[BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            playBoard[i] = BLANK_SPACE;
        }

        randMove = new Random();
    }

    // Clears Board
    public void clearBoard() {

        for (int i = 0; i < BOARD_SIZE; i++) {
            playBoard[i] = BLANK_SPACE;
        }
    }

    // Set player a move
    public void setMove(char player, int place) {

        playBoard[place] = player;
    }

    // Makes a computer move
    public int getComMove() {

        int move;

        // Checks if a computer played a move in that spot
        for (int i = 0; i < getBoardSize(); i++) {
            if (playBoard[i] != REAL_PLAYER && playBoard[i] != COM_PLAYER) {
                char currPlace = playBoard[i];
                playBoard[i] = COM_PLAYER;

                if (winCheck() == 3) {
                    setMove(COM_PLAYER, i);
                    return i;
                }
                else {
                    playBoard[i] = currPlace;
                }
            }
        }

        // Check to block human player from winning
        for (int i = 0; i < getBoardSize(); i++) {
            if (playBoard[i] != REAL_PLAYER && playBoard[i] != COM_PLAYER) {
                char currPlace = playBoard[i];
                playBoard[i] = REAL_PLAYER;

                if (winCheck() == 2) {
                    setMove(COM_PLAYER, i);
                    return i;
                }
                else {
                    playBoard[i] = currPlace;
                }
            }
        }

        // If play spot is empty, then make move
        do {
            move = randMove.nextInt(getBoardSize());
        } while (playBoard[move] == REAL_PLAYER || playBoard[move] == COM_PLAYER);
            setMove(COM_PLAYER, move);

        return move;
    }

    // Checks for winner
    public int winCheck() {

        // Checks for horizontal win, 1 row at a time
        for (int i = 0; i <= 6; i += 3) {
            // Check human or player 1
            if ((playBoard[i] == REAL_PLAYER && playBoard[i + 1] == REAL_PLAYER && playBoard[i + 2] == REAL_PLAYER) ||
                    (playBoard[i] == PLAYER1 && playBoard[i + 1] == PLAYER1 && playBoard[i + 2] == PLAYER1)) {
                return 2;
            }
            // Check computer or player 2
            if ((playBoard[i] == COM_PLAYER && playBoard[i + 1] == COM_PLAYER && playBoard[i + 2] == COM_PLAYER) ||
                    (playBoard[i] == PLAYER2 && playBoard[i + 1] == PLAYER2 && playBoard[i + 2] == PLAYER2)) {
                return 3;
            }
        }

        // Checks for vertical win, 1 column at a time
        for (int i = 0; i <= 2; i++) {
            // Check human or player 1
            if ((playBoard[i] == REAL_PLAYER && playBoard[i + 3] == REAL_PLAYER && playBoard[i + 6] == REAL_PLAYER) ||
                    (playBoard[i] == PLAYER1 && playBoard[i + 3] == PLAYER1 && playBoard[i + 6] == PLAYER1)) {
                return 2;
            }
            // checks computer or player 2
            if ((playBoard[i] == COM_PLAYER && playBoard[i + 3] == COM_PLAYER && playBoard[i + 6] == COM_PLAYER) ||
                    (playBoard[i] == PLAYER2 && playBoard[i + 3] == PLAYER2 && playBoard[i + 6] == PLAYER2)) {
                return 3;
            }
        }

        // checks diagonals for human
        if ((playBoard[0] == REAL_PLAYER && playBoard[4] == REAL_PLAYER && playBoard[8] == REAL_PLAYER) ||
                (playBoard[2] == REAL_PLAYER && playBoard[4] == REAL_PLAYER && playBoard[6] == REAL_PLAYER)) {
            return 2;
        }

        // checks diagonals for player 1
        if ((playBoard[0] == PLAYER1 && playBoard[4] == PLAYER1 && playBoard[8] == PLAYER1) ||
                (playBoard[2] == PLAYER1 && playBoard[4] == PLAYER1 && playBoard[6] == PLAYER1)) {
            return 2;
        }

        // checks diagonals for computer
        if ((playBoard[0] == COM_PLAYER && playBoard[4] == COM_PLAYER && playBoard[8] == COM_PLAYER) ||
                (playBoard[2] == COM_PLAYER && playBoard[4] == COM_PLAYER && playBoard[6] == COM_PLAYER)) {
            return 3;
        }

        // checks diagonals for player 2
        if ((playBoard[0] == PLAYER2 && playBoard[4] == PLAYER2 && playBoard[8] == PLAYER2) ||
                (playBoard[2] == PLAYER2 && playBoard[4] == PLAYER2 && playBoard[6] == PLAYER2)) {
            return 3;
        }

        // check for tie
        for (int i = 0; i < getBoardSize(); i++) {
            if ((playBoard[i] != REAL_PLAYER && playBoard[i] != COM_PLAYER) ||
                    (playBoard[i] != PLAYER1 && playBoard[i] != PLAYER2)) {
                return 0;
            }
        }

        // if it gets here, then no one has won, and no one can move, so must be tie
        return 1;
    }

}





















































