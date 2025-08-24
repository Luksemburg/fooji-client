package com.example.foojiclient;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner spinner = findViewById(R.id.spinnerGender);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_items,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private boolean validateForm() {
        boolean valid = true;

        EditText username = findViewById(R.id.textViewUsername);
        EditText email = findViewById(R.id.textViewEmail);
        EditText phone = findViewById(R.id.editTextTextPhone);
        EditText inviteCode = findViewById(R.id.textInviteCode);
        EditText password = findViewById(R.id.editTextTextPassword);
        EditText confirmPassword = findViewById(R.id.editTextTextConfirmPassword);

        if (username.getText().toString().trim().isEmpty()) {
            username.setError("Required");
            valid = false;
        }
        if (password.getText().toString().trim().isEmpty()) {
            password.setError("Required");
            valid = false;
        }
        if (confirmPassword.getText().toString().trim().isEmpty()) {
            confirmPassword.setError("Required");
            valid = false;
        }
        if (email.getText().toString().trim().isEmpty()) {
            email.setError("Required");
            valid = false;
        }
        if (phone.getText().toString().trim().isEmpty()) {
            phone.setError("Required");
            valid = false;
        }
        if (inviteCode.getText().toString().trim().isEmpty()) {
            inviteCode.setError("Required");
            valid = false;
        }
        if(!password.getText().toString().trim().equals(confirmPassword.getText().toString().trim())){
            confirmPassword.setError("Passwords do not match");
            password.setError("Passwords do not match");
            valid = false;
        }

        return valid;
    }

    public void onRegister(){
        if (validateForm()) {
            // Submit form
        }
    }

}
