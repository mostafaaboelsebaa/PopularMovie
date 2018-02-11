package com.example.malek.popularmovie;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Malek on 2/9/2018.
 */

public class JsonParser {
    private static final String TAG = "JsonParser";

    public static ArrayList getMoviesList(String jsonStr) throws JSONException {
        ArrayList<Movie> movies =new ArrayList<Movie>();
        JSONObject jsonObject=new JSONObject(jsonStr);
        JSONArray jsonObjectArray=jsonObject.getJSONArray("results");

        for (int i=0;i<jsonObjectArray.length();i++){
            Movie movie =new Movie();
            JSONObject temp=jsonObjectArray.getJSONObject(i);
            movie.setDate(temp.getString("release_date"));
            movie.setPoster(temp.getString("poster_path"));
            movie.setTitle(temp.getString("title"));
            movie.setVote_average(temp.getString("vote_average"));
            movie.setOverview(temp.getString("overview"));
            movies.add(movie);
            Log.d(TAG, "getMoviesList: "+ movie.toString());

        }
        return movies;

    }
}
