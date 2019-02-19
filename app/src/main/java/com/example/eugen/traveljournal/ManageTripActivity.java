package com.example.eugen.traveljournal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class ManageTripActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_trip);
        Toolbar toolbar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        TextView textView = (TextView)findViewById(R.id.nav_action_textview);
        textView.setText(getString(R.string.detailstoolbartext));
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        final Bundle extras = new Bundle();
        final EditText name = findViewById(R.id.name);
        final EditText destination = findViewById(R.id.destination);
        final RadioGroup type = findViewById(R.id.type);
        final SeekBar price = findViewById(R.id.price);
        price.setMax(1000);
        final RatingBar rating = findViewById(R.id.rating);
        final Button button = findViewById(R.id.save);
        final Button from = findViewById(R.id.fromdate);
        final Button to = findViewById(R.id.todate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(ManageTripActivity.this,DestinationsList.class);
                extras.putString("Name", name.getText().toString());
                extras.putString("Destination", destination.getText().toString());
                extras.putString("From Date",from.getText().toString());
                extras.putString("To Date",to.getText().toString());
                extras.putFloat("Rating",rating.getRating());
                int radioId = type.getCheckedRadioButtonId();
                View radioButton = type.findViewById(radioId);
                int idx = type.indexOfChild(radioButton);
                RadioButton r = (RadioButton) type.getChildAt(idx);
                extras.putString("Type",r.getText().toString());
                extras.putFloat("Price",price.getProgress());
                goBack.putExtras(extras);
                setResult(RESULT_OK,goBack);
                finish();
            }
        });

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"from date");
                i=0;
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"to date");
                i=1;
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String date = DateFormat.getDateInstance().format(c.getTime());
        Button b1 = findViewById(R.id.fromdate);
        Button b2 = findViewById(R.id.todate);
        if(i==0){
            b1.setText(date);
        }
        else{
            b2.setText(date);
        }
    }
}