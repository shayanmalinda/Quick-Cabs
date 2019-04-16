package com.shayanmalinda.quickcabs;


import android.content.Context;
import android.os.AsyncTask;
import java.math.BigInteger;
import java.util.HashMap;
public class PasswordChangeProcess2 extends AsyncTask<String,Void,String> {

    String user, newpass = null;

    String HttpURL = "http://idexserver.tk/b05/shayan/quickcabs/login/changePassword.php";
//    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    Context ctx;
    PasswordChangeProcess2(Context ctx)
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


//                progressDialog.dismiss();


            }

            @Override
            protected String doInBackground(String... params) {
                user = params[0];
                newpass = params[1];

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

