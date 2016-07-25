package com.iris.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

/**
 * Created by iris on 7/21/16.
 */
public class Movie {

    private final String POSTER_IMAGE_PATH_FORMAT = "https://image.tmdb.org/t/p/w185/%s";

    private final String BACKDROP_IMAGE_PATH_FORMAT = "https://image.tmdb.org/t/p/w342/%s";

    String backdropPath;

    String originalTitle;

    String posterPath;


    public String getBackdropPath() {
        return String.format(BACKDROP_IMAGE_PATH_FORMAT, backdropPath);
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterPath() {
        return String.format(POSTER_IMAGE_PATH_FORMAT, posterPath);
    }

    String overview;

    public Movie(JSONObject jsonObject) throws JSONException{
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.posterPath = jsonObject.getString("poster_path");
    }

    public static ArrayList<Movie> fromJsonArray(JSONArray jsonArray) throws JSONException{
        ArrayList<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            movieList.add(new Movie(jsonArray.getJSONObject(i)));
        }
        return movieList;
    }
}
