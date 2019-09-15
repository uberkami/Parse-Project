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

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

  @RequiresApi(api = Build.VERSION_CODES.O)
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

    ParseObject object = new ParseObject("ExampleObject");
    object.put("myNumber", "123");
    object.put("myString", "rob");

    object.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException ex) {
        if (ex == null) {
          Log.i("Parse Result", "Successful!");
        } else {
          Log.i("Parse Result", "Failed" + ex.toString());
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
