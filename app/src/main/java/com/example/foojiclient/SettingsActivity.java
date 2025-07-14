package com.example.foojiclient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String modeCurrent = prefs.getString("mode", "mixed");
        String vocabularyCurrent = prefs.getString("vocabulary", "false,false,false,false,true");

        String[] parts = vocabularyCurrent.split(",");
        boolean[] vocabularyActive = new boolean[parts.length];
        for(int i = 0; i < parts.length; i++){
            vocabularyActive[i] = Boolean.parseBoolean(parts[i].trim());
        }

        if(vocabularyActive[0]){
            findViewById(R.id.button_n1).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
        }
        if(vocabularyActive[1]){
            findViewById(R.id.button_n2).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
        }
        if(vocabularyActive[2]){
            findViewById(R.id.button_n3).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
        }
        if(vocabularyActive[3]){
            findViewById(R.id.button_n4).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
        }
        if(vocabularyActive[4]){
            findViewById(R.id.button_n5).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
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

    public void onOkClick(View view){
        finish();
    }

    public void onN5Click(View view){
        if(!isVocabularyActive(5)) {
            findViewById(R.id.button_n5).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
            setVocabularyToActive(5);
        }else{
            if(setVocabularyToInactive(5)) {
                findViewById(R.id.button_n5).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        R.color.purple_700));
            }
        }
    }


    public void onN4Click(View view){
        if(!isVocabularyActive(4)) {
            findViewById(R.id.button_n4).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
            setVocabularyToActive(4);
        }else{
            if(setVocabularyToInactive(4)) {
                findViewById(R.id.button_n4).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        R.color.purple_700));
            }
        }
    }

    public void onN3Click(View view){
        if(!isVocabularyActive(3)) {
            findViewById(R.id.button_n3).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
            setVocabularyToActive(3);
        }else{
            if(setVocabularyToInactive(3)) {
                findViewById(R.id.button_n3).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        R.color.purple_700));
            }
        }
    }

    public void onN2Click(View view){
        if(!isVocabularyActive(2)) {
            findViewById(R.id.button_n2).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
            setVocabularyToActive(2);
        }else {
            if(setVocabularyToInactive(2)) {
                findViewById(R.id.button_n2).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        R.color.purple_700));
            }
        }
    }

    public void onN1Click(View view){
        if(!isVocabularyActive(1)) {
            findViewById(R.id.button_n1).setBackgroundTintList(ContextCompat.getColorStateList(this,
                    com.google.android.material.R.color.material_deep_teal_500));
            setVocabularyToActive(1);
        }else{
            if(setVocabularyToInactive(1)) {
                findViewById(R.id.button_n1).setBackgroundTintList(ContextCompat.getColorStateList(this,
                        R.color.purple_700));
            }
        }
    }

    private boolean setVocabularyToInactive(int vocabulary) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String vocabularyCurrent = prefs.getString("vocabulary", "false,false,false,false,true");
        String[] parts = vocabularyCurrent.split(",");
        boolean isAllVocabulariesInactive = true;

        for(int i = 0; i < parts.length; i++){
            if(vocabulary - 1 != i){
                if(Boolean.parseBoolean(parts[i])){
                    isAllVocabulariesInactive = false;
                    break;
                }
            }
        }

        if(!isAllVocabulariesInactive) {
            parts[vocabulary - 1] = "false";
            prefs.edit().putString("vocabulary", String.join(",", parts)).apply();
            return true;
        }else{
            Toast.makeText(this, "All vocabularies can not be off!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void setVocabularyToActive(int vocabulary) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String vocabularyCurrent = prefs.getString("vocabulary", "false,false,false,false,true");
        String[] parts = vocabularyCurrent.split(",");
        parts[vocabulary - 1] = "true";
        prefs.edit().putString("vocabulary", String.join(",", parts)).apply();
    }

    private boolean isVocabularyActive(int vocabulary) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String vocabularyCurrent = prefs.getString("vocabulary", "false,false,false,false,true");
        String[] parts = vocabularyCurrent.split(",");
        return Boolean.parseBoolean(parts[vocabulary - 1]);
    }
}
