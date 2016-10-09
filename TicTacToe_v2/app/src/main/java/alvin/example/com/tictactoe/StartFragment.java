package alvin.example.com.tictactoe;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class StartFragment extends Fragment {

    private static final int REQUEST_CODE_GAME_ACTIVITY = 1111;

    public StartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // attach click listener to single player button
        Button single = (Button) getActivity().findViewById(R.id.singlePlayerButton);
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = R.id.singlePlayerButton;
                startGame(id);
            }
        });

        // attach click listener to multi player button
        Button multi = (Button) getActivity().findViewById(R.id.twoPlayerButton);
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = R.id.twoPlayerButton;
                startGame(id);
            }
        });
    }

    // Starts game activity based on selected player number
    public void startGame(int id) {
        Intent intent = new Intent(getActivity(), Game.class);
        intent.putExtra("num_players_id", id);
        startActivityForResult(intent, REQUEST_CODE_GAME_ACTIVITY);
    }

}
