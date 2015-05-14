package softcone.csapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by YuJin on 2015-04-24.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private Spinner mSpinCsName, mSpinBranch;
    private ArrayAdapter<String> mAdaptCsName, mAdaptBranch;
    private Button mBtnLogin, mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSpinCsName = (Spinner)findViewById(R.id.spinner_csName);
        mSpinBranch = (Spinner)findViewById(R.id.spinner_branch);
        mAdaptCsName = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        mAdaptCsName.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mAdaptBranch = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        mAdaptBranch.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        ParseQuery query = new ParseQuery("shop");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if(e == null) {
                    for (int i = 0; i < parseObjects.size(); i++) {
                        String name = parseObjects.get(i).getString("shop_name");
                        String branch = parseObjects.get(i).getString("branch");
                        mAdaptCsName.add(name);
                        mAdaptBranch.add(branch);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSpinCsName.setAdapter(mAdaptCsName);
        mSpinBranch.setAdapter(mAdaptBranch);

        mBtnLogin = (Button)findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mBtnRegister = (Button)findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.btn_login:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_register:
                break;
        }
    }
}
