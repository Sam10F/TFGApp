package com.example.samuel.tfgapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class secondActivity extends AppCompatActivity {

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setMyToolbar();
    }


    private void setMyToolbar(){
        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setTitle(R.string.action_dashboard);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
