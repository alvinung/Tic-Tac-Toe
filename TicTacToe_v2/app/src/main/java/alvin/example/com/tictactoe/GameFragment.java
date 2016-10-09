package alvin.example.com.tictactoe;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class GameFragment extends Fragment {

    private GameLogic game;
    private Button boardButtons[];
    private Button newGameButton;
    private TextView infoTextView;
    private TextView humanScore;
    private TextView tieCounter;
    private TextView computerScore;
    private TextView player1;
    private TextView player2;

    private int humanCounter = 0;
    private int tieCount = 0;
    private int computerCounter = 0;
    private int playId;

    private boolean humanFirst = true;
    private boolean player1First = true;
    private boolean gameOver = false;
    public static char currentPlayer;

    public GameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_game, container, false);

        newGameButton = (Button) view.findViewById(R.id.newGameButton);
        boardButtons = new Button[game.getBoardSize()];
        boardButtons[0] = (Button) view.findViewById(R.id.one);
        boardButtons[1] = (Button) view.findViewById(R.id.two);
        boardButtons[2] = (Button) view.findViewById(R.id.three);
        boardButtons[3] = (Button) view.findViewById(R.id.four);
        boardButtons[4] = (Button) view.findViewById(R.id.five);
        boardButtons[5] = (Button) view.findViewById(R.id.six);
        boardButtons[6] = (Button) view.findViewById(R.id.seven);
        boardButtons[7] = (Button) view.findViewById(R.id.eight);
        boardButtons[8] = (Button) view.findViewById(R.id.nine);

        infoTextView = (TextView) view.findViewById(R.id.Info);
        humanScore = (TextView) view.findViewById(R.id.humanScore);
        tieCounter = (TextView) view.findViewById(R.id.tiesCounter);
        computerScore = (TextView) view.findViewById(R.id.computerScore);
        player1 = (TextView) view.findViewById(R.id.human);
        player2 = (TextView) view.findViewById(R.id.computer);

        game = new GameLogic();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // pull out player num id
        Intent intent = getActivity().getIntent();
        this.playId = intent.getIntExtra("num_players_id", R.id.singlePlayerButton);


        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(playId);
            }
        });
        startGame(playId);
    }

    // Starts type of game based on given resource ID
    public void startGame(int id) {
        if (id == R.id.singlePlayerButton) {
            startSinglePlayerGame();
        }
        if (id == R.id.twoPlayerButton) {
            player1.setText(R.string.player1);
            player2.setText(R.string.player2);
            startTwoPlayerGame();
        }
    }

    // Game for 2 players
    private void startTwoPlayerGame() {

        game.clearBoard();

        for (int i = 0; i < boardButtons.length; i++) {
            boardButtons[i].setText("");
            boardButtons[i].setEnabled(true);
            boardButtons[i].setOnClickListener(new TwoPlayButtonClickListener(i));
        }
        if (player1First) {
            infoTextView.setText(R.string.player1_turn);
            currentPlayer = game.PLAYER1;
            player1First = false;
        }
        else {
            infoTextView.setText(R.string.player2_turn);
            currentPlayer = game.PLAYER2;
            player1First = true;
        }
        gameOver = false;

    }

    private class TwoPlayButtonClickListener implements View.OnClickListener {

        int place;

        public TwoPlayButtonClickListener(int place) {
            this.place = place;
        }

        public void onClick(View view) {
            if (!gameOver) {
                if (boardButtons[place].isEnabled()) {
                    setMove(currentPlayer, place);
                    int winner = game.winCheck();

                    if (currentPlayer == game.PLAYER1) {
                        currentPlayer = game.PLAYER2;
                    }
                    else {
                        currentPlayer = game.PLAYER1;
                    }

                    if (winner == 0) {
                        if (currentPlayer == game.PLAYER2) {
                            infoTextView.setText(R.string.player2_turn);
                        }
                        else {
                            infoTextView.setText(R.string.player1_turn);
                        }
                    }
                    else if (winner == 1) {
                        infoTextView.setText(R.string.result_tie);
                        tieCount++;
                        tieCounter.setText(Integer.toString(tieCount));
                        gameOver = true;
                    }
                    else if (winner == 2) {
                        infoTextView.setText(R.string.result_player1_wins);
                        humanCounter++;
                        humanScore.setText(Integer.toString(humanCounter));
                        gameOver = true;
                    }
                    else {
                        infoTextView.setText(R.string.result_player2_wins);
                        computerCounter++;
                        computerScore.setText(Integer.toString(computerCounter));
                        gameOver = true;
                    }
                }
            }
        }
    }

    // Game for single player
    private void startSinglePlayerGame() {

        game.clearBoard();

        for (int i = 0; i < boardButtons.length; i++) {
            boardButtons[i].setText("");
            boardButtons[i].setEnabled(true);
            boardButtons[i].setOnClickListener(new ButtonClickListener(i));
        }

        if (humanFirst) {
            infoTextView.setText(R.string.first_turn_human);
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

    private void setMove(char player, int place) {

        game.setMove(player,  place);
        boardButtons[place].setEnabled(false);
        boardButtons[place].setText(String.valueOf(player));

        if (player == game.REAL_PLAYER || player == game.PLAYER1) {
            boardButtons[place].setTextColor(Color.RED);
        }
        else {
            boardButtons[place].setTextColor(Color.BLACK);
        }
    }


}
