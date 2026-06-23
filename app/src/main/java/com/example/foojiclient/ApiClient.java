package com.example.foojiclient;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://ohfak-212-8-49-35.run.pinggy-free.link"; // Use 10.0.2.2 if running on Android emulator

    private static SessionManager sessionManager;

    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor(sessionManager))
            .build();
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static WordApiService getWordApiService() {
        return retrofit.create(WordApiService.class);
    }
}
