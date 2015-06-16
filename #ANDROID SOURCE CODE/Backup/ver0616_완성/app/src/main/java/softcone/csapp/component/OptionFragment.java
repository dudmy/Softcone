package softcone.csapp.component;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.rey.material.widget.EditText;

import com.parse.ParseUser;

import softcone.csapp.LoginActivity;
import softcone.csapp.MainActivity;
import softcone.csapp.R;
import softcone.csapp.manager.SharedPreference;

/**
 * Created by YuJin on 2015-06-09.
 */
public class OptionFragment extends Fragment implements View.OnClickListener{

    TextView tvUser;
    Button btnLogout;
    EditText et_time;
    SharedPreference pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) getActivity()).setActionBarTitle("설정");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_option, container, false);

        tvUser = (TextView) v.findViewById(R.id.tv_user);
        btnLogout = (Button) v.findViewById(R.id.btn_logout);
        et_time = (EditText) v.findViewById(R.id.et_time);

        btnLogout.setOnClickListener(this);

        pref = new SharedPreference(getActivity());
        et_time.setText(pref.getValue("alarm_time", "30"));

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvUser.setText("You are logged in as " + MainActivity.username);

        et_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                pref.put("alarm_time", et_time.getText().toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                ParseUser.logOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }


}
