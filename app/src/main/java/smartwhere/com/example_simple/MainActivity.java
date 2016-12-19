package smartwhere.com.example_simple;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.proximity.library.Action;
import com.proximity.library.NameValuePair;
import com.proximity.library.ProximityControl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_FINE_LOCATION = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        /* optionally add receiver for communication errors */
        LocalBroadcastManager.getInstance(this).registerReceiver(mCommunicationsErrorReceiver,
                new IntentFilter("communication-error-action"));

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_FINE_LOCATION);
            }
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
	/* optionally start proximity service to make sure it is running.
	* if SERVICE_AUTO_START = true in your ProximityConfiguration class, the service will
	* autostart on coldboot, user_present or power changes.
	* If it is set to false, then you will need to start and stop the service as desired.
	*/
        ProximityControl.startService(getApplicationContext());
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

    /* example implementation of custom action receiver */
    private BroadcastReceiver mCustomActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String object_type = (intent.hasExtra("object_type") ? intent.getStringExtra("object_type") : "");
            String state = (intent.hasExtra("state") ? intent.getStringExtra("state") : "0");
            if (intent.hasExtra("action")) {
                Action action = (Action) intent.getSerializableExtra("action");
                int action_type = action.getType();
                /* action_type will be 127 (Custom Action)
                * loop through name/value pairs to get the data
                * i.e. employee_id
                * and order_id
                * */
                List<NameValuePair> nvps = action.getNameValues();
                if(nvps != null) {
                    for (NameValuePair nvp : nvps) {
                        String name = nvp.getName();
                        String value = nvp.getValue();
                    }
                }
            }
        }
    };

    /* optionally trap communication errors.  */
    private BroadcastReceiver mCommunicationsErrorReceiver = new BroadcastReceiver() {
        @Override

        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent

            if(intent.hasExtra("error")) {
                Throwable error = (Throwable) intent.getSerializableExtra("error");
                Log.i("my tag", "Communication Error received.  Error message = " + error.getMessage());
                Toast.makeText(context, "Communication Error received: " + error.getMessage(), Toast.LENGTH_LONG).show();
            } else if(intent.hasExtra("no_content")) {
                String code = intent.getStringExtra("no_content");
                Toast.makeText(context,"No content found for tag "+code, Toast.LENGTH_LONG).show();
            }

        }
    };


}


