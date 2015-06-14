package softcone.csapp.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.rey.material.widget.Button;

import java.util.ArrayList;
import java.util.List;

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

    private com.rey.material.widget.EditText edit_title;
    private com.rey.material.widget.EditText edit_info;
    private Button btn_notice_insert;
    private ToggleButton toggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrayList = new ArrayList<NoticeData>();
        adapter = new NoticeAdapter(getActivity(), arrayList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notice, container, false);

        listView = (ExpandingListView) v.findViewById(R.id.lv_notice);

        edit_title = (com.rey.material.widget.EditText) v.findViewById(R.id.edit_title);
        edit_info = (com.rey.material.widget.EditText) v.findViewById(R.id.edit_info);
        btn_notice_insert = (Button) v.findViewById(R.id.btn_notice_insert);
        toggle = (ToggleButton) v.findViewById(R.id.toggle);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 서버에 Item class 데이터 요청
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Notice");

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
                notice.saveInBackground();

                listView.deferNotifyDataSetChanged();
            }
        });

    }


}
