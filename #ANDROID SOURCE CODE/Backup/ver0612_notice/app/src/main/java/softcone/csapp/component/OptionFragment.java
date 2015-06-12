package softcone.csapp.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

import softcone.csapp.R;

/**
 * Created by YuJin on 2015-06-09.
 */
public class OptionFragment extends Fragment implements View.OnClickListener{

    String user;
    TextView tvUser;
    Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_option, container, false);

        tvUser = (TextView) v.findViewById(R.id.tv_user);
        btnLogout = (Button) v.findViewById(R.id.btn_logout);

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        user = currentUser.getUsername().toString();

        tvUser.setText("You are logged in as " + user);

        btnLogout.setOnClickListener(this);

        return v;
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
