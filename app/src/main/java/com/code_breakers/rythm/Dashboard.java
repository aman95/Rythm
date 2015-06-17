package com.code_breakers.rythm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.code_breakers.rythm.fragment.Circles;
import com.parse.ParseUser;


public class Dashboard extends AppCompatActivity implements Dashboard_NavDrawer.NavigationDrawerCallbacks {

    private Toolbar toolbar;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //getActionBar().setDisplayShowHomeEnabled(true);

        //Setting up Navigation Drawer
        Dashboard_NavDrawer drawer_fragment = (Dashboard_NavDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_Dashboard_NavDrawer);
        drawer_fragment.setUp(R.id.fragment_Dashboard_NavDrawer, (DrawerLayout) findViewById(R.id.drawerLayout_Dashboard), toolbar);

        //Setting up user session using parse
        ParseUser currentUser = ParseUser.getCurrentUser();

        //Change text acc to user session
        //final TextView person_name = (TextView) findViewById(R.id.person_name);
        name = currentUser.getString("FullName");
        //person_name.setText(name);


    }

//    public void goToCreateCircle(View view) {
////        Intent i = new Intent(getApplicationContext(),Create_circle.class);
////        startActivity(i);
////        return;
//        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.containerFragment, Circles.newInstance("Hello", "World"));
//                fragmentTransaction.commit();
//
//    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Toast.makeText(getApplicationContext(), "For Navigation Position = " + position, Toast.LENGTH_SHORT).show();
        //Update Dashboard container acc to option selected fron navigation drawer
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 0: {
                fragmentManager.beginTransaction()
                        .replace(R.id.containerFragment, Circles.newInstance(position, name))
                        .commit();
                toolbar.setTitle(R.string.drawer_item_0);

                break;
            }
            case 1: {
                fragmentManager.beginTransaction()
                        .replace(R.id.containerFragment, Circles.newInstance(position, name))
                        .commit();
                toolbar.setTitle(R.string.drawer_item_1);
                break;
            }
            case 2: {
                fragmentManager.beginTransaction()
                        .replace(R.id.containerFragment, Circles.newInstance(position, name))
                        .commit();
                toolbar.setTitle(R.string.drawer_item_2);
                break;
            }
            case 3: {
                fragmentManager.beginTransaction()
                        .replace(R.id.containerFragment, Circles.newInstance(position, name))
                        .commit();
                toolbar.setTitle(R.string.drawer_item_3);
                break;
            }
            case 4: {
                fragmentManager.beginTransaction()
                        .replace(R.id.containerFragment, Circles.newInstance(position, name))
                        .commit();
                toolbar.setTitle(R.string.drawer_item_4);
            }
        }



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
            Intent i = new Intent(getApplicationContext(), about.class);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.logout) {
            //Logging out user through parse script
            ParseUser.logOut();
            Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), login.class);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.createCircle) {

            //Intent i = new Intent(getApplicationContext(),Create_circle.class);
            startActivity(new Intent(getApplicationContext(), Create_circle.class));
            //finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
