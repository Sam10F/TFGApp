package com.example.samuel.tfgapp;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


import org.bson.Document;

import java.net.Socket;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
    Client mySocket = new Client("192.168.0.29", 8083);

    TextView mainText;
    String sexo;
    ListView lista;
    ArrayAdapter<String> adaptador;

    boolean ended = false;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(10);

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        mySocket.setClientCallback(new Client.ClientCallback () {
            MongoClientURI uri = new MongoClientURI( "mongodb://192.168.0.29:27017/dtb" );
            MongoClient mongoClient = new MongoClient(uri);
            final MongoDatabase db = mongoClient.getDatabase(uri.getDatabase());



            @Override
            public void onMessage(String message) {
            }

            @Override
            public void onConnect(Socket socket) {
                mySocket.send("Hello World!\n");

                FindIterable<Document> mydatabaserecords = db.getCollection("usoDeInternet").find();
                MongoCursor<Document> iterator = mydatabaserecords.iterator();

                //mydatabaserecords.


                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(5);

                Document doc = iterator.next();

                while (iterator.hasNext()) {
                    doc = iterator.next();

                    if(doc != null){
                        String rangos = doc.getString("RANGOS");
                        adaptador.add(rangos);
                    }
                }

                adaptador.remove(null);
                mySocket.disconnect();
            }

            @Override
            public void onDisconnect(Socket socket, String message) {

                ended = true;

            }

            @Override
            public void onConnectError(Socket socket, String message) {
            }
        });

        //
        mySocket.connect();

        int i = 0;
        while(!ended){
            i++;
        }

        //progressBar.setVisibility(View.GONE);

        lista = (ListView) findViewById(R.id.list_man);
        mainText = findViewById(R.id.mainText);
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE: " + adaptador.getItem(0));
        mainText.setText(sexo);


        adaptador.add("OLI");
        lista.setAdapter(adaptador);


    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
