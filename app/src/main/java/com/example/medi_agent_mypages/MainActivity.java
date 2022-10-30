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
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mainTitle;
    private EditText editTextFullname,editTextDOB,editTextEmail,editTextPassword;
    private Button registerUser;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mainTitle = (TextView) findViewById(R.id.lblRegisterUserPageTitle);
        mainTitle.setOnClickListener(this);
        registerUser = (Button) findViewById(R.id.btnRegisterUser);
        registerUser.setOnClickListener(this);


        editTextFullname = (EditText) findViewById(R.id.editTextName);
        editTextDOB = (EditText) findViewById(R.id.editTextDOB);
        editTextEmail = (EditText) findViewById(R.id.editTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

           // case R.id.lblRegisterUserPageTitle:
             //   startActivity(new Intent(this, mainActivity2.class) );
               // break;
            case R.id.btnRegisterUser:
                registerUser();}}
    private void registerUser() {

        String fullname = editTextFullname.getText().toString().trim();
        String dob = editTextDOB.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(fullname.isEmpty()){
            editTextFullname.setError("Fullname is required");
            editTextFullname.requestFocus();
            return;
        }

        if (dob.isEmpty()){
            editTextDOB.setError("Age is required");
            editTextDOB.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;

        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {

            editTextPassword.setError("Password should be longer than 6 characters");
            editTextPassword.requestFocus();
            return;
        }




        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            User user = new User(fullname,dob,email,password);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(MainActivity.this, "User Has been Registered Successfully", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.VISIBLE);
                                            }

                                            else
                                            {
                                                Toast.makeText(MainActivity.this, "Failed to Register User, Try Again", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Failed to Register User", Toast.LENGTH_LONG).show();

                        }



                    }
                });
    }}
