package com.iris.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iris.flickster.R;
import com.iris.flickster.models.Movie;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by iris on 7/21/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivPoster);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivImage.setImageResource(0);
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
        String imagePath;
        if (getContext().getResources().getConfiguration().orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            imagePath = movie.getBackdropPath();
        } else {
            imagePath = movie.getPosterPath();
        }
        Picasso.with(getContext()).load(imagePath)
               .placeholder(R.mipmap.movie_placeholder)
               .into(viewHolder.ivImage);
        return convertView;
    }
}
