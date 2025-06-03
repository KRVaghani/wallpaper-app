/*
package com.allwallpaper.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.allwallpaper.DataBase.FavoriteDB;
import com.allwallpaper.Model.Allimgmodel;
import com.allwallpaper.R;

import java.util.ArrayList;

@SuppressWarnings("All")
public class Favoriteadapter extends RecyclerView.Adapter<Favoriteadapter.Viewholder> {
//    List<Allimgmodel>allimgmodelList;

    ArrayList<Allimgmodel> allimgmodels;

    Context context;

    FavoriteDB favoriteDB;

    public Favoriteadapter(ArrayList<Allimgmodel> allimgmodels, Context context) {
        this.allimgmodels = allimgmodels;
        this.context = context;
    }

    @NonNull
    @Override
    public Favoriteadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favoriteDB = new FavoriteDB(context);

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.fav_item, parent, false);
        return new Viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Favoriteadapter.Viewholder holder, int position) {
        Allimgmodel allimgmodel = allimgmodels.get(position);

        readCursorData();
//        holder.imageView.setImageResource(allimgmodelList.get(position).getImage());
    }


    @Override
    public int getItemCount() {
        return allimgmodels.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView, favorite;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            favorite = itemView.findViewById(R.id.favorite);

            //add fav btn
            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Allimgmodel allimgmodel = allimgmodels.get(position);

                    if (allimgmodel.getFavStatus().equals("0")) {
                        allimgmodel.setFavStatus("1");
                        favoriteDB.insertIntoTheDatabase(allimgmodel.getImage(), allimgmodel.getKey_id(), allimgmodel.getFavStatus());
                        favorite.setBackgroundResource(R.drawable.favorite_border);
                    } else {
                        allimgmodel.setFavStatus("0");
                        favoriteDB.remove_fav(allimgmodel.getKey_id());
                        favorite.setBackgroundResource(R.drawable.favorite_border);
                    }
                }
            });
        }
    }

    private void createTableOnFirstStart() {
        favoriteDB.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(Allimgmodel allimgmodel, Viewholder viewholder) {
        Cursor cursor = favoriteDB.read_all_data(allimgmodel.getKey_id());
        SQLiteDatabase db = favoriteDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavoriteDB.FAVORITE_STATUS));
                allimgmodel.setFavStatus(item_fav_status);
            }
        }
    }

}
*/
