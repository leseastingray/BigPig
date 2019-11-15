package com.example.bigpig;

import android.app.Activity;
import android.os.Bundle;

public class SettingsActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // set the XML layout view for settings
        setContentView(R.layout.activity_settings);
    }
}
