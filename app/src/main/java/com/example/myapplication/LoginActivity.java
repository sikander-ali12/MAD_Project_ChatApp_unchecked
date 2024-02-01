package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends Activity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        // Find the TextView with ID "need_new_account_link"
        TextView needAccountTextView = findViewById(R.id.need_new_account_link);

        // Set an OnClickListener to the TextView
        needAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the TextView is clicked, start the RegistrationActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Find views in the login layout
        EditText emailEditText = findViewById(R.id.login_email);
        EditText passwordEditText = findViewById(R.id.login_password);
        Button loginButton = findViewById(R.id.login_button);

        // Set OnClickListener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get email and password from the EditText fields
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Perform Firebase login
                loginUser(email, password);
            }
        });
    }

    private void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Login success
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                        // Get the authenticated user
                        FirebaseUser user = firebaseAuth.getCurrentUser();

                        if (user != null) {
                            // Example: Go to the ChatList activity after successful login
                            Intent intent = new Intent(LoginActivity.this, ChatList.class);
                            // Pass the user's email as currentUsername
                            intent.putExtra("currentUsername", user.getEmail());
                            startActivity(intent);
                            finish(); // Finish LoginActivity to remove it from the back stack
                        }
                    } else {
                        // If login fails, display a message to the user.
                        Toast.makeText(LoginActivity.this, "Login failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

