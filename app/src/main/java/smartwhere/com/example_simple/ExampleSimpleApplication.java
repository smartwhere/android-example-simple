

package smartwhere.com.example_simple;
import android.app.Application;
import android.content.res.Configuration;

import com.proximity.library.ProximityControl;

import java.util.HashMap;

public class ExampleSimpleApplication extends Application {
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!

    private static final String PROXIMITY_API_KEY = "192607";
    private static final String PROXIMITY_API_SECRET = "nxj9tkkqltp";
    private static final String PROXIMITY_APPLICATION_ID = "2917694";

    @Override
    public void onCreate() {
        super.onCreate();

        /* configure Proximity Service
            this needs to be done at least once somewhere within the application.  It DOES NOT need
            to be done in the Application class.
            Create a hashmap and pass configuation values as shown below
         */

        HashMap<String, String> configuration = new HashMap<>();

        // REQUIRED: Retreive API_KEY and API_SECRET from the Web UI under Admin->Account Information
        configuration.put("API_KEY", PROXIMITY_API_KEY);
        configuration.put("API_SECRET", PROXIMITY_API_SECRET);

        // REQUIRED: Application ID must be defined in Web UI under Admin->Applications
        configuration.put("APPLICATION_ID", PROXIMITY_APPLICATION_ID);

        // IMPORTANT: should not release with DEBUG_LOG enabled
        configuration.put("DEBUG_LOG","FALSE");

        /* IMPORTANT: SERVICE_AUTO_START must be enabled in order to provide background processing - If disabled, use ProximityControl.startService(context) and
            ProximityControl.stopService(context) to enable proximity features
         */
        configuration.put("SERVICE_AUTO_START","TRUE");

        /* Enable geofences under 100m */
        configuration.put("ENABLE_GEOFENCE_RANGING","TRUE");

        //Optional activity to handle custom event actions
        configuration.put("CUSTOM_ACTION_ACTIVITY","smartwhere.com.example_simple.ProximityCustomActionActivity");

        /*  PROMPT_FOR_LOCATION_PERMISSION
            FALSE = DO NOT prompt for location permissions automatically. Default is TRUE which will prompt for permissions if needed.
            Set to false in order to handle permission prompting

         */
        configuration.put("PROMPT_FOR_LOCATION_PERMISSION","FALSE");

        ProximityControl.configureService(getApplicationContext(), configuration);
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
