package com.example.androidproj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CustomGameActivity extends AppCompatActivity implements View.OnClickListener {

    // Array to hold the buttons of the game board
    private Button[][] buttons = new Button[3][3];

    // Variable to track whose turn it is (true for player X, false for player O)
    private boolean playerXTurn = true; // Player X starts

    // Variable to keep track of the number of rounds played
    private int roundCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_game);

        // Initialize buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID); // Find button by ID
                buttons[i][j].setOnClickListener(this); // Set OnClickListener for button
            }
        }
    }

    @Override
    public void onClick(View v) {
        // Check if the clicked button is empty
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        // Set the text of the clicked button based on whose turn it is
        if (playerXTurn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        // Increment the round count
        roundCount++;

        // Check for win or draw
        if (checkForWin()) {
            if (playerXTurn) {
                playerWins("X");
            } else {
                playerWins("O");
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            // Switch turns
            playerXTurn = !playerXTurn;
        }
    }

    // Method to check for a win
    private boolean checkForWin() {
        String[][] field = new String[3][3];

        // Store the text of each button in the field array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }

        // Check diagonals
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    // Method to display a message when a player wins
    private void playerWins(String player) {
        Toast.makeText(this, "Player " + player + " wins!", Toast.LENGTH_SHORT).show();
        resetGame(); // Reset the game board
    }

    // Method to display a message when the game ends in a draw
    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetGame(); // Reset the game board
    }

    // Method to reset the game board
    private void resetGame() {
        // Clear the text of all buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        // Reset round count and player turn
        roundCount = 0;
        playerXTurn = true;
    }
}
