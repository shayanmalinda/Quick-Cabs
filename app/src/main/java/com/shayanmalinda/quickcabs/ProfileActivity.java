package com.shayanmalinda.quickcabs;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    String email = null;
    String phone = null;
    String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle(R.string.profile);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle extras = getIntent().getExtras();
        email = extras.getString("email");
        phone = extras.getString("phone");
        username = extras.getString("username");
        TextView email2 = findViewById(R.id.profileEmail);
        TextView phone2 = findViewById(R.id.profileMobile);
        TextView username2 = findViewById(R.id.profileusername);
        email2.setText("Email Address : " +email);
        phone2.setText("Mobile Number : " + phone);
        username2.setText(username);
    }

    public void logout(View v){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void EditUserDetails(View v){
        Intent intent = new Intent(this,EditProfileActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("phone",phone);
        startActivity(intent);
    }
}
