package com.example.kane.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kane.Interface.ItemClickListener;
import com.example.kane.R;

import org.w3c.dom.Text;

public class CuisineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtCuisineName;
    public TextView txtCuisineCount;
    public ImageView cuisineImage;

    private ItemClickListener itemClickListener;

    public CuisineViewHolder(View itemView){
        super(itemView);

        txtCuisineName = (TextView) itemView.findViewById(R.id.cuisine_name);
        txtCuisineCount = (TextView) itemView.findViewById(R.id.cuisine_count);
        cuisineImage = (ImageView) itemView.findViewById(R.id.cuisine_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void onClick(View view){
        itemClickListener.onClick(view, getAbsoluteAdapterPosition(), false);
    }
}
