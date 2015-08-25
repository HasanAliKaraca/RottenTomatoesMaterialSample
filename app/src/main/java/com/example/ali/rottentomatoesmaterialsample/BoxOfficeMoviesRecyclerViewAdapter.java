/*
* Copyright (C) 2015 Hasan Ali Karaca - http://www.hasanalikaraca.com
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.ali.rottentomatoesmaterialsample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BoxOfficeMoviesRecyclerViewAdapter extends RecyclerView.Adapter<BoxOfficeMovieViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;


    private List<BoxOfficeMovie> moviesList;

    public BoxOfficeMoviesRecyclerViewAdapter(Context context, List<BoxOfficeMovie> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;

        moviesList = items;
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    @Override
    public BoxOfficeMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        //not necessary
        view.setBackgroundResource(mBackground);

        return new BoxOfficeMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BoxOfficeMovieViewHolder boxOfficeViewholder, int position) {

        BoxOfficeMovie movie = moviesList.get(position);

        String criticsScore = String.valueOf(movie.getCriticsScore() + "%");
        String cast = movie.getCastList();
        String title = movie.getTitle();
        String posterUrl = movie.getPosterUrl();


        boxOfficeViewholder.tvCriticsScore.setText(criticsScore);
        boxOfficeViewholder.tvCast.setText(cast);
        boxOfficeViewholder.tvTitle.setText(title);
        boxOfficeViewholder.mMovie = movie;

        Glide.with(boxOfficeViewholder.imAvatar.getContext())
                .load(posterUrl)
                .fitCenter()
                .into(boxOfficeViewholder.imAvatar);

        boxOfficeViewholder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, BoxOfficeMovieDetailActivity.class);
                intent.putExtra(BoxOfficeMovieDetailActivity.EXTRA_MOVIE, boxOfficeViewholder.mMovie);

                context.startActivity(intent);
            }
        });

    }


}