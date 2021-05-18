package com.example.kane.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.kane.Model.Trending;
import com.example.kane.R;
import com.example.kane.RestaurantDetail;
import com.example.kane.ViewHolder.AreaViewHolder;
import com.example.kane.ViewHolder.CuisineViewHolder;
import com.example.kane.ViewHolder.TrendingViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference cuisine;
    DatabaseReference area;
    DatabaseReference trending;

    RecyclerView recycler_cuisine;
    RecyclerView.LayoutManager layoutManager;

    RecyclerView recycler_area;
    RecyclerView recycler_trending;

    FirebaseRecyclerAdapter<Trending, TrendingViewHolder> trendingadapter;

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
        trending = database.getReference("Trending");

        //load cuisine
        recycler_cuisine = view.findViewById(R.id.recycler_cuisine);
        recycler_cuisine.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));

        loadCuisine();

        //load area
        recycler_area = view.findViewById(R.id.recycler_area);
        recycler_area.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));

        loadArea();

        //load trending
        recycler_trending = view.findViewById(R.id.recycler_trending);
        recycler_trending.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));

        loadTrending();

        return view;
    }

    private void loadTrending() {
        trendingadapter = new FirebaseRecyclerAdapter<Trending, TrendingViewHolder>(Trending.class, R.layout.trending_item, TrendingViewHolder.class, trending) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void populateViewHolder(TrendingViewHolder trendingViewHolder, Trending trending, int i) {
                trendingViewHolder.txtTrendingName.setText(trending.getName());
                trendingViewHolder.txtTrendingInfo.setText(trending.getPriceTag()+" • "+trending.getType()+" • "+trending.getStars()+" ("+trending.getReviews()+")");
                Picasso.get().load(trending.getImage()).into(trendingViewHolder.trendingImage);
                trendingViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //start restaurant detail
                        Intent restaurantDetail = new Intent(getActivity(), RestaurantDetail.class);
                        restaurantDetail.putExtra("RestaurantID", trendingadapter.getRef(position).getKey()); //send ID to new activity
                        startActivity(restaurantDetail);
                    }
                });
            }
        };
        recycler_trending.setAdapter(trendingadapter);
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