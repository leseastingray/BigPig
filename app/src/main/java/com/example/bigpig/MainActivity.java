package com.example.bigpig;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import java.util.Random;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
    implements OnEditorActionListener {

    // variable and field declarations
    private EditText player1Name;
    private EditText player2Name;
    private TextView player1ScoreTV;
    private TextView player2ScoreTV;
    private TextView turnPointsTV;
    private Button rollDie;
    private Button endTurn;
    private Button newGame;

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

        // roll die
        // end turn
        // new game
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
