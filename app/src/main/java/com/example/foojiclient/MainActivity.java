package com.example.foojiclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.foojiclient.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Word correct;
    private int intCorrect;

    private static int correctCounter = 0;
    private static int incorrectCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshCorrectCounter();
        refreshIncorrectCounter();

        //BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);*/
        //NavigationUI.setupWithNavController(binding.navView, navController);

        int lim = 5;
        Random random = new Random();

        Call<List<Word>> call = ApiClient.getWordApiService().getRandomWords(lim);
        call.enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(@NonNull Call<List<Word>> call, @NonNull Response<List<Word>> response) {
                if (response.isSuccessful()) {
                    List<Word> words = response.body();
                    // Do something with words
                    TextView textView = findViewById(R.id.textView);
                    StringBuilder builder = new StringBuilder();
                    builder.append("Select correct translation:\n");
                    assert words != null;

                    intCorrect = random.nextInt(lim);
                    correct = words.get(intCorrect);
                    builder.append(correct.getKanji());
                    if(!correct.getKanji().equals(correct.getHiragana())){
                        builder.append("\n").append("[").append(correct.getHiragana())
                                .append("]");
                    }
                    textView.setText(builder.toString());

                    Button button1 = findViewById(R.id.button1);
                    button1.setText(words.get(0).getEnglish());
                    button1.setEnabled(true);
                    Button button2 = findViewById(R.id.button2);
                    button2.setText(words.get(1).getEnglish());
                    button2.setEnabled(true);
                    Button button3 = findViewById(R.id.button3);
                    button3.setText(words.get(2).getEnglish());
                    button3.setEnabled(true);
                    Button button4 = findViewById(R.id.button4);
                    button4.setText(words.get(3).getEnglish());
                    button4.setEnabled(true);
                    Button button5 = findViewById(R.id.button5);
                    button5.setText(words.get(4).getEnglish());
                    button5.setEnabled(true);
                }else{
                    Intent intent = new Intent(MainActivity.this, ErrorActivity.class);
                    Log.e("NETWORK RESPONSE FAILED", response.code() + response.message());
                    intent.putExtra("error_message", response.code() + response.message());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {
                Intent intent = new Intent(MainActivity.this, ErrorActivity.class);
                // Handle error
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.navigation_profile) {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.navigation_statistic) {
            Toast.makeText(this, "Statistics clicked", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.navigation_settings) {
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            finish();
            startActivity(intent);
            return true;
        }else if (id == R.id.navigation_about) {
            Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            finish();
            startActivity(intent);
            return true;
        }else if (id == R.id.navigation_exit) {
            Toast.makeText(this, "Exit clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClick1(View view) {
        Log.d("CLICK", "=========== onClick1: " + correct.getId());
        Button button = findViewById(R.id.button1);
        if(intCorrect + 1 == 1){
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.material_deep_teal_500));
            correctCounter++;
            refreshCorrectCounter();
        }else{
            setGreenToCorrectButton();
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
            incorrectCounter++;
            refreshIncorrectCounter();
        }
        enableNextButton();
    }

    public void onClick2(View view) {
        Log.d("CLICK", "=========== onClick2: " + correct.getId());
        Button button = findViewById(R.id.button2);
        if(intCorrect + 1 == 2){
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.material_deep_teal_500));
            correctCounter++;
            refreshCorrectCounter();
        }else{
            setGreenToCorrectButton();
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
            incorrectCounter++;
            refreshIncorrectCounter();
        }
        enableNextButton();
    }

    public void onClick3(View view) {
        Log.d("CLICK", "=========== onClick3: " + correct.getId());
        Button button = findViewById(R.id.button3);
        if(intCorrect + 1 == 3){
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.material_deep_teal_500));
            correctCounter++;
            refreshCorrectCounter();
        }else{
            setGreenToCorrectButton();
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
            incorrectCounter++;
            refreshIncorrectCounter();
        }
        enableNextButton();
    }

    public void onClick4(View view) {
        Log.d("CLICK", "=========== onClick4: " + correct.getId());
        Button button = findViewById(R.id.button4);
        if(intCorrect + 1 == 4){
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.material_deep_teal_500));
            correctCounter++;
            refreshCorrectCounter();
        }else{
            setGreenToCorrectButton();
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
            incorrectCounter++;
            refreshIncorrectCounter();
        }
        enableNextButton();
    }

    public void onClick5(View view) {
        Log.d("CLICK", "=========== onClick5: " + correct.getId());
        Button button = findViewById(R.id.button5);
        if(intCorrect + 1 == 5){
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.material_deep_teal_500));
            correctCounter++;
            refreshCorrectCounter();
        }else{
            setGreenToCorrectButton();
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
            incorrectCounter++;
            refreshIncorrectCounter();
        }
        enableNextButton();
    }

    private void setGreenToCorrectButton() {
        switch (intCorrect + 1){
            case 1:
                findViewById(R.id.button1).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        com.google.android.material.R.color.material_deep_teal_500));
                break;
            case 2:
                findViewById(R.id.button2).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        com.google.android.material.R.color.material_deep_teal_500));
                break;
            case 3:
                findViewById(R.id.button3).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        com.google.android.material.R.color.material_deep_teal_500));
                break;
            case 4:
                findViewById(R.id.button4).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        com.google.android.material.R.color.material_deep_teal_500));
                break;
            case 5:
                findViewById(R.id.button5).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        com.google.android.material.R.color.material_deep_teal_500));
                break;
        }
    }

    public void reset(View view){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void refreshIncorrectCounter(){
        TextView textView = findViewById(R.id.incorrectCounter);
        textView.setText(String.valueOf(incorrectCounter));
    }

    private void refreshCorrectCounter(){
        TextView textView = findViewById(R.id.correctCounter);
        textView.setText(String.valueOf(correctCounter));
    }

    private void enableNextButton(){
        Button button = findViewById(R.id.buttonNext);
        button.setEnabled(true);
        button.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.yellow));

        Button button1 = findViewById(R.id.button1);
        //button1.setBackgroundColor(0x5555);
        button1.setEnabled(false);
        Button button2 = findViewById(R.id.button2);
        button2.setEnabled(false);
        Button button3 = findViewById(R.id.button3);
        button3.setEnabled(false);
        Button button4 = findViewById(R.id.button4);
        button4.setEnabled(false);
        Button button5 = findViewById(R.id.button5);
        button5.setEnabled(false);
    }
}