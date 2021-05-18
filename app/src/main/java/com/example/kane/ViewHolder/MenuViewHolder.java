package com.example.kane.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kane.R;
import com.travijuu.numberpicker.library.NumberPicker;

public class MenuViewHolder extends RecyclerView.ViewHolder {
    public TextView txtMenuName;
    public TextView txtMenuPrice;
    public TextView txtMenuInfo;
    public ImageView menuImage;

    public MenuViewHolder(View itemView) {
        super(itemView);

        txtMenuName = itemView.findViewById(R.id.menu_name);
        txtMenuPrice = itemView.findViewById(R.id.menu_price);
        txtMenuInfo = itemView.findViewById(R.id.menu_info);
        menuImage = itemView.findViewById(R.id.menu_image);
    }
}
