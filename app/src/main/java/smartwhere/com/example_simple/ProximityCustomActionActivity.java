package smartwhere.com.example_simple;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.proximity.library.Action;
import com.proximity.library.NameValuePair;

import java.util.List;

public class ProximityCustomActionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Context context = getApplicationContext();
        Intent intent = getIntent();

        /* check to see if we were started with a custom action intent */
        if (intent != null && intent.getAction() != null && intent.getAction().equals("custom-proximity-action")){
            setIntent(null); // set activity intent to null to clear sticky intent

            /* determine which type of proximity object triggered this event */
            String object_type = (intent.hasExtra("object_type") ? intent.getStringExtra("object_type") : "");
            // object_type will be; beacon, tag, fence, or wifi

            /* we can determine which state triggered this event */
            String state = (intent.hasExtra("state") ? intent.getStringExtra("state") : "0");
                  /* state will be; 0=DISCOVERED, 1=ENTERED, 2=DWELLED,  3=EXITED */


            /* get the action object associated with this event */
            if(intent.hasExtra("action")) {
                Action action = (Action) intent.getSerializableExtra("action");
                int action_type = action.getType();

				 /* action_type will be 127 (Custom Action)
				    The Action class associated with the proximity device will include the action type.
				    You can get a list of attributes associated with this action, that are defined
				    on the server for custom actions.

				    Event variables entered in the event UI are also exposed in the action name/value pairs

				 */


                List<NameValuePair> nvps = action.getNameValues();
                if(nvps != null) {
                    for (NameValuePair nvp : nvps) {
                        String name = nvp.getName();
                        String value = nvp.getValue();

                        switch (name) {
                            case "location":
                        /* do something with location value, such as starting an activity and passing the location */
                                break;
                            case "business_name":
                        /* do something with business name value */
                                break;
                        }
                    }
                }

            }
            /* if we are done, then we can close this activity */

            this.finish();
        }
    }
}