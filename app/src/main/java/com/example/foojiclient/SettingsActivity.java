package com.example.foojiclient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String modeCurrent = prefs.getString("mode", "mixed");
        int vocabularyCurrent = prefs.getInt("vocabulary", 5);

        switch (vocabularyCurrent){
            case 1:
                findViewById(R.id.button_n1).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        com.google.android.material.R.color.material_deep_teal_500));
                break;
            case 2:
                findViewById(R.id.button_n2).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        com.google.android.material.R.color.material_deep_teal_500));
                break;
            case 3:
                findViewById(R.id.button_n3).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        com.google.android.material.R.color.material_deep_teal_500));
                break;
            case 4:
                findViewById(R.id.button_n4).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        com.google.android.material.R.color.material_deep_teal_500));
                break;
            case 5:
                findViewById(R.id.button_n5).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        com.google.android.material.R.color.material_deep_teal_500));
                break;
        }

        if(modeCurrent.equals("translate")){
            findViewById(R.id.button_translation).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
            findViewById(R.id.button_hiragana).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    R.color.purple_700));
            findViewById(R.id.button_mixed).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    R.color.purple_700));
        }else if(modeCurrent.equals("hiragana")){
            findViewById(R.id.button_hiragana).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
            findViewById(R.id.button_mixed).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    R.color.purple_700));
            findViewById(R.id.button_translation).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    R.color.purple_700));
        }else{
            findViewById(R.id.button_mixed).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
            findViewById(R.id.button_translation).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    R.color.purple_700));
            findViewById(R.id.button_hiragana).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    R.color.purple_700));

        }
    }

    public void onTranslateClick(View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.edit().putString("mode", "translate").apply();
        findViewById(R.id.button_translation).setBackgroundTintList(ContextCompat.getColorStateList(this,
                com.google.android.material.R.color.material_deep_teal_500));
        findViewById(R.id.button_hiragana).setBackgroundTintList(ContextCompat.getColorStateList(this,
                R.color.purple_700));
        findViewById(R.id.button_mixed).setBackgroundTintList(ContextCompat.getColorStateList(this,
                R.color.purple_700));
    }

    public void onHiraganaClick(View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.edit().putString("mode", "hiragana").apply();
        findViewById(R.id.button_hiragana).setBackgroundTintList(ContextCompat.getColorStateList(this,
                com.google.android.material.R.color.material_deep_teal_500));
        findViewById(R.id.button_mixed).setBackgroundTintList(ContextCompat.getColorStateList(this,
                R.color.purple_700));
        findViewById(R.id.button_translation).setBackgroundTintList(ContextCompat.getColorStateList(this,
                R.color.purple_700));
    }
    public void onMixedClick(View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.edit().putString("mode", "mixed").apply();
        findViewById(R.id.button_mixed).setBackgroundTintList(ContextCompat.getColorStateList(this,
                com.google.android.material.R.color.material_deep_teal_500));
        findViewById(R.id.button_translation).setBackgroundTintList(ContextCompat.getColorStateList(this,
                R.color.purple_700));
        findViewById(R.id.button_hiragana).setBackgroundTintList(ContextCompat.getColorStateList(this,
                R.color.purple_700));
    }

    //TO DO: need to persist every vocabulary separately and enable/disable button styling

    public void onN5Click(View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.edit().putInt("vocabulary", 5).apply();
    }

    public void onN4Click(View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.edit().putInt("vocabulary", 4).apply();
    }

    public void onN3Click(View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.edit().putInt("vocabulary", 3).apply();
    }

    public void onN2Click(View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.edit().putInt("vocabulary", 2).apply();
    }

    public void onN1Click(View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.edit().putInt("vocabulary", 1).apply();
    }
}
