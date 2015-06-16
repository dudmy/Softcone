package softcone.csapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

/**
 * Created by YuJin on 2015-04-24.
 */
public class LoadingActivity extends Activity {

    public static boolean resume_bool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // Determine whether the current user is an anonymous user
                if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {

                    // If user is anonymous, send the user to LoginActivity.class
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();

                } else {

                    // If current user is NOT anonymous user
                    // Get current user data from Parse.com
                    ParseUser currentUser = ParseUser.getCurrentUser();

                    if (currentUser != null) {

                        // Send logged in users to MainActivity.class
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        startActivity(new Intent(getApplicationContext(), NoticeDialog.class));
                        finish();

                    } else {

                        // Send user to LoginActivity.class
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();

                    }
                }

            }
        }, 2000);
    }
}
