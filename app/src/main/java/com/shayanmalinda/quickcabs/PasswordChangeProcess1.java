package com.shayanmalinda.quickcabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

 public class PasswordChangeProcess1 extends AsyncTask<String,Void,String> {

    ProgressDialog progressDialog;
    String newpassword = null;

    Context ctx;
    PasswordChangeProcess1(Context ctx)
    {
        this.ctx=ctx;
    }

    public String doInBackground(String... params) {

        String flag = null;
        String adress = "http://idexserver.tk/b05/shayan/quickcabs/login/list.php?username=\""+params[0]+"\"";
        InputStream inputStream = null;
        String line = null, result = null, data[];
        try {
            URL url = new URL(adress);
            HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");


            try{

                inputStream = new BufferedInputStream(httpsURLConnection.getInputStream());

            }
            catch (Exception e){
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read input stream into a string
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            inputStream.close();
            result = stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // Parse json data
        try{
            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject = null;

            data = new String[jsonArray.length()];
            for (int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                data[i] = jsonObject.getString("password"); // column name

                if(data[i].equals(params[1])){
                    flag = params[0];
                    newpassword = params[2];
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(ctx,"Please Wait",null,true,true);

    }

    @Override
    protected void onPostExecute(String result) {


        progressDialog.dismiss();
        if(result!=null){
//            Intent intent = new Intent(ctx, PasswordChangeProcess2.class);
//            intent.putExtra("username",result);
//            intent.putExtra("newpassword",newpassword);
//            ctx.startActivity(intent);

            PasswordChangeProcess2 p = new PasswordChangeProcess2();
            p.resume(result,newpassword);
        }
        else{
            Toast.makeText(ctx, "Old Password is Incorrect", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}