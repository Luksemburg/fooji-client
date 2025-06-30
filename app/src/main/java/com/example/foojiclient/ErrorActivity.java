package com.example.foojiclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ErrorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        TextView errorMessage = findViewById(R.id.error_message);
        Button retryButton = findViewById(R.id.error_retry);

        // Get message from Intent extras
        String errorText = getIntent().getStringExtra("error_message");
        errorMessage.setText(errorText != null ? errorText : "Something went wrong.");

        retryButton.setOnClickListener(v -> {
            // You can restart your main activity or perform any retry logic
            startActivity(new Intent(ErrorActivity.this, MainActivity.class));
            finish(); // Close the error screen
        });
    }
}

