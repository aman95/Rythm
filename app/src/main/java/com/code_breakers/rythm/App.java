package com.code_breakers.rythm;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by aman on 12/5/15.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "Lu5JsXQMfDWxzvI9MPKFEJ4FyAAaY8ZDmfOoPXqb", "dZBg7x6QBIJGRXI1eRGldJU7KPRTF5htOazSwCQV");
    }
}
