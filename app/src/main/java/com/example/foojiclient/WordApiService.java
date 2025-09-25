package com.example.foojiclient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import java.util.List;

public interface WordApiService {

    @GET("words/random")
    Call<List<Word>> getRandomWords(@Query("limit") int limit, @Query("mode") String mode,
                                    @Query("vocabulary") String vocabulary);
    @POST("login/login")
    Call<UserDTO> login(@Body LoginRequest loginRequest);

    @POST("login/register")
    Call<UserDTO> registerUser(@Body UserDTO user);
}
