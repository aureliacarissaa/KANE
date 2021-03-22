package com.example.kane.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kane.Interface.ItemClickListener;
import com.example.kane.Model.Area;
import com.example.kane.Model.Cuisine;
import com.example.kane.R;
import com.example.kane.ViewHolder.AreaViewHolder;
import com.example.kane.ViewHolder.CuisineViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference cuisine;
    DatabaseReference area;

    RecyclerView recycler_cuisine;
    RecyclerView.LayoutManager layoutManager;

    RecyclerView recycler_area;

    public HomeFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        //init
        database = FirebaseDatabase.getInstance();
        cuisine = database.getReference("Cuisine");
        area = database.getReference("Area");

        //load cuisine
        recycler_cuisine = view.findViewById(R.id.recycler_cuisine);
        recycler_cuisine.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));

        loadCuisine();

        recycler_area = view.findViewById(R.id.recycler_area);
        recycler_area.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));

        loadArea();

        return view;
    }

    private void loadCuisine(){
        FirebaseRecyclerAdapter<Cuisine, CuisineViewHolder> adapter = new FirebaseRecyclerAdapter<Cuisine, CuisineViewHolder>(Cuisine.class, R.layout.cuisine_item, CuisineViewHolder.class, cuisine) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void populateViewHolder(CuisineViewHolder cuisineViewHolder, Cuisine cuisine, int i) {
                cuisineViewHolder.txtCuisineName.setText(cuisine.getName());
                cuisineViewHolder.txtCuisineCount.setText("Search "+cuisine.getCount()+" Restaurants");
                Picasso.get().load(cuisine.getImage()).into(cuisineViewHolder.cuisineImage);
                final Cuisine clickItem = cuisine;
                cuisineViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });
            }
        };
        recycler_cuisine.setAdapter(adapter);
    }

    private void loadArea(){
        FirebaseRecyclerAdapter<Area, AreaViewHolder> adapter = new FirebaseRecyclerAdapter<Area, AreaViewHolder>(Area.class, R.layout.area_item, AreaViewHolder.class, area) {
            @Override
            protected void populateViewHolder(AreaViewHolder areaViewHolder, Area area, int i) {
                areaViewHolder.txtAreaName.setText(area.getName());
            }
        };
        recycler_area.setAdapter(adapter);
    }
}