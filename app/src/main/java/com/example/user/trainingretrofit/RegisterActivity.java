package com.example.user.trainingretrofit;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Body;

public class RegisterActivity extends AppCompatActivity {
    Button btn_register;
    EditText txt_id, txt_email,txt_password, txt_token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_register = (Button)findViewById(R.id.submit_register);
        txt_email = (EditText)findViewById(R.id.txt_email);
        txt_id = (EditText)findViewById(R.id.txt_id);
        txt_password = (EditText)findViewById(R.id.txt_password);
        txt_token = (EditText)findViewById(R.id.txt_token);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new GsonBuilder()
//                        .setDateFormat("YYYY-MM-dd'T'HH:mm:ssZ")
                        .create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://private-87daa-rizalmh.apiary-mock.com/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                UserApi user_api = retrofit.create(UserApi.class);
                User user = new User(txt_id.getText().toString(),txt_email.getText().toString(),txt_password.getText().toString(),txt_token.getText().toString());
                Call<User> call = user_api.saveUser(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Response<User> response, Retrofit retrofit) {
                        int status = response.code();
                        alert_message("Message",String.valueOf(status));
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });

            }
        });
    }

    public void alert_message(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);

        builder.setTitle(title);

        builder.setMessage(message);

        builder.show();

    }

}
