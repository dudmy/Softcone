package softcone.csapp.manager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by YuJin on 2015-06-14.
 */
public class AlarmService extends Service {

    Thread thread;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    int timeGap;
    int alarmTime;

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreference pref = new SharedPreference(this);
        alarmTime = Integer.parseInt(pref.getValue("alarm_time", "1"));

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
            while (!thread.isInterrupted()) {
                // 오늘 날짜 가져오기
                final Date now = new Date();

                // 서버에 Item class 데이터 요청
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Life");

                // 오늘 날짜에 해당하는 것만 검색
                query.whereEqualTo("day", dateFormat.format(now));

                query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> parseObjects, ParseException e) {
                    if (e == null) {
                        for (ParseObject po : parseObjects) {

                            // 현재 시간과 유통기한 시간과 차이 구하기
                            String[] itemTime = po.getString("time").split(":");
                            String[] nowTime = timeFormat.format(now).split(":");
                            timeGap = Integer.parseInt(itemTime[1]) - Integer.parseInt(nowTime[1]);

                            // 폐기 시간 alarmTime 분 전에 푸시
                            if (itemTime[0].equals(nowTime[0]) && timeGap == alarmTime) {

                                ParseQuery pushQuery = ParseInstallation.getQuery();
                                pushQuery.whereEqualTo("channels", "csapp");

                                ParsePush push = new ParsePush();
                                push.setQuery(pushQuery);
                                push.setMessage(po.getString("name") + " 폐기 " +
                                        String.valueOf(alarmTime) + "분 전 입니다.");
                                push.sendInBackground();
                                }
                            }
                        }
                        }
                    });

                // 31초 마다 Thread 돌리기
                SystemClock.sleep(31000);
            }
            }
        });

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        thread.start();

        Toast.makeText(getApplicationContext(), "편해?편앱! 서비스 시작", Toast.LENGTH_SHORT).show();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        thread.interrupt();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
