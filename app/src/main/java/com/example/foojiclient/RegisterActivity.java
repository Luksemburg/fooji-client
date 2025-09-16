package com.example.foojiclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText phone;
    EditText inviteCode;
    EditText password;
    EditText confirmPassword;
    EditText location;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.textViewUsername);
        email = findViewById(R.id.textViewEmail);
        phone = findViewById(R.id.editTextTextPhone);
        inviteCode = findViewById(R.id.textInviteCode);
        password = findViewById(R.id.editTextTextPassword);
        confirmPassword = findViewById(R.id.editTextTextConfirmPassword);
        location = findViewById(R.id.textViewLocation);
        spinner = findViewById(R.id.spinnerGender);

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

    public void onRegister(View view){
        if (validateForm()) {
            // Submit form
            UserDTO newUser = new UserDTO();
            newUser.setUsername(String.valueOf(username.getText()));
            newUser.setEmail(String.valueOf(email.getText()));
            newUser.setPassword(String.valueOf(password.getText()));
            newUser.setPhone(Long.parseLong(String.valueOf(phone.getText())));
            newUser.setGender(String.valueOf(spinner.getSelectedItem()));
            newUser.setLocation(String.valueOf(location.getText()));
            newUser.setInviteCode(Long.parseLong(String.valueOf(inviteCode.getText())));

            ApiClient.getWordApiService().registerUser(newUser).enqueue(new Callback<UserDTO>() {
                @Override
                public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                    if (response.isSuccessful()) {
                        // handle success
                        UserDTO createdUser = response.body();
                        Log.i("REG", "onResponse: " + response);
                        //TODO: save user for view in profile info
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<UserDTO> call, Throwable t) {
                    // Handle error
                    // TODO add more logical handling
                    Intent intent = new Intent(RegisterActivity.this, ErrorActivity.class);
                    if(t.getLocalizedMessage() != null) {
                        Log.e("NETWORK ERROR", t.getLocalizedMessage());
                        intent.putExtra("error_message", t.getLocalizedMessage());
                    }else{
                        Log.e("NETWORK ERROR", "UNKNOWN ERROR");
                        intent.putExtra("error_message", "Server is not responding. Please try again later.");
                    }
                    startActivity(intent);
                }
            });
        }
    }

}
