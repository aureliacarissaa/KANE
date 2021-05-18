package com.example.kane;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kane.Database.Database;
import com.example.kane.Model.Menu;
import com.example.kane.Model.Order;
import com.example.kane.Model.Trending;
import com.example.kane.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MenuPage extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference menu;
    DatabaseReference restaurants;

    RecyclerView recycler_menu;
    FirebaseRecyclerAdapter<Menu, MenuViewHolder> adapter;

    TextView edtRestaurantName;
    TextView edtRestaurantLocation;
    Button btnSummary;

    String restaurantID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) this).getSupportActionBar().hide();
        setContentView(R.layout.activity_menu_page);

        //init
        database = FirebaseDatabase.getInstance();
        menu = database.getReference("Menu");
        restaurants = database.getReference("Trending");
        edtRestaurantName = findViewById(R.id.restaurant_name);
        edtRestaurantLocation = findViewById(R.id.restaurant_location);

        //get extras
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        restaurantID = extras.getString("restaurantID");
        getDetailRestaurant(restaurantID);

        //load menu
        recycler_menu = findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        recycler_menu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        loadMenu(restaurantID);

        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void loadMenu(String restaurantID){
        adapter = new FirebaseRecyclerAdapter<Menu, MenuViewHolder>(Menu.class, R.layout.menu_item, MenuViewHolder.class,
                menu.orderByChild("RestaurantID").equalTo(restaurantID)) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void populateViewHolder(MenuViewHolder menuViewHolder, Menu menu, int i) {
                menuViewHolder.txtMenuName.setText(menu.getName());
                menuViewHolder.txtMenuPrice.setText("Rp "+menu.getPrice());
                menuViewHolder.txtMenuInfo.setText(menu.getInfo());
                menuViewHolder.numberPicker.setMin(0);
                menuViewHolder.numberPicker.setValue(0);
                Picasso.get().load(menu.getImage()).into(menuViewHolder.menuImage);
            }
        };
        recycler_menu.setAdapter(adapter);
    }

    private void getDetailRestaurant (String restaurantID) {
        restaurants.child(restaurantID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Trending trending = snapshot.getValue(Trending.class);

                edtRestaurantName.setText(trending.getName());
                edtRestaurantLocation.setText(trending.getLocation());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}