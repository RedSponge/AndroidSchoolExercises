package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.redsponge.coolapp.R;

public class ColourBarsActivity extends Activity {


    private SeekBar sbRed;
    private SeekBar sbGreen;
    private SeekBar sbBlue;

    private int bgRed;
    private int bgGreen;
    private int bgBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_bars);

        sbRed = findViewById(R.id.sbRed);
        sbGreen = findViewById(R.id.sbGreen);
        sbBlue = findViewById(R.id.sbBlue);

        bgRed = bgGreen = bgBlue = 255;

        SeekBar.OnSeekBarChangeListener sbcl = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(seekBar == sbRed) {
                    bgRed = progress;
                } else if(seekBar == sbBlue) {
                    bgBlue = progress;
                } else if(seekBar == sbGreen) {
                    bgGreen = progress;
                }
                updateDisplay();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        sbRed.setOnSeekBarChangeListener(sbcl);
        sbGreen.setOnSeekBarChangeListener(sbcl);
        sbBlue.setOnSeekBarChangeListener(sbcl);
    }


    public void updateDisplay() {
        getWindow().getDecorView().setBackgroundColor((255 << 24) + (bgRed << 16) + (bgGreen << 8) + bgBlue);
    }
}
