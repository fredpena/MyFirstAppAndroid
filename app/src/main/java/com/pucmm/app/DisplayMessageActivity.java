package com.pucmm.app;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

      //  Toast.makeText(this, "Hello DisplayMessageActivity!", Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        String message =intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);

    }
}