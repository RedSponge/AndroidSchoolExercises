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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.coolapp.R;

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
            imgDisplay.setImageBitmap(bmp);
        }
    }

    private void requireGalleryPicture() {
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_CHOICE_ACTION);

    }

    private void requireCameraPicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_IMAGE_TAKEN_ACTION);
    }
}

