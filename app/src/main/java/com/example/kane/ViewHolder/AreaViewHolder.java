package com.example.kane.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kane.Interface.ItemClickListener;
import com.example.kane.R;

public class AreaViewHolder extends RecyclerView.ViewHolder {
    public TextView txtAreaName;

    public AreaViewHolder(View itemView){
        super(itemView);

        txtAreaName = (TextView) itemView.findViewById(R.id.area_name);
    }

}
