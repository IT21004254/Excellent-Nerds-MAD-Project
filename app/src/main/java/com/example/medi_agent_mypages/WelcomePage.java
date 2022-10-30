package com.example.medi_agent_mypages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity implements View.OnClickListener {


    private Button signUp,logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);


        signUp = (Button) findViewById(R.id.button3);
        signUp.setOnClickListener(this);

        logIn = (Button) findViewById(R.id.button4);
        logIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button3:
                startActivity(new Intent(this,MainActivity.class));
                break;

            case R.id.button4:
                startActivity(new Intent(this,LoginUser.class));
                break;
    }
}}