package com.shayanmalinda.quickcabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseDateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);
        setTitle(R.string.selectadate);
    }

    public  void triggerback(View v){
        finish();
    }
}
