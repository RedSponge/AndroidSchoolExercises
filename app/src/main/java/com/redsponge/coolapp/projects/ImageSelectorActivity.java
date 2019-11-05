package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.coolapp.R;

import androidx.annotation.Nullable;

public class ImageSelectorActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capturer);
    }

    public void setImage(View view) {

    }
}

