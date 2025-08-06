package com.example.foojiclient;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import java.util.List;

public interface WordApiService {

    @GET("words/random")
    Call<List<Word>> getRandomWords(@Query("limit") int limit, @Query("mode") String mode,
                                    @Query("vocabulary") String vocabulary);

    @POST("login/login")
    Call<List<Word>> login(@Query("login") String login, @Query("password") String password);
}
