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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class BoxOfficeListFragment extends Fragment {

    ArrayList<BoxOfficeMovie> mMoviesList;
    private RecyclerView rv;
    private BoxOfficeMoviesRecyclerViewAdapter adapter;
    private Context context;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View view;

    private void fetchBoxOfficeMoviesWithDummyData() {
        mMoviesList = BoxOfficeMovie.getDummyData();
        UpdateBoxOfficeMoviesListModel(mMoviesList);

    }

    private void fetchBoxOfficeMovies() {

        RottenTomatoesClient client = new RottenTomatoesClient();

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    // Get the movies json array
                    JSONArray items = response.getJSONArray("movies");
                    // Parse json array into array of model objects
                    ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJson(items);
                    UpdateBoxOfficeMoviesListModel(movies);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
        //client.getBoxOfficeMovies(this, responseListener, errorListener);
        client.getBoxOfficeMovies(context, responseListener, errorListener);

        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_box_office_list, container, false);

        rv = (RecyclerView) view.findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        context = getActivity();

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchBoxOfficeMovies();
            }
        });

        //fetchBoxOfficeMoviesWithDummyData();
        fetchBoxOfficeMovies();

        return view;
    }

    private void UpdateBoxOfficeMoviesListModel(ArrayList<BoxOfficeMovie> moviesList) {

        adapter = new BoxOfficeMoviesRecyclerViewAdapter(context, moviesList);
        rv.setAdapter(adapter);
    }

}
