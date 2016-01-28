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


   // update geofences from server defaults to every 20 minutes
   // note: as of version 15160 updates are based upon movement.
   // this is used as the maximum time if the device does not move
    public static final long GEOFENCE_UPDATE_TIME = DateUtils.HOUR_IN_MILLIS * 1;


    // frequency of  wifi network list updates from server defaults to 20 minutes
    public static final long WIFI_UPDATE_TIME = DateUtils.MINUTE_IN_MILLIS * 60;

    // The Time To Live (TTL) settings are cache times for events.  If you are using
    // custom server scripts that should be not be cached, set these to 0
    public static final long BEACON_TTL = DateUtils.MINUTE_IN_MILLIS * 20;
    public static final long FENCE_TTL = DateUtils.MINUTE_IN_MILLIS * 20;
    public static final long WIFI_TTL = DateUtils.MINUTE_IN_MILLIS * 20;
    public static final long TAG_TTL = DateUtils.MINUTE_IN_MILLIS * 20;

    // Defaults are 15 second scan interval with 2 second duration.  Here we set the
    // Interval to 30 seconds with a 3 second duration (10% duty cycle).
    // Beacon scanning is performed only when inside geofences or Wifi networks which
    // have BLE Scanning defined as enabled
    public static final long BEACON_SCAN_INTERVAL = DateUtils.SECOND_IN_MILLIS * 30;
    public static final long BEACON_SCAN_DURATION = DateUtils.SECOND_IN_MILLIS * 3;


}
