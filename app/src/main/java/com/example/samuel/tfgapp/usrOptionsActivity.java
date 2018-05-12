package com.example.samuel.tfgapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import Session.Session;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class usrOptionsActivity extends AppCompatActivity {

    EditText mail,pswd,repeatpswd,usrusr;
    Toolbar myToolbar;
    EditText editText;
    Toast toast;
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usr_options);

        queue = Volley.newRequestQueue(this);

        setMyToolbar();
        fillFields();
    }

    private void fillFields(){
        editText = findViewById(R.id.usrusr);
        editText.setText(Session.getUserName());

        editText = findViewById(R.id.mail);
        editText.setText(Session.getEmail());

        editText = findViewById(R.id.pswrdd);
        editText.setText(Session.getPswd());

        editText = findViewById(R.id.mobphone);
        editText.setText(Session.getPswd());
    }

    private void setMyToolbar(){
        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setTitle("Edit your profile");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void cancel(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void edit(View view){

        usrusr = findViewById(R.id.usrusr);
        pswd = findViewById(R.id.pswrdd);
        mail = findViewById(R.id.mail);
        repeatpswd = findViewById(R.id.mobphone);

        if(!pswd.getText().toString().equals(repeatpswd.getText().toString())){
            toast = Toast.makeText(getApplicationContext(), "The passwords are not the same ", Toast.LENGTH_SHORT);
            toast.show();
        }else {

            JSONObject jsonBodyObj = new JSONObject();
            try {

                String signupToken = Jwts.builder()
                        .claim("mail", mail.getText().toString())
                        .claim("usr", usrusr.getText().toString())
                        .claim("pswd", pswd.getText().toString())
                        .claim("oldUsrName", Session.getUserName())
                        .signWith(
                                SignatureAlgorithm.HS256,
                                "secret".getBytes("UTF-8")
                        )
                        .compact();

                jsonBodyObj.put("signupToken", signupToken);


            } catch (Exception e) {
                e.printStackTrace();
            }
            final String requestBody = jsonBodyObj.toString();

            String url = "http://192.168.0.25:8082/v1/edit.json";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            toast = Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT);
                            toast.show();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            String body;
                            if(error.networkResponse!=null) {
                                try {
                                    body = new String(error.networkResponse.data, "UTF-8");
                                    body.replace('"', ' ' );
                                    toast = Toast.makeText(getApplicationContext(), body, Toast.LENGTH_SHORT);
                                    toast.show();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            ) {
                @Override
                public byte[] getBody() {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody, "utf-8");
                        return null;
                    }
                }
            };
            queue.add(postRequest);
        }
    }

}
