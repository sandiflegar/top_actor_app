package com.example.actorapp1;

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

import com.example.actorapp1.Retrofit.Actors;
import com.example.actorapp1.Retrofit.GetDataService;
import com.example.actorapp1.Retrofit.ResultActors;
import com.example.actorapp1.Retrofit.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorFragment extends Fragment {

   private GridView actorsGrid;
   private ActorsAdapter actorsAdapter;
   private ArrayList<Actors> actors;

    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_actor, container, false);
        actorsGrid = (GridView) view.findViewById(R.id.actorsGridView);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResultActors> call = service.getAllActors("13cf97e011aefd1f9b73e7c4dc2e4957");
        call.enqueue(new Callback<ResultActors>() {
            @Override
            public void onResponse(Call<ResultActors> call, Response<ResultActors> response) {
                if(response.isSuccessful()){
                    if(response.body() !=null) {
                        Log.e("onSuccess", response.body().toString());
                        actors = response.body().actors;
                        actorsAdapter = new ActorsAdapter(actors, getContext());
                        actorsGrid.setAdapter(actorsAdapter);
                    }else {
                        Log.e("onEmptyResponse", "Return empty response");
                    }
                }
            }
            @Override
            public void onFailure(Call<ResultActors> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });

        setHasOptionsMenu(true);
        actorsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent actorsIntent = new Intent(ActorFragment.this.getActivity(), ActorInfoActivity.class);
                actorsIntent.putExtra("actor_picture", String.valueOf(actors.get(position).getProfile_path()));
                actorsIntent.putExtra("actor_id", actors.get(position).getId());
                actorsIntent.putExtra("actor_name", actors.get(position).getName());
                startActivity(actorsIntent);
            }
        });
        return view;
    }

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
                    if(actorsAdapter != null) {
                        actorsAdapter.getFilter().filter(newText);}
                        return false;
                    }
            });
    }
}
