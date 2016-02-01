package com.example.akkitheimmortal.lr1;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.logging.Handler;


public class firstpage extends Activity {

    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mComponentName;
    private static ProgressDialog progressBar;
    private static int progressBarStatus = 0;
    private ComponentName mComponentName1;
    private static final String description = "Sample Administrator description";
    private static final int ADMIN_INTENT = 15;
    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds

    protected LocationManager locationManager;

    // private static Handler progressBarHandler = new Handler();
    private int fileCount = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);

        // Ringtone button

        // source code referred from http://stackoverflow.com/questions/2618182/how-to-play-ringtone-alarm-sound-in-android
        // on button click the phone starts to ring with the current ringtone of the phone.
        // this feature helps to search for phone by hearing the ringtone.
        // It rings the phone even though the phone is on vibrate mode.

        Button ringbtn = (Button) findViewById(R.id.button);

        ringbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                Ringtone ringtoneSound = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);

                ringtoneSound.play();
            }
        });

        //location button

        // source code for location referred from http://www.javacodegeeks.com/2010/09/android-location-based-services.html

        // on button click the current latitude and longitude of the phone can be obtained.
        // This checks for the service provider and works accordingly.
        // The latitude and longitude occurs as a toast to the user.

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new MyLocationListener()
        );
        // to find Location
        Button locationbtn = (Button) findViewById(R.id.button2);

        locationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showCurrentLocation();
            }
        });

        // to start camera

        // source code for camera referred to http://stackoverflow.com/questions/5788413/camera-launch-on-button-click-in-android
        // http://developer.android.com/guide/topics/media/camera.html

        // on button click the camera is activated and a picture of where or with whom your
        // device is, can be clicked.

        Button cambtn = (Button) findViewById(R.id.button1);

        cambtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " Clicked Succesfully ",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);
            }
        });


        // Admin Enable

        // source code referred from - http://developer.android.com/guide/topics/admin/device-admin.html#lock
        // For the lock screen feature to work we need to enable the device admin.
        // on button click the device admin is activated.

        Button adminbtn = (Button) findViewById(R.id.btnEnable);

        adminbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " Clicked Succesfully ",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName1);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, description);
                startActivityForResult(intent, ADMIN_INTENT);

                // intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN)
            }
        });


        // to lock the phone

        // source code referred from http://karanbalkar.com/2014/01/tutorial-71-implement-lock-screen-in-android/
        // This locks the phone when button is clicked.
        // For this feature to work the admin must be enabled.

        mDevicePolicyManager = (DevicePolicyManager) getSystemService(
                Context.DEVICE_POLICY_SERVICE);
        Button lockbtn = (Button) findViewById(R.id.button3);

        lockbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isAdmin = mDevicePolicyManager.isAdminActive(mComponentName);
                if (isAdmin) {
                    mDevicePolicyManager.lockNow();
                } else {
                    Toast.makeText(getApplicationContext(), "Not Registered as admin", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // to wipe the SD card

        // source code referred from - http://stackoverflow.com/questions/21645572/wipeout-all-sdcard-files-programmaticaly
        // on button click all your valuable data from sd card can be erased.
        // this feature helps when you want to secure the data of SD card from getting stolen.

        Button wipebtn = (Button) findViewById(R.id.button4);

        wipebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                new AlertDialog.Builder(getApplicationContext()).setCancelable(true)
                        .setMessage("This will attempt to wipe all directories and files on your SD card.")
                        .setPositiveButton("WIPE", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                FormatSDCard(v);
                            }
                        }).setNegativeButton("NO!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });

    }

    protected void showCurrentLocation() {

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            String message = String.format(
                    "Current Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(getApplicationContext(), message,
                    Toast.LENGTH_SHORT).show();
        }

    }

    private class MyLocationListener implements LocationListener {

        public void onLocationChanged(Location location) {
            String message = String.format(
                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

        public void onStatusChanged(String s, int i, Bundle b) {
            Toast.makeText(getApplicationContext(), "Provider status changed",
                    Toast.LENGTH_SHORT).show();
        }

        public void onProviderDisabled(String s) {
            Toast.makeText(getApplicationContext(),
                    "Provider disabled by the user. GPS turned off",
                    Toast.LENGTH_SHORT).show();
        }

        public void onProviderEnabled(String s) {
            Toast.makeText(getApplicationContext(),
                    "Provider enabled by the user. GPS turned on",
                    Toast.LENGTH_SHORT).show();
        }

    }


    public void FormatSDCard(View v){
        progressBar = new ProgressDialog(v.getContext());
        progressBar.setCancelable(false);
        progressBar.setMessage("File deleting ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();

        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < 100) {
                    //fileCount = 0;
                    // process some tasks
                    progressBarStatus = wipingSdcard();
                    // your computer is too fast, sleep 1 second
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                   /* progressBarHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });*/
                }

                // ok, file is deleted,
                if (progressBarStatus >= 100) {

                    // sleep 2 seconds, so that you can see the 100%
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // close the progress bar dialog
                    progressBar.dismiss();
                }
            }
        }).start();
    }


    public int wipingSdcard() {
        File deleteMatchingFile = new File(Environment
                .getExternalStorageDirectory().toString());
        try {
            File[] filenames = deleteMatchingFile.listFiles();
            if (filenames != null && filenames.length > 0) {
                for (File tempFile : filenames) {
                    if (tempFile.isDirectory()) {
                        wipeDirectory(tempFile.toString());
                        tempFile.delete();
                    } else {
                        tempFile.delete();
                    }
                    fileCount++;
                    progressBarStatus = fileCount;

                   /* progressBarHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });*/
                }
            } else {
                deleteMatchingFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileCount;
    }

    private void wipeDirectory(String name) {
        File directoryFile = new File(name);
        File[] filenames = directoryFile.listFiles();
        if (filenames != null && filenames.length > 0) {
            for (File tempFile : filenames) {
                if (tempFile.isDirectory()) {
                    wipeDirectory(tempFile.toString());
                    tempFile.delete();
                } else {
                    tempFile.delete();
                }
            }
        } else {
            directoryFile.delete();
        }
    }
}




