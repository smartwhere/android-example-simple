package smartwhere.com.example_simple;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
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
import com.proximity.library.Attribute;
import com.proximity.library.NameValuePair;
import com.proximity.library.ProximityControl;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_FINE_LOCATION = 10001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        /* example of how to ask for location permissions if you have permission permissions disabled
            In the application class ExampleSimpleApplication, we have configured the proximity SDK NOT to prompt for permission
            so that we can handle it ourselves...
            Normally, you would put this logic somewhere within your application where prompting for location permission makes sense.
         */

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_FINE_LOCATION);
            }
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        Attribute.getInstance(getApplicationContext()).setAttributeValue("vip","1");



	/* optionally start proximity service to make sure it is running.
	* if SERVICE_AUTO_START = true in your Proximity configuration (which is the default), the service will
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

}


