package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.redsponge.coolapp.R;

/**
 *
 */
public class ImageCapturerActivity extends Activity {

    private int IMAGE_TAKEN_ACTION = 1;
    private ImageView imgDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capturer);

        imgDisplay = findViewById(R.id.imgDisplay);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(IMAGE_TAKEN_ACTION == requestCode && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgDisplay.setImageBitmap(photo);
        }
    }

    public void setImage(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, IMAGE_TAKEN_ACTION);
    }
}
