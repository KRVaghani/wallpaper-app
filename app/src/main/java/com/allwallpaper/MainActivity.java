package com.allwallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.allwallpaper.Adapter.Allimgadapter;
import com.allwallpaper.Creation.MainActivity_creation;
import com.allwallpaper.Model.Allimgmodel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {
    RecyclerView AllImgrv;
    String name;
    public DrawerLayout drawerLayout;
    private NavigationView navigation;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    List<Allimgmodel> allimgmodels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AllImgrv = findViewById(R.id.AllImgrv);
        name = getIntent().getStringExtra("name");
        if (name.equals("sunset")) {
            getData();
        } else if (name.equals("Nature")) {
            getnature();
        } else if (name.equals("Birds")) {
            getbird();
        } else if (name.equals("Lion")) {
            getlion();
        } else if (name.equals("Horsh")) {
            gethorsh();
        }
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    Intent intent = new Intent(MainActivity.this, Main_List.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.shere) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Here is the share content body";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                    finish();
                } else if (id == R.id.rate) {
                    Intent intent = new Intent(MainActivity.this, Main_List.class);
                    Toast.makeText(MainActivity.this, "Thanks for 5star rate", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }else if (id == R.id.mycreation) {
                    Intent intent = new Intent(MainActivity.this, MainActivity_creation.class);
                    Toast.makeText(MainActivity.this, "My Creation", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }if (id == R.id.exit) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Are you sure you want to Exit?");
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity.this.finish();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                }
                finish();
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


    private void getData() {
        AllImgrv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        allimgmodels.add(new Allimgmodel(R.drawable.sun1));
        allimgmodels.add(new Allimgmodel(R.drawable.sun2));
        allimgmodels.add(new Allimgmodel(R.drawable.sun3));
        allimgmodels.add(new Allimgmodel(R.drawable.sun4));
        allimgmodels.add(new Allimgmodel(R.drawable.sun5));
        allimgmodels.add(new Allimgmodel(R.drawable.sun6));
        allimgmodels.add(new Allimgmodel(R.drawable.sun7));
        allimgmodels.add(new Allimgmodel(R.drawable.sun8));
        allimgmodels.add(new Allimgmodel(R.drawable.sun9));
        allimgmodels.add(new Allimgmodel(R.drawable.sun10));
        Allimgadapter allimgadapter = new Allimgadapter(allimgmodels, MainActivity.this);
        AllImgrv.setAdapter(allimgadapter);
    }

    private void getnature() {
        AllImgrv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        allimgmodels.add(new Allimgmodel(R.drawable.natur1));
        allimgmodels.add(new Allimgmodel(R.drawable.natur2));
        allimgmodels.add(new Allimgmodel(R.drawable.natur3));
        allimgmodels.add(new Allimgmodel(R.drawable.natur4));
        allimgmodels.add(new Allimgmodel(R.drawable.natur5));
        allimgmodels.add(new Allimgmodel(R.drawable.natur6));
        allimgmodels.add(new Allimgmodel(R.drawable.natur7));
        allimgmodels.add(new Allimgmodel(R.drawable.natur8));
        allimgmodels.add(new Allimgmodel(R.drawable.natur9));
        allimgmodels.add(new Allimgmodel(R.drawable.natur10));
        Allimgadapter allimgadapter = new Allimgadapter(allimgmodels, MainActivity.this);
        AllImgrv.setAdapter(allimgadapter);
    }

    private void getbird() {
        AllImgrv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        allimgmodels.add(new Allimgmodel(R.drawable.bird1));
        allimgmodels.add(new Allimgmodel(R.drawable.bird2));
        allimgmodels.add(new Allimgmodel(R.drawable.bird3));
        allimgmodels.add(new Allimgmodel(R.drawable.bird4));
        allimgmodels.add(new Allimgmodel(R.drawable.bird5));
        allimgmodels.add(new Allimgmodel(R.drawable.bird6));
        allimgmodels.add(new Allimgmodel(R.drawable.bird7));
        allimgmodels.add(new Allimgmodel(R.drawable.bird8));
        allimgmodels.add(new Allimgmodel(R.drawable.bird9));
        allimgmodels.add(new Allimgmodel(R.drawable.bird10));
        Allimgadapter allimgadapter = new Allimgadapter(allimgmodels, MainActivity.this);
        AllImgrv.setAdapter(allimgadapter);
    }

    private void getlion() {
        AllImgrv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        allimgmodels.add(new Allimgmodel(R.drawable.lion1));
        allimgmodels.add(new Allimgmodel(R.drawable.lion2));
        allimgmodels.add(new Allimgmodel(R.drawable.lion3));
        allimgmodels.add(new Allimgmodel(R.drawable.lion4));
        allimgmodels.add(new Allimgmodel(R.drawable.lion5));
        allimgmodels.add(new Allimgmodel(R.drawable.lion6));
        allimgmodels.add(new Allimgmodel(R.drawable.lion7));
        allimgmodels.add(new Allimgmodel(R.drawable.lion8));
        allimgmodels.add(new Allimgmodel(R.drawable.lion9));
        allimgmodels.add(new Allimgmodel(R.drawable.lion10));
        Allimgadapter allimgadapter = new Allimgadapter(allimgmodels, MainActivity.this);
        AllImgrv.setAdapter(allimgadapter);
    }

    private void gethorsh() {
        AllImgrv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        allimgmodels.add(new Allimgmodel(R.drawable.horse1));
        allimgmodels.add(new Allimgmodel(R.drawable.horse2));
        allimgmodels.add(new Allimgmodel(R.drawable.horse3));
        allimgmodels.add(new Allimgmodel(R.drawable.horse4));
        allimgmodels.add(new Allimgmodel(R.drawable.horse5));
        allimgmodels.add(new Allimgmodel(R.drawable.horse6));
        allimgmodels.add(new Allimgmodel(R.drawable.horse7));
        allimgmodels.add(new Allimgmodel(R.drawable.horse8));
        allimgmodels.add(new Allimgmodel(R.drawable.horse9));
        allimgmodels.add(new Allimgmodel(R.drawable.horse10));
        Allimgadapter allimgadapter = new Allimgadapter(allimgmodels, MainActivity.this);
        AllImgrv.setAdapter(allimgadapter);
    }
}