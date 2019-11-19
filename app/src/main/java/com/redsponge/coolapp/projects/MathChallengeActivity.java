package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coolapp.R;
import com.redsponge.coolapp.projects.math.Operator;
import com.redsponge.coolapp.util.alert.AlertUtils;

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

    private int questionNum;
    private int numQuestions;

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

        numQuestions = 10;
        questionNum = 1;

        generateQuestion();
        updateCurrentQuestionDisplay();
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

    public void updateCurrentQuestionDisplay() {
        tvQuestionNumberDisplay.setText(String.format(Locale.UK, getString(R.string.math_challenge_current_question), questionNum, numQuestions));
    }

    public void tryGuess(View view) {
        if(numberGuess == question.apply(operandA, operandB)) {
            questionNum++;
            if(questionNum > numQuestions) {
                winChallenge();
                return;
            }
            updateCurrentQuestionDisplay();
            generateQuestion();
            deleteGuess(null);
        } else {
            tvAnswerDisplay.setTextColor(Color.RED);
        }
    }

    private void winChallenge() {
        AlertUtils.showConfirmPrompt(this, "You Won!", "Would you like to play again?", (a, b) -> restart(), (a, b) -> end());
    }

    private void end() {
        finish();
    }

    private void restart() {
        questionNum = 1;

        updateCurrentQuestionDisplay();
        generateQuestion();
        deleteGuess(null);
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
