package com.example.user.trainingretrofit;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emmasuzuki.easyform.EasyFormEditText;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EasyTextInputLayout txt_email;
    EditText txt_password;
    Button btn_login,btn_register;
    String tmp_email,tmp_password;
    String alert=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_email = (EasyTextInputLayout)findViewById(R.id.txt_email);
        txt_password = (EditText)findViewById(R.id.txt_password);
        btn_login = (Button)findViewById(R.id.submit_button);
        btn_register = (Button)findViewById(R.id.register_btn);

        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        //implement interface for get all user
//        btn_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_obj = new Intent(MainActivity.this, RegisterActivity.class);
//                startActivity(intent_obj);
//                Fragment fragment = new FragmentRegister();
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.fragment_place,fragment);
//                ft.commit();
//            }
//        });
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Gson gson = new GsonBuilder()
////                .setDateFormat("YYYY-MM-dd'T'HH:mm:ssZ")
//                        .create();
//
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://private-87daa-rizalmh.apiary-mock.com/")
//                        .addConverterFactory(GsonConverterFactory.create(gson))
//                        .build();
//                UserApi user_api = retrofit.create(UserApi.class);
//                Call<Users> call = user_api.getUsers();
//                if (txt_email.getEditText().getText().toString() !="" && txt_password.getEditText().getText().toString() !=""){
//                    tmp_email = txt_email.getEditText().getText().toString();
//                    tmp_password = txt_password.getEditText().getText().toString();
//                    call.enqueue(new Callback<Users>() {
//                        @Override
//                        public void onResponse(Response<Users> response, Retrofit retrofit) {
//                            int status = response.code();
//                            //this extract data from retrofit with for()loop
//                            for (Users.UserItem user : response.body().getUsers()){
////                                Log.e("CHECKAPI",String.valueOf(user.getEmail()));
////                                Log.e("CHECKAPI",String.valueOf(tmp_email));
//                                cek_login(String.valueOf(user.getEmail()), String.valueOf(user.getPassword()));
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Throwable t) {
//                        }
//                    });
//                }else{
//                    Toast.makeText(MainActivity.this,"Email dan Password harus di isi",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//        call.enqueue(new Callback<Users>() {
//            @Override
//            public void onResponse(Response<Users> response, Retrofit retrofit) {
//                int status = response.code();
//                tv_respond.setText(String.valueOf(status));
//                //this extract data from retrofit with for()loop
//                for (Users.UserItem user : response.body().getUsers()){
//                    tv_result_api.append(
//                            "Id = " + String.valueOf(user.getId()) +
//                                    System.getProperty("line.separator") +
//                                    "Email = " + user.getEmail() +
//                                    System.getProperty("line.separator") +
//                                    "Password = " + user.getPassword() +
//                                    System.getProperty("line.separator") +
//                                    "Token Auth = " + user.getToken_auth() +
//                                    System.getProperty("line.separator") +
//                                    "Created at = " + user.getCreated_at() +
//                                    System.getProperty("line.separator") +
//                                    "Updated at = " + user.getUpdated_at() +
//                                    System.getProperty("line.separator") +
//                                    System.getProperty("line.separator")
//                    );
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                tv_respond.setText(String.valueOf(t));
//            }
//        });
    }
    public void cek_login(String email, String password, String token){
        Log.e("CHECKAPI",email);
        Log.e("CHECKAPI",password);

        if (tmp_email.equals(email) && tmp_password.equals(password)){
//            Log.e("CHECKAPI",txt_email.getText().toString());
//            Log.e("CHECKAPI",txt_password.getText().toString());
            Fragment fragment = new FragmentLogin();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place,fragment);
            ft.commit();

            SharedPreferences set_shared_preference = getSharedPreferences("authentication", MODE_PRIVATE);
            SharedPreferences.Editor sp_editor = set_shared_preference.edit();
            sp_editor.putString("token_authentication", token);
            sp_editor.commit();
        }else{
            alert ="Failed";
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_button :
                Gson gson = new GsonBuilder()
//                .setDateFormat("YYYY-MM-dd'T'HH:mm:ssZ")
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://private-87daa-rizalmh.apiary-mock.com/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                UserApi user_api = retrofit.create(UserApi.class);
                Call<Users> call = user_api.getUsers();
                    tmp_email = txt_email.getEditText().getText().toString();
                    tmp_password = txt_password.getText().toString();
                    call.enqueue(new Callback<Users>() {
                        @Override
                        public void onResponse(Response<Users> response, Retrofit retrofit) {
                            int status = response.code();
                            //this extract data from retrofit with for()loop
                            for (Users.UserItem user : response.body().getUsers()){
//                                Log.e("CHECKAPI",String.valueOf(user.getEmail()));
//                                Log.e("CHECKAPI",String.valueOf(tmp_email));

                                cek_login(String.valueOf(user.getEmail()), String.valueOf(user.getPassword()), String.valueOf(user.getToken_auth()));
                            }
                            alert_message("Message", alert);
                        }

                        @Override
                        public void onFailure(Throwable t) {
                        }
                    });
                break;
            case R.id.register_btn :
                Intent intent_obj = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent_obj);
        }
    }
    public void alert_message(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);

        builder.setTitle(title);

        builder.setMessage(message);

        builder.show();

    }
}
