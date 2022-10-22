package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class ECGTest extends AppCompatActivity {

    TextView getdate,gettime,getlocation;
    String datepass,timepass, locationpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecg_test1);
    getdate = findViewById(R.id.date);
    gettime = findViewById(R.id.time);
    getlocation = findViewById(R.id.venue);
    datepass = getIntent().getExtras().getString("Value");
    timepass = getIntent().getExtras().getString("TimeValue");
    locationpass = getIntent().getExtras().getString("LocationValue");
    getdate.setText(datepass);
    gettime.setText(timepass);
    getlocation.setText(locationpass);

    }
}