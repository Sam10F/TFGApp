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
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import Session.Session;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class LogInActivity extends AppCompatActivity {

    EditText pswd,usrusr;
    TextView sup,lin;

    RequestQueue queue;

    Toast toast;

    private static int workload = 12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        workload = 10;

        queue = Volley.newRequestQueue(this);

        lin = findViewById(R.id.lin);
        usrusr = findViewById(R.id.usrusr);
        pswd = findViewById(R.id.pswrdd);
        sup = findViewById(R.id.sup);
        sup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(LogInActivity.this, SignUp.class);
                startActivity(it);
            }
        });
    }

    public void goDemo(View view){
        Intent intent = new Intent(this, FirstPage.class);
        startActivity(intent);
    }

    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return(hashed_password);
    }

    public void logIn(View view){




        JSONObject jsonBodyObj = new JSONObject();
        try {

            String loginToken = Jwts.builder()
                    .claim("usr", usrusr.getText().toString())
                    .claim("pswd", pswd.getText().toString())
                    .signWith(
                            SignatureAlgorithm.HS256,
                            "secret".getBytes("UTF-8")
                    )
                    .compact();

            jsonBodyObj.put("loginToken", loginToken);
        }catch (Exception e){
            e.printStackTrace();
        }
        final String requestBody = jsonBodyObj.toString();


        String url = "http://192.168.0.25:8082/v1/login.json";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Intent intent = new Intent(getApplicationContext(), FirstPage.class);
                        startActivity(intent);
                        String finalString = response.replace("[", "");
                        finalString = finalString.replace("]", "");
                        finalString = finalString.replace("\"", "");

                        Session.setUserName(finalString.split(",")[0]);
                        Session.setEmail(finalString.split(",")[1]);


                        toast = Toast.makeText(getApplicationContext(), "Bienvenido " + Session.getUserName(), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        toast = Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT);
                        toast.show();
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
