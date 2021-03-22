package com.example.kane.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kane.Interface.ItemClickListener;
import com.example.kane.Model.Cuisine;
import com.example.kane.R;
import com.example.kane.ViewHolder.CuisineViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference cuisine;

    RecyclerView recycler_cuisine;
    RecyclerView.LayoutManager layoutManager;

    public HomeFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //init
        database = FirebaseDatabase.getInstance();
        cuisine = database.getReference("Cuisine");

        //load cuisine
        recycler_cuisine = view.findViewById(R.id.recycler_cuisine);
        recycler_cuisine.setHasFixedSize(true);
        recycler_cuisine.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));

        loadMenu();

        return view;
    }

    private void loadMenu(){
        FirebaseRecyclerAdapter<Cuisine, CuisineViewHolder> adapter = new FirebaseRecyclerAdapter<Cuisine, CuisineViewHolder>(Cuisine.class, R.layout.cuisine_item, CuisineViewHolder.class, cuisine) {
            @Override
            protected void populateViewHolder(CuisineViewHolder cuisineViewHolder, Cuisine cuisine, int i) {
                cuisineViewHolder.txtCuisineName.setText(cuisine.getName());
                cuisineViewHolder.txtCuisineCount.setText(cuisine.getCount());
                //Picasso.get().load("https://i.ibb.co/LgN44bj/Japanese.png").into(cuisineViewHolder.cuisineImage);
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
}