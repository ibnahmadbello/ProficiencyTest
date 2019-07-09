package com.example.proficiencytest.network;

import com.example.proficiencytest.model.User;
import com.example.proficiencytest.model.UserResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @GET("api/users")
    Call<UserResult> getUsers();

    @POST("api/users")
    Call<User> postUser(@Field("name") String userName, @Field("job") String jobPosition);


}