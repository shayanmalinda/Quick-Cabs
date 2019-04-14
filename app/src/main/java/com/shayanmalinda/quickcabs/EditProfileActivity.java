package com.shayanmalinda.quickcabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {

    String username,email,phone = null;


    String HttpURL = "http://idexserver.tk/b05/shayan/quickcabs/profile/update.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setTitle("Edit Profile");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Bundle extra = getIntent().getExtras();
        username = extra.getString("username");
        email = extra.getString("email");
        phone = extra.getString("phone");

        EditText etUsername = findViewById(R.id.editProfileUsername);
        EditText etEmail = findViewById(R.id.editProfileEmail);
        EditText etPhone = findViewById(R.id.editProfilePhone);

        etUsername.setText(username);
        etEmail.setText(email);
        etPhone.setText(phone);
    }

    public void saveDetails(View v){
        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {
            Context ctx;
            StudentRecordUpdateClass(Context ctx)
            {
                this.ctx=ctx;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(EditProfileActivity.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                Intent intent = new Intent(ctx, ProfileActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("email",email);
                intent.putExtra("phone",phone);
                ctx.startActivity(intent);

                progressDialog.dismiss();


            }

            @Override
            protected String doInBackground(String... params) {

                EditText etUsername = findViewById(R.id.editProfileUsername);
                EditText etEmail = findViewById(R.id.editProfileEmail);
                EditText etPhone = findViewById(R.id.editProfilePhone);

                username = etUsername.getText().toString();
                email = etEmail.getText().toString();
                phone = etPhone.getText().toString();

                hashMap.put("username",username);

                hashMap.put("email",email);

                hashMap.put("phone",phone);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass(this);

        studentRecordUpdateClass.execute(username,email,phone);
    }

    public void changePassword(View v){
        Intent intent = new Intent(this,ChangePasswordActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}
