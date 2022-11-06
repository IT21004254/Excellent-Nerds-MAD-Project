package com.example.mediagent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText name, specialist, email, hospital, durl;
    Button btnAdd, btnBack;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle("Doctor Channeling - OPD");

        name = (EditText) findViewById(R.id.txtName);
        specialist = (EditText) findViewById(R.id.txtSpecialist);
        email = (EditText) findViewById(R.id.txtemail);
        hospital = (EditText) findViewById(R.id.txtHospital);
        durl = (EditText) findViewById(R.id.txtImageUrl);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBack);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(AddActivity.this, R.id.txtHospital,"^[0-9]{10}$", R.string.err_tel);
        btnAdd.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if (awesomeValidation.validate()) {
                                              insertData();
                                              clearAll();

                                          } else {
                                              Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                                          }
                                      }
                                  });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("email",email.getText().toString());
        map.put("specialist",specialist.getText().toString());
        map.put("hospital",hospital.getText().toString());
        map.put("durl",durl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("doctors").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Data Insert Successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddActivity.this, "Error While Insertion.", Toast.LENGTH_SHORT).show();

                    }
                });
    }
    private void clearAll()
    {
        name.setText("");
        email.setText("");
        specialist.setText("");
        hospital.setText("");
        durl.setText("");


    }
}