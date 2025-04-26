package com.example.truthanddareapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Initialize the start button
        Button startButton = findViewById(R.id.startGameButton);
        startButton.setOnClickListener(v -> {
            // Reset spin count when starting a new game
            SharedPreferences preferences = getSharedPreferences("TruthDarePrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("SpinCount", 0);
            editor.apply();

            // Navigate to SpinTheBottleActivity
            Intent intent = new Intent(StartActivity.this, SpinTheBottleActivity.class);
            startActivity(intent);
            finish(); // Close StartActivity
        });
    }
}
