package com.example.truthanddareapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class TruthActivity extends AppCompatActivity {

    private static final String[] TRUTH_QUESTIONS = {
            "What is your biggest fear?",
            "Have you ever lied to your best friend?",
            "What is your most embarrassing moment?",
            "Who do you have a crush on?",
            "What’s a secret you’ve never told anyone?"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truth);

        // Reference to the TextView
        TextView truthQuestionTextView = findViewById(R.id.truthQuestion);
        Button backButton = findViewById(R.id.backButton);

        // Select a random truth question
        Random random = new Random();
        String randomTruth = TRUTH_QUESTIONS[random.nextInt(TRUTH_QUESTIONS.length)];
        truthQuestionTextView.setText(randomTruth);

        // Back button to navigate to SpinTheBottleActivity
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(TruthActivity.this, SpinTheBottleActivity.class);
            startActivity(intent);
            finish(); // Close current activity
        });
    }
}
