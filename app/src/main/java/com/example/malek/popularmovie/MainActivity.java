package com.example.malek.popularmovie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView SearchResultsTextView;
    ArrayList<Movie> movies =new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.MovieRecycler);
        new AsyTask().execute(Network.buildUrl(Network.popular));
        MovieAdapter movieAdapter=new MovieAdapter(movies);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

    }
  class AsyTask extends AsyncTask<URL, Void, String>{

      @Override
      protected String doInBackground(URL... params) {
          URL searchUrl = params[0];
          String SearchResults = null;
          try {
              SearchResults = Network.getResponseFromHttpUrl(searchUrl);
          } catch (IOException e) {
              e.printStackTrace();
          }
          return SearchResults;

  }

      @Override
      protected void onPostExecute(String SearchResults) {
          if (SearchResults != null && !SearchResults.equals("")) {
              try {
                  movies =JsonParser.getMoviesList(SearchResults);
                  MovieAdapter movieAdapter=new MovieAdapter(movies);
                  recyclerView.setAdapter(movieAdapter);

              } catch (JSONException e) {
                  e.printStackTrace();
              }



          }

      }
  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.Most_rate) {
            new AsyTask().execute(Network.buildUrl(Network.MostRated));
            MovieAdapter movieAdapter=new MovieAdapter(movies);
            recyclerView.setAdapter(movieAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));

            return true;
        }
        else if (itemThatWasClickedId == R.id.Most_pop){
            new AsyTask().execute(Network.buildUrl(Network.popular));
            MovieAdapter movieAdapter=new MovieAdapter(movies);
            recyclerView.setAdapter(movieAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));

            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
