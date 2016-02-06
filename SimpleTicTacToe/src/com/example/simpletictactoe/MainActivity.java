package com.example.simpletictactoe;

//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//public class MainActivity extends ActionBarActivity {
public class MainActivity extends AppCompatActivity {

	private TicTacToeGame game;
	private Button boardButtons[];
	private TextView infoTextView;
	private TextView humanScore;
	private TextView tieCounter;
	private TextView computerScore;
	
	private int humanCounter = 0;
	private int tieCount = 0;
	private int computerCounter = 0;
	
	private boolean humanFirst = true;
	private boolean gameOver = false;
	
	@SuppressWarnings("static-access")
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		boardButtons = new Button[game.getBoardSize()];
		boardButtons[0] = (Button) findViewById(R.id.one);
		boardButtons[1] = (Button) findViewById(R.id.two);
		boardButtons[2] = (Button) findViewById(R.id.three);
		boardButtons[3] = (Button) findViewById(R.id.four);
		boardButtons[4] = (Button) findViewById(R.id.five);
		boardButtons[5] = (Button) findViewById(R.id.six);
		boardButtons[6] = (Button) findViewById(R.id.seven);
		boardButtons[7] = (Button) findViewById(R.id.eight);
		boardButtons[8] = (Button) findViewById(R.id.nine);
		
		infoTextView = (TextView) findViewById(R.id.Info);
		humanScore = (TextView) findViewById(R.id.humanScore);
		tieCounter = (TextView) findViewById(R.id.tiesCounter);
		computerScore = (TextView) findViewById(R.id.computerScore);
		
		humanScore.setText(Integer.toString(humanCounter));
		tieCounter.setText(Integer.toString(tieCount));
		computerScore.setText(Integer.toString(computerCounter));
		
		game = new TicTacToeGame();
		
		startGame();
		
	}
	
	@SuppressWarnings("static-access")
	private void startGame() {
		
		game.clearBoard();
		
		for (int i = 0; i < boardButtons.length; i++) {
			boardButtons[i].setText("");
			boardButtons[i].setEnabled(true);
			boardButtons[i].setOnClickListener(new ButtonClickListener(i));
		}
		
		if (humanFirst) {
			infoTextView.setText(R.string.first_human);
			humanFirst = false;
		}
		else {
			infoTextView.setText(R.string.computer_turn);
			int move = game.getComMove();
			setMove(game.COM_PLAYER, move);
			humanFirst = true;
		}
		gameOver = false;
	}
	
	private class ButtonClickListener implements View.OnClickListener {
		
		int place;
		
		public ButtonClickListener(int place) {
		
			this.place = place;
		}
		
		@SuppressWarnings("static-access")
		public void onClick(View view) {
			
			if (!gameOver) {
				if (boardButtons[place].isEnabled()) {
					setMove(game.REAL_PLAYER, place);
					
					int winner = game.winCheck();
					
					if (winner == 0) {
						infoTextView.setText(R.string.computer_turn);
						int move = game.getComMove();
						setMove(game.COM_PLAYER, move);
						winner = game.winCheck();
					}
					
					if (winner == 0) {
						infoTextView.setText(R.string.human_turn);
					}
					else if (winner == 1) {
						infoTextView.setText(R.string.result_tie);
						tieCount++;
						tieCounter.setText(Integer.toString(tieCount));
						gameOver = true;
					}
					else if (winner == 2) {
						infoTextView.setText(R.string.result_human_wins);
						humanCounter++;
						humanScore.setText(Integer.toString(humanCounter));
						gameOver = true;
					}
					else {
						infoTextView.setText(R.string.result_computer_wins);
						computerCounter++;
						computerScore.setText(Integer.toString(computerCounter));
						gameOver = true;
					}
				}
			}
		}
	}
	
	@SuppressWarnings("static-access")
	private void setMove(char player, int place) {
		
		game.setMove(player,  place);
		boardButtons[place].setEnabled(false);
		boardButtons[place].setText(String.valueOf(player));
		
		if (player == game.REAL_PLAYER) {
			boardButtons[place].setTextColor(Color.RED);
		}
		else {
			boardButtons[place].setTextColor(Color.BLACK);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()) {
		
		case R.id.new_game:
			startGame();
			break;
		case R.id.exit_game:
			MainActivity.this.finish();
			break;
		}
		
		return true;
	}
}
