package com.shayanmalinda.quickcabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule);
        setTitle(R.string.sheduledhire);
    }
    public void ChooseDate(View v){
        Intent intent = new Intent(this,ChooseDateActivity.class);
        startActivity(intent);
    }
    public void ChooseTime(View v){
        Intent intent = new Intent(this,ChooseTimeActivity.class);
        startActivity(intent);
    }
}
