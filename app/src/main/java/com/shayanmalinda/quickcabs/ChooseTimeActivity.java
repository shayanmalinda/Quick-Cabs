package com.shayanmalinda.quickcabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);
        setTitle(R.string.selectatime);

    }

    public  void triggerback(View v){
        finish();
    }
}
