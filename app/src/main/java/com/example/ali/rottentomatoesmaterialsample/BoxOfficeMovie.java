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

import android.os.Parcelable;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class BoxOfficeMovie implements Serializable {
    private String title;
    private int year;
    private String synopsis;
    private String posterUrl;
    private String posterUrlBig;
    private int criticsScore;
    private ArrayList<String> castList;

    public static ArrayList<BoxOfficeMovie> fromJson(JSONArray jsonArr) {
        ArrayList<BoxOfficeMovie> movies = new ArrayList<BoxOfficeMovie>(jsonArr.length());

        // Process each result in json array, decode and convert to movie
        // object
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject moviesJson = null;

            try {
                moviesJson = jsonArr.getJSONObject(i);

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            BoxOfficeMovie movie = new BoxOfficeMovie().fromJson(moviesJson);

            if (movie != null) {
                movies.add(movie);
            }

        }

        return movies;

    }

    public static BoxOfficeMovie fromJson(JSONObject json) {
        BoxOfficeMovie b = new
                BoxOfficeMovie();

        try {
            b.title = json.getString("title");
            b.year = json.getInt("year");
            b.synopsis = json.getString("synopsis");
            b.posterUrl = json.getJSONObject("posters").getString("profile");
            b.posterUrlBig = json.getJSONObject("posters").getString("detailed");
            b.criticsScore = json.getJSONObject("ratings").getInt("critics_score");

            // Construct simple array of cast names
            b.castList = new ArrayList<String>();
            JSONArray abridgedCast = json.getJSONArray("abridged_cast");
            for (int i = 0; i < abridgedCast.length(); i++) {
                String castName = abridgedCast.getJSONObject(i).getString("name");
                b.castList.add(castName);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return b;
    }

    public static ArrayList<BoxOfficeMovie> getDummyData() {
        ArrayList<BoxOfficeMovie> movies = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            ArrayList<String> castList = new ArrayList<String>();
            castList.add("Ali");
            castList.add("Veli");

            BoxOfficeMovie movie = new BoxOfficeMovie();
            movie.setTitle("Movie " + String.valueOf(i));
            movie.setCastList(castList);
            movie.setCriticsScore(33);
            movie.setPosterUrl("http://www.wpclipart.com/page_frames/full_page_signs/Thumb_Up_full_page_color.png");
            movie.setPosterUrlBig("http://www.wpclipart.com/page_frames/full_page_signs/Thumb_Up_full_page_color.png");
            movie.setYear(1998);

            movies.add(movie);

        }

        return movies;

    }

    public String getPosterUrlBig() {
        return posterUrlBig;
    }

    public BoxOfficeMovie setPosterUrlBig(String posterUrlBig) {
        this.posterUrlBig = posterUrlBig;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BoxOfficeMovie setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getYear() {
        return year;
    }

    public BoxOfficeMovie setYear(int year) {
        this.year = year;
        return this;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public BoxOfficeMovie setSynopsis(String synopsis) {
        this.synopsis = synopsis;
        return this;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public BoxOfficeMovie setPosterUrl(String posterUrl) {

        this.posterUrl = posterUrl;
        return this;
    }

    public int getCriticsScore() {
        return criticsScore;
    }

    public BoxOfficeMovie setCriticsScore(int criticsScore) {
        this.criticsScore = criticsScore;
        return this;
    }

    public String getCastList() {
        return TextUtils.join(", ", castList);
    }

    public BoxOfficeMovie setCastList(ArrayList<String> castList) {
        this.castList = castList;
        return this;
    }
}