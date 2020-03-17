package com.example.actorapp1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.actorapp1.Retrofit.CastInMovie;

import java.util.ArrayList;

public class MoviesInfoAdapter2 extends RecyclerView.Adapter<MoviesInfoAdapter2.ViewHolder> {

    private ArrayList<CastInMovie> cast;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    MoviesInfoAdapter2(Context context, ArrayList<CastInMovie> cast) {
        this.mInflater = LayoutInflater.from(context);
        this.cast = cast;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.movie_cast_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CastInMovie tmp = cast.get(position);
        holder.cast_name.setText(tmp.getName());
        holder.tx_ime_char.setText(tmp.getCharacter());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return cast.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cast_name;
        TextView tx_ime_char;
        ViewHolder(View itemView) {
            super(itemView);
            cast_name = itemView.findViewById(R.id.cast_name);
            tx_ime_char = (TextView) itemView.findViewById(R.id.cast_char);
        }
    }

    // convenience method for getting data at click position
    CastInMovie getItem(int id) {
        return cast.get(id);
    }

}