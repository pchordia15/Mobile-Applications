package com.example.akkitheimmortal.lr1;



/**
 * Created by akkitheimmortal on 5/9/2015.
 */
    import android.content.Intent;
    import android.app.Service;
    import android.os.IBinder;
    import android.util.Log;
    import android.app.Activity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.TextView;
    import android.widget.Toast;

public class MyService extends Service {

        private static final String TAG = "com.example.ServiceExample";

        @Override
        public void onCreate() {
            Log.i(TAG, "Service onCreate");
            Toast.makeText(this, " Service onCreate ",
                    Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {
                public void run() {


                }
            }).start();



        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {

            return Service.START_STICKY;
            }

        @Override
        public IBinder onBind(Intent arg0) {
            // TODO Auto-generated method stub
            Log.i(TAG, "Service onBind");
            Toast.makeText(getApplicationContext(), " Service onBind",
                    Toast.LENGTH_SHORT).show();

            Log.i(TAG, "Service onStartCommand");
            Toast.makeText(this, " Service onStartCommand ",
                    Toast.LENGTH_SHORT).show();

            for (int i = 0; i < 3; i++)
            {
                long endTime = System.currentTimeMillis() + 10*1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) {
                        }
                    }
                }
                Log.i(TAG, "Service running");
            }



            return null;
        }

        @Override
        public void onDestroy() {
            Log.i(TAG, "Service onDestroy");
        }
    }

