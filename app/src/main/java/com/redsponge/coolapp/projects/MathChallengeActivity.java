package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.example.coolapp.R;

public class MathChallengeActivity extends Activity {

    private int[] buttonIds;
    private Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_challenge);

        buttonIds = new int[] {
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

        // TODO: Add 0

        setupButtons();
    }

    private void setupButtons() {
        buttons = new Button[buttonIds.length];
        for(int i = 0; i < buttonIds.length; i++) {
            int buttonId = buttonIds[i];
            Button button = findViewById(buttonId);
            button.setText("" + (i + 1));
            buttons[i] = button;
        }
    }
}
