package com.example.truthanddareapp;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class SpinTheBottleActivity extends AppCompatActivity {

    private ImageView bottleImage;
    private Button spinButton;
    private TextView spinCountText;
    private int spinCount = 0; // Spin count
    private SharedPreferences preferences; // SharedPreferences instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_the_bottle);

        // Initialize UI elements
        bottleImage = findViewById(R.id.bottleImage);
        spinButton = findViewById(R.id.spinButton);
        spinCountText = findViewById(R.id.spinCountText);
        Button dareButton = findViewById(R.id.dareButton);
        Button truthButton = findViewById(R.id.truthButton);
        Button homeButton = findViewById(R.id.homeButton);

        // Initialize SharedPreferences
        preferences = getSharedPreferences("TruthDarePrefs", Context.MODE_PRIVATE);
        spinCount = preferences.getInt("SpinCount", 0); // Retrieve saved count
        spinCountText.setText("Spin Count: " + spinCount);

        // Set click listeners
        dareButton.setOnClickListener(v -> navigateTo(DareActivity.class));
        truthButton.setOnClickListener(v -> navigateTo(TruthActivity.class));
        homeButton.setOnClickListener(v -> resetAndNavigateTo(StartActivity.class));

        // Spin button listener
        spinButton.setOnClickListener(v -> spinBottle());
    }

    private void spinBottle() {
        spinCount++; // Increase spin count
        spinCountText.setText("Spin Count: " + spinCount);

        // Save spin count in SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("SpinCount", spinCount);
        editor.apply();

        // Generate a random angle for the bottle to stop at
        Random random = new Random();
        float randomAngle = random.nextInt(360) + 720; // At least 2 full rotations

        // Animate the bottle spinning
        ObjectAnimator bottleAnimator = ObjectAnimator.ofFloat(bottleImage, "rotation", 0f, randomAngle);
        bottleAnimator.setDuration(2000); // 2 seconds spin duration
        bottleAnimator.start();

        // Show a message indicating the bottle's final stop position
        bottleAnimator.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                super.onAnimationEnd(animation);
                float finalAngle = randomAngle % 360;
                Toast.makeText(SpinTheBottleActivity.this, "Bottle stopped at: " + finalAngle + "°", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateTo(Class<?> activityClass) {
        Intent intent = new Intent(SpinTheBottleActivity.this, activityClass);
        startActivity(intent);
    }

    private void resetAndNavigateTo(Class<?> activityClass) {
        // Reset spin count in SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("SpinCount", 0);
        editor.apply();

        // Navigate to home
        Intent intent = new Intent(SpinTheBottleActivity.this, activityClass);
        startActivity(intent);
        finish();
    }
}
