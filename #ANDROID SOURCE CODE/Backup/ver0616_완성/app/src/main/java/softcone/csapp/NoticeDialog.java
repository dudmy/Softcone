package softcone.csapp;

import android.app.Activity;
import android.os.Bundle;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 쟈 on 2015-06-16.
 */
public class NoticeDialog extends Activity {

    private String notice_content = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_notice);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Notice");

        query.whereEqualTo("username", MainActivity.username);
        query.orderByAscending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    // 받아온 데이터 리스트를 돌면서 NoticeData 추가
                    for (ParseObject po : parseObjects) {
                        notice_content = notice_content + "\n"+po.getString("title")+"\n"+po.getString("info")+"\n";
                    }
                    showNotice();
                }
            }
        });

    }

    public void showNotice(){
        final SweetAlertDialog dialog = new SweetAlertDialog(this);
        dialog.setTitleText("공지사항")
                .setContentText(notice_content)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .show();
    }

}
