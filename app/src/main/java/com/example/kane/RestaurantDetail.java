package com.example.kane;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.kane.Model.Trending;
import com.goodiebag.horizontalpicker.HorizontalPicker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RestaurantDetail extends AppCompatActivity {

    TextView restaurantName, restaurantSmallInfo, restaurantLocation, restaurantInfo;
    ImageView restaurantImage;
    Button btnChooseTable;
    EditText edtdatepicker, edttimepicker;
    HorizontalPicker hpText;
    final Calendar myCalendar = Calendar.getInstance();

    String restaurantID = "";

    FirebaseDatabase database;
    DatabaseReference restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity) this).getSupportActionBar().hide();

        setContentView(R.layout.activity_restaurant_detail);

        //firebase
        database = FirebaseDatabase.getInstance();
        restaurants = database.getReference("Trending");

        //init view
        restaurantName = findViewById(R.id.restaurant_name);
        restaurantSmallInfo = findViewById(R.id.restaurant_smallinfo);
        restaurantLocation = findViewById(R.id.restaurant_location);
        restaurantInfo = findViewById(R.id.restaurant_info);

        restaurantImage = findViewById(R.id.restaurant_image);

        btnChooseTable = findViewById(R.id.continuetotable);

        edtdatepicker = findViewById(R.id.datepicker);
        edttimepicker = findViewById(R.id.timepicker);
        hpText = (HorizontalPicker) findViewById(R.id.hpicker);

        //get restaurant id from intent
        if(getIntent() != null)
            restaurantID = getIntent().getStringExtra("RestaurantID");
        if(!restaurantID.isEmpty()) {
            getDetailRestaurant(restaurantID);
        }

        //number picker
        List<HorizontalPicker.PickerItem> textItems = new ArrayList<>();
        for(int i=1;i<=9;i++){
            textItems.add(new HorizontalPicker.TextItem(""+i));
        }
        hpText.setItems(textItems,2);

        //date
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        edtdatepicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(RestaurantDetail.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //time
        edttimepicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(RestaurantDetail.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edttimepicker.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });

        btnChooseTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = edtdatepicker.getText().toString();
                String time = edttimepicker.getText().toString();
                String party = hpText.getItems().toString();

                Bundle extras = new Bundle();

                extras.putString("date", date);
                extras.putString("time", time);
                extras.putString("party", party);
                extras.putString("restaurantID", restaurantID);

                Intent chooseTable = new Intent(RestaurantDetail.this, TablePage.class);
                chooseTable.putExtras(extras); //send ID to new activity
                startActivity(chooseTable);
            }
        });
    }

    private void getDetailRestaurant (String restaurantID) {
        restaurants.child(restaurantID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Trending trending = snapshot.getValue(Trending.class);

                Picasso.get().load(trending.getImage()).into(restaurantImage);
                restaurantName.setText(trending.getName());
                restaurantSmallInfo.setText(trending.getPriceTag()+" • "+trending.getType()+" • "+trending.getStars()+" ("+trending.getReviews()+")");
                restaurantLocation.setText(trending.getLocation());
                restaurantInfo.setText(trending.getInfo());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/mm/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        edtdatepicker.setText(sdf.format(myCalendar.getTime()));
    }
}