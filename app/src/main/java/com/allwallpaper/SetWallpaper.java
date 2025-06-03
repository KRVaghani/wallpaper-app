package com.allwallpaper;

import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.allwallpaper.Adapter.Allimgadapter;
import com.allwallpaper.Model.Allimgmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
public class SetWallpaper extends AppCompatActivity {
    private static final String TAG = SetWallpaper.class.getSimpleName();
    ImageView setimg;
    ArrayList<Allimgmodel> allimgmodelArrayList = new ArrayList<>();
    FloatingActionButton open, Apply, wallshare, download, favorite;
    TextView tvlock, tvhome, tvboth, tvsetwall, tvdownload, tvwallshare, tvfavorite;
    Boolean isAllFabsVisible;
    RecyclerView recyclerView;
    private int imageposition = 0;
    Allimgadapter allimgadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        open = findViewById(R.id.open);
        tvsetwall = findViewById(R.id.tvsetwall);
        tvwallshare = findViewById(R.id.tvwallshare);
        tvdownload = findViewById(R.id.tvdownload);
        tvfavorite = findViewById(R.id.tvfavorite);
        wallshare = findViewById(R.id.wallshare);
        download = findViewById(R.id.download);
        favorite = findViewById(R.id.favorite);
        setimg = findViewById(R.id.setimg);
        Apply = findViewById(R.id.Apply);
        recyclerView = findViewById(R.id.rv_fullview);

        Dialog dialog = new Dialog(SetWallpaper.this);

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        Intent intent = getIntent();
        if (intent != null) {
            imageposition = intent.getIntExtra("IMG", 0);
            Log.e(TAG, "onCreate: all image position " + imageposition);
            setimg.setImageResource(imageposition);

        }
//        int image = getIntent().getIntExtra("IMG", 0);
//        setimg.setImageResource(image);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(allimgadapter);

//        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
//        pagerSnapHelper.attachToRecyclerView(recyclerView);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.smoothScrollToPosition(imageposition);

        Log.e(TAG, "onCreate: image position " + imageposition);
        recyclerView.smoothScrollToPosition(imageposition);
        recyclerView.scrollToPosition(imageposition);
        if (allimgadapter != null) {
            allimgadapter.notifyDataSetChanged();

        }


        Apply.setVisibility(View.GONE);
        wallshare.setVisibility(View.GONE);
        download.setVisibility(View.GONE);
        favorite.setVisibility(View.GONE);
        tvsetwall.setVisibility(View.GONE);
        tvwallshare.setVisibility(View.GONE);
        tvdownload.setVisibility(View.GONE);
        tvfavorite.setVisibility(View.GONE);

        isAllFabsVisible = false;

