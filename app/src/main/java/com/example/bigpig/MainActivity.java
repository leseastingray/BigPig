package com.example.bigpig;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import java.util.Random;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

    // variable and field declarations

    PigGame game;
    private EditText player1Name;
    private EditText player2Name;
    private TextView player1ScoreTV;
    private TextView player2ScoreTV;
    private TextView turnPointsTV;
    private ImageView dieView;
    private Button rollDie;
    private Button endTurn;
    private Button newGame;

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

        // Widget references
        // player1 editText name
        player1Name = (EditText)findViewById(R.id.player1EditText);
        // player2 editText name
        player2Name = (EditText)findViewById(R.id.player2EditText);
        // new game button
        newGameButton = (Button)findViewById(R.id.NewGameButton);
        // roll die button
        // end turn button

        // Set event listeners

    }

    // run PigGame
    private void newGame()
    {
        PigGame pigGame = new PigGame();

        // user input strings
        String player1NameText = player1Name.getText().ToString();
        String player2NameText = player2Name.getText().ToString();

        // displays
        player1Name.setText(player1NameText);
        player2Name.setText(player2NameText);
    }

    // name edits
}
