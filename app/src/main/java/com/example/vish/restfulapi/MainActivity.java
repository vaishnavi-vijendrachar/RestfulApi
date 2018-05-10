package com.example.vish.restfulapi;


/*****
 * Basic REST API example using http GET functionality
 */

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView response;
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        response = (TextView) findViewById(R.id.textView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeatherDetails();
            }
        });
    }


    public void getWeatherDetails(){
        new NetworkHandler().execute();
    }


    private static class NetworkHandler extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {

            String inputLine;

            try {
                URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.connect();
                con.setRequestMethod(REQUEST_METHOD);
                con.setReadTimeout(READ_TIMEOUT);
                con.setConnectTimeout(CONNECTION_TIMEOUT);
                con.getResponseMessage();
                con.getResponseCode();
                Log.d("vish",con.getResponseMessage());
                Log.d("vish"," " + con.getResponseCode());


                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                while((inputLine = reader.readLine()) != null ){
                    stringBuilder.append(inputLine);
                    Log.d("vish",stringBuilder.toString());
                }

                //response.setText(con.toString());
                Log.d("vish",con.toString());

            }
            catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

}
