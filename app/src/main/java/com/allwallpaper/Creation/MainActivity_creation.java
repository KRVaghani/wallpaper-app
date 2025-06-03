package com.allwallpaper.Creation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.allwallpaper.MainActivity;
import com.allwallpaper.R;

import java.io.File;
import java.util.ArrayList;
@SuppressWarnings("all")
public class MainActivity_creation extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView back;
    ImageView noImage;
    ArrayList<Image> arrayList = new ArrayList<>();
    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
            result -> {
                if (result) {
                    getImages();
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onfailure","error");
        setContentView(R.layout.activity_main_creation);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        noImage=findViewById(R.id.novideoimg);
        back=findViewById(R.id.back);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);

        if (ActivityCompat.checkSelfPermission(MainActivity_creation.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            activityResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else if (ActivityCompat.checkSelfPermission(MainActivity_creation.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            activityResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            getImages();
        }
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity_creation.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });

    }


    public void adss() {
        if (arrayList.size() <= 0) {
            this.noImage.setVisibility(0);
            this.recyclerView.setVisibility(8);
            return;
        }
        this.noImage.setVisibility(8);
        this.recyclerView.setVisibility(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getImages();
        bindView();
    }



    private void getImages(){
        arrayList.clear();
        String filePath = "/storage/emulated/0/Pictures"+"/"+getString(R.string.app_name);
        Log.e("###creation",filePath+"");
        File file = new File(filePath);
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.getPath().endsWith(".png") || file1.getPath().endsWith(".jpg")) {
                    arrayList.add(new Image(file1.getName(), file1.getPath(), file1.length()));
                }
            }
        }
        ImageAdapter adapter = new ImageAdapter(MainActivity_creation.this, arrayList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, path) -> startActivity(new Intent(MainActivity_creation.this, ImageViewerActivity.class).putExtra("image", path)));
    }

    private void bindView() {
        this.noImage = (ImageView) findViewById(R.id.novideoimg);
        if (arrayList.size() <= 0) {
            this.noImage.setVisibility(0);
            this.recyclerView.setVisibility(8);
        } else {
            this.noImage.setVisibility(8);
            this.recyclerView.setVisibility(0);
        }
    }


}