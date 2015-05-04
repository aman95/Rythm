package com.code_breakers.rythm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class Dashboard extends ActionBarActivity {

    private Toolbar toolbar;
    ParseQuery<ParseObject> query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //getActionBar().setDisplayShowHomeEnabled(true);

        final TextView person_name = (TextView)findViewById(R.id.person_name);
        SharedPreferences prefs = getSharedPreferences("isLoggedIn", 0);
        //final int isLogged = prefs.getInt("log", 0);
        final String userPhone = prefs.getString("userPhone",null);

        query = ParseQuery.getQuery("Login_table");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Login_table");
        query.whereEqualTo("phone", userPhone);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> user, ParseException e) {
                if (e == null) {
                    //String s = email.toString();
                    String name = user.get(0).getString("name");
                    person_name.setText(name);

                } else {
                    Log.d("Dashboard", "Error: " + e.getMessage());
                }
            }
        });
    }

    public void goToCreateCircle(View view)
    {
        Intent i = new Intent(getApplicationContext(),Create_circle.class);
        startActivity(i);
        return;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(),about.class);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.logout) {
            SharedPreferences prefs = getSharedPreferences("isLoggedIn", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("log", 0);
            editor.putString("userPhone",null);
            editor.commit();
            Intent i = new Intent(getApplicationContext(),login.class);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.createCircle) {

            Intent i = new Intent(getApplicationContext(),Create_circle.class);
            startActivity(i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
