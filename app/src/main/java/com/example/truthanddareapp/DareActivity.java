package com.example.truthanddareapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class DareActivity extends AppCompatActivity {

    private static final String[] DARE_QUESTIONS = {
            "Do 20 push-ups.",
            "Sing the chorus of your favorite song.",
            "Run around the room acting like a chicken.",
            "Send a funny selfie to the last person you texted.",
            "Do your best impression of a celebrity of your choice."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dare);

        // Reference to the TextView and Button
        TextView dareQuestionTextView = findViewById(R.id.dareQuestion);
        Button backButton = findViewById(R.id.backButton);

        // Select a random dare question
        Random random = new Random();
        String randomDare = DARE_QUESTIONS[random.nextInt(DARE_QUESTIONS.length)];
        dareQuestionTextView.setText(randomDare);

        // Back button to navigate to SpinTheBottleActivity
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(DareActivity.this, SpinTheBottleActivity.class);
            startActivity(intent);
            finish(); // Close current activity
        });
    }
}
