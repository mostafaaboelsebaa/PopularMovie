package com.example.malek.popularmovie;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Malek on 2/9/2018.
 */

public class Network {
    private static final String TAG  = "Network";
    final static String BASE_URL =
            "http://api.themoviedb.org/3";

    final static String API_KEY= "api_key";
    final static String my_API_KEY = "481d4752fa7689e3c9ea3211ac2c9f83";
    public final  static String popular="/movie/popular";
    public final  static String MostRated="/movie/top_rated";
   // final  static String url="http://api.themoviedb.org/3/movie/popular?api_key=481d4752fa7689e3c9ea3211ac2c9f83";



    public static URL buildUrl(String paramter) {
        Uri builtUri = Uri.parse(BASE_URL+paramter).buildUpon()
                .appendQueryParameter(API_KEY,my_API_KEY)
                .build();
        Log.d(TAG, "buildUrl: "+ builtUri.toString());

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
