package com.example.eugen.traveljournal;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DestinationsList extends AppCompatActivity {
    private static final String TAG = "DestinationsList";
    //variabile
    private final static int REQUEST_CODE_1 = 1;
    FloatingActionButton fab;
    public ArrayList<String> mNames = new ArrayList<>();
    public ArrayList<String> mDestinations = new ArrayList<>();
    public ArrayList<String> mImages = new ArrayList<>();
    public ArrayList<String> mTripTypes = new ArrayList<>();
    public ArrayList<Float> mPrices = new ArrayList<>();
    public ArrayList<String> mFromDates = new ArrayList<>();
    public ArrayList<String> mToDates = new ArrayList<>();
    public ArrayList<Integer> mRatings = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations_list);

        Log.d(TAG, "onCreate: started.");

        Toolbar toolbar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        TextView textView = (TextView)findViewById(R.id.nav_action_textview);
        textView.setText(getString(R.string.toolbardestinations));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        fab = findViewById(R.id.fab);
        //initImageBitmaps();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DestinationsList.this,ManageTripActivity.class);
                startActivityForResult(intent,REQUEST_CODE_1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQUEST_CODE_1:
                if(resultCode == RESULT_OK){
                    Bundle extras = data.getExtras();
                    mImages.add("https://www.sortiraparis.com/images/55/1467/407707-que-faire-cette-semaine-a-paris-du-12-au-18-novembre-2018.jpg");
                    mNames.add(extras.getString("Name"));
                    mDestinations.add(extras.getString("Destination"));
                    mTripTypes.add("a");
                    mPrices.add((float)4);
                    mFromDates.add(extras.getString("From Date"));
                    mToDates.add(extras.getString("To Date"));
                    mRatings.add(4);


                    Log.d(TAG, "onActivityResult: going to init recycle with data received");

                    initRecyclerView();
                }
        }
    }
    //private void initImageBitmaps() {
        //Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        //mImages.add(R.drawable.ic_menu_contact);
        //mDestinations.add("San Francisco");
        //mNames.add("Summer 2017");

//        mImageUrls.add("https://www.sortiraparis.com/images/55/1467/407707-que-faire-cette-semaine-a-paris-du-12-au-18-novembre-2018.jpg");
//        mNames.add("Paris");
//        mSeasons.add("Winter 2016");
//
//        mImageUrls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdROzwIJNg0AZ-HA9kXpUEwdxPfNNOKuGAIPL88NgCvRrA7U7J");
//        mNames.add("Shanghai");
//        mSeasons.add("Winter 2015");
//
//        mImageUrls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfjXg4oTrJp9vR3k-7h7aMOUbGerAW45DVC6LokupmhbiWLV6M");
//        mNames.add("Los Angeles");
//        mSeasons.add("Summer 2018");


   // }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: initialising recycler view.");
        RecyclerView recyclerView = findViewById(R.id.destinationrecycler);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mNames,mDestinations,mTripTypes,mPrices,mFromDates,mToDates,mImages,mRatings,this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}