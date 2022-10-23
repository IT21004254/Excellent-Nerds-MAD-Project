package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
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

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText etDate;
    EditText Date1;
    DatePickerDialog.OnDateSetListener setListener;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    EditText date;
    Spinner Location,Time;
    Button book,check;
    Client client;
    String datepass, timepass, locationpass;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDate = findViewById(R.id.et_date);
        book = (Button) findViewById(R.id.book);
        check = (Button) findViewById(R.id.check);
        date = (EditText)findViewById(R.id.et_date);
        Location = (Spinner) findViewById(R.id.location);
        Time = (Spinner) findViewById(R.id.time);



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



        book.setOnClickListener(new View.OnClickListener() {

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

           client.setLocation(Location.getSelectedItem().toString().trim());
            client.setDate(date.getText().toString().trim());
            client.setTime(Time.getSelectedItem().toString().trim());
           databaseReference.push().setValue(client);

           Toast.makeText(MainActivity.this,"Value Stored Sucessfully", Toast.LENGTH_LONG).show();
        }
    });*/

    }
}



