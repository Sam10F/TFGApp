package com.example.samuel.tfgapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;


public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adaptador;
    ListView lista;
    Toolbar myToolbar;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        setMyToolbar();



        final TextView mTextView = (TextView) findViewById(R.id.mainText);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.0.29:8082/v1/data.json";

        // Request a string response from the provided URL.

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        // Display the first 500 characters of the response string.
                        System.out.println();
                        try{
                            JSONArray jsonArray = new JSONArray(response);

                            for(int i = 0; i < jsonArray.length(); i++){
                                String sexo = jsonArray.getJSONObject(i).getString("SEXO");
                                adaptador.add(sexo);
                            }
                            adaptador.remove(null);
                            System.out.println(jsonArray.getJSONObject(0).getString("SEXO"));

                        }catch (Exception e){
                            e.printStackTrace();
                        }





                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        lista = (ListView) findViewById(R.id.list_man);
        lista.setAdapter(adaptador);
    }

    private void setMyToolbar(){
        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setTitle(R.string.setions_home);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        setSupportActionBar(myToolbar);

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
                toast = Toast.makeText(getApplicationContext(), "Dasboard", Toast.LENGTH_SHORT);
                toast.show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



}
