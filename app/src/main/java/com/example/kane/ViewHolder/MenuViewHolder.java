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
    public NumberPicker numberPicker;

    public MenuViewHolder(View itemView) {
        super(itemView);

        txtMenuName = (TextView) itemView.findViewById(R.id.menu_name);
        txtMenuPrice = (TextView) itemView.findViewById(R.id.menu_price);
        txtMenuInfo = (TextView) itemView.findViewById(R.id.menu_info);
        menuImage = (ImageView) itemView.findViewById(R.id.menu_image);
        numberPicker = (NumberPicker) itemView.findViewById(R.id.number_picker);
    }
}
