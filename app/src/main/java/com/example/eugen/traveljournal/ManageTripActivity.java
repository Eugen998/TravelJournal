package com.example.eugen.traveljournal;

import android.app.DatePickerDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.time.Year;
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

        Button from = findViewById(R.id.fromdate);
        Button to = findViewById(R.id.todate);
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
