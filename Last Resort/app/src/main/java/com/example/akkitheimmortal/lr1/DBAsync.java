package com.example.akkitheimmortal.lr1;

import android.app.Activity;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by akkitheimmortal on 5/11/2015.
 */
public class DBAsync {


    String Name1,RingKey1,CameraKey1,email1,Password1,LocationKey1;

    DBAsync(String Name,String email,String Password,String RingKey,String CameraKey,String LocationKey)
    {
        Name1=Name;
        RingKey1 = RingKey;
        CameraKey1 = CameraKey;
        email1=email;
        Password1=Password;
        LocationKey1 = LocationKey;
        //uid=uid;
        //Longitude=Longitude;
        //Latitude=Latitude;
    }



    class Async_Send extends AsyncTask<String,String,String>
    {



        @Override
        protected String doInBackground(String... strings) {

            try {

                Log.e("Inside doInBackgroud","");


                // url where the data will be posted
                String postReceiverUrl = "http://52.0.161.1/credentials/register.php";
//                Log.e("Post url", "postURL: " + postReceiverUrl);

                // HttpClient
                HttpClient httpClient = new DefaultHttpClient();

                // post header
                HttpPost httpPost = new HttpPost(postReceiverUrl);

                Log.e("Data ara", Name1 + email1 + Password1 + RingKey1 + CameraKey1);
                // add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                //   nameValuePairs.add(new BasicNameValuePair("uid", uid));
                nameValuePairs.add(new BasicNameValuePair("Name", Name1));
                nameValuePairs.add(new BasicNameValuePair("Email", email1));
                nameValuePairs.add(new BasicNameValuePair("Password", Password1));
                nameValuePairs.add(new BasicNameValuePair("RingKey", RingKey1));
                // nameValuePairs.add(new BasicNameValuePair("Latitude", Latitude));
                //  nameValuePairs.add(new BasicNameValuePair("Longitude", Longitude));
                nameValuePairs.add(new BasicNameValuePair("CameraKey", CameraKey1));
                nameValuePairs.add(new BasicNameValuePair("LocationKey", LocationKey1));

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            /*} catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
*/
                // execute HTTP post request
                HttpResponse response = httpClient.execute(httpPost);
  /*          } catch (IOException e) { 
                e.printStackTrace();
  */
                HttpEntity resEntity = response.getEntity();

                if (resEntity != null) {

                   final String responseStr = EntityUtils.toString(resEntity).trim();
                    Log.v("Send data to DB", "Response: " +  responseStr);

                }
            }catch (IOException e) {
                    e.printStackTrace();
                }

                // you can add an if statement here and do other actions based on the response
           // }


            return null;
        }

    }

    public void SendData()
    {
        Log.v("Send data function", " ");

        new Async_Send().execute();
 //       Activity activity = new MainActivity();

    }
}
