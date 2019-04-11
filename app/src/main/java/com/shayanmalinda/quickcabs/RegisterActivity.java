package com.shayanmalinda.quickcabs;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    EditText username,email,phone,pass1,pass2;
    String strUsername, strEmail, strPhone, strPass1, strPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle(R.string.signup2);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        pass1 = (EditText) findViewById(R.id.pass1);
        pass2 = (EditText) findViewById(R.id.pass2);
    }

    public void signup(View v) {

        strUsername = username.getText().toString();
        strEmail = email.getText().toString();
        strPhone = phone.getText().toString();
        strPass1 = pass1.getText().toString();
        strPass2 = pass2.getText().toString();
//        String ServerURL = "http://idexserver.tk/b05/shayan/quickcabs/login/add.php";

        if(strUsername.isEmpty()){
            Toast.makeText(this, "Username is Empty", Toast.LENGTH_SHORT).show();
        }

        else if(strEmail.isEmpty()){
            Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show();
        }

        else if(strPhone.isEmpty()){
            Toast.makeText(this, "Phone Number is Empty", Toast.LENGTH_SHORT).show();
        }

        else if(strPass1.isEmpty() && strPass2.isEmpty()){
            Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show();
        }
        else{

            if(strPass1.equals(strPass2)){

                String method="register";
                LoginBackgroundTask backgroundTask=new LoginBackgroundTask(this);
                backgroundTask.execute(method,strUsername,strEmail,strPhone,strPass1);

                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);

            }

            else{
                Toast.makeText(this, "Password Mismatched", Toast.LENGTH_SHORT).show();
            }

        }


    }



}

