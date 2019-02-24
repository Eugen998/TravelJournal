package com.example.eugen.traveljournal;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ManageTripActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    int i = 0;
    private static int RESULT_LOAD_IMAGE = 1;
    private Uri selectedImage;
    static final int REQUEST_IMAGE_CAPTURE = 2;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        TextView textView = (TextView) findViewById(R.id.nav_action_textview);
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
        final Button galleryImg = findViewById(R.id.gallery_photo);
        final Button takePic = findViewById(R.id.camera_photo);


        galleryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(ManageTripActivity.this, DestinationsList.class);
                extras.putString("Name", name.getText().toString());
                extras.putString("Destination", destination.getText().toString());
                extras.putString("From Date", from.getText().toString());
                extras.putString("To Date", to.getText().toString());
                extras.putFloat("Rating", rating.getRating());
                int radioId = type.getCheckedRadioButtonId();
                View radioButton = type.findViewById(radioId);
                int idx = type.indexOfChild(radioButton);
                RadioButton r = (RadioButton) type.getChildAt(idx);
                extras.putString("Type", r.getText().toString());
                extras.putFloat("Price", price.getProgress());
                extras.putString("Image", selectedImage.toString());
                goBack.putExtras(extras);
                setResult(RESULT_OK, goBack);
                finish();
            }
        });

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "from date");
                i = 0;
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "to date");
                i = 1;
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String date = DateFormat.getDateInstance().format(c.getTime());
        Button b1 = findViewById(R.id.fromdate);
        Button b2 = findViewById(R.id.todate);
        if (i == 0) {
            b1.setText(date);
        } else {
            b2.setText(date);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImage = data.getData();

            mImageView = findViewById(R.id.testpic);
            Glide.with(this).load(selectedImage).into(mImageView);
            TextView uriText = findViewById(R.id.uriText);
            uriText.setText(selectedImage.toString());
            //path = getRealPathFromURI(selectedImage,this);
            //Toast.makeText(this,sel, Toast.LENGTH_LONG).show();
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            mImageView = findViewById(R.id.testpic);
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                mImageView.setImageBitmap(bitmap);
                //selectedImage = getImageUri(this,bitmap);
            }
        }

    }
    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}