package com.example.actorapp1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.actorapp1.Retrofit.Actors;
import com.example.actorapp1.Retrofit.Movies;
import com.example.actorapp1.baza.Movie;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends ArrayAdapter<Movies> {

    private ArrayList<Movies> dataSet;
    private ArrayList<Movies> dataSetFull;
    Context mContext;
    String baseUrl="https://image.tmdb.org/t/p/w500";
    // View lookup cache
    private static class ViewHolder {
        TextView tx_ime_film;
        ImageView im_poster;
    }

    public MoviesAdapter(ArrayList<Movies> data, Context context) {
        super(context, R.layout.fragment_movies_gridview, data);
        this.dataSet = data;
        this.mContext=context;
        dataSetFull = new ArrayList<>(dataSet);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movies dataModel = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.fragment_movies_gridview, parent, false);
            viewHolder.tx_ime_film = (TextView) convertView.findViewById(R.id.imeFilm);
            viewHolder.im_poster = (ImageView) convertView.findViewById(R.id.poster);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        Picasso.get()
                .load(baseUrl+dataModel.getPoster_path())
                .into( viewHolder.im_poster);

        viewHolder.tx_ime_film.setText(dataModel.getTitle());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return actorFilter;
    }

    private Filter actorFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Movies> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataSetFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Movies item : dataSetFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataSet.clear();
            dataSet.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}