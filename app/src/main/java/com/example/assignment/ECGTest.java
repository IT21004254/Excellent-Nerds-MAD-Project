package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ECGTest extends AppCompatActivity {

    TextView getdate,gettime,getlocation;
    String datepass,timepass, locationpass;
    private Button cancelbooking, gotohome, pay;
    Dialog dialog, dialog1, dialog2;
    EditText name,age;


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

    dialog = new Dialog(ECGTest.this);
    dialog.setContentView(R.layout.custombackground);
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