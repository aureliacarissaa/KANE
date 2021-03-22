package com.example.kane.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kane.R;

public class TrendingViewHolder extends RecyclerView.ViewHolder {
    public TextView txtTrendingName;
    public TextView txtTrendingInfo;
    public ImageView trendingImage;

    public TrendingViewHolder(View itemView) {
        super(itemView);

        txtTrendingName = (TextView) itemView.findViewById(R.id.trending_name);
        txtTrendingInfo = (TextView) itemView.findViewById(R.id.trending_info);
        trendingImage = (ImageView) itemView.findViewById(R.id.trending_image);
    }
}