        open.setOnClickListener(view -> {
            if (!isAllFabsVisible) {

                Apply.show();
                wallshare.show();
                download.show();
                favorite.show();
                tvsetwall.setVisibility(View.VISIBLE);
                tvwallshare.setVisibility(View.VISIBLE);
                tvdownload.setVisibility(View.VISIBLE);
                tvfavorite.setVisibility(View.VISIBLE);

                isAllFabsVisible = true;
            } else {
                Apply.hide();
                wallshare.hide();
                download.hide();
                favorite.hide();
                tvsetwall.setVisibility(View.GONE);
                tvwallshare.setVisibility(View.GONE);
                tvdownload.setVisibility(View.GONE);
                tvfavorite.setVisibility(View.GONE);

                isAllFabsVisible = false;
            }
        });
        Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.setwallpaper_dailog);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                tvlock = dialog.findViewById(R.id.tvlock);
                tvhome = dialog.findViewById(R.id.tvhome);
                tvboth = dialog.findViewById(R.id.tvboth);

                tvlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                        try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Bitmap bitmap = ((BitmapDrawable) setimg.getDrawable()).getBitmap();
                                wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK);
                                Toast.makeText(SetWallpaper.this, "Lock screen wallpaper set successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SetWallpaper.this, "Lock screen wallpaper setting is not supported on this device.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(SetWallpaper.this, "Failed to set lock screen wallpaper", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                tvhome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                        try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Bitmap bitmap = ((BitmapDrawable) setimg.getDrawable()).getBitmap();
                                wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM);
                                Toast.makeText(SetWallpaper.this, "HOME screen wallpaper set successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SetWallpaper.this, "HOME screen wallpaper setting is not supported on this device.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(SetWallpaper.this, "Failed to set HOME screen wallpaper", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });

                tvboth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                        try {
                            myWallpaperManager.setResource(imageposition);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(SetWallpaper.this, "Wallpaper Success", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetWallpaper blendMish2 = SetWallpaper.this;
                File saveBitmap = blendMish2.saveBitmap(blendMish2.getScreenShot1());
                Intent intent = new Intent();
                intent.putExtra("erase_image_blendmish", saveBitmap.toString());
                SetWallpaper.this.finish();
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Share wallpaper
        wallshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePhoto(setimg);
            }

            private void sharePhoto(ImageView imageView) {
                imageView.setDrawingCacheEnabled(true);
                imageView.buildDrawingCache();
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/jpeg");
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                String path = MediaStore.Images.Media.insertImage(
                        getContentResolver(),
                        bitmap,
                        "Image Description",
                        null);
                Uri photoUri = Uri.parse(path);
                shareIntent.putExtra(Intent.EXTRA_STREAM, photoUri);
                startActivity(Intent.createChooser(shareIntent, "Share Image"));
            }
        });
    }

    public Bitmap getScreenShot1() {
        Bitmap bitmap = null;
        try {
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rlimg);
            relativeLayout.setBackgroundColor(0);
            bitmap = Bitmap.createBitmap(relativeLayout.getMeasuredWidth(), relativeLayout.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            relativeLayout.draw(new Canvas(bitmap));
            return bitmap;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to capture screenshot because:");
            sb.append(e.getMessage());
            Log.d("ScreenShotActivity", sb.toString());
            return bitmap;
        }
    }

    public File saveBitmap(Bitmap bitmap) {
        File filepath = Environment.getExternalStorageDirectory();
        File dir = new File(filepath.getAbsolutePath() + "/" + Environment.DIRECTORY_PICTURES + "/" + getResources().getString(R.string.app_name));
        Toast.makeText(this, "Download Successfully", Toast.LENGTH_SHORT).show();
        Log.e("#filepath", String.valueOf(filepath));
        dir.mkdirs();
        String FileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png";
        File file = new File(dir, FileName);
        file.renameTo(file);
        Log.e("#file", String.valueOf(file));
        try {
            OutputStream output = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public class SharedPreference {
        public static final String PREFS_NAME = "POCKTCODE_APP";
        public static final String FAVORITES = "code_Favorite";

        public SharedPreference() {
            super();
        }

        public void saveFavorites(Context context, List<Allimgmodel> favorites) {
            SharedPreferences settings;
            SharedPreferences.Editor editor;

            settings = context.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);
            editor = settings.edit();

            Gson gson = new Gson();
            String jsonFavorites = gson.toJson(favorites);

            editor.putString(FAVORITES, jsonFavorites);

            editor.commit();
        }

        public void addFavorite(Context context, Allimgmodel code) {
            List<Allimgmodel> favorites = getFavorites(context);

            if (favorites == null)
                favorites = new ArrayList<Allimgmodel>();
            favorites.add(code);
            saveFavorites(context, favorites);
        }

        public void removeFavorite(Context context, Allimgmodel code) {
            ArrayList<Allimgmodel> favorites = getFavorites(context);
            if (favorites != null) {
                favorites.remove(code);
                saveFavorites(context, favorites);
            }
        }

        public ArrayList<Allimgmodel> getFavorites(Context context) {
            SharedPreferences settings;
            List<Allimgmodel> favorites;

            settings = context.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);

            if (settings.contains(FAVORITES)) {
                String jsonFavorites = settings.getString(FAVORITES, null);
                Gson gson = new Gson();
                Allimgmodel[] favoriteItems = gson.fromJson(jsonFavorites,
                        Allimgmodel[].class);

                favorites = Arrays.asList(favoriteItems);
                favorites = new ArrayList<Allimgmodel>(favorites);
            } else
                return null;
            return (ArrayList<Allimgmodel>) favorites;
        }
    }
}
