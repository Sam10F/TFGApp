package com.example.samuel.tfgapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;

import graphMaker.HHRRBySexAndPeriod;

public class secondActivity extends AppCompatActivity {

    Toast toast;
    Toolbar myToolbar;

    GraphView graph;

    HHRRBySexAndPeriod grapMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //grapMaker = new HHRRBySexAndPeriod(this);
        graph = findViewById(R.id.generalChart);

        setMyToolbar();
        prepareDetail();
    }


    private void setMyToolbar(){
        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setTitle(R.string.action_dashboard);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void prepareDetail(){
        String typeOfGraph = (String)getIntent().getSerializableExtra("typeOfGraph");

        switch (typeOfGraph){
            case "lineChart":

                try{
                    grapMaker.createLineGraph(graph);
                }catch (Exception e){e.printStackTrace();}

                break;
        }
    }

}
