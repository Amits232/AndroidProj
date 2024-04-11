package com.example.androidproj;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to the buttons
        Button btnGuessGame = findViewById(R.id.btn_guess_game);
        Button btnImageCapture = findViewById(R.id.btn_image_capture);
        Button btnCalculator = findViewById(R.id.btn_calculator);
        Button btnCustomGame = findViewById(R.id.btn_custom_game);

        // Get reference to the image view
        imageView = findViewById(R.id.image);

        // Set click listener for all buttons
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Determine which button was clicked based on its ID
                if (v.getId() == R.id.btn_guess_game) {
                    // Start Guess the Number Game activity
                    Intent guessGameIntent = new Intent(MainActivity.this, GuessGameActivity.class);
                    startActivity(guessGameIntent);
                } else if (v.getId() == R.id.btn_image_capture) {
                    // Start Image Capture activity
                    Intent imageCaptureIntent = new Intent(MainActivity.this, ImageCaptureActivity.class);
                    startActivityForResult(imageCaptureIntent, REQUEST_IMAGE_CAPTURE);
                } else if (v.getId() == R.id.btn_calculator) {
                    // Start Calculator activity
                    Intent calculatorIntent = new Intent(MainActivity.this, CalculatorActivity.class);
                    startActivity(calculatorIntent);
                } else if (v.getId() == R.id.btn_custom_game) {
                    // Start Custom Game activity
                    Intent customGameIntent = new Intent(MainActivity.this, CustomGameActivity.class);
                    startActivity(customGameIntent);
                }
            }
        };

        // Assign click listener to all buttons
        btnGuessGame.setOnClickListener(buttonClickListener);
        btnImageCapture.setOnClickListener(buttonClickListener);
        btnCalculator.setOnClickListener(buttonClickListener);
        btnCustomGame.setOnClickListener(buttonClickListener);
    }
}