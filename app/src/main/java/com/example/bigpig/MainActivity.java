package com.example.bigpig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.view.View;
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

public class MainActivity extends AppCompatActivity
    implements OnEditorActionListener, OnClickListener
{
    // variable and field declarations
    private PigGame game;
    private EditText player1NameEditText;
    private EditText player2NameEditText;
    private TextView player1ScoreTextView;
    private TextView player2ScoreTextView;
    private TextView turnPointsTextView;
    private TextView turnNameTextView;
    private ImageView dieView;
    private Button rollDieButton;
    private Button endTurnButton ;
    private Button newGameButton;

    // for saving and restoring values
    private SharedPreferences savedValues;
    // for preferences
    private SharedPreferences prefs;

    private String player1NameString = "";
    private String player2NameString = "";
    private String currentPlayer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PigGame game = new PigGame();

        // Widget references
        // player1 editText name
        player1NameEditText = (EditText)findViewById(R.id.player1EditText);
        // player2 editText name
        player2NameEditText = (EditText)findViewById(R.id.player2EditText);
        // turnName TextView
        turnNameTextView = (TextView)findViewById(R.id.turnNameTextView);
        // turnPoints TextView
        turnPointsTextView = (TextView)findViewById(R.id.turnNameTextView);
        // new game button
        newGameButton = (Button)findViewById(R.id.newGameButton);
        // rollDie button
        rollDieButton = (Button)findViewById(R.id.rollDieButton);
        // endTurn
        endTurnButton = (Button)findViewById(R.id.endTurnButton);
        // dieView
        dieView = (ImageView)findViewById(R.id.dieImageView);

        // onEditor listeners
        player1NameEditText.setOnEditorActionListener(this);
        player2NameEditText.setOnEditorActionListener(this);
        // onClick listeners
        newGameButton.setOnClickListener(this);
        rollDieButton.setOnClickListener(this);
        endTurnButton.setOnClickListener(this);

        // set preferences
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences for saving values
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

    }
    @Override
    public void onPause()
    {
        // save instance variables
        Editor editor = savedValues.edit();
        editor.putString("player1NameString", player1NameString);
        editor.putString("player2NameString", player2NameString);

        editor.commit();
        super.onPause();
    }
    @Override
    public void onResume()
    {
        super.onResume();

        // get instance variables
        player1NameString = savedValues.getString("player1NameString", "");
        player2NameString = savedValues.getString("player2NameString", "");
    }

    @Override
    // connected to EditText widget
    public boolean onEditorAction(TextView v, int actionID, KeyEvent event)
    {
        // Close the soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        // set
        if (actionID == EditorInfo.IME_ACTION_DONE ||
                actionID == EditorInfo.IME_ACTION_UNSPECIFIED)
        {
            // UI
            player1NameEditText.setText(player1NameString);
            player2NameEditText.setText(player2NameString);
        }
        return false;
    }

    // name edits
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putInt(player1ScoreTextView.toString(), game.getPlayer1Score());
        outState.putInt(player2ScoreTextView.toString(), game.getPlayer2Score());
        super.onSaveInstanceState(outState);
    }

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
                int playerRoll = game.rollDie();

                // display roll
                displayDie(playerRoll);
                turnPointsTextView.setText(playerRoll);
                break;
            case R.id.endTurnButton:

                game.checkForWinner();

                game.changeTurn();

                currentPlayer = game.getCurrentPlayer();
                turnNameTextView.setText(currentPlayer);
                break;
            case R.id.newGameButton:
                game.resetGame();

        }
    }

    private void displayDie(int id)
    {
        id = 0;

        switch (id)
        {
            case 1:
                id = R.drawable.die8side1;
                break;
            case 2:
                id = R.drawable.die8side2;
                break;
            case 3:
                id = R.drawable.die8side3;
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

}
