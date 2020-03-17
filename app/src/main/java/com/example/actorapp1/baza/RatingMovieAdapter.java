package com.example.actorapp1.baza;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.actorapp1.R;

import java.util.ArrayList;
import java.util.List;

public class RatingMovieAdapter extends RecyclerView.Adapter<RatingMovieAdapter.ViewHolder> {

    private ArrayList<Movie> movie;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public RatingMovieAdapter(Context context, ArrayList<Movie> mv) {
        this.mInflater = LayoutInflater.from(context);
        this.movie = mv;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_movies_rating, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie item = movie.get(position);
        holder.movieName.setText(item.getMovieName());
        holder.movieRate.setText(item.getRatingMovie());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return movie.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView movieRate;
        TextView movieName;
        public ViewHolder(View itemView) {
            super(itemView);
            movieRate =  itemView.findViewById(R.id.ratingFilmGrade);
            movieName =  itemView.findViewById(R.id.ratingFilmText);
        }
    }

    // convenience method for getting data at click position
    Movie getItem(int id) {
        return movie.get(id);
    }

}
