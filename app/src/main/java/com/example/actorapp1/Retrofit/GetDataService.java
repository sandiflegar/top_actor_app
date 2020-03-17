package com.example.actorapp1.Retrofit;

import com.example.actorapp1.baza.Actor;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("3/movie/popular")
    Call<ResultMovies> getAllMovies(@Query("api_key") String api_key);
    @GET("3/movie/{movie_id}/credits")
    Call<Cast> getCast(@Path("movie_id") String movie_id, @Query("api_key") String api_key);
    @GET("3/person/popular")
    Call<ResultActors> getAllActors(@Query("api_key") String api_key);
    @GET("3/person/{person_id}")
    Call<ActorBiography> getBiography(@Path("person_id") String person_id, @Query("api_key") String api_key);
}
