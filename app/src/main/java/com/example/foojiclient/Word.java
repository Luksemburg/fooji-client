package com.example.foojiclient;
import java.io.Serializable;

public class Word implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    private String kanji;
    private String hiragana;
    private String english;

    public Long getId() {
        return id;
    }

    public String getKanji() {
        return kanji;
    }

    public String getEnglish() {
        return english;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
