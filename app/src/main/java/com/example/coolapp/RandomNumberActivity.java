package com.example.coolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class RandomNumberActivity extends Activity {

    private static final int MIN_NUMBER = 0;
    private static final int MAX_NUMBER = 99;


    private int rollSum;
    private int timesRolled;

    private Random random;

    private TextView tvNumberDisplay;

    private TextView tvSumDisplay;
    private TextView tvNumRollsDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_number);

        tvNumberDisplay = findViewById(R.id.tvNumberDisplay);
        tvSumDisplay = findViewById(R.id.tvSumDisplay);
        tvNumRollsDisplay = findViewById(R.id.tvNumRollsDisplay);

        random = new Random();
    }

    public void generateRandomNumber(View view) {
        int number = randomMinMax(MIN_NUMBER, MAX_NUMBER);
        tvNumberDisplay.setText("" + number);

        tvNumberDisplay.setTextSize(36);

        rollSum++;
        timesRolled += number;

        updateDisplay();
    }

    public void restartValues(View view) {
        rollSum = timesRolled = 0;
        updateDisplay();
    }

    private void updateDisplay() {
        tvSumDisplay.setText("" + rollSum);
        tvNumRollsDisplay.setText("" + timesRolled);
    }

    private int randomMinMax(int min, int max) {
        return random.nextInt(max - min + 1) - min;
    }

}
