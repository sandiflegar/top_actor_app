package com.example.actorapp1;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.actorapp1.Retrofit.ActorBiography;
import com.example.actorapp1.Retrofit.GetDataService;
import com.example.actorapp1.Retrofit.RetrofitClientInstance;
import com.example.actorapp1.baza.Actor;
import com.example.actorapp1.baza.ActorDao;
import com.example.actorapp1.baza.AppDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import static com.example.actorapp1.MainActivity.db;
import static com.example.actorapp1.baza.AppDatabase.db;

public class ActorInfoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    String baseUrl = "https://image.tmdb.org/t/p/w500";
    ImageView imageActor;
    private TextView actorBio;
    private Spinner spinnerActor;
    private Button rateActorBtn;
    private ArrayList<ActorBiography> bio;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_info);

        imageActor = (ImageView) findViewById(R.id.imageActor);

        final Intent actorsIntent = getIntent();
        String profile_path = actorsIntent.getStringExtra("actor_picture");
        final String actor_name=actorsIntent.getStringExtra("actor_name");
        getSupportActionBar().setTitle(actor_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Picasso.get()
                .load(baseUrl+profile_path)
                .into(imageActor);

        final Spinner spinner = (Spinner) findViewById(R.id.spinnerActor);
        final Button button = (Button) findViewById(R.id.rateActorBtn);

        spinner.setOnItemSelectedListener(this);

        List<String> ratingActor = new ArrayList<>();
        ratingActor.add("1");
        ratingActor.add("2");
        ratingActor.add("3");
        ratingActor.add("4");
        ratingActor.add("5");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ratingActor);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        db = AppDatabase.getDatabase(getApplicationContext());
        ActorDao actorDao = db.actorDao();
        List<Actor> ac = actorDao.getActors();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actor actor = new Actor();
                actor.setActorName(actor_name);
                String ratingActor = spinner.getSelectedItem().toString();
                actor.setRatingActor(ratingActor);
                ActorDao actorDao = db.actorDao();
                actorDao.insertActor(actor);
                Toast.makeText(getApplicationContext(), "Your rating has been saved!", Toast.LENGTH_LONG).show();
                finish();
            }
        });


        final int id = actorsIntent.getIntExtra("actor_id", 0);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ActorBiography> call = service.getBiography(String.valueOf(id), "13cf97e011aefd1f9b73e7c4dc2e4957");
        call.enqueue(new Callback<ActorBiography>() {
            @Override
            public void onResponse(Call<ActorBiography> call, Response<ActorBiography> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null){
                        Log.e("onSuccess", response.body().toString());

                        actorBio = (TextView) findViewById(R.id.actorInfo);
                        actorBio.setText(response.body().getBiography());
                        actorBio.setMovementMethod(new ScrollingMovementMethod());
                    }
                }

            }
            @Override
            public void onFailure(Call<ActorBiography> call, Throwable t) {
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

    public void onClick(View v) {
        Intent intent = new Intent(ActorInfoActivity.this, RatingFragment.class);
        intent.putExtra("rate_actor", String.valueOf(spinnerActor.getSelectedItem()));
        startActivity(intent);
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String ratingActor = parent.getItemAtPosition(position).toString();
    }


    public void onNothingSelected(AdapterView<?> parent){

    }
}
