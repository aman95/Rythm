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

import java.util.List;


public class signup extends ActionBarActivity {

    ParseQuery<ParseObject> query;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        query = ParseQuery.getQuery("Login_table");
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
        query.whereEqualTo("phone", PhoneNum);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> user, ParseException e) {
                if (e == null) {
                    //String s = email.toString();
                    if(user.size() == 0)
                    {
                        final ParseObject login_table = new ParseObject("Login_table");
                        login_table.put("name", Name);
                        login_table.put("phone", PhoneNum);
                        login_table.put("email", Email);
                        login_table.put("password",Password);
                        login_table.saveInBackground();
                        Toast.makeText(getApplicationContext(),"Signup Successful",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),login.class);
                        startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(),"Phone Number already Exits.",Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
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
