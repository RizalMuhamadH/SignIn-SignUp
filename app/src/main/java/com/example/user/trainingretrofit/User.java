package com.example.user.trainingretrofit;

import android.text.Editable;

/**
 * Created by User on 07/06/2016.
 */
public class User {
    private String  id;
    private String email;
    private String password;
    private String token_auth;

    public User(String id, String email, String password, String token_auth){
        this.id = id;
        this.email = email;
        this.password = password;
        this.token_auth = token_auth;
    }

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken_auth() {
        return token_auth;
    }

    public void setToken_auth(String token_auth) {
        this.token_auth = token_auth;
    }
}
