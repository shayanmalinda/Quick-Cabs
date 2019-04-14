package com.shayanmalinda.quickcabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class PasswordChangeProcess2 extends AppCompatActivity {

    String user, newpass;

    String HttpURL = "http://idexserver.tk/b05/shayan/quickcabs/login/changePassword.php";
//    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void resume(String username, String newpassword){
        user = username;
        newpass = newpassword;
        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {
            Context ctx;
            StudentRecordUpdateClass(Context ctx)
            {
                this.ctx=ctx;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

//                progressDialog = ProgressDialog.show(PasswordChangeProcess2.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
//
//                Toast.makeText(ctx, "Password Changed", Toast.LENGTH_SHORT).show();
//                finish();

//                super.onPostExecute(httpResponseMsg);

//                progressDialog.dismiss();


            }

            @Override
            protected String doInBackground(String... params) {

                byte[] pass = newpass.getBytes();
                BigInteger passEncrypt = null;

                try{
                    passEncrypt = new BigInteger(1, md5.encryptMD5(pass));
                }catch (Exception e){
                    e.printStackTrace();
                }

                String passEncryptString = passEncrypt.toString();

                hashMap.put("username",user);

                hashMap.put("password",passEncryptString);

                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass(this);

        studentRecordUpdateClass.execute(username,newpassword);
    }




}