package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.GridLayoutAnimationController;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coolapp.R;

import androidx.annotation.Nullable;

public class ImageSelectorActivity extends Activity {

    public static final String TAG = "ImageSelectorActivity";

    private static final int CAMERA_IMAGE_TAKEN_ACTION = 1;
    private static final int GALLERY_CHOICE_ACTION = 2;

    private ImageView imgDisplay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capturer);
        imgDisplay = findViewById(R.id.imgDisplay);
    }

    public void setImage(View view) {
        new AlertDialog.Builder(this).setSingleChoiceItems(new String[]{
                "Camera",
                "Gallery"
        }, -1, (dialog, which) -> {
            Log.i(TAG, "onClick: " + which);
            dialog.dismiss();
            switch (which) {
                case 0:
                    requireCameraPicture();
                    break;
                case 1:
                    requireGalleryPicture();
                    break;
                default:
                    throw new RuntimeException("Invalid Choice " + which);
            }
        }).setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK) {
            Toast.makeText(this, "Whoops! Something Went Wrong!", Toast.LENGTH_LONG).show();
            return;
        }

        if(requestCode == CAMERA_IMAGE_TAKEN_ACTION) {
            imgDisplay.setImageBitmap((Bitmap) data.getExtras().get("data"));
        }
        else if(requestCode == GALLERY_CHOICE_ACTION) {
            Uri selectedImage = data.getData();

            String[] filePathCol = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathCol, null, null, null);

            c.moveToFirst();
            int colIndex = c.getColumnIndex(filePathCol[0]);

            String imgDecodableString = c.getString(colIndex);
            c.close();


            Bitmap bmp = BitmapFactory.decodeFile(imgDecodableString);
            // TODO: Fix this, doesn't work for some reason
            imgDisplay.setImageBitmap(bmp);
        }
    }

    private void requireGalleryPicture() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(intent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(intent, GALLERY_CHOICE_ACTION);
    }

    private void requireCameraPicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_IMAGE_TAKEN_ACTION);
    }
}

