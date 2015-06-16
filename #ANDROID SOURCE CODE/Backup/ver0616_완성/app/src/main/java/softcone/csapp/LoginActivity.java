package softcone.csapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.rey.material.widget.Spinner;

/**
 * Created by YuJin on 2015-06-09.
 */
public class LoginActivity extends ActionBarActivity implements View.OnClickListener{

    String shop, branch, password, username;
    Button btnLogin, btnSignUp;
    EditText edtPassword;
    private String[] string_shop = {"CU", "GS25", "세븐일레븐"};
    private String[] string_branch = {"한성대점"};

    private Spinner spinner_shop;
    private Spinner spinner_branch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        spinner_shop = (Spinner)findViewById(R.id.spinner_shop);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_spn, string_shop);
        adapter.setDropDownViewResource(R.layout.row_spn_dropdown);
        spinner_shop.setAdapter(adapter);

        spinner_branch = (Spinner)findViewById(R.id.spinner_branch);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), R.layout.row_spn, string_branch);
        adapter2.setDropDownViewResource(R.layout.row_spn_dropdown);
        spinner_branch.setAdapter(adapter2);

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
                shop = string_shop[spinner_shop.getSelectedItemPosition()];
                branch = string_branch[spinner_branch.getSelectedItemPosition()];
                password = edtPassword.getText().toString();
                username = shop + "/" + branch;

                // Send data to Parse.com for verification
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            // If user exist and authenticated, send user to MainActivity.class
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            startActivity(new Intent(getApplicationContext(), NoticeDialog.class));

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
                shop = string_shop[spinner_shop.getSelectedItemPosition()];
                branch = string_branch[spinner_branch.getSelectedItemPosition()];
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
