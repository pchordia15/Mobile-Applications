package examples.csci567.simpledbpriyankachordia;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        context = this;
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

        private View rootView;
        private DbHelper db;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);

            Button add = (Button) rootView.findViewById(R.id.button);
            Button get = (Button) rootView.findViewById(R.id.button2);

            add.setOnClickListener(this);
            get.setOnClickListener(this);

            db = new DbHelper(getActivity());
            return rootView;
        }


    @Override
    public void onClick(View src) {
        switch (src.getId()) {
            case R.id.button2:
                EditText txt = (EditText) rootView.findViewById(R.id.editText);
                //To write data in the database checking for duplications

                if (db.insertText(txt.getText().toString())) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Write successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Can't write duplicate data", Toast.LENGTH_LONG).show();
                }

                txt.setText("");
                break;

            case R.id.button:

                // To Read data from the database into TextView.
                TextView txt2 = (TextView) rootView.findViewById(R.id.textView);
                txt2.setText(db.getText());
                break;
            }
        }
    }
}
