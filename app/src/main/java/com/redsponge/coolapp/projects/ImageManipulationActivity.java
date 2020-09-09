package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.redsponge.coolapp.R;

public class ImageManipulationActivity extends Activity {

    private ImageView ivColourAffected;
    private SeekBar sbRed, sbGreen, sbBlue, sbAlpha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_manipulation);

        sbRed = findViewById(R.id.sbRed);
        sbGreen = findViewById(R.id.sbGreen);
        sbBlue = findViewById(R.id.sbBlue);
        sbAlpha = findViewById(R.id.sbAlpha);

        ivColourAffected = findViewById(R.id.ivColourAffected);
//        ivColourAffected.setColorFilter(0xFF110011, PorterDuff.Mode.ADD);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateImageColour();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        sbRed.setOnSeekBarChangeListener(listener);
        sbGreen.setOnSeekBarChangeListener(listener);
        sbBlue.setOnSeekBarChangeListener(listener);
        sbAlpha.setOnSeekBarChangeListener(listener);

        int width = ivColourAffected.getWidth();
        int height = ivColourAffected.getHeight();
        Bitmap bitmap = ((BitmapDrawable)ivColourAffected.getDrawable()).getBitmap();

        Bitmap O = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), bitmap.getConfig());

        for(int i=0; i<O.getWidth(); i++){
            for(int j=0; j<O.getHeight(); j++){
                int pixel = O.getPixel(i, j);
                int p = bitmap.getPixel(i, j);
                int b = Color.blue(p);
                int x =  Color.red(p);
                int y =  Color.green(p);
                b =  b+150;
                int grey = (x + y + b) / 3;
                O.setPixel(i, j, Color.argb(Color.alpha(p), grey, grey, grey));
            }
        }
        ivColourAffected.setImageBitmap(O);

//        for(int i = 0; i < width; i++) {
//            for(int j = 0; j < height; j++) {
//                int pixel = bitmap.getPixel(i, j);
//                int r = Color.red(pixel);
//                int g = Color.green(pixel);
//                int b = Color.blue(pixel);
//                int grayscaleColor = (r + g + b) / 3;
//                bitmap.setPixel(i, j, Color.argb(255, grayscaleColor, grayscaleColor, grayscaleColor));
//            }
//        }
//
//        ivColourAffected.setImageBitmap(bitmap);
    }

    private void updateImageColour() {
//        int r = sbRed.getProgress();
//        int g = sbGreen.getProgress();
//        int b = sbBlue.getProgress();
//        int a = sbAlpha.getProgress();
//
//        ivColourAffected.setColorFilter(Color.argb(a, r, g, b), PorterDuff.Mode.MULTIPLY);
    }
}
