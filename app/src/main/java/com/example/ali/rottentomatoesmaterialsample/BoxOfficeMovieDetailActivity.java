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

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class BoxOfficeMovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "selected_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office_movie_detail);

        Intent intent = getIntent();
        BoxOfficeMovie movie = (BoxOfficeMovie) intent.getSerializableExtra(EXTRA_MOVIE);
        String movieName = movie.getTitle();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(movieName);

        setMovieInfo(movie);

    }

    private void setMovieInfo(BoxOfficeMovie movie) {
        String title = movie.getTitle();
        String year = String.valueOf(movie.getYear());
        String score = String.valueOf(movie.getCriticsScore());

        TextView tvMovieName = (TextView) findViewById(R.id.tvMovieName);
        tvMovieName.setText(movie.getTitle());

        TextView tvMovieInfo = (TextView) findViewById(R.id.tvMovieInfo);
        tvMovieInfo.setText(movie.getSynopsis());

        //cast
        TextView tvMovieCast = (TextView) findViewById(R.id.tvMovieCast);
        tvMovieCast.setText(movie.getCastList());

        //summary ınfo
        TextView tvRelatedInfo = (TextView) findViewById(R.id.tvRelatedInfo);

        String info = String.format("%s \n %s \n %s %%",title,year,score);
        tvRelatedInfo.setText(info);


        String imageUri = movie.getPosterUrlBig();

        loadBackdrop(imageUri);
    }

    private void loadBackdrop(String imageUrl) {

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this)
                .load((imageUrl))
                        //.centerCrop()
                .into(imageView);
    }

    /*   @Override
       public boolean onCreateOptionsMenu(Menu menu) {
           // Inflate the menu; this adds items to the action bar if it is present.
           getMenuInflater().inflate(R.menu.menu_box_office_movie_detail, menu);
           return true;
       }
   */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
