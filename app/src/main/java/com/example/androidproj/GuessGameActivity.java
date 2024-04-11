package com.example.androidproj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class GuessGameActivity extends AppCompatActivity {

    private EditText editTextGuess;
    private Button buttonGuess;
    private TextView textViewResult;
    private int minNumber = 1;
    private int maxNumber = 10;
    private int randomNumber;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_game);

        editTextGuess = findViewById(R.id.editTextGuess);
        buttonGuess = findViewById(R.id.buttonGuess);
        textViewResult = findViewById(R.id.textViewResult);

        // Generate a random number between the current range
        generateRandomNumber();

        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(maxNumber - minNumber + 1) + minNumber;
    }

    private void checkGuess() {
        String guessText = editTextGuess.getText().toString();
        if (guessText.isEmpty()) {
            textViewResult.setText("Please enter a number.");
            return;
        }

        int guess = Integer.parseInt(guessText);

        if (guess < minNumber || guess > maxNumber) {
            textViewResult.setText("Please enter a number between " + minNumber + " and " + maxNumber + ".");
        } else if (guess < randomNumber) {
            textViewResult.setText("Too low! Try again.");
        } else if (guess > randomNumber) {
            textViewResult.setText("Too high! Try again.");
        } else {
            textViewResult.setText("Congratulations! You guessed the number.");

            // Increase the range and generate a new random number
            maxNumber *= 2;
            generateRandomNumber();

            // Update UI for the new range
            editTextGuess.setText(""); // Clear the guess input
            textViewResult.append("\nNew range: " + minNumber + " - " + maxNumber);

        }
    }
}
