package com.example.eugen.traveljournal;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.Date;

public class DestinationsList extends AppCompatActivity {
    private static final String TAG = "DestinationsList";
    //variabile
    FloatingActionButton fab;
    public ArrayList<String> mNames = new ArrayList<>();
    public ArrayList<String> mDestinations = new ArrayList<>();
    public ArrayList<Image> mImages = new ArrayList<>();
    public ArrayList<String> mTripTypes = new ArrayList<>();
    public ArrayList<Integer> mPrices = new ArrayList<>();
    public ArrayList<Date> mFromDates = new ArrayList<>();
    public ArrayList<Date> mToDates = new ArrayList<>();
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
//        initImageBitmaps();
        initRecyclerView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DestinationsList.this,ManageTripActivity.class));
            }
        });
    }

//    private void initImageBitmaps() {
//        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
//
//        mImageUrls.add("https://cdn.thecrazytourist.com/wp-content/uploads/2017/09/ccimage-shutterstock_412496293.jpg");
//        mNames.add("San Francisco");
//        mSeasons.add("Summer 2017");
//
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
//
//
//    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: initialising recycler view.");
        RecyclerView recyclerView = findViewById(R.id.destinationrecycler);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mNames,mDestinations,mTripTypes,mPrices,mFromDates,mToDates,mImages,mRatings,this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}