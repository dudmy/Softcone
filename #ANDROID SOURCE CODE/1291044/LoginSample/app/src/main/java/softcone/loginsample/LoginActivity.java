package softcone.loginsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by YuJin on 2015-05-11.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    String shop, branch, password, username;
    Button btnLogin, btnSignUp;
    EditText edtShop, edtBranch, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtShop = (EditText)findViewById(R.id.edt_shop);
        edtBranch = (EditText)findViewById(R.id.edt_branch);
        edtPassword = (EditText)findViewById(R.id.edt_password);

        btnLogin = (Button)findViewById(R.id.btn_login);
        btnSignUp = (Button)findViewById(R.id.btn_signUp);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            // Login Button
            case R.id.btn_login:
                shop = edtShop.getText().toString();
                branch = edtBranch.getText().toString();
                password = edtPassword.getText().toString();
                username = shop + "/" + branch;

                // Send data to Parse.com for verification
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            // If user exist and authenticated, send user to Welcome.class
                            Intent intent = new Intent(getBaseContext(), Welcome.class);
                            startActivity(intent);
                            Toast.makeText(getBaseContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getBaseContext(), "No such user exist, please sign up", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

            // Sign up Button
            case R.id.btn_signUp:
                shop = edtShop.getText().toString();
                branch = edtBranch.getText().toString();
                password = edtPassword.getText().toString();
                username = shop + "/" + branch;

                // Force user to fill up the form
                if (shop.equals("") && branch.equals("") && password.equals("")) {
                    Toast.makeText(getBaseContext(), "Please complete the sign up form", Toast.LENGTH_SHORT).show();
                } else {
                    // Save new user data into Parse.com Data Storage
                    ParseUser user = new ParseUser();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(getBaseContext(), "Successfully Signed up, please log in.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getBaseContext(), "Sign up Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
        }
    }
}
