package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ECGTest extends AppCompatActivity {

    TextView getdate,gettime,getlocation;
    String datepass,timepass, locationpass;
    private Button cancelbooking;
    private Button gotohome;
    private Button pay;
    private String ppname;
    private String ppage;
    Dialog dialog, dialog1, dialog2;
    EditText name,age,pname, page;
    TextView apoint12;
    Button savedata;
    List<String> myList;
    DatabaseReference databaseReference12;
    FirebaseDatabase database12;
    Client client1;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerlayout;
    NavigationView navigationview;
    Toolbar toolbar;


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
    savedata = (Button) findViewById(R.id.savedata);
    myList = new ArrayList<>();
    dialog = new Dialog(ECGTest.this);
    dialog.setContentView(R.layout.custombackground);
    pname = (EditText) findViewById(R.id.PersonName);
    page = (EditText) findViewById(R.id.ageECG);
    apoint12 = findViewById(R.id.apoint);
    ppname = pname.getText().toString().trim();
    ppage = page.getText().toString().trim();
    client1 = new Client();
    databaseReference12 = database12.getInstance().getReference().child("Client");

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
                    Intent intent = new Intent (ECGTest.this,WelcomePage.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.profile) {
                    Intent intent1 = new Intent (ECGTest.this,WelcomePage.class);
                    startActivity(intent1);
                    return true;
                }

                if (id == R.id.ic_BMI) {
                    Intent intent2 = new Intent (ECGTest.this,WelcomePage.class);
                    startActivity(intent2);
                    return true;
                }

                if (id == R.id.ic_step) {
                    Intent intent3 = new Intent (ECGTest.this,WelcomePage.class);
                    startActivity(intent3);
                    return true;
                }
                if (id == R.id.ic_Settings) {
                    Intent intent4 = new Intent (ECGTest.this,WelcomePage.class);
                    startActivity(intent4);
                    return true;
                }
                if (id == R.id.ic_signout) {
                    Intent intent5 = new Intent (ECGTest.this,WelcomePage.class);
                    startActivity(intent5);
                    return true;
                }

                return false;


            }
        });

    int increment_number = 0;
    int val = ++increment_number;
    apoint12.setText(Integer.toString(val));


    savedata.setOnClickListener(new View.OnClickListener() {


        @Override
        public void onClick(View view) {
           /* myList.add(ppname);
            myList.add(ppage);
            myList.add(locationpass);
            myList.add(datepass);
            myList.add(timepass);
            databaseReference12.setValue(myList);
*/
            if(TextUtils.isEmpty(name.getText().toString()))
            {
                Toast.makeText(ECGTest.this,"Please Enter Patient Name", Toast.LENGTH_LONG).show();
            }

            else if (TextUtils.isEmpty(age.getText().toString()))
            {
                Toast.makeText(ECGTest.this,"Please Enter Patient Age", Toast.LENGTH_LONG).show();
            }
            else
            {
                client1.setName(pname.getText().toString().trim());
                client1.setAge(page.getText().toString().trim());
                client1.setAppointmentno(apoint12.getText().toString().trim());
                client1.setLocation(locationpass);
                client1.setDate(datepass);
                client1.setTime(timepass);
                Toast.makeText(ECGTest.this,"Values Stored Sucessfully", Toast.LENGTH_LONG).show();

            }


            databaseReference12.push().setValue(client1);
        }
    });



    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
    {
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
    }
    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

    Button okay = dialog.findViewById(R.id.btn_okay);
    Button cancel = dialog.findViewById(R.id.btn_cancel);
    dialog.setCancelable(false);

    okay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent (ECGTest.this,WelcomePage.class);
            startActivity(intent);
        }


    });
    cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.dismiss();
        }
    });

    cancelbooking = findViewById(R.id.cancel);

    cancelbooking.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.show();
        }
    });

        dialog1 = new Dialog(ECGTest.this);
        dialog1.setContentView(R.layout.custombackgroundhome);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            dialog1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog1.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        Button okayhome = dialog1.findViewById(R.id.btn_okay_home);
        Button cancelhome = dialog1.findViewById(R.id.btn_cancel_home);
        dialog1.setCancelable(false);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ECGTest.this,WelcomePage.class);
                startActivity(intent);
            }


        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        gotohome = findViewById(R.id.returnhome);

        gotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.show();
            }
        });

        okayhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ECGTest.this,WelcomePage.class);
                startActivity(intent);
            }
        });

        cancelhome.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });

//Pay Button//
        pay = findViewById(R.id.pay);

        name = findViewById(R.id.PersonName);
        age = findViewById(R.id.ageECG);

        dialog2 = new Dialog(ECGTest.this);
        dialog2.setContentView(R.layout.custombackgroundpaynow);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            dialog2.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog2.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        Button okaypay = dialog2.findViewById(R.id.btn_okay_pay);
        Button cancelpay = dialog2.findViewById(R.id.btn_cancel_pay);
        dialog2.setCancelable(false);


        okaypay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ECGTest.this,WelcomePage.class);
                startActivity(intent);
            }
        });

        cancelpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.dismiss();
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText().toString()))
                {
                    Toast.makeText(ECGTest.this,"Please Enter Patient Name", Toast.LENGTH_LONG).show();
                }

                else if (TextUtils.isEmpty(age.getText().toString()))
                {
                    Toast.makeText(ECGTest.this,"Please Enter Patient Age", Toast.LENGTH_LONG).show();
                }
                else
                {
                    dialog1.show();

                }
            }
        });
    }
}