package com.iris.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.iris.flickster.adapters.MovieArrayAdapter;
import com.iris.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter _movieArrayAdapter;
    ListView lvItems;
    String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movies = new ArrayList<>();
        lvItems = (ListView) findViewById(R.id.lvMovies);
        _movieArrayAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(_movieArrayAdapter);
        fetchMovieData();

    }

    private void fetchMovieData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray moviesJsonResult;
                try {
                    moviesJsonResult = response.getJSONArray("results");
                    movies.addAll(Movie.fromJsonArray(moviesJsonResult));
                    _movieArrayAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }
}
