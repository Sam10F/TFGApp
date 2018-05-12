package com.example.samuel.tfgapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.io.UnsupportedEncodingException;

import Session.Session;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SignUp extends AppCompatActivity {

    EditText mail,pswd,repeatpswd,usrusr;
    TextView lin,sup;

    RequestQueue queue;

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        queue = Volley.newRequestQueue(this);

        sup = findViewById(R.id.sup);
        lin = findViewById(R.id.lin);
        usrusr = findViewById(R.id.usrusr);
        pswd = findViewById(R.id.pswrdd);
        mail = findViewById(R.id.mail);
        repeatpswd = findViewById(R.id.mobphone);
        lin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(SignUp.this, LogInActivity.class);
                startActivity(it);
            }
        });
    }

    public void signUp(View view){

        if(usrusr.getText().toString().equals("") || mail.getText().toString().equals("") || pswd.getText().toString().equals("")){
            toast = Toast.makeText(getApplicationContext(), "You have to fill all the fields", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(!pswd.getText().toString().equals(repeatpswd.getText().toString())){
            toast = Toast.makeText(getApplicationContext(), "The passwords are not the same ", Toast.LENGTH_SHORT);
            toast.show();
        }else {

            JSONObject jsonBodyObj = new JSONObject();
            try {

                String signupToken = Jwts.builder()
                        .claim("mail", mail.getText().toString())
                        .claim("usr", usrusr.getText().toString())
                        .claim("pswd", pswd.getText().toString())
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

            String url = "http://192.168.0.25:8082/v1/signup.json";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                            String finalString = response.replace("[", "");
                            finalString = finalString.replace("]", "");
                            finalString = finalString.replace("\"", "");

                            Session.setUserName(finalString.split(",")[0]);
                            Session.setEmail(finalString.split(",")[1]);

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
