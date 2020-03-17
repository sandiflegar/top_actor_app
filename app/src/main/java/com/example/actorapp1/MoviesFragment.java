package com.example.actorapp1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.GridView;
import android.support.v7.widget.SearchView;

import com.example.actorapp1.Retrofit.GetDataService;
import com.example.actorapp1.Retrofit.Movies;
import com.example.actorapp1.Retrofit.ResultMovies;
import com.example.actorapp1.Retrofit.RetrofitClientInstance;
import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {

    private GridView moviesGrid;
    private MoviesAdapter moviesAdapter;
    private ArrayList<Movies> posters;

    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movies, container, false);
        moviesGrid = (GridView) view.findViewById(R.id.moviesGridView);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResultMovies> call = service.getAllMovies("13cf97e011aefd1f9b73e7c4dc2e4957");
        call.enqueue(new Callback<ResultMovies>() {
            @Override
            public void onResponse(Call<ResultMovies> call, Response<ResultMovies> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.e("onSuccess", response.body().toString());

                        posters = response.body().movies;
                        moviesAdapter = new MoviesAdapter(posters, getContext());
                        moviesGrid.setAdapter(moviesAdapter);
//                        }
                    } else {
                        Log.e("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultMovies> call, Throwable t) {
                Log.e("error", t.getMessage());

            }
        });
        setHasOptionsMenu(true);
        moviesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent moviesIntent = new Intent(MoviesFragment.this.getActivity(), MoviesInfoActivity.class);
                moviesIntent.putExtra("movie_name", String.valueOf(posters.get(position).getTitle()));
                moviesIntent.putExtra("movie_poster", String.valueOf(posters.get(position).getPoster_path()));
                moviesIntent.putExtra("movie_id", posters.get(position).getId());
                moviesIntent.putExtra("overview", posters.get(position).getOverview());
                startActivity(moviesIntent);
            }
        });
        return view;
    }

    //če bi imel activity, gledaj spodaj!

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movies);

        moviesGrid = (GridView) moviesGrid.findViewById(R.id.moviesGridView);

        MoviesAdapter moviesAdapter = new MoviesAdapter(getContext(), posters);
        moviesGrid.setAdapter(moviesAdapter);

        moviesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent moviesIntent = new Intent(MoviesFragment.this.getActivity(), MoviesInfoActivity.class);
                moviesIntent.putExtra("movie_poster", posters[position]);
                startActivity(moviesIntent);
            }
        });
    }*/


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(moviesAdapter != null){
                moviesAdapter.getFilter().filter(newText);}
                return false;
            }
        });
    }
}