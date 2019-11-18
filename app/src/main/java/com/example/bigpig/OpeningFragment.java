package com.example.bigpig;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.inputmethodservice.InputMethodService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
import android.os.Bundle;

public class OpeningFragment extends Fragment
    implements OnEditorActionListener {

    // variable and field declarations
    private EditText player1NameEditText;
    private EditText player2NameEditText;
    private Button newGameButton;
    private String player1Name = "";
    private String player2Name = "";
    public static final String PLAYER_1_NAME = "player1Name";
    public static final String PLAYER_2_NAME = "player2Name";
    private boolean twoPaneLayout;
    private Activity activity;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // inflate view for fragment
        View v = inflater.inflate(R.layout.fragment_opening, container, false);
        // Widget references
        // player1 editText name
        player1NameEditText = (EditText)v.findViewById(R.id.player1NameEditText);
        // player2 editText name
        player2NameEditText = (EditText)v.findViewById(R.id.player2NameEditText);
        // new game button
        newGameButton = (Button)v.findViewById(R.id.newGameButton);

        // onEditor listeners
        player1NameEditText.setOnEditorActionListener(this);
        player2NameEditText.setOnEditorActionListener(this);

        // set focus to Player1NameEditText
        player1NameEditText.requestFocus();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = (MainActivity)getActivity();

        // TODO: Check to see if FirstActivity has loaded a single or dual pane layout

    }
    // TODO: Why isn't the onClick in layout hooking up to this
    public void newGame() {
        // create intent
        Intent intent = new Intent(getActivity(), MainActivity.class);
        // put extras in intent
        intent.putExtra(PLAYER_1_NAME, player1Name);
        intent.putExtra(PLAYER_2_NAME, player2Name);
        // start new activity
        startActivity(intent);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // Close the soft keyboard
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        // set
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            // set player names in UI
            player1Name = player1NameEditText.getText().toString();
            player2Name = player2NameEditText.getText().toString();
            player1NameEditText.setText(player1Name);
            player2NameEditText.setText(player2Name);
            // set focus to roll button
            newGameButton.requestFocus();

        }
        return false;
    }


}
