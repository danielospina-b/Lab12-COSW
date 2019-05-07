package edu.eci.cosw.APIApp.network.services;

import edu.eci.cosw.APIApp.network.model.LoginWrapper;
import edu.eci.cosw.APIApp.network.model.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("user/login")
    Call<Token> attemptLogin(@Body LoginWrapper user);

}
