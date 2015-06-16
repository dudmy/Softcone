package softcone.csapp.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import softcone.csapp.MainActivity;
import softcone.csapp.R;
import softcone.csapp.list.ExpandingListView;
import softcone.csapp.list.NoticeAdapter;
import softcone.csapp.list.NoticeData;

/**
 * Created by YuJin on 2015-06-09.
 */
public class NoticeFragment extends Fragment {

    private ExpandingListView listView;
    private ArrayList<NoticeData> arrayList;
    public NoticeAdapter adapter;

    private EditText edit_title;
    private EditText edit_info;
    private Button btn_notice_insert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) getActivity()).setActionBarTitle("공지사항");

        arrayList = new ArrayList<>();
        adapter = new NoticeAdapter(getActivity(), arrayList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notice, container, false);

        listView = (ExpandingListView) v.findViewById(R.id.lv_notice);

        edit_title = (EditText) v.findViewById(R.id.edit_title);
        edit_info = (EditText) v.findViewById(R.id.edit_info);
        btn_notice_insert = (Button) v.findViewById(R.id.btn_notice_insert);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 서버에 Item class 데이터 요청
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Notice");

        query.orderByDescending("createdAt");
        query.whereEqualTo("username", MainActivity.username);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {

                    // 받아온 데이터 리스트를 돌면서 NoticeData 추가
                    for (ParseObject po : parseObjects) {
                        arrayList.add(new NoticeData(po.getObjectId(), po.getBoolean("toggle"),
                                po.getString("title"), po.getString("info"), po.getCreatedAt(), 170));
                    }
                    listView.setAdapter(adapter);
                }
            }
        });

        btn_notice_insert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ParseObject notice = new ParseObject("Notice");

                notice.put("info", edit_info.getText().toString());
                notice.put("title", edit_title.getText().toString());
                notice.put("toggle", true);
                notice.put("username", MainActivity.username);

                notice.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        addNewNotice();
                    }
                });
            }
        });

    }

    // 화면 갱신을 위한 메소드
    public void addNewNotice() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Notice");

        query.whereEqualTo("username", MainActivity.username);
        query.orderByDescending("createdAt");

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject po, ParseException e) {

                if (e == null) {
                    arrayList.add(new NoticeData(po.getObjectId(), po.getBoolean("toggle"),
                            po.getString("title"), po.getString("info"), po.getCreatedAt(), 170));
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }


}
