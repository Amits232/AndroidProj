package com.example.androidproj;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// Package and imports are necessary for accessing Android framework classes and functionality

public class CalculatorActivity extends AppCompatActivity {

    private EditText editText; // EditText view for displaying input/output
    private StringBuilder input = new StringBuilder(); // StringBuilder to store user input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator); // Set layout for the activity

        editText = findViewById(R.id.editText); // Initialize EditText reference to the view

        // Set click listeners for number buttons
        setNumberButtonClickListeners();

        // Set click listeners for operation buttons
        setOperationButtonClickListeners();

        // Set click listener for clear button
        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInput(); // Call clearInput() method when clear button is clicked
            }
        });

        // Set click listener for equal button
        findViewById(R.id.button_equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult(); // Call calculateResult() method when equal button is clicked
            }
        });

        // Set click listener for dot button
        findViewById(R.id.button_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.toString().contains(".")) { // Check if dot is not already present in input
                    input.append("."); // Append dot to input StringBuilder
                    editText.setText(input.toString()); // Update EditText view with the modified input
                }
            }
        });
    }

    // Method to set click listeners for number buttons
    private void setNumberButtonClickListeners() {
        int[] numberButtonIds = {
                R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
                R.id.button_8, R.id.button_9
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    input.append(button.getText()); // Append clicked number to input StringBuilder
                    editText.setText(input.toString()); // Update EditText view with the modified input
                }
            });
        }
    }

    // Method to set click listeners for operation buttons
    private void setOperationButtonClickListeners() {
        int[] operationButtonIds = {
                R.id.button_add, R.id.button_subtract,
                R.id.button_multiply, R.id.button_divide
        };

        for (int id : operationButtonIds) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    input.append(button.getText()); // Append clicked operation symbol to input StringBuilder
                    editText.setText(input.toString()); // Update EditText view with the modified input
                }
            });
        }
    }

    // Method to clear input and EditText view
    private void clearInput() {
        input.setLength(0); // Clear StringBuilder
        editText.setText(""); // Clear EditText view
    }

    // Method to calculate and display result
    private void calculateResult() {
        try {
            String result = evaluate(input.toString()); // Evaluate input expression
            editText.setText(result); // Display result in EditText view
            input.setLength(0); // Clear StringBuilder
            input.append(result); // Update StringBuilder with result
        } catch (ArithmeticException e) {
            editText.setText("Error"); // Display "Error" if evaluation fails
            input.setLength(0); // Clear StringBuilder
        }
    }

    // Method to evaluate mathematical expression
    private String evaluate(String expression) {
        return String.valueOf(eval(expression)); // Return evaluated result as string
    }

    // Recursive descent parsing to evaluate mathematical expression
    private double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            // Method to move to the next character in the expression
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            // Method to consume a specific character in the expression
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar(); // Skip whitespace characters
                if (ch == charToEat) { // Check if current character matches the specified one
                    nextChar(); // Move to the next character
                    return true;
                }
                return false;
            }

            // Method to parse the entire expression
            double parse() {
                nextChar(); // Start by moving to the first character
                double x = parseExpression(); // Parse the expression
                // Check if there are any unexpected characters left in the expression
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x; // Return the parsed result
            }

            // Method to parse an expression containing addition and subtraction
            double parseExpression() {
                double x = parseTerm(); // Parse the first term of the expression
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // If encountering '+', parse the next term and add it
                    else if (eat('-')) x -= parseTerm(); // If encountering '-', parse the next term and subtract it
                    else return x; // If no more addition or subtraction operators are found, return the result
                }
            }

            // Method to parse a term containing multiplication and division
            double parseTerm() {
                double x = parseFactor(); // Parse the first factor of the term
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // If encountering '*', parse the next factor and multiply it
                    else if (eat('/')) x /= parseFactor(); // If encountering '/', parse the next factor and divide by it
                    else return x; // If no more multiplication or division operators are found, return the result
                }
            }

            // Method to parse a factor, which can be a number or a subexpression in parentheses
            double parseFactor() {
                if (eat('+')) return parseFactor(); // If encountering '+', parse the next factor
                if (eat('-')) return -parseFactor(); // If encountering '-', parse the next factor and negate it
                double x;
                int startPos = this.pos; // Record the starting position of the factor
                if (eat('(')) { // If encountering '(', parse a subexpression
                    x = parseExpression(); // Parse the subexpression
                    eat(')'); // Consume the closing parenthesis
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // If encountering a number or a dot
                    // Continue parsing characters until a non-numeric character or dot is encountered
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    // Parse the numeric substring and convert it to a double
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch); // If encountering an unexpected character, throw an exception
                }
                return x; // Return the parsed factor
            }
        }.parse(); // Invoke the parse() method of the anonymous inner class to start parsing
    }
}
