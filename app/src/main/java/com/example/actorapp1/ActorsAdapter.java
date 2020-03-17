package com.example.actorapp1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.actorapp1.Retrofit.Actors;
import com.example.actorapp1.Retrofit.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ActorsAdapter extends ArrayAdapter<Actors> implements Filterable {

    private ArrayList<Actors> dataSet;
    private ArrayList<Actors> dataSetFull;
    Context mContext;
    String baseUrl = "https://image.tmdb.org/t/p/w500";

    private static class ViewHolder{
        TextView tx_ime_actor;
        ImageView im_actor;
    }

    public ActorsAdapter(ArrayList<Actors> data, Context context) {
        super(context, R.layout.fragment_actors_gridview, data);
        this.dataSet = data;
        this.mContext = context;
        dataSetFull = new ArrayList<>(dataSet);
    }
    private int lastPosition = -1;


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Actors dataModel = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.fragment_actors_gridview, parent, false);
            viewHolder.tx_ime_actor = (TextView) convertView.findViewById(R.id.imeActor);
            viewHolder.im_actor = (ImageView) convertView.findViewById(R.id.actorPicture);

            result = convertView;
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Picasso.get()
                .load(baseUrl+dataModel.getProfile_path())
                .into(viewHolder.im_actor);

        viewHolder.tx_ime_actor.setText(dataModel.getName());

        return convertView;
    }
    @Override
    public Filter getFilter() {
        return actorFilter;
    }

    private Filter actorFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Actors> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataSetFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Actors item : dataSetFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
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
