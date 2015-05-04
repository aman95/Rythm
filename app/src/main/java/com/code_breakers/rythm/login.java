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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
        query.whereEqualTo("phone", PhoneNum);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> user, ParseException e) {
                if (e == null) {
                    //String s = email.toString();
                    if(user.size() == 1)
                    {
                        if(user.get(0).getString("password").equals(Password))
                        {
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            SharedPreferences prefs = getSharedPreferences("isLoggedIn", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putInt("log", 1);
                            editor.putString("userPhone",PhoneNum);
                            editor.commit();
                            Intent i = new Intent(getApplicationContext(),Dashboard.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),"Wrong Password.",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(),"User does not Exists.",Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
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
        Intent j = new Intent(this,signup.class);
        startActivity(j);
        finish();
    }
   /* */
}
