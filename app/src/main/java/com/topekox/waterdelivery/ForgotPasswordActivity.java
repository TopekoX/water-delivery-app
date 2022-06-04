package com.topekox.waterdelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editTextEmail = findViewById(R.id.editTextForgotPasswordEmail);
        progressBar = findViewById(R.id.progressBarForgotPassword);
        mAuth = FirebaseAuth.getInstance();
    }
    
    public void forgotPasswordResetButtonPressed(View view) {
        resetPassword();
    }

    private void resetPassword() {

        String email = editTextEmail.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please Enter Valid Email");
            editTextEmail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    toastMessage("Please Check Your Email to Reset Password");
                    Intent intent = new Intent(ForgotPasswordActivity.this, SignInActivity.class);
                    startActivity(intent);
                } else {
                    toastMessage("Failed to Reset Password");
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(ForgotPasswordActivity.this,
                message, Toast.LENGTH_LONG).show();
    }
}