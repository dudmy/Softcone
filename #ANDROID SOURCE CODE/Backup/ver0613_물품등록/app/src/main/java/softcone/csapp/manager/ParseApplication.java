package softcone.csapp.manager;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by YuJin on 2015-06-09.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here.
        // Application ID, Client Key
        Parse.initialize(this, "ZVDVs21M1bzc7BncHrpMuwnwxzCK6NZX1EBWIBif", "klzIkZZs5c5zxYsn2yUMVl54CVGtyNUlUU9b4Use");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}
