package com.code_breakers.rythm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class login extends ActionBarActivity {

    ParseQuery<ParseObject> query;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        query = ParseQuery.getQuery("Login_table");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    public void login(View view)
    {
        EditText phno = (EditText)findViewById(R.id.phone);
        final String PhoneNum = phno.getText().toString();
        EditText pass = (EditText)findViewById(R.id.password);
        final String Password = pass.getText().toString();

        //Logging in user through parse
        ParseUser.logInInBackground(PhoneNum, Password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(i);
                    finish();
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    Toast.makeText(getApplicationContext(),"Error occurred: e = "+e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

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
    public void changeToSignup(View view)
    {
        //Toast.makeText(getApplicationContext(),"llllllllllllllllllllll",Toast.LENGTH_SHORT).show();
        //Intent j = new Intent(this,signup.class);
        startActivity(new Intent(this, signup.class));
        //finish();
    }
   /* */
}
