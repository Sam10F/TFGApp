package com.example.samuel.tfgapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import graphMaker.FemaleResearch;
import graphMaker.HHRRBySexAndPeriod;

public class FRDetail extends AppCompatActivity {

    Toast toast;
    Toolbar myToolbar;

    GraphView graph;

    FemaleResearch grapMaker;
    JSONArray jsonArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frdetail);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.0.25:8082/v1/female_research.json";

        // Request a string response from the provided URL.

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        // Display the first 500 characters of the response string.
                        try{
                            jsonArray = new JSONArray(response);
                            prepareDetail();
                            prepareTable("Companies", "double");

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

        graph = findViewById(R.id.generalChart);

        setMyToolbar();

    }


    private void setMyToolbar(){
        String title = (String)getIntent().getSerializableExtra("title");

        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setTitle(title);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void prepareDetail() throws JSONException {
        String typeOfGraph = (String)getIntent().getSerializableExtra("typeOfGraph");
        grapMaker = new FemaleResearch(jsonArray);

        switch (typeOfGraph){
            case "lineChart":
                grapMaker.createLineGraph(graph, "", true);
                break;
            case "barChart":
                grapMaker.createBarGraph(graph, "", true);
                break;
            case "pointChart":
                grapMaker.createPointGraph(graph, "", true);
                break;
        }
    }

    private void prepareTable(String key, String clss) throws JSONException{
        TableLayout ll = findViewById(R.id.detailTable);

        JSONArray sortedJsonArray = sortJSONArray(jsonArray, key, clss);

        for (int i = 0; i <sortedJsonArray.length(); i++) {
            TableRow row= new TableRow(this);

            TextView yyears = new TextView(this);
            String yy = Integer.toString(sortedJsonArray.getJSONObject(i).getInt("Years"));
            yyears.setText(yy);
            yyears.setGravity(Gravity.CENTER);

            TextView years = new TextView(this);
            String y = Integer.toString(sortedJsonArray.getJSONObject(i).getInt("Companies"));
            years.setText(y);
            years.setGravity(Gravity.CENTER);
            TextView sex = new TextView(this);
            sex.setText(sortedJsonArray.getJSONObject(i).getString("Higher education"));
            sex.setGravity(Gravity.CENTER);
            TextView europe = new TextView(this);
            String e = Double.toString(sortedJsonArray.getJSONObject(i).getDouble("Public administration"));
            europe.setText(e);
            europe.setGravity(Gravity.CENTER);
            TextView spain = new TextView(this);
            String s = Double.toString(sortedJsonArray.getJSONObject(i).getDouble("Private non-profit institutions"));
            spain.setText(s);
            spain.setGravity(Gravity.CENTER);

            row.addView(yyears);
            row.addView(years);
            row.addView(sex);
            row.addView(europe);
            row.addView(spain);

            try{
                ll.removeViewAt(i+1);
            }catch (Exception exception){exception.printStackTrace();}
            ll.addView(row,i+1);
        }
    }

    public void orderBy(View view) throws JSONException{
        String tag = (String) view.getTag();
        String key = tag.split("/")[0];
        String clss = tag.split("/")[1];

        prepareTable(key, clss);

        toast = Toast.makeText(getApplicationContext(), "Sorted by: " + key, Toast.LENGTH_SHORT);
        toast.show();
    }

    private JSONArray sortJSONArray(JSONArray jsonArray, final String key, final String clss) throws JSONException{
        List<JSONObject> jsonValues = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonValues.add(jsonArray.getJSONObject(i));
        }

        Collections.sort( jsonValues, new Comparator<JSONObject>() {
            //You can change "Name" with "ID" if you want to sort by ID
            private final String KEY_NAME = key;

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = "";
                String valB = "";

                try {
                    switch (clss){
                        case "int":
                            int ia = (int) a.get(KEY_NAME);
                            int ib = (int) b.get(KEY_NAME);
                            valA = Integer.toString(ia);
                            valB = Integer.toString(ib);
                            break;
                        case "double":
                            Double da = (Double) a.get(KEY_NAME);
                            Double db = (Double) b.get(KEY_NAME);
                            valA = Double.toString(da);
                            valB = Double.toString(db);
                            break;

                        default:
                            valA = (String)a.get(KEY_NAME);
                            valB = (String)b.get(KEY_NAME);
                            break;
                    }

                }
                catch (JSONException e) {
                    //do something
                }

                return valA.compareTo(valB);
                //if you want to change the sort order, simply use the following:
                //return -valA.compareTo(valB);
            }
        });

        JSONArray sortedJsonArray = new JSONArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }

        return sortedJsonArray;
    }


}
