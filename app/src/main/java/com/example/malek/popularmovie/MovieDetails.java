package com.example.malek.popularmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ImageView poster=(ImageView) findViewById(R.id.posterImage);
        TextView title=(TextView) findViewById(R.id.movieTitle);
        TextView releaseDate=(TextView) findViewById(R.id.releaseDate);
        TextView overview=(TextView) findViewById(R.id.overview);
        TextView vote_average=(TextView) findViewById(R.id.vote_average);

        /* I use the parcable to send objects between activities and i found this in android developer site
        * https://developer.android.com/reference/android/os/Parcelable.html*/
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        Movie movie=bundle.getParcelable(MovieAdapter.MOVIE_EXTRA_KEY );
        Picasso.with(getBaseContext())
                .load("http://image.tmdb.org/t/p/w185"+ movie.getPoster())
                .placeholder(R.drawable.placeholder)
                .into(poster);
        title.setText(movie.getTitle());
        releaseDate.setText(movie.getDate());
        overview.setText(movie.getOverview());
        vote_average.setText(movie.getVote_average());

    }
}
