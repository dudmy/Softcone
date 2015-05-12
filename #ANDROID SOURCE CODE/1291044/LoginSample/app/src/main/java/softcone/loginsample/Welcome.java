package softcone.loginsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

/**
 * Created by YuJin on 2015-05-11.
 */
public class Welcome extends Activity {

    String user;
    TextView tvUser;
    Button btnLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvUser = (TextView) findViewById(R.id.tv_user);
        btnLogout = (Button) findViewById(R.id.btn_logout);

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        user = currentUser.getUsername().toString();

        tvUser.setText("You are logged in as " + user);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Logout current user
                ParseUser.logOut();
                finish();
            }
        });
    }
}
