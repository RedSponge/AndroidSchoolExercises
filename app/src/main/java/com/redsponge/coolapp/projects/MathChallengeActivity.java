package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coolapp.R;

public class MathChallengeActivity extends Activity {

    private static final int MAX_DIGITS = 6;
    private int[] digitButtonIds;
    private Button[] digitButtons;

    private TextView tvQuestionNumberDisplay;
    private TextView tvQuestion;
    private TextView tvAnswerDisplay;
    private int numberGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_challenge);

        digitButtonIds = new int[] {
                R.id.buttonZero,
                R.id.buttonOne,
                R.id.buttonTwo,
                R.id.buttonThree,
                R.id.buttonFour,
                R.id.buttonFive,
                R.id.buttonSix,
                R.id.buttonSeven,
                R.id.buttonEight,
                R.id.buttonNine,
        };

        setupDigitButtons();

        tvQuestionNumberDisplay = findViewById(R.id.tvQuestionNumberDisplay);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvAnswerDisplay = findViewById(R.id.tvAnswerDisplay);
        numberGuess = 0;
    }

    private void setupDigitButtons() {
        digitButtons = new Button[digitButtonIds.length];
        for(int i = 0; i < digitButtonIds.length; i++) {
            int buttonId = digitButtonIds[i];
            Button button = findViewById(buttonId);
            button.setText("" + (i));
            digitButtons[i] = button;

            int finalI = i;
            button.setOnClickListener((v) -> enterDigit(finalI));
        }
    }

    private void enterDigit(int digit) {
        if(("" + numberGuess).length() < MAX_DIGITS) {
            numberGuess = (numberGuess * 10) + digit;
            updateGuessDisplay();
        }
    }

    private void updateGuessDisplay() {
        tvAnswerDisplay.setText("" + numberGuess);
    }

    public void deleteGuess(View view) {
        numberGuess = 0;
        updateGuessDisplay();
    }

    public void tryGuess(View view) {
        Toast.makeText(this, "Trying guess", Toast.LENGTH_SHORT).show();
    }
}