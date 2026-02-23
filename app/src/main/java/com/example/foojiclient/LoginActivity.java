package com.example.foojiclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.IOException;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

                    SharedPreferences preferences = getSharedPreferences("FooJiPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("token", token);

                    assert receivedUser != null;
                    editor.putString("username", receivedUser.getUsername());
                    editor.putString("email", receivedUser.getEmail());
                    editor.putString("phone", String.valueOf(receivedUser.getPhone()));
                    editor.putString("location", receivedUser.getLocation());
                    editor.putString("gender", receivedUser.getGender());
                    editor.apply();

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
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("212337228143-6djukr8r7iromq22cv5g74434bu381gl.apps.googleusercontent.com")
                .build();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001) {
            Task<GoogleSignInAccount> task =
                    GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account =
                        task.getResult(ApiException.class);

                String idToken = account.getIdToken();

                sendTokenToBackend(idToken);

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendTokenToBackend(String idToken) {

        //OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("idToken", idToken)
                .build();

        /*Request request = new Request.Builder()
                .url("...Server URL")
                .post(body)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {*/

        ApiClient.getWordApiService().googleLogin(body).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                // handle server response
                Log.i("googleLogin", "===== googleLogin ===== " + response.body());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }

            /*@Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                // handle server response
            }*/

        });
    }
}
