/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;
import android.os.Build;
//import android.service.autofill.SaveCallback;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

    //  @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("1f1aafeba0e3ecbf91e10b1d54cd8d19e279a44c")
                .clientKey("234525ad463bd1fcd40a75ba62fb108ca3a2e236")
                .server("http://3.120.208.148:80/parse/")
                .build()
        );
/*
ParseObject tweet = new ParseObject("tweet");
tweet.put("username", "maleck");
tweet.put("tweet", "morgen ist auch noch ein tag, bro!");
tweet.saveInBackground(new SaveCallback() {
  @Override
  public void done(ParseException e) {
    if(e == null){
      //everything is ok
      Log.i("success", "tweet saved");
    } else {
      e.printStackTrace();
    }
  }
});
*/

        ParseQuery<ParseObject> query = ParseQuery.getQuery("tweet");


        query.getInBackground("hZjNHEU85X", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null && object != null) {
                    object.put("username", "Donald Trump");
                    object.put("tweet", "I am the best president that ever existed! Ever!");

                    object.saveInBackground();
                    Log.i("username", object.getString("username"));
                    Log.i("tweet", object.getString("tweet"
                    ));
                }
            }
        });

        query.getInBackground("5xjkGlhmIE", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                try {
                    object.delete();
                    object.saveInBackground();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });


        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
