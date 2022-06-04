package com.topekox.waterdelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText editTextUserEmail, editTextPassword;
    private TextView textViewRegister, textViewForgotPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextUserEmail = findViewById(R.id.editTextSignInUserEmail);
        editTextPassword = findViewById(R.id.editTextSigInPassword);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewRegister = findViewById(R.id.textViewRegister);
        progressBar = findViewById(R.id.progressBarSignin);

        mAuth = FirebaseAuth.getInstance();
    }

    public void txtSignInForgotPasswordClicked(View view) {
        Intent intent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    public void txtSignInRegisterClicked(View view) {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void buttonSignInClicked(View view) {
        String email = editTextUserEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextUserEmail.setError("Please Enter Valid Email");
            editTextUserEmail.requestFocus();
        }
        if (password.length() < 6) {
            editTextPassword.setError("Please Enter Password containing at least 6 characters");
            editTextPassword.requestFocus();
        }

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length() >= 6) {
            progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);
                        toastMessage("User Has Successfully Signed In");

                        startActivity(new Intent(SignInActivity.this, DashboardActivity.class));
                    } else {
                        progressBar.setVisibility(View.GONE);
                        toastMessage("User is Failed Signed In");
                    }
                }
            });
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(SignInActivity.this,
                message, Toast.LENGTH_LONG).show();
    }
}