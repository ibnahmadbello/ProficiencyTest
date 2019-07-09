package com.example.proficiencytest.network;

import com.example.proficiencytest.model.NewUser;
import com.example.proficiencytest.model.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @GET("api/users")
    Call<UserResult> getUsers();

    @POST("api/users")
    Call<NewUser> postUser(@Body NewUser newUser);


}