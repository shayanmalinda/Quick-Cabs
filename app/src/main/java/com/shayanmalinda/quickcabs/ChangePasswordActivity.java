package com.shayanmalinda.quickcabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;

public class ChangePasswordActivity extends AppCompatActivity {

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extra = getIntent().getExtras();
        username = extra.getString("username");
    }

    public void changePassword(View v){

        EditText etpassword = findViewById(R.id.oldPassword);
        EditText newpassword1 = findViewById(R.id.newPassword1);
        EditText newpassword2 = findViewById(R.id.newPasswordConfirm);
        String p = etpassword.getText().toString();
        String newpass1 = newpassword1.getText().toString();
        String newpass2 = newpassword2.getText().toString();

        byte[] pass = etpassword.getText().toString().getBytes();
        BigInteger passEncrypt = null;

        try{
            passEncrypt = new BigInteger(1, md5.encryptMD5(pass));
        }catch (Exception e){
            e.printStackTrace();
        }

        String passEncryptString = passEncrypt.toString();

        if(p.isEmpty()){
            Toast.makeText(this, "Old Password is Empty", Toast.LENGTH_SHORT).show();
        }
        else{
            if(newpass1.equals(newpass2)){
                PasswordChangeProcess1 backgroundTask=new PasswordChangeProcess1(this);
                backgroundTask.execute(username,passEncryptString,newpass1);
                finish();
            }
            else{
                Toast.makeText(this, "New Password is Mismatched", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void cancelChangePassword(View v){
        this.finish();
    }
}
