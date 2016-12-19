package smartwhere.com.example_simple;

import android.text.format.DateUtils;

/**
 * Created by robertwhelan on 1/27/16.
 */
public class ProximityConfiguration {

 /*
 * Example of Proximity Configuration File
 */

   /* REQUIRED SETTINGS */

   // TODO:// add account api key, secret and application id
   public static final String API_KEY = "XXXXXX";
   public static final String API_SECRET = "XXXXXX";

   // note: nfc tags include the following application id
   public static final String APPLICATION_ID = "XXXXXX";

   /* OPTIONAL SETTINGS */

    // enable output of debug messages - defaults to false
    public static final boolean DEBUG_LOG = true;

   // automatically start service on coldboot, user presence and power state changes
   // defaults to false
   public static final boolean SERVICE_AUTO_START = true;


    // The Time To Live (TTL) settings are cache times for events.  If you are using
    // custom server scripts that should be not be cached, set these to 0
    // NOTE: Here these are set to three minutes to allow the developer devices to be updated
    // rapidly
    public static final long BEACON_TTL = DateUtils.MINUTE_IN_MILLIS * 3;
    public static final long FENCE_TTL = DateUtils.MINUTE_IN_MILLIS * 3;
    public static final long WIFI_TTL = DateUtils.MINUTE_IN_MILLIS * 3;
    public static final long TAG_TTL = DateUtils.MINUTE_IN_MILLIS * 3;

    public static final String CUSTOM_ACTION_ACTIVITY = "smartwhere.com.example_simple.ProximityCustomActionActivity";


}
