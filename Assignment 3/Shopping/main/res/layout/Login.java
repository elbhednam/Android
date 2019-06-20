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

public class Login extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword;
    private static final String TAG = Login.class.getSimpleName();
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
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

    // Open Registration activity
    public void onClickRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    // Login with email and password
    public void onClickLogin(View view) {
        (auth.signInWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If successful login, open Home activity
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                        }
                        // Show failed login toast
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            toastMessage("Authentication failed.");
                            updateUI(null);
                        }
                    }
                });
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
