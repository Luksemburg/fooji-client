package com.example.foojiclient;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //NavigationUI.setupWithNavController(binding.navView, navController);

        int lim = 4;
        Random random = new Random();

        Call<List<Word>> call = ApiClient.getWordApiService().getRandomWords(lim);
        call.enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                if (response.isSuccessful()) {
                    List<Word> words = response.body();
                    // Do something with words
                    TextView textView = findViewById(R.id.textView);
                    StringBuilder builder = new StringBuilder();
                    builder.append("Select the correct translation of the word:\n");
                    assert words != null;
                    /*for (Word word : words) {
                        builder.append("English: ").append(word.getEnglish()).append("\n")
                                .append("Kanji: ").append(word.getKanji()).append("\n")
                                .append("Hiragana: ").append(word.getHiragana()).append("\n\n");
                    }*/
                    intCorrect = random.nextInt(lim) + 1;
                    correct = words.get(intCorrect);
                    builder.append(correct.getKanji());
                    textView.setText(builder.toString());

                    Button button1 = findViewById(R.id.button1);
                    button1.setText(words.get(0).getEnglish());
                    Button button2 = findViewById(R.id.button2);
                    button2.setText(words.get(1).getEnglish());
                    Button button3 = findViewById(R.id.button3);
                    button3.setText(words.get(2).getEnglish());
                    Button button4 = findViewById(R.id.button4);
                    button4.setText(words.get(3).getEnglish());
                }
            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {
                // Handle error
            }

        });
    }

    public void onClick1(View view) {
        Log.d("CLICK", "=========== onClick1: " + correct.getId());
        Button button = findViewById(R.id.button1);
        if(intCorrect + 1 == 1){
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.material_deep_teal_500));
            correctCounter++;
            refreshCorrectCounter();
        }else{
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
            incorrectCounter++;
            refreshIncorrectCounter();
        }
    }

    public void onClick2(View view) {
        Log.d("CLICK", "=========== onClick2: " + correct.getId());
        Button button = findViewById(R.id.button2);
        if(intCorrect + 1 == 2){
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.material_deep_teal_500));
            correctCounter++;
            refreshCorrectCounter();
        }else{
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
            incorrectCounter++;
            refreshIncorrectCounter();
        }
    }

    public void onClick3(View view) {
        Log.d("CLICK", "=========== onClick3: " + correct.getId());
        Button button = findViewById(R.id.button3);
        if(intCorrect + 1 == 3){
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.material_deep_teal_500));
            correctCounter++;
            refreshCorrectCounter();
        }else{
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
            incorrectCounter++;
            refreshIncorrectCounter();
        }
    }

    public void onClick4(View view) {
        Log.d("CLICK", "=========== onClick4: " + correct.getId());
        Button button = findViewById(R.id.button4);
        if(intCorrect + 1 == 4){
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.material_deep_teal_500));
            correctCounter++;
            refreshCorrectCounter();
        }else{
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
            incorrectCounter++;
            refreshIncorrectCounter();
        }
    }

    private void refreshIncorrectCounter(){
        TextView textView = findViewById(R.id.incorrectCounter);
        textView.setText(String.valueOf(incorrectCounter));
    }

    private void refreshCorrectCounter(){
        TextView textView = findViewById(R.id.correctCounter);
        textView.setText(String.valueOf(correctCounter));
    }
}