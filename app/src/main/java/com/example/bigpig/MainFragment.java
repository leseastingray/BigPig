package com.example.bigpig;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
import java.util.Random;
import android.os.Bundle;

public class MainFragment extends Fragment implements OnClickListener
{
    // variable and field declarations
    private PigGame game;
    private TextView player1ScoreTextView;
    private TextView player2ScoreTextView;
    private TextView turnPointsTextView;
    private TextView turnNameTextView;
    private ImageView dieView;
    private Button rollDieButton;
    private Button endTurnButton;
    private Button newGameButton;
    private TextView winnerTextView;

    // for saving and restoring values
    private SharedPreferences savedValues;

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1ScoreString = "";
    private String player2ScoreString = "";
    private String currentPlayer = "";
    private String currentPoints = "";
    private int turnPoints = 0;
    private int playerRoll = 0;
    private String yesWinner = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new PigGame();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        rollDieButton = (Button)view.findViewById(R.id.rollDieButton);
        endTurnButton = (Button)view.findViewById(R.id.endTurnButton);
        rollDieButton.setOnClickListener(this);
        endTurnButton.setOnClickListener(this);

        //SharedPreferences for saving values
        //savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
        return view;

    }
    /*
    @Override
    public void onPause()
    {
        // save instance variables
        Editor editor = savedValues.edit();
        editor.putString("player1NameString", player1Name);
        editor.putString("player2NameString", player2Name);
        editor.putInt("player1Score", player1Score);
        editor.putInt("player2Score", player2Score);
        editor.putInt("playerRoll", playerRoll);
        editor.putString("currentPoints", currentPoints);
        editor.putString("currentPlayer", currentPlayer);

        editor.commit();
        super.onPause();
    }
    @Override
    public void onResume()
    {
        super.onResume();

        // get instance variables
        player1Name = savedValues.getString("player1NameString", "");
        player2Name = savedValues.getString("player2NameString", "");
        player1Score = savedValues.getInt("player1Score", 0);
        player2Score = savedValues.getInt("player2Score",0);
        playerRoll = savedValues.getInt("playerRoll", 0);
        currentPoints = savedValues.getString("currentPoints","");
        currentPlayer = savedValues.getString("currentPlayer", "");
    }

    // name edits
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        outState.putString("player1Name", player1Name);
        outState.putString("player2Name", player2Name);
        outState.putInt("player1Score", player1Score);
        outState.putInt("player2score", player2Score);
        outState.putInt("playerRoll", playerRoll);
        super.onSaveInstanceState(outState);
    }
    */
    @Override
    // this would replace onClickListener...
    public void onClick(View v)
    {
        // get button id
        switch (v.getId())
        {
            // rollDieButton:
            //     roll game die
            //     display die
            //     set text to points
            // endTurnButton:
            //     calculate points
            //     check for winner
            //     change turn
            //     switch players and set turn text
            // newGameButton:
            //     reset game
            case R.id.rollDieButton:
                playerRoll = game.rollDie();

                // disable roll button if 8
                if (playerRoll == 8)
                {
                    rollDieButton.setEnabled(false);
                }
                displayDie(playerRoll);

                currentPoints = Integer.toString(playerRoll);
                turnPointsTextView.setText(currentPoints);
                break;
            case R.id.endTurnButton:
                // get turn points
                turnPoints = game.getTurnPoints();


                // change turn (math)
                game.changeTurn();
                // get current player
                currentPlayer = game.getCurrentPlayer();


                player1Score = game.getPlayer1Score();
                player1ScoreTextView.setText(Integer.toString(player1Score));

                player2Score = game.getPlayer2Score();
                player2ScoreTextView.setText(Integer.toString(player2Score));

                // check for winner
                yesWinner = game.checkForWinner();
                if (yesWinner != "")
                {
                    winnerTextView.setText(yesWinner);
                    rollDieButton.setEnabled(false);
                    endTurnButton.setEnabled(false);
                }
                // update turn points
                turnPointsTextView.setText("");
                // update turn name display
                turnNameTextView.setText(currentPlayer);
                // re-enable roll die
                rollDieButton.setEnabled(true);
                break;
        }
    }

    private void displayDie(int dieRoll)
    {
        int id = 0;

        switch (dieRoll)
        {
            case 1:
                id = R.drawable.die8side1;
                break;
            case 2:
                id = R.drawable.die8side2;
                break;
            case 3:
                id = R.drawable.die8side3;
                break;
            case 4:
                id = R.drawable.die8side4;
                break;
            case 5:
                id = R.drawable.die8side5;
                break;
            case 6:
                id = R.drawable.die8side6;
                break;
            case 7:
                id = R.drawable.die8side7;
                break;
            case 8:
                id = R.drawable.die8side8;
                break;
        }
        dieView.setImageResource(id);
    }
    // reset UI
    private void resetGameUI()
    {
        // reset UI variables
        player1ScoreString = "";
        player2ScoreString = "";
        currentPlayer = "";
        currentPoints = "";
        yesWinner = "";


        // reset widgets
        player1ScoreTextView.setText("");
        player2ScoreTextView.setText("");
        turnNameTextView.setText("");
        turnPointsTextView.setText("");
        winnerTextView.setText("");

        // enable buttons
        rollDieButton.setEnabled(true);
        endTurnButton.setEnabled(true);
    }
}
