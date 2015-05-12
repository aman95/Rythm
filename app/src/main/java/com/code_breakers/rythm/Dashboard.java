package com.code_breakers.rythm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.code_breakers.rythm.preferences.setSharedPreferences;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class Dashboard extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //getActionBar().setDisplayShowHomeEnabled(true);

        //Intialising Parse
        //Parse.enableLocalDatastore(this);

        //Setting up Navigation Drawer
        Dashboard_NavDrawer drawer_fragment = (Dashboard_NavDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_Dashboard_NavDrawer);
        drawer_fragment.setUp(R.id.fragment_Dashboard_NavDrawer,(DrawerLayout)findViewById(R.id.drawerLayout_Dashboard),toolbar);

        //Setting up user session using parse
        ParseUser currentUser = ParseUser.getCurrentUser();

        //Change text acc to user session
        final TextView person_name = (TextView)findViewById(R.id.person_name);
        String name = currentUser.getString("FullName");
        person_name.setText(name);

        //Testing shared prefernces
        //setSharedPreferences.saveToPreferences(this,"test","Test message Stored, Yeah!!!");


    }

    public void goToCreateCircle(View view)
    {
        Intent i = new Intent(getApplicationContext(),Create_circle.class);
        startActivity(i);
        return;
        //String msg =setSharedPreferences.readFromPreferences(this,"test","No preference stored");
        //Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
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
            //Logging out user through parse script
            ParseUser.logOut();
            Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(),login.class);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.createCircle) {

            //Intent i = new Intent(getApplicationContext(),Create_circle.class);
            startActivity(new Intent(getApplicationContext(),Create_circle.class));
            //finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
