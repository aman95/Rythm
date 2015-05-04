package com.code_breakers.rythm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class Create_circle extends ActionBarActivity {

    private Toolbar toolbar;
    String userPhone;
    ParseQuery<ParseObject> query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_circle);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        SharedPreferences prefs = getSharedPreferences("isLoggedIn", 0);
        //final int isLogged = prefs.getInt("log", 0);
        userPhone = prefs.getString("userPhone",null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_circle, menu);
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

        return super.onOptionsItemSelected(item);
    }

    public void addToCircle(View view)
    {
        EditText ph = (EditText)findViewById(R.id.numSearch);
        final String phNum = ph.getText().toString();
        RadioGroup rg = (RadioGroup)findViewById(R.id.radio);
        int checkedId = rg.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton)findViewById(checkedId);
        final String circle = rb.getText().toString();

        query = ParseQuery.getQuery("Login_table");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Login_table");
        query.whereEqualTo("phone", phNum);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> user, ParseException e) {
                if (e == null) {
                    //String s = email.toString();
                    if(user.size() == 0)
                    {
                        Toast.makeText(getApplicationContext(),"Contact Does not exist",Toast.LENGTH_SHORT).show();
                    } else {
                        ParseObject circle_table = new ParseObject("circle_table");
                        circle_table.put("person",phNum);
                        circle_table.put("adder",userPhone);
                        circle_table.put("circle",circle);
                        circle_table.saveInBackground();
                        Toast.makeText(getApplicationContext(),"Contact Added Successfully",Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d("Dashboard", "Error: " + e.getMessage());
                }
            }
        });
    }
}
