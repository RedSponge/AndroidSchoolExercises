package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coolapp.R;
import com.redsponge.coolapp.projects.math.Operator;

import java.util.Locale;
import java.util.Random;

public class MathChallengeActivity extends Activity {

    private static final int MAX_DIGITS = 6;
    private int[] digitButtonIds;
    private Button[] digitButtons;

    private TextView tvQuestionNumberDisplay;
    private TextView tvQuestion;
    private TextView tvAnswerDisplay;
    private int numberGuess;

    private Operator question;
    private int operandA, operandB;

    private Random rnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_challenge);
        rnd = new Random();

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

        generateQuestion();
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
        tvAnswerDisplay.setTextColor(Color.BLACK);
    }

    public void deleteGuess(View view) {
        numberGuess = 0;
        updateGuessDisplay();
    }

    public void tryGuess(View view) {
        if(numberGuess == question.apply(operandA, operandB)) {
            generateQuestion();
            deleteGuess(null);
        } else {
            tvAnswerDisplay.setTextColor(Color.RED);
        }
    }

    private void generateQuestion() {
        question = Operator.Operators.ALL[rnd.nextInt(Operator.Operators.ALL.length)];
        operandA = rnd.nextInt(20 - 2 + 1) + 2;
        operandB = rnd.nextInt(20 - 2 + 1) + 2;

        if(operandA < operandB) swapOperands();

        tvQuestion.setText(String.format(Locale.UK, "%s = ?", question.getRepresentation(operandA, operandB)));
    }

    private void swapOperands() {
        int c = operandA;
        operandA = operandB;
        operandB = c;
    }
}
