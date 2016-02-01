package com.example.akkitheimmortal.lr1;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    TextView loginScreen;
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);

         loginScreen = (TextView) findViewById(R.id.link_to_login);

        final EditText name = (EditText) findViewById(R.id.reg_fullname);
        final EditText email = (EditText) findViewById(R.id.reg_email);
        final EditText Password = (EditText) findViewById(R.id.reg_password);
        final EditText RingKey = (EditText) findViewById(R.id.ringKey);
        final EditText CameraKey = (EditText) findViewById(R.id.cameraKey);
        final EditText LocationKey = (EditText) findViewById(R.id.locationKey);

        Log.d("Ram Ram :","");
        button = (Button)findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"In nlick",Toast.LENGTH_SHORT).show();
                String Name,emailid,password,ringKey,cameraKey,locationKey;

                Name = name.getText().toString();
                emailid = email.getText().toString();
                password = Password.getText().toString();
                ringKey = RingKey.getText().toString();
                cameraKey = CameraKey.getText().toString();
                locationKey = LocationKey.getText().toString();

                Log.e("Gore Values :",Name+ emailid +password+ringKey+cameraKey+locationKey);

                DBAsync dbass = new  DBAsync(Name,emailid,password,ringKey,cameraKey,locationKey);

                dbass.SendData();

            }
        });
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Closing registration screen
                // Switching to Login Screen/closing register screen


            }


    });

    }
}