package com.example.simpletictactoe;

import java.util.Random;

public class TicTacToeGame {

	private char playBoard[];
	private final static int BOARD_SIZE = 9;
	
	public static final char REAL_PLAYER = 'X';
	public static final char COM_PLAYER = 'O';
	public static final char BLANK_SPACE = ' ';
	
	private Random randMove;
	
	public static int getBoardSize() {
		return BOARD_SIZE;
	}
	
	// initialize board
	public TicTacToeGame() {
		
		playBoard = new char[BOARD_SIZE];
		
		for (int i = 0; i < BOARD_SIZE; i++) {
			playBoard[i] = BLANK_SPACE;
		}
		
		randMove = new Random();
	}
	
	// clears board
	public void clearBoard() {
		
		for (int i = 0; i < BOARD_SIZE; i++) {
			playBoard[i] = BLANK_SPACE;
		}
	}
	
	// sets a move
	public void setMove(char player, int place) {
		
		playBoard[place] = player;
	}
	
	// makes computer move
	public int getComMove() {
		
		int move;
		
		// checks of computer played a move in that spot
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
		
		// check to block human from winning
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
		
		// if play spot is empty, then make move
		do {
			move = randMove.nextInt(getBoardSize());
		} while (playBoard[move] == REAL_PLAYER || playBoard[move] == COM_PLAYER);
			
			setMove(COM_PLAYER, move);
		
		return move;
	}
	
	public int winCheck() {
		
		// check for horizontal win, 1 row at a time
		for (int i = 0; i <= 6; i += 3) {
			// check human
			if (playBoard[i] == REAL_PLAYER && playBoard[i + 1] == REAL_PLAYER && playBoard[i + 2] == REAL_PLAYER) {
				return 2;
			}
			// check computer
			if (playBoard[i] == COM_PLAYER && playBoard[i + 1] == COM_PLAYER && playBoard[i + 2] == COM_PLAYER) {
				return 3;
			}
		}
		
		// check for vertical win, 1 column at a time
		for (int i = 0; i <= 2; i++) {
			// checks human
			if (playBoard[i] == REAL_PLAYER && playBoard[i + 3] == REAL_PLAYER && playBoard[i + 6] == REAL_PLAYER) {
				return 2;
			}
			// checks computer
			if (playBoard[i] == COM_PLAYER && playBoard[i + 3] == COM_PLAYER && playBoard[i + 6] == COM_PLAYER) {
				return 3;
			}
		}
		
		// checks diagonals for human
		if ((playBoard[0] == REAL_PLAYER && playBoard[4] == REAL_PLAYER && playBoard[8] == REAL_PLAYER) ||
				(playBoard[2] == REAL_PLAYER && playBoard[4] == REAL_PLAYER && playBoard[6] == REAL_PLAYER)) {
			return 2;
		}
		// checks diagonals for computer
		if ((playBoard[0] == COM_PLAYER && playBoard[4] == COM_PLAYER && playBoard[8] == COM_PLAYER) ||
				(playBoard[2] == COM_PLAYER && playBoard[4] == COM_PLAYER && playBoard[6] == COM_PLAYER)) {
			return 3;
		}
		
		// check for tie
		for (int i = 0; i < getBoardSize(); i++) {
			if (playBoard[i] != REAL_PLAYER && playBoard[i] != COM_PLAYER) {
				return 0;
			}
		}
		
		// if it gets here, then no one has won, and no one can move, so must be tie
		return 1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
