package com.example.eugen.traveljournal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class DestinationsList extends AppCompatActivity {
    private static final String TAG = "DestinationsList";
    //variabile
    public ArrayList<String> mNames = new ArrayList<>();
    public ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations_list);
        Log.d(TAG, "onCreate: started.");
        initImageBitmaps();
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://cdn.thecrazytourist.com/wp-content/uploads/2017/09/ccimage-shutterstock_412496293.jpg");
        mNames.add("San Francisco");

        mImageUrls.add("https://www.sortiraparis.com/images/55/1467/407707-que-faire-cette-semaine-a-paris-du-12-au-18-novembre-2018.jpg");
        mNames.add("Paris");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: initialising recycler view.");
        RecyclerView recyclerView = findViewById(R.id.destinationrecycler);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,mNames,mImageUrls);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}