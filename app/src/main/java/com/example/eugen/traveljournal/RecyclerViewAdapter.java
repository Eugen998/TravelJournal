package com.example.eugen.traveljournal;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mDestinations = new ArrayList<>();
    private ArrayList<String> mTripTypes = new ArrayList<>();
    private ArrayList<Integer> mPrices = new ArrayList<>();
    private ArrayList<Date> mFromDates = new ArrayList<>();
    private ArrayList<Date> mToDates = new ArrayList<>();
    private ArrayList<Image> mImages = new ArrayList<>();
    private ArrayList<Integer> mRatings = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mNames, ArrayList<String> mDestinations, ArrayList<String> mTripTypes, ArrayList<Integer> mPrices, ArrayList<Date> mFromDates, ArrayList<Date> mToDates, ArrayList<Image> mImages, ArrayList<Integer> mRatings, Context mContext) {
        this.mNames = mNames;
        this.mDestinations = mDestinations;
        this.mTripTypes = mTripTypes;
        this.mPrices = mPrices;
        this.mFromDates = mFromDates;
        this.mToDates = mToDates;
        this.mImages = mImages;
        this.mRatings = mRatings;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG,"onBindViewHolder: called.");
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(i))
                .into(viewHolder.image);
        viewHolder.Name.setText(mNames.get(i));
        viewHolder.Destination.setText(mDestinations.get(i));
        viewHolder.tripType = mNames.get(i);
        viewHolder.Price = mPrices.get(i);
        viewHolder.fromDate = mFromDates.get(i);
        viewHolder.toDate = mToDates.get(i);
        viewHolder.Rating = mRatings.get(i);
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"clicked on " + mNames.get(i));
                Toast.makeText(mContext, mNames.get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Name;
        TextView Destination;
        String tripType;
        Integer Price;
        Date fromDate;
        Date toDate;
        CircleImageView image;
        Integer Rating;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            Destination = itemView.findViewById(R.id.trip_destination);
            Name = itemView.findViewById(R.id.trip_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
