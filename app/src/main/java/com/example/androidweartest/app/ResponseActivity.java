package com.example.androidweartest.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class ResponseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);

        String response = getIntent().getStringExtra(MainActivity.RESPUESTA_ELEGIDA);

        TextView responseTextView = (TextView)findViewById(R.id.response);
        responseTextView.setText(
                getResources().getString(R.string.response_received) + ": " + response);
    }

}
