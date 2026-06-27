package com.example.foojiclient;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://cjrki-212-8-49-63.free.pinggy.net"; // Use 10.0.2.2 if running on Android emulator

    private static Retrofit retrofit;
    public static void init(Context context) {

        SessionManager sessionManager =
                new SessionManager(context.getApplicationContext());

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(sessionManager))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static WordApiService getWordApiService() {
        if (retrofit == null) {
            throw new IllegalStateException("ApiClient.init() was not called");
        }
        return retrofit.create(WordApiService.class);
    }
}
