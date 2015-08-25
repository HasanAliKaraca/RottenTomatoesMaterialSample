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

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public  class  BoxOfficeMovieViewHolder extends RecyclerView.ViewHolder {
    public final View mView;

    public final TextView tvTitle;
    public final TextView tvCriticsScore;
    public final TextView tvCast;
    public final CircleImageView imAvatar;
    public BoxOfficeMovie mMovie;

    public BoxOfficeMovieViewHolder(View view) {
        super(view);
        mView = view;

        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvCriticsScore = (TextView) view.findViewById(R.id.tvCriticsScore);
        tvCast = (TextView) view.findViewById(R.id.tvCast);
        imAvatar = (CircleImageView) view.findViewById(R.id.imAvatar);
    }

}