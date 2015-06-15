package softcone.csapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

import softcone.csapp.component.HomeFragment;
import softcone.csapp.component.NoticeFragment;
import softcone.csapp.component.OptionFragment;
import softcone.csapp.component.SearchFragment;
import softcone.csapp.manager.BackPressClose;

/**
 * Created by YuJin on 2015-06-09.
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private BackPressClose backPressClose;

    public final static int FRAGMENT_NOTICE = 0;
    public final static int FRAGMENT_OPTION = 1;
    public final static int FRAGMENT_HOME = 2;
    public final static int FRAGMENT_SEARCH = 3;

    private int currentFragment;

    private Button btn_notice;
    private Button btn_option;
    private Button btn_home;
    private Button btn_search;
    private Button btn_barcode;

    public static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser currentUser = ParseUser.getCurrentUser();
        username = currentUser.getUsername().toString();

        backPressClose = new BackPressClose(this);

        btn_notice = (Button) findViewById(R.id.btn_notice);
        btn_option = (Button) findViewById(R.id.btn_option);
        btn_home = (Button) findViewById(R.id.btn_home);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_barcode = (Button) findViewById(R.id.btn_barcode);

        btn_notice.setOnClickListener(this);
        btn_option.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        btn_barcode.setOnClickListener(this);

        currentFragment = FRAGMENT_HOME;
        setFragment(currentFragment);
    }

    public void setFragment(int newIndex) {

        Fragment f = null;
        f = getFragment(newIndex);

        // replace fragment
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.layout_top, f);

        // Commit the transaction
        transaction.commit();

    }

    private Fragment getFragment(int index) {
        Fragment f = null;

        switch (index) {
            case FRAGMENT_NOTICE:
                f = new NoticeFragment();
                break;
            case FRAGMENT_OPTION:
                f = new OptionFragment();
                break;
            case FRAGMENT_HOME:
                f = new HomeFragment();
                break;
            case FRAGMENT_SEARCH:
                f = new SearchFragment();
                break;
        }

        return f;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_notice:
                currentFragment = FRAGMENT_NOTICE;
                setFragment(currentFragment);
                break;

            case R.id.btn_option:
                currentFragment = FRAGMENT_OPTION;
                setFragment(currentFragment);
                break;

            case R.id.btn_home:
                currentFragment = FRAGMENT_HOME;
                setFragment(currentFragment);
                break;

            case R.id.btn_search:
                currentFragment = FRAGMENT_SEARCH;
                setFragment(currentFragment);
                break;

            case R.id.btn_barcode:
                startActivity(new Intent(this, BarcodeActivity.class));
                break;
        }
    }

    // 뒤로가기 두 번 누를 시 호출
    @Override
    public void onBackPressed() {
        backPressClose.BackClose();
    }

}