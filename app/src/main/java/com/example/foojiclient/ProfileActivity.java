package com.example.foojiclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText phone;
    EditText inviteCode;
    EditText newPassword;
    EditText confirmNewPassword;
    EditText oldPassword;
    EditText location;
    Spinner gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences prefs = getSharedPreferences("FooJiPrefs", MODE_PRIVATE);
        String usernameSaved = prefs.getString("username", " === No Data === ");
        String emailSaved = prefs.getString("email", " === No Data === ");
        String phoneSaved = prefs.getString("phone", " === No Data === ");
        String locationSaved = prefs.getString("location", " === No Data === ");
        String genderSaved = prefs.getString("gender", null);

        username = findViewById(R.id.edit_username);
        username.setText(usernameSaved);
        email = findViewById(R.id.edit_email);
        email.setText(emailSaved);
        phone = findViewById(R.id.edit_phone);
        phone.setText(phoneSaved);
        location = findViewById(R.id.edit_location);
        location.setText(locationSaved);

        //TODO make this mechanic work
        oldPassword = findViewById(R.id.edit_old_pwd);
        newPassword = findViewById(R.id.edit_new_pwd);
        confirmNewPassword = findViewById(R.id.edit_confirm_new_pwd);

        Spinner genderSpinner = findViewById(R.id.edit_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_items,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        if(gender != null){
            setSpinnerSelectionByText(genderSpinner, genderSaved);
        }else{
            genderSpinner.setSelection(0);
        }


    }

    public void onSaveProfile(View view){
        if (validateForm()) {
            // Submit form
            UserDTO newUser = new UserDTO();
            newUser.setUsername(String.valueOf(username.getText()));
            //newUser.setEmail(String.valueOf(email.getText()));
            newUser.setPassword(String.valueOf(newPassword.getText()));
            newUser.setPhone(Long.parseLong(String.valueOf(phone.getText())));
            //TODO here an error, try to register empty
            newUser.setGender(String.valueOf(gender.getSelectedItem()));
            newUser.setLocation(String.valueOf(location.getText()));
            newUser.setInviteCode(Long.parseLong(String.valueOf(inviteCode.getText())));

            ApiClient.getWordApiService().updateProfile(newUser).enqueue(new Callback<UserDTO>() {
                @Override
                public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                    if (response.isSuccessful()) {
                        // handle success
                        UserDTO createdUser = response.body();
                        String token = response.headers().get("Authorization");
                        Log.i("REG", "onResponse: " + response);

                        SharedPreferences preferences = getSharedPreferences("FooJiPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("token", token);

                        assert createdUser != null;
                        editor.putString("username", createdUser.getUsername());
                        //editor.putString("email", createdUser.getEmail());
                        editor.putString("phone", String.valueOf(createdUser.getPhone()));
                        editor.putString("location", createdUser.getLocation());
                        editor.putString("gender", createdUser.getGender());
                        editor.apply();

                        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<UserDTO> call, Throwable t) {
                    // Handle error
                    // TODO add more logical handling
                    Intent intent = new Intent(ProfileActivity.this, ErrorActivity.class);
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

    public void onCancelProfile(View view){
        finish();
    }

    private boolean validateForm() {
        boolean valid = true;

        if (username.getText().toString().trim().isEmpty()) {
            username.setError("Required");
            valid = false;
        }

        if (phone.getText().toString().trim().isEmpty()) {
            phone.setError("Required");
            valid = false;
        }

        if(!newPassword.getText().toString().trim().equals(confirmNewPassword.getText().toString().trim())){
            confirmNewPassword.setError("Passwords do not match");
            newPassword.setError("Passwords do not match");
            valid = false;
        }

        return valid;
    }

    private void setSpinnerSelectionByText(Spinner spinner, String text) {
        ArrayAdapter<?> adapter = (ArrayAdapter<?>) spinner.getAdapter();

        for (int i = 0; i < adapter.getCount(); i++) {
            Object item = adapter.getItem(i);

            if (item != null && item.toString().equals(text)) {
                spinner.setSelection(i);
                return;
            }
        }
    }
}
