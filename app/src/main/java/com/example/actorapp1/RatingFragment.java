package com.example.actorapp1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.actorapp1.baza.Actor;
import com.example.actorapp1.baza.ActorDao;
import com.example.actorapp1.baza.AppDatabase;
import com.example.actorapp1.baza.Movie;
import com.example.actorapp1.baza.MovieDao;
import com.example.actorapp1.baza.RatingActorAdapter;
import com.example.actorapp1.baza.RatingMovieAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.actorapp1.baza.AppDatabase.db;

//import static com.example.actorapp1.MainActivity.db;


public class RatingFragment extends Fragment {

    public static AppDatabase db;
    private RecyclerView recyclerViewMovies;
    private RecyclerView recyclerViewActors;
    private RatingMovieAdapter ratingMovieAdapter;
    private RatingActorAdapter ratingActorAdapter;

    public RatingFragment(){
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db = AppDatabase.getDatabase(getContext());
        ArrayList<Movie> tmp=new ArrayList<>(db.movieDao().getMovies());//db.movieDao().getMovies();


        recyclerViewMovies = (RecyclerView) getView().findViewById(R.id.movieRecyclerView);

        ratingMovieAdapter = new RatingMovieAdapter(getContext(), tmp);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMovies.setAdapter(ratingMovieAdapter);

        ArrayList<Actor> actors = new ArrayList<>(db.actorDao().getActors());

        recyclerViewActors = (RecyclerView) getView().findViewById(R.id.actorRecyclerView);
        ratingActorAdapter = new RatingActorAdapter(getContext(),actors);
        recyclerViewActors.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewActors.setAdapter(ratingActorAdapter);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_rating, container, false);

        //MovieDao movieDao = db.movieDao();
//        ArrayList<Movie> movieList = new ArrayList<Movie>();
//        movieList = (ArrayList<Movie>) movieDao.getMovies();
        //List<Movie> movieList = movieDao.getMovies();

//        String info ="";
//        for(Movie movie1 : movieList){
//            String name = movie1.getMovieName();
//            String rating = movie1.getRatingMovie();
//            info = info+"\n\n"+"You rated "+name+" with the rating of "+rating+" stars!";
//        }

        //RecyclerView recyclerViewActors = (RecyclerView) view.findViewById(R.id.actorRecyclerView);

      // ArrayList<Movie> lst=new ArrayList<Movie>(movieList);
    /*    mAdapter = new MoviesRecAdapter(lst);
        recyclerViewMovies.setAdapter(mAdapter);*/

    /*    mAdapter = new MoviesRecAdapter(lst);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewMovies.setAdapter(mAdapter);
*/
       // movieRatingTxt.setText(info);

        //ActorDao actorDao = db.actorDao();


        View rootView = inflater.inflate(R.layout.fragment_rating, container, false);
        return rootView;
    }
}
