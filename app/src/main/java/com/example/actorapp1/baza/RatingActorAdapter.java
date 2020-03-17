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

public class RatingActorAdapter extends RecyclerView.Adapter<RatingActorAdapter.ViewHolder> {

    private List<Actor> actorList;
    private LayoutInflater mInflater;

    public RatingActorAdapter(Context context, ArrayList<Actor> actorList) {
        this.actorList = actorList;
        this.mInflater = mInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_actors_rating, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Actor tmp = actorList.get(position);
        holder.actorRate.setText(tmp.getRatingActor());
        holder.actorName.setText(tmp.getActorName());

    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView actorRate;
        TextView actorName;
        ViewHolder(View itemView) {
            super(itemView);
            actorRate = itemView.findViewById(R.id.ratingActorGrade);
            actorName = itemView.findViewById(R.id.ratingActorText);
        }
    }
    Actor getItem(int id) {
        return actorList.get(id);
    }
}
