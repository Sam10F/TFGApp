package com.example.samuel.tfgapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import graphMaker.HHRRBySexAndPeriod;

public class MainActivity extends AppCompatActivity{

    ArrayAdapter<String> adaptador;

    Toast toast;

    DrawerLayout mDrawerLayout;
    Toolbar myToolbar;
    NavigationView navigationView;

    GraphView lineGraph, barGraph, pointGraph;

    HHRRBySexAndPeriod graphMaker;

    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        setMyToolbar();
        setDrawerLayout();


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.36:8082/v1/data.json";

        // Request a string response from the provided URL.

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        // Display the first 500 characters of the response string.
                        try{
                            jsonArray = new JSONArray(response);
                            graphMaker = new HHRRBySexAndPeriod(jsonArray);

                            lineGraph   = findViewById(R.id.lineChart);
                            barGraph    = findViewById(R.id.barChart);
                            pointGraph  = findViewById(R.id.pointChart);

                            try{
                                graphMaker.createLineGraph(lineGraph);
                                graphMaker.createBarGraph(barGraph);
                                graphMaker.createPointGraph(pointGraph);
                            }catch (Exception e){e.printStackTrace();}

                        }catch (Exception e){
                            e.printStackTrace();
                        }





                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);






    }

    private void setMyToolbar(){
        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setTitle(R.string.setions_home);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        setSupportActionBar(myToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

    }

    private void setDrawerLayout(){
        mDrawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        // set item as selected to persist highlight
                        item.setChecked(true);
                        //close navigation drawer
                        mDrawerLayout.closeDrawer(GravityCompat.START);

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        switch (item.getItemId()) {
                            case R.id.drw_science_technology:
                                toast = Toast.makeText(getApplicationContext(), "Science and technology", Toast.LENGTH_SHORT);
                                toast.show();
                                break;
                            case R.id.drw_maps:
                                toast = Toast.makeText(getApplicationContext(), "Maps", Toast.LENGTH_SHORT);
                                toast.show();
                                break;
                            case R.id.drw_customers:
                                toast = Toast.makeText(getApplicationContext(), "Customers", Toast.LENGTH_SHORT);
                                toast.show();
                                break;
                            case R.id.drw_reports:
                                toast = Toast.makeText(getApplicationContext(), "Reports", Toast.LENGTH_SHORT);
                                toast.show();
                                break;
                            case R.id.drw_integration:
                                toast = Toast.makeText(getApplicationContext(), "Integration", Toast.LENGTH_SHORT);
                                toast.show();
                                break;
                        }

                        // set item as selected to persist highlight
                        item.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();


                        return true;
                    }
                });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logIn:
                toast = Toast.makeText(getApplicationContext(), "LogIn", Toast.LENGTH_SHORT);
                toast.show();
                return true;

            case R.id.action_dashboard:
                Intent intent = new Intent(this, secondActivity.class);
                /*EditText editText = (EditText) findViewById(R.id.editText);
                String message = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);*/
                startActivity(intent);
                return true;

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;




            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void seeDetail(View mview){
        String typeOfGraph = (String) mview.getTag();

        Intent intent = new Intent(this, secondActivity.class);
        intent.putExtra("typeOfGraph", typeOfGraph);
        //intent.putExtra()

        startActivity(intent);
    }

}
