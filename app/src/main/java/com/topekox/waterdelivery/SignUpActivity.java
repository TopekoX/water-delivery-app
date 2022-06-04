package com.topekox.waterdelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextPhoneNumber;
    private EditText editTextEmail;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEmail = findViewById(R.id.editTextEmailAddress);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        progressBar = findViewById(R.id.progressBar);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUpButtonClicked(View view) {
        String username = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        if (username.isEmpty()) {
            editTextUserName.setError("Please enter username");
            editTextUserName.requestFocus();
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Please enter password");
            editTextPassword.requestFocus();
        }
        if (phoneNumber.isEmpty()) {
            editTextPhoneNumber.setError("Please enter phone number");
            editTextPhoneNumber.requestFocus();
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Please enter email");
            editTextEmail.requestFocus();
        }

        if (!username.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty() && !password.isEmpty()) {

            progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                User user = new User(username, password, email, phoneNumber);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    toastMessage("User Registered Successfully");
                                                    progressBar.setVisibility(View.GONE);
                                                } else {
                                                    toastMessage("User Failed to Register");
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            }
                                        });
                            } else {
                                toastMessage("User Failed to Register");
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(SignUpActivity.this,
                message, Toast.LENGTH_LONG).show();
    }
}