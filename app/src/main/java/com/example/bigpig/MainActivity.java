package com.example.bigpig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
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
    implements OnEditorActionListener
{
    // variable and field declarations
    private PigGame game;
    private EditText player1Name;
    private EditText player2Name;
    private TextView player1ScoreTV;
    private TextView player2ScoreTV;
    private TextView turnPointsTV;
    private ImageView dieView;
    private Button rollDieButton;
    private Button endTurnButton ;
    private Button newGameButton;


    // event handlers
    public void play(View v)
    {
        int playerRoll = game.rollDie();
        turnPointsTV.setText(playerRoll);
    }
    private void displayDie(Die die)
    {
        int id = 0;

        switch (die)
        {
            case one:
                id = R.drawable.die8side1;
                break;
            case two:
                id = R.drawable.die8side2;
                break;
            case three:
                id = R.drawable.die8side3;
            case four:
                id = R.drawable.die8side4;
                break;
            case five:
                id = R.drawable.die8side5;
                break;
            case six:
                id = R.drawable.die8side6;
                break;
            case seven:
                id = R.drawable.die8side7;
                break;
            case eight:
                id = R.drawable.die8side8;
                break;
        }
        dieView.setImageResource(id);
    }

    public void endTurn()
    {
        game.changeTurn();
    }
    // display dice face


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PigGame game = new PigGame();

        // Widget references
        // player1 editText name
        player1Name = (EditText)findViewById(R.id.player1EditText);
        // player2 editText name
        player2Name = (EditText)findViewById(R.id.player2EditText);
        // new game button
        newGameButton = (Button)findViewById(R.id.newGameButton);

        //displayScores();
    }
    @Override
    // connected to EditText widget
    public boolean OnEditorAction(TextView v, int actionID, KeyEvent event)
    {
        // Close the soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        return false;
    }

    @Override
    // this would replace onClickListener...
    public void OnClick(View v)
    {
        play(v);
    }

    // name edits
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putInt(player1ScoreTV.toString(), game.getPlayer1Score());
        outState.putInt(player2ScoreTV.toString(), game.getPlayer2Score());
        super.onSaveInstanceState(outState);
    }
}
