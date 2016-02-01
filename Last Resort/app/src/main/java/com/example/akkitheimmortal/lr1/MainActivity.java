package com.example.akkitheimmortal.lr1;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/*       Welcome to Last Resort
*     An app to find your lost phone with the various features provided in the application.
*     This app has various features such as:-
*     1. Login - to avoid unauthorized user access to your phone.
*     2. Find my Phone - Rings your phone with the current ringtone of your phone to identify where your phone is kept.
*     3. GPS information - You can track the location (latitude and longitude) of your phone with this feature.
*     4. Camera - To capture the picture of your phone where it is.
*     5. Lock Setting - To lock the phone as soon as you click on the button. This feature helps you secure your phone from
*                       unauthorized access.
*
*     Extra:
*     1. Erase SD Card - This feature helps you erase all the data from your phone SD card so that no one can access it.
*
*     Project Members :-
*     1. Priyanka Chordia
*     2. Akshay Gore
*
*     Sources Referred :-
*
*     1. http://stackoverflow.com/questions/5788413/camera-launch-on-button-click-in-android
*     2. http://developer.android.com/guide/topics/media/camera.html
*     3. http://www.javacodegeeks.com/2010/09/android-location-based-services.html
*     4. http://karanbalkar.com/2014/01/tutorial-71-implement-lock-screen-in-android/
*     5. http://stackoverflow.com/questions/2618182/how-to-play-ringtone-alarm-sound-in-android
*     6. http://stackoverflow.com/questions/21645572/wipeout-all-sdcard-files-programmaticaly
*     7. http://developer.android.com/guide/topics/admin/device-admin.html#lock
*     8. https://www.codeofaninja.com/2013/04/android-http-client.html
*     9. http://stackoverflow.com/questions/9563131/connect-android-app-with-the-database-from-a-website
*     10. http://www.pinoytux.com/linux/tip-testing-your-phpmysql-connection
*
*
*     Front End :- Android Studio
*     Back End :- Amazon Web Services EC2, Php
*
*     Hurdles in Project :- Developing Front End of our project was not of much difficulty as compared to back end.
*                           We tried with all the possible ways we could do the back end using EC2. We were successful in
*                           sending the data to server. We tried every way to retrieve the data from the server but to no
*                           success.
*
* */


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView registerScreen = (TextView) findViewById(R.id.link_to_register);

        registerScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });




        Button loginbtn = (Button) findViewById(R.id.btnLogin);
        loginbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                try {
                    Intent i = new Intent(MainActivity.this, firstpage.class);
                    startActivity(i);
                } catch(Exception e){
                    registerScreen.setText(e.getStackTrace().toString());
                }
            }
        });


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
