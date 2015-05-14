package softcone.csapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by YuJin on 2015-04-24.
 */
public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // 앱 아이디, 클라이언트 키 초기화
        //Parse.initialize(this, "ZVDVs21M1bzc7BncHrpMuwnwxzCK6NZX1EBWIBif", "klzIkZZs5c5zxYsn2yUMVl54CVGtyNUlUU9b4Use");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
