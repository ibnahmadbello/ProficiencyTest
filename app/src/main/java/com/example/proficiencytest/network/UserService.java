package com.example.proficiencytest.network;

import com.example.proficiencytest.model.UserResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("api/users")
    Call<UserResult> getUsers();

}