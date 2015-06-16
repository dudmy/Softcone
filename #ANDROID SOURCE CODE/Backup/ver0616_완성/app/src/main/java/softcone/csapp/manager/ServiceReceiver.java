package softcone.csapp.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by YuJin on 2015-06-14.
 */
public class ServiceReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if(action.equals(Intent.ACTION_PACKAGE_ADDED)){

            // 앱이 설치되었을 때
            Intent i = new Intent(context, AlarmService.class);
            context.startService(i);

        } else if(action.equals(Intent.ACTION_PACKAGE_REPLACED)){

            // 앱이 업데이트 되었을 때
            Intent i = new Intent(context, AlarmService.class);
            context.startService(i);
        }

    }

}
