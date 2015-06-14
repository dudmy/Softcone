package softcone.csapp.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;
import com.rey.material.widget.Spinner;

import softcone.csapp.R;
import softcone.csapp.manager.SPreference;

/**
 * Created by YuJin on 2015-06-09.
 */
public class OptionFragment extends Fragment implements View.OnClickListener{

    String[] time = {"1", "5", "10"};

    String user;
    TextView tvUser;
    Button btnLogout;
    Spinner spinTime;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_option, container, false);

        tvUser = (TextView) v.findViewById(R.id.tv_user);
        btnLogout = (Button) v.findViewById(R.id.btn_logout);
        spinTime = (Spinner) v.findViewById(R.id.spin_time);

        btnLogout.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.row_spn, time);
        adapter.setDropDownViewResource(R.layout.row_spn_dropdown);
        spinTime.setAdapter(adapter);

        SPreference pref = new SPreference(getActivity());
        spinTime.setSelection(pref.getValue("select_num", 0));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        user = currentUser.getUsername().toString();

        tvUser.setText("You are logged in as " + user);

        spinTime.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Spinner spinner, View view, int i, long l) {
                SPreference pref = new SPreference(getActivity());
                pref.put("select_num", spinTime.getSelectedItemPosition());
                pref.put("alarm_time", time[spinTime.getSelectedItemPosition()]);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                ParseUser.logOut();
                getActivity().finish();
                break;
        }
    }


}
