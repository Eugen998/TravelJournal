package com.example.eugen.traveljournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class signup extends AppCompatActivity implements View.OnClickListener {

    EditText inputUsername;
    EditText inputPassword;
    EditText inputEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputUsername = findViewById(R.id.signup_name);
        inputPassword = findViewById(R.id.signup_password);
        inputEmail = findViewById(R.id.signup_email);

        findViewById(R.id.link_login).setOnClickListener(this);
        findViewById(R.id.btn_signup).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    private void registerUser() {
        String username = inputUsername.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty()) {
            inputEmail.setError("Please enter input Email");
            inputEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("Email input is not valid");
            inputEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            inputPassword.setError("Please enter input Password");
            inputPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            inputPassword.setError("Password is too short");
            inputPassword.requestFocus();
            return;
        }
        if (username.isEmpty()) {
            inputUsername.setError("Please choose an username");
            inputUsername.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User registered succesfully", Toast.LENGTH_LONG).show();
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                registerUser();
                startActivity(new Intent(this,DestinationsList.class));
                break;
            case R.id.link_login:
                startActivity(new Intent(this, signin.class));
                break;
        }

    }
}
