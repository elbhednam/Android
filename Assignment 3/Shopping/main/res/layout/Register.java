package com.example.amandabakalarczyk.booktracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private FirebaseAuth auth;
    private static final String TAG = Register.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        auth = FirebaseAuth.getInstance();
    }

    // Go back to login activity
    public void onClickLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    // Register new user
    public void onClickRegister(View view) {

        if (isInvalid()) {
            Toast.makeText(this, "Please enter valid information for all fields", Toast.LENGTH_LONG).show();
        }

        else {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            (auth.createUserWithEmailAndPassword(email, password))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Register.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }
    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            // User is signed in
            Log.d(TAG, "onAuthStateChanged:signed_in:" + currentUser.getUid());
            toastMessage("Successfully signed in with: " + currentUser.getEmail());

        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
            toastMessage("Successfully signed out.");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    // Check if information entered is valid
    // TODO: Check for valid email and password
    private boolean isInvalid() {
        return (etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty());
    }
}