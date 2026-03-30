package com.example.foojiclient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
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
        String gender = prefs.getString("gender", null);

        EditText usernameView = findViewById(R.id.edit_username);
        usernameView.setText(username);
        EditText emailView = findViewById(R.id.edit_email);
        emailView.setText(email);
        EditText phoneView = findViewById(R.id.edit_phone);
        phoneView.setText(phone);
        EditText locationView = findViewById(R.id.edit_location);
        locationView.setText(location);

        Spinner genderSpinner = findViewById(R.id.edit_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_items,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        if(gender != null){
            setSpinnerSelectionByText(genderSpinner, gender);
        }else{
            genderSpinner.setSelection(0);
        }


    }

    public void onSaveProfile(View view){

    }

    public void onCancelProfile(View view){

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
