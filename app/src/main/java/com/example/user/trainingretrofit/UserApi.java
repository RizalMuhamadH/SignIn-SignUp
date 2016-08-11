package com.example.user.trainingretrofit;


import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by User on 07/06/2016.
 */
public interface UserApi {
    @GET("/users")
    Call<Users> getUsers();

    @GET("/users/{id}")
    Call<User> getUser(@Path("id") int user_id, @Body User user);

    @PUT("/users/{id}")
    Call<User> updateUser(@Path("id") int user_id, @Body User user);

    @POST("/users")
    Call<User> saveUser(@Body User user);

    @DELETE("users/{id}")
    Call<User> deleteUser(@Path("id") String user_id);
}
