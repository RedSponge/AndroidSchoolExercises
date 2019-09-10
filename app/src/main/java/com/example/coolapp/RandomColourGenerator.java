package com.example.coolapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class RandomColourGenerator extends Activity {

    private Random rnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_colour);

        rnd = new Random();
    }


    public void generateRandomColour(View view) {
        int r = rnd.nextInt(256);
        int g = rnd.nextInt(256);
        int b = rnd.nextInt(256);
        int a = 255;
        int colour =  (a << 24) + (r << 16) + (g << 8) + b;

        getWindow().getDecorView().setBackgroundColor(colour);
    }
}
