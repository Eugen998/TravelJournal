package com.example.eugen.traveljournal;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
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
    private DrawerLayout drawerLayout;
    private final static int REQUEST_CODE_1 = 1;
    FloatingActionButton fab;
    public ArrayList<String> mNames = new ArrayList<>();
    public ArrayList<String> mDestinations = new ArrayList<>();
    public ArrayList<Uri> mImages = new ArrayList<>();
    public ArrayList<String> mTripTypes = new ArrayList<>();
    public ArrayList<Float> mPrices = new ArrayList<>();
    public ArrayList<String> mFromDates = new ArrayList<>();
    public ArrayList<String> mToDates = new ArrayList<>();
    public ArrayList<Float> mRatings = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations_list);
        Toolbar toolbar = (Toolbar)findViewById(R.id.nav_action);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open_nav_drawer,R.string.close_nav_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Log.d(TAG, "onCreate: started.");

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
                    Uri path = Uri.parse(extras.getString("Image"));
                    mImages.add(path);
                    mNames.add(extras.getString("Name"));
                    mDestinations.add(extras.getString("Destination"));
                    mTripTypes.add(extras.getString("Type"));
                    mPrices.add(extras.getFloat("Price"));
                    mFromDates.add(extras.getString("From Date"));
                    mToDates.add(extras.getString("To Date"));
                    mRatings.add(extras.getFloat("Rating"));


                    Log.d(TAG, "onActivityResult: going to init recycle with data received");

                    initRecyclerView();
                }
        }
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: initialising recycler view.");
        RecyclerView recyclerView = findViewById(R.id.destinationrecycler);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mNames,mDestinations,mTripTypes,mPrices,mFromDates,mToDates,mImages,mRatings,this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

}