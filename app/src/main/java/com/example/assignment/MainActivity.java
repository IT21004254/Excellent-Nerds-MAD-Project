package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.ktx.Firebase;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    EditText etDate;
    EditText Date1;
    DatePickerDialog.OnDateSetListener setListener;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    EditText date;
    Spinner Location,Time;
    Button book,check;
    Client client;
    String datepass, timepass, locationpass,location1;
    DrawerLayout drawerlayout;
    NavigationView navigationview;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        etDate = findViewById(R.id.et_date);
        book = (Button) findViewById(R.id.book);
        //check = (Button) findViewById(R.id.check);
        date = (EditText)findViewById(R.id.et_date);
        Location = (Spinner) findViewById(R.id.location);
        Time = (Spinner) findViewById(R.id.time);

       drawerlayout = findViewById(R.id.drawerlayout);
      navigationview = findViewById(R.id.navigationview);

      //navigationview.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

      toolbar = findViewById(R.id.toolbar);

       setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this,drawerlayout,toolbar,R.string.navigration_open,R.string.nagigration_close);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();



        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.ic_home) {
                    Intent intent = new Intent (MainActivity.this,WelcomePage.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.profile) {
                    Intent intent1 = new Intent (MainActivity.this,WelcomePage.class);
                    startActivity(intent1);
                    return true;
                }

                if (id == R.id.ic_BMI) {
                    Intent intent2 = new Intent (MainActivity.this,WelcomePage.class);
                    startActivity(intent2);
                    return true;
                }

                if (id == R.id.ic_step) {
                    Intent intent3 = new Intent (MainActivity.this,WelcomePage.class);
                    startActivity(intent3);
                    return true;
                }
                if (id == R.id.ic_Settings) {
                    Intent intent4 = new Intent (MainActivity.this,WelcomePage.class);
                    startActivity(intent4);
                    return true;
                }
                if (id == R.id.ic_signout) {
                    Intent intent5 = new Intent (MainActivity.this,WelcomePage.class);
                    startActivity(intent5);
                    return true;
                }

                return false;


            }
        });
        //navigationview.setNavigationItemSelectedListener();
        Calendar calender = Calendar.getInstance();
        final int year = calender.get(Calendar.YEAR);
        final int month = calender.get(Calendar.MONTH);
        final int day = calender.get(Calendar.DAY_OF_MONTH);

    etDate.setOnClickListener(new OnClickListener(){
        @Override
                public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day){
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        etDate.setText(date);
                    }
                },year,month,day);
                    datePickerDialog.show();
            }
        });



        book.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty((CharSequence) Location.getSelectedItem().toString()))
                {
                    Toast.makeText(MainActivity.this,"Please Enter Location", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(date.getText().toString()))
                {
                    Toast.makeText(MainActivity.this,"Please Enter Date", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty((CharSequence) Time.getSelectedItem().toString()))
                {
                    Toast.makeText(MainActivity.this,"Please Enter Time", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, ECGTest.class);
                    datepass = date.getText().toString();
                    timepass = Time.getSelectedItem().toString();
                    locationpass = Location.getSelectedItem().toString();
                    intent.putExtra("Value", datepass);
                    intent.putExtra("TimeValue", timepass);
                    intent.putExtra("LocationValue", locationpass);
                    startActivity(intent);
                    finish();

                }

            }
    });



     /*   date = (EditText)findViewById(R.id.et_date);
        Location = (Spinner) findViewById(R.id.location);
        Time = (Spinner) findViewById(R.id.time);
        client = new Client();
        databaseReference = database.getInstance().getReference().child("Client");
        book.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {

                client.setLocation(Location.getSelectedItem().toString().trim());
                client.setDate(date.getText().toString().trim());
                client.setTime(Time.getSelectedItem().toString().trim());
                databaseReference.push().setValue(client);

                Toast.makeText(MainActivity.this,"Value Stored Sucessfully", Toast.LENGTH_LONG).show();
            }
        });



        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userenteredlocation = Location.getSelectedItem().toString().trim();
                String userentereddate = date.getText().toString().trim();
                String userenteredtime = Time.getSelectedItem().toString().trim();

                DatabaseReference refrence = FirebaseDatabase.getInstance().getReference("Client");

                Query checkdata = refrence.orderByChild("location").equalTo(userenteredtime);

                checkdata.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            String locationFromDB = dataSnapshot.child(userentereddate).child("date").getValue(String.class);

                            if (locationFromDB.equals(userentereddate))
                            {
                                Toast.makeText(MainActivity.this,"Please Use Different Place", Toast.LENGTH_LONG).show();
                                String TimeFromDB = dataSnapshot.child(userentereddate).child("time").getValue(String.class);
                                if(TimeFromDB.equals(userenteredtime))
                                {
                                    Toast.makeText(MainActivity.this,"Please Use Different Place", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    date = (EditText)findViewById(R.id.et_date);
    Location = (Spinner) findViewById(R.id.location);
    Time = (Spinner) findViewById(R.id.time);
    client = new Client();
    databaseReference = database.getInstance().getReference().child("Client");
    book.setOnClickListener(new View.OnClickListener()
    {

        @Override
        public void onClick(View view) {

       //client.setLocation(Location.getSelectedItem().toString().trim());
            //client.setDate(date.getText().toString().trim());
            //client.setTime(Time.getSelectedItem().toString().trim());
           //databaseReference.push().setValue(client);

            location1 = Location.getSelectedItem().toString();

           Toast.makeText(MainActivity.this,"Value Stored Sucessfully", Toast.LENGTH_LONG).show();
            myList.add(location1);
            //myList.add("Hi");

            databaseReference.setValue(myList).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful())
                    {

                    }

                }
            });
            {

            }

        }
    });*/





    }


}



