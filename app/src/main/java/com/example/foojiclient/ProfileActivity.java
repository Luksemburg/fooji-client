package com.example.foojiclient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences prefs = getSharedPreferences("FooJiPrefs", MODE_PRIVATE);
        String username = prefs.getString("username", " === No Data === ");
        String email = prefs.getString("email", " === No Data === ");
        String phone = prefs.getString("phone", " === No Data === ");
        String location = prefs.getString("location", " === No Data === ");
        String gender = prefs.getString("gender", " === No Data === ");

        EditText usernameView = findViewById(R.id.edit_username);
        usernameView.setText(username);
        EditText emailView = findViewById(R.id.edit_email);
        usernameView.setText(email);
        EditText phoneView = findViewById(R.id.edit_phone);
        usernameView.setText(phone);
        EditText locationView = findViewById(R.id.edit_location);
        usernameView.setText(location);

        Spinner genderView = findViewById(R.id.edit_gender);
        //TODO inflate and select an option


    }
}
