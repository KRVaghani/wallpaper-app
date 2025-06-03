package com.allwallpaper.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.allwallpaper.Model.Allimgmodel;
import com.allwallpaper.R;
import com.allwallpaper.SetWallpaper;
import com.google.gson.Gson;

import java.util.List;

@SuppressWarnings("all")
public class Allimgadapter extends RecyclerView.Adapter<Allimgadapter.Viewholder> {

    List<Allimgmodel> allimgmodels;
    Activity activity;

    public Allimgadapter(List<Allimgmodel> allimgmodels, Activity activity) {
        this.allimgmodels = allimgmodels;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Allimgadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.allimg_data, parent, false);
        Viewholder viewHolder = new Viewholder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Allimgadapter.Viewholder holder, int position) {
        holder.imageview.setImageResource(allimgmodels.get(position).getImage());

        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, SetWallpaper.class)
                        .putExtra("IMG", allimgmodels.get(position).getImage()));
            }
        });
    }
    @Override
    public int getItemCount() {
        return allimgmodels.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView imageview ;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.imageview);
        }
    }
}
