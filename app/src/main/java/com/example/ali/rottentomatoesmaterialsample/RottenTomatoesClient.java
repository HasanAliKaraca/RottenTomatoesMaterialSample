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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public class RottenTomatoesClient {
    private final String API_KEY = "9htuhtcb4ymusd73d4z6jxcj"; // paste your key here
    private final String API_BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    private AsyncHttpClient client;

    public RottenTomatoesClient() {
        this.client = new AsyncHttpClient();
    }

    public String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    public void getBoxOfficeMovies(JsonHttpResponseHandler handler) {
        String url = getApiUrl("lists/movies/box_office.json");

        RequestParams params = new RequestParams("apikey", API_KEY);

        client.get(url, params, handler);
    }

    public void getBoxOfficeMovies(Context context, Response.Listener<JSONObject> responseListener,
                                   Response.ErrorListener errorListener) {

        String url = getApiUrl("lists/movies/box_office.json?apikey=" + API_KEY);

        JsonObjectRequest jsObjRequest =

                new JsonObjectRequest(Request.Method.GET, url,(String) null, responseListener, errorListener);

        VolleySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }
}
