package com.example.actorapp1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.actorapp1.Retrofit.Actors;
import com.example.actorapp1.Retrofit.Cast;
import com.example.actorapp1.Retrofit.CastInMovie;
import com.example.actorapp1.Retrofit.GetDataService;
import com.example.actorapp1.Retrofit.Movies;
import com.example.actorapp1.Retrofit.ResultMovies;
import com.example.actorapp1.Retrofit.RetrofitClientInstance;
import com.example.actorapp1.baza.AppDatabase;
import com.example.actorapp1.baza.Movie;
import com.example.actorapp1.baza.MovieDao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import static com.example.actorapp1.RatingFragment.db;
import static com.example.actorapp1.baza.AppDatabase.db;

public class MoviesInfoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    String baseUrl="https://image.tmdb.org/t/p/w500";
    ImageView poster;
        private Spinner spinner;
        private Button rateMovieBtn;
        private TextView movieInfo;
        private ArrayList<CastInMovie> cast;
        private RecyclerView castRecyclerView;
        private MoviesInfoAdapter2 moviesInfoAdapter2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_info);

        poster = (ImageView) findViewById(R.id.posterMovie);
        movieInfo = (TextView) findViewById(R.id.movieInfo);

        Intent moviesIntent = getIntent();
        String poster_path=moviesIntent.getStringExtra("movie_poster");
        final String movie_name=moviesIntent.getStringExtra("movie_name");
        getSupportActionBar().setTitle(movie_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.get()
                .load(baseUrl+poster_path)
                .into(poster);

        movieInfo.setText(moviesIntent.getStringExtra("overview"));

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final Button button = (Button) findViewById(R.id.rateMovieBtn);

        spinner.setOnItemSelectedListener(this);

        List<String> rating = new ArrayList<>();
        rating.add("1");
        rating.add("2");
        rating.add("3");
        rating.add("4");
        rating.add("5");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rating);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        // dobi vse rejtane filme
        db = AppDatabase.getDatabase(getApplicationContext());
        MovieDao movieDao=db.movieDao();
        List<Movie> mv= movieDao.getMovies();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = new Movie();
                movie.setMovieName(movie_name);
                String rating=spinner.getSelectedItem().toString();
                movie.setRatingMovie(rating);
                MovieDao movieDao=db.movieDao();
                movieDao.insert(movie);
                Toast.makeText(getApplicationContext(),"Your rating has been saved!",Toast.LENGTH_LONG).show();
                finish();

            }
        });
        int id=moviesIntent.getIntExtra("movie_id",0);
        castRecyclerView = (RecyclerView) findViewById(R.id.listOfActors);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Cast> call = service.getCast(String.valueOf(id),"13cf97e011aefd1f9b73e7c4dc2e4957");
        call.enqueue(new Callback<Cast>() {
            @Override
            public void onResponse(Call<Cast> call, Response<Cast> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.e("onSuccess", response.body().toString());

                        cast = response.body().castInMovies;
                        moviesInfoAdapter2 = new MoviesInfoAdapter2(getApplicationContext(), cast);
                        castRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        castRecyclerView.setAdapter(moviesInfoAdapter2);
                        Log.e("","");
                    } else {
                        Log.e("onEmptyResponse", "Returned empty response");
                    }

                }
            }

            @Override
            public void onFailure(Call<Cast> call, Throwable t) {
                Log.e("error", t.getMessage());

            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MoviesInfoActivity.this, RatingFragment.class);
        intent.putExtra("rate", String.valueOf(spinner.getSelectedItem()));
        startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String rating = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
