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

        // Set title
        setTitle("Afik and Amits App");

        // Get references to the buttons
        Button btnGuessGame = findViewById(R.id.btn_guess_game);
        Button btnImageCapture = findViewById(R.id.btn_image_capture);
        Button btnCalculator = findViewById(R.id.btn_calculator);
        Button btnCustomGame = findViewById(R.id.btn_custom_game);

        // Get reference to the image view
        imageView = findViewById(R.id.image);

        // Set click listeners for the buttons
        btnGuessGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Guess the Number Game activity
                // Intent intent = new Intent(MainActivity.this, GuessGameActivity.class);
                // startActivity(intent);
            }
        });

        btnImageCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Image Capture activity
                Intent intent = new Intent(MainActivity.this, ImageCaptureActivity.class);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });

        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Calculator activity
                // Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                // startActivity(intent);
            }
        });

        btnCustomGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Custom Game activity
                // Intent intent = new Intent(MainActivity.this, CustomGameActivity.class);
                // startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
            }
        }
    }
}
