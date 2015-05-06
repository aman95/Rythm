package com.code_breakers.rythm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable Local Datastore.
       Parse.enableLocalDatastore(this);

       Parse.initialize(this, "Lu5JsXQMfDWxzvI9MPKFEJ4FyAAaY8ZDmfOoPXqb", "dZBg7x6QBIJGRXI1eRGldJU7KPRTF5htOazSwCQV");

        /*final ParseObject login_table = new ParseObject("Login_table");
        login_table.put("phone", "8826170616");
        login_table.put("email", "aman1995k1@gmail.com");
        login_table.put("password", "qwerty");
        login_table.saveInBackground();
        final String[] playerName = new String[10];
        final int[] s = new int[1];

        SharedPreferences prefs = getSharedPreferences("isLoggedIn", 0);
        final int isLogged = prefs.getInt("log", 0);
        final String userPhone = prefs.getString("userPhone",null);*/

        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            // do stuff with the user
            Intent i = new Intent(getApplicationContext(),Dashboard.class);
            startActivity(i);
            finish();
        } else {
            // show the signup or login screen
            Intent i = new Intent(getApplicationContext(),login.class);
            startActivity(i);
            finish();
        }



        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Login_table");
        query.whereEqualTo("phone", "8826170616");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> email, ParseException e) {
                if (e == null) {
                    //String s = email.toString();
                    playerName[0] = email.get(0).getString("password");

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        final TextView viewpg = (TextView) findViewById(R.id.textView);
        Button okbut = (Button) findViewById(R.id.button);
        View.OnClickListener listen = new View.OnClickListener(){
         @Override
            public void onClick(View view) {



             viewpg.setText("isLoggedIn = "+isLogged+" Email: "+playerName[0]);

             Intent i = new Intent(getApplicationContext(),login.class);
             startActivity(i);
             finish();

            }
        };
        okbut.setOnClickListener(listen);*/

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
