package com.example.coolapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CatchTheButtonActivity extends Activity {

    public static final String TAG = CatchTheButtonActivity.class.getSimpleName();

    private Button buttonToCatch;
    private Random random;

    private int screenW;
    private int screenH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_the_button);
        buttonToCatch = findViewById(R.id.buttonToCatch);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        screenW = metrics.widthPixels - 200;
        screenH = metrics.heightPixels - 200;



        random = new Random();

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
                    }
                });
            }
        }, 0, 1000);
    }
}
