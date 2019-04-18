package edu.eci.cosw.APIApp.utils;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("auth")
    Call<Token> attemptLogin(@Body LoginWrapper user);

}
