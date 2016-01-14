package com.example.sonyama.ideas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.fetch_action:
                fetchChannelList();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Fetch JSON-data from server
     */
    public void fetchChannelList() {
        String parsedString = "asd";

        try {

            URL url = new URL("http://benlly.framgia.vn/v1.0/channels/");
            URLConnection conn = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) conn;

            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("X-Benlly-Api-Token", "cf5b8d8429699057fa85af897571c178d10ef466b514589c090df2597850aa1e");
            httpConn.setRequestProperty("X-Benlly-Api-Version", "1.0");
            httpConn.setRequestProperty("X-Benlly-Device-Type", "iOS");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.connect();

            InputStream info = new BufferedInputStream(httpConn.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(info));
            parsedString = r.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView channelJson = (TextView)findViewById(R.id.channels_json);
        channelJson.setText(parsedString);
    }
}
