package com.allwallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("all")
public class Main_List extends AppCompatActivity {

    private ImageView imgsunset,imgnature,imgbirds,imgline,imghors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        imgsunset=findViewById(R.id.imgsunset);
        imgnature=findViewById(R.id.imgnature);
        imgbirds=findViewById(R.id.imgbirds);
        imgline=findViewById(R.id.imgline);
        imghors=findViewById(R.id.imghors);

        imgsunset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main_List.this,MainActivity.class);
                i.putExtra("name","sunset");
                startActivity(i);
            }
        });
        imgnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main_List.this,MainActivity.class);
                i.putExtra("name","Nature");
                startActivity(i);
            }
        });
        imgbirds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main_List.this,MainActivity.class);
                i.putExtra("name","Birds");
                startActivity(i);
            }
        });
        imgline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main_List.this,MainActivity.class);
                i.putExtra("name","Lion");
                startActivity(i);
            }
        });
        imghors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main_List.this,MainActivity.class);
                i.putExtra("name","Horsh");
                startActivity(i);
            }
        });
    }
    public void customExitDialog()
    {
        // creating custom dialog
        final Dialog dialog = new Dialog(Main_List.this);

        // setting content view to dialog
        dialog.setContentView(R.layout.custom_exit_dialog);

        // getting reference of TextView
        TextView dialogButtonYes = (TextView) dialog.findViewById(R.id.textViewYes);
        TextView dialogButtonNo = (TextView) dialog.findViewById(R.id.textViewNo);

        // click listener for No
        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dismiss the dialog
                dialog.dismiss();

            }
        });
        // click listener for Yes
        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dismiss the dialog and exit the exit
                dialog.dismiss();
                finish();

            }
        });

        // show the exit dialog
        dialog.show();
    }
    @Override
    public void onBackPressed() {
        // calling the function
        customExitDialog();
    }
}