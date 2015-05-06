package com.code_breakers.rythm;

import android.content.Intent;
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
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;


public class signup extends ActionBarActivity {

    //ParseQuery<ParseObject> query;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //query = ParseQuery.getQuery("Login_table");
    }

    public void register(View view)
    {
        EditText phno = (EditText) findViewById(R.id.phno);
        final String PhoneNum = phno.getText().toString();
        EditText name = (EditText) findViewById(R.id.name);
        final String Name = name.getText().toString();
        EditText email = (EditText) findViewById(R.id.email);
        final String Email = email.getText().toString();
        EditText pass = (EditText) findViewById(R.id.password);
        final String Password = pass.getText().toString();

        //Parse script to create new user (phone no, unique)
        ParseUser user = new ParseUser();
        user.setUsername(PhoneNum);
        user.setPassword(Password);
        user.setEmail(Email);

        // other fields can be set just like with ParseObject
        user.put("FullName", Name);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Toast.makeText(getApplicationContext(),"Signup Successful",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(i);
                    finish();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(getApplicationContext(),"Error occurred: e = "+e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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
