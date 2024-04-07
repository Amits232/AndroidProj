package com.example.androidproj;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to the buttons
        Button btnGuessGame = findViewById(R.id.btn_guess_game);
        Button btnImageCapture = findViewById(R.id.btn_image_capture);
        Button btnCalculator = findViewById(R.id.btn_calculator);
        Button btnCustomGame = findViewById(R.id.btn_custom_game);

        // Set click listeners for the buttons
        btnGuessGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Guess the Number Game activity
                Intent intent = new Intent(MainActivity.this, GuessGameActivity.class);
                startActivity(intent);
            }
        });

        btnImageCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Image Capture activity
                Intent intent = new Intent(MainActivity.this, ImageCaptureActivity.class);
                startActivity(intent);
            }
        });

        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Calculator activity
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });

        btnCustomGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Custom Game activity
                Intent intent = new Intent(MainActivity.this, CustomGameActivity.class);
                startActivity(intent);
            }
        });
    }
}
