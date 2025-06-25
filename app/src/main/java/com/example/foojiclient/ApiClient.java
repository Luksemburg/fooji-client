package com.example.foojiclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://rnzbr-91-195-91-229.a.free.pinggy.link/"; // Use 10.0.2.2 if running on Android emulator

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static WordApiService getWordApiService() {
        return retrofit.create(WordApiService.class);
    }
}
