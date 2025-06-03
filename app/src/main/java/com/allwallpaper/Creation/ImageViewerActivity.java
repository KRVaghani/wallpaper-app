package com.allwallpaper.Creation;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import com.allwallpaper.R;
import com.bumptech.glide.Glide;

import java.io.File;

@SuppressWarnings("all")
public class ImageViewerActivity extends AppCompatActivity {

    ImageView back_arrow;
    LinearLayout instagram_btn;
    LinearLayout whatsapp_btn;
    LinearLayout fb_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        instagram_btn = findViewById(R.id.instagram_btn);
        whatsapp_btn = findViewById(R.id.whatsapp_btn);
        fb_btn = findViewById(R.id.fb_btn);

        back_arrow = findViewById(R.id.back_arrow);
        String path = null;
        ImageView imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        if (intent != null) {
            Glide.with(ImageViewerActivity.this).load(intent.getStringExtra("image")).placeholder(R.drawable.loder_b).into(imageView);
            path = intent.getStringExtra("image");
        }

        ImageView share = findViewById(R.id.shareImage);
        String finalPath = path;
        share.setOnClickListener(v -> new ShareCompat.IntentBuilder(ImageViewerActivity.this).setStream(Uri.parse(finalPath)).setType("image/*").setChooserTitle("Share Image").startChooser());

        fb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(finalPath);

                String link = "https://play.google.com/store/apps/details?id=" + getPackageName();
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setType("image/*");
                shareIntent.setPackage("com.facebook.katana");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Created by :- " + getResources().getString(R.string.app_name) + "\n" + link);
                startActivity(Intent.createChooser(shareIntent, "Share with"));


            }
        });
        instagram_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(finalPath);

                String link = "https://play.google.com/store/apps/details?id=" + getPackageName();
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setType("image/*");
                shareIntent.setPackage("com.instagram.android");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Created by :- " + getResources().getString(R.string.app_name) + "\n" + link);
                startActivity(Intent.createChooser(shareIntent, "Share with"));


            }
        });


        whatsapp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(finalPath);

                String link = "https://play.google.com/store/apps/details?id=" + getPackageName();
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setType("image/*");
                shareIntent.setPackage("com.whatsapp");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Created by :- " + getResources().getString(R.string.app_name) + "\n" + link);
                startActivity(Intent.createChooser(shareIntent, "Share with"));


            }
        });


        ImageView delete = findViewById(R.id.deleteImage);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] projection = new String[]{MediaStore.Images.Media._ID};
                String selection = MediaStore.Images.Media.DATA + " = ?";
                String[] selectionArgs = new String[]{new File(finalPath).getAbsolutePath()};
                Uri queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(queryUri, projection, selection, selectionArgs, null);
                if (cursor.moveToFirst()) {
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                    Uri deleteUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                    try {
                        contentResolver.delete(deleteUri, null, null);
                        boolean delete1 = new File(finalPath).delete();
                        Log.e("TAG", delete1 + "");
                        Toast.makeText(ImageViewerActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(ImageViewerActivity.this, "Error Deleting Video", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ImageViewerActivity.this, "File Not Find", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}