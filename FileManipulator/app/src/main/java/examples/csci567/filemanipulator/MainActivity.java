package examples.csci567.filemanipulator;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {


    public static BufferedReader rbuff;
    public static BufferedWriter wbuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/pc.txt");
            FileReader filereader = new FileReader(file);
            rbuff = new BufferedReader(filereader);
            FileWriter filewriter = new FileWriter(file);
            wbuff = new BufferedWriter(filewriter);
        } catch (Exception e) {
            Log.e("Errors: ", e.toString());
        }
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements OnClickListener {
        View rootView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Button button1 = (Button) rootView.findViewById(R.id.button);
            Button button2 = (Button) rootView.findViewById(R.id.button2);
            Button button3 = (Button) rootView.findViewById(R.id.button3);
            button1.setOnClickListener(this);
            button2.setOnClickListener(this);
            button3.setOnClickListener(this);
            return rootView;
        }


        public void onClick(View src) {
            switch (src.getId()) {
                case R.id.button3:
                    EditText et = (EditText) rootView.findViewById(R.id.text1);
                    String text = et.getText().toString();
                    try {
                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/pc.txt");
                        FileWriter filewriter = new FileWriter(file);
                        wbuff = new BufferedWriter(filewriter);
                        wbuff.write(text);
                        wbuff.close();
                        Toast.makeText(getActivity().getApplicationContext(),"Replace Button Pressed",Toast.LENGTH_SHORT).show();
                        et.setText("");
                    } catch (Exception e) {
                        Log.e("Errors while Replace: ", e.toString());
                    }
                    break;

                case R.id.button:
                    String contents = "";
                    try {
                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/pc.txt");
                        FileReader filereader = new FileReader(file);
                        rbuff = new BufferedReader(filereader);
                        TextView tv = (TextView) rootView.findViewById(R.id.textView);
                        EditText et2 = (EditText) rootView.findViewById(R.id.text1);
                        contents = rbuff.readLine();
                        filereader.close();
                        tv.setText(contents);
                        rbuff.close();
                        Toast.makeText(getActivity().getApplicationContext(),"Read Button Pressed",Toast.LENGTH_SHORT).show();
                        et2.setText("");

                    } catch (Exception e) {
                        Log.e("Errors while Reading: ", e.toString());
                        contents = "";
                    } finally {
                        TextView tv = (TextView) rootView.findViewById(R.id.textView);
                        tv.setText(contents);
                    }

            case R.id.button2:
                EditText et1 = (EditText) rootView.findViewById(R.id.text1);
                String text1 = et1.getText().toString();
                try {
                    File file = new File(Environment.getExternalStorageDirectory().getPath() + "/pc.txt");
                    FileWriter filewriter = new FileWriter(file,true);
                    wbuff = new BufferedWriter(filewriter);
                    wbuff.write(text1);
                    wbuff.close();
                    et1.setText("");
                } catch (Exception e) {
                    Log.e("Errors while Append: ", e.toString());
                }
            }
        }
    }
}
