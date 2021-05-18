package com.example.kane;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kane.Model.Trending;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class TablePage extends AppCompatActivity {

    TextView restaurantName, restaurantLocation;
    Button btnContinueFood;
    ImageView btnBack;
    EditText edtNotes;

    FirebaseDatabase database;
    DatabaseReference restaurants;

    String restaurantID = "";
    //TODO copy paste
    String date = "";
    String time = "";
    String party = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity) this).getSupportActionBar().hide();

        setContentView(R.layout.activity_table_page);

        //init view
        restaurantName = findViewById(R.id.restaurant_name);
        restaurantLocation = findViewById(R.id.restaurant_location);

        btnContinueFood = findViewById(R.id.continuetofood);
        btnBack = findViewById(R.id.back_button);

        edtNotes = findViewById(R.id.reservationNotes);

        //spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.table_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        //firebase
        database = FirebaseDatabase.getInstance();
        restaurants = database.getReference("Trending");

        //get extras
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        restaurantID = extras.getString("restaurantID");
        //TODO copy paste
        date = extras.getString("date");
        time = extras.getString("time");
        party = extras.getString("party");

        if(!restaurantID.isEmpty()) {
            getDetailRestaurant(restaurantID);
        }

        btnContinueFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String table = spinner.getSelectedItem().toString();
                String notes = edtNotes.getText().toString();

                extras.putString("table", table);
                extras.putString("notes", notes);

                Intent chooseMenu = new Intent(TablePage.this, MenuPage.class);
                chooseMenu.putExtras(extras); //send ID to new activity
                startActivity(chooseMenu);
            }
        });
    }

    private void getDetailRestaurant (String restaurantID) {
        restaurants.child(restaurantID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Trending trending = snapshot.getValue(Trending.class);

                restaurantName.setText(trending.getName());
                restaurantLocation.setText(trending.getLocation());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}