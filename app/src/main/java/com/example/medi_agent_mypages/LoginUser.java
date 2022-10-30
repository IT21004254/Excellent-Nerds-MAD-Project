package com.example.medi_agent_mypages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUser extends AppCompatActivity implements View.OnClickListener {

    private TextView register, forgotPassword;
    private EditText editTextEmail,editTextPassword;
    private Button signin;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);


        register = (TextView) findViewById(R.id.lblregisteruser);
        register.setOnClickListener(this);

        signin = (Button) findViewById(R.id.btnlogin);
        signin.setOnClickListener(this);

        forgotPassword = (TextView) findViewById(R.id.lblforgotpassword);
        forgotPassword.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.tbemailaddress);
        editTextPassword = (EditText) findViewById(R.id.tbpassword);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onClick(View v) {

        switch (v.getId()){

            case R.id.lblregisteruser:
                startActivity(new Intent(this,MainActivity.class));
                break;

            case R.id.btnlogin:
                userLogin();
                break;

            case R.id.lblforgotpassword:
                startActivity(new Intent(this,ForgotPassword.class));
                break;
        }

    }

    private void userLogin(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){

            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){

            editTextPassword.setError("Min password length is 6 Characters!");
            editTextPassword.requestFocus();
            return;
        }

       // progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    startActivity(new Intent(LoginUser.this,Home.class));

                }else {
                    Toast.makeText(LoginUser.this,"Failed to Login! Please Check your Credentials.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}