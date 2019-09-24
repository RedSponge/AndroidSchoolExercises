package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coolapp.R;

import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CatchTheButtonActivity extends Activity {

    public static final String TAG = CatchTheButtonActivity.class.getSimpleName();

    private Button buttonToCatch;
    private TextView tvNumClicks;
    private int clicks;

    private Random random;

    private int screenW;
    private int screenH;

    private Toast[] messages;
    private int color = 0xFFAAAAAA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_the_button);

        messages = new Toast[] {
            toast("Hey!"),
            toast("Stop it!"),
            toast("Ouch!"),
            toast("Don't you dare press me again"),
        };

        buttonToCatch = findViewById(R.id.buttonToCatch);
        tvNumClicks = findViewById(R.id.tvNumClicked);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        screenW = metrics.widthPixels - 200;
        screenH = metrics.heightPixels - 200;

        buttonToCatch.setBackgroundColor(color);

        random = new Random();

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    Random R = new Random();
                    final float dx = R.nextFloat() * (getWindowManager().getDefaultDisplay().getWidth() - buttonToCatch.getWidth());
                    final float dy = R.nextFloat() * (getWindowManager().getDefaultDisplay().getHeight() - buttonToCatch.getHeight());
                    buttonToCatch.animate()
                            .x(dx)
                            .y(dy)
                            .setDuration(100)
                            .start();
                    Log.i(TAG, "scramblePosition x: " + buttonToCatch.getX());
                    Log.i(TAG, "scramblePosition y: " + buttonToCatch.getY());
                });
            }
        }, 0, 1000);
    }

    public void caughtButton(View view) {
        clicks++;
        tvNumClicks.setText(String.format(Locale.UK, "Times Clicked: %d", clicks));

        if(clicks % 10 == 0) {
            messages[random.nextInt(messages.length)].show();
            if((color & 0x00FF0000) != 0x00FF0000) {
                color += 0x00110000;
                buttonToCatch.setBackgroundColor(color);
            }
        }
    }

    private Toast toast(String s) {
        return Toast.makeText(this, s, Toast.LENGTH_LONG);
    }
}
