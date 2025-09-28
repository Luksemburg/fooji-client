package com.example.foojiclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onNewRegister(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onSkipRegister(View view){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onLogin(View view){

        EditText username = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextTextPassword);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(String.valueOf(username.getText()));
        loginRequest.setPassword(String.valueOf(password.getText()));
        ApiClient.getWordApiService().login(loginRequest).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    // handle success
                    UserDTO receivedUser = response.body();
                    String token = response.headers().get("Authorization");
                    if(receivedUser != null) {
                        Log.i("LOGIN", "onResponse: " + receivedUser);
                        Log.i("LOGIN", "Token: " + token);
                    }
                    //TODO: save user for view in profile info
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                // Handle error
                // TODO add more logical handling
                Intent intent = new Intent(LoginActivity.this, ErrorActivity.class);
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

    public void onGoogleLogin(View view){

    }
}
