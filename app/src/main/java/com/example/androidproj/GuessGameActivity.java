package com.example.androidproj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class CustomGameActivity extends AppCompatActivity {

    private EditText editTextGuess;
    private Button buttonGuess;
    private TextView textViewResult;
    private int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_game);

        editTextGuess = findViewById(R.id.editTextGuess);
        buttonGuess = findViewById(R.id.buttonGuess);
        textViewResult = findViewById(R.id.textViewResult);

        // Generate a random number between 1 and 100
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
    }

    private void checkGuess() {
        String guessText = editTextGuess.getText().toString();
        if (guessText.isEmpty()) {
            textViewResult.setText("Please enter a number.");
            return;
        }

        int guess = Integer.parseInt(guessText);

        if (guess < 1 || guess > 100) {
            textViewResult.setText("Please enter a number between 1 and 100.");
        } else if (guess < randomNumber) {
            textViewResult.setText("Too low! Try again.");
        } else if (guess > randomNumber) {
            textViewResult.setText("Too high! Try again.");
        } else {
            textViewResult.setText("Congratulations! You guessed the number.");
            buttonGuess.setEnabled(false); // Disable the button after correct guess
        }
    }
}
