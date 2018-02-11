package com.example.malek.popularmovie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Malek on 2/9/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Viewholder> {
    private static final String TAG = "MovieAdapter";
    public static final String MOVIE_EXTRA_KEY = "movieKey";
    ArrayList<Movie> movies = new ArrayList<>();
    Context context;

    MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        context = parent.getContext();
        return new Viewholder(rootView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        Movie movie = movies.get(position);

        Picasso.with(context).load("http://image.tmdb.org/t/p/w185"+ movie.getPoster()).placeholder(R.drawable.placeholder).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        public Viewholder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.postimage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context,MovieDetails.class);
           // Log.d(TAG, "onClick: ");
            Bundle extras=new Bundle();
            extras.putParcelable(MOVIE_EXTRA_KEY,movies.get(getAdapterPosition()));
            intent.putExtras(extras);
            context.startActivity(intent);

        }
    }
}
