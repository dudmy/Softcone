package softcone.csapp.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.rey.material.widget.Button;

import java.util.ArrayList;

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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_notice, container, false);

        listView = (ExpandingListView) v.findViewById(R.id.lv_notice);
        arrayList = new ArrayList<NoticeData>();
        adapter = new NoticeAdapter(getActivity(), arrayList);

        edit_title = (com.rey.material.widget.EditText) v.findViewById(R.id.edit_title);
        edit_info = (com.rey.material.widget.EditText) v.findViewById(R.id.edit_info);
        btn_notice_insert = (Button) v.findViewById(R.id.btn_notice_insert);

        try {

            // parse.com 에서 읽어온 object 들을 저장할 List
            ArrayList<ParseObject> datas = new ArrayList<ParseObject>();

            // 서버에 Item class 데이터 요청
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Notice");

            // 읽어온 데이터를 List 에 저장
            datas.addAll(query.find());

            for (ParseObject object : datas) {
                arrayList.add(new NoticeData(object.getBoolean("toggle"), object.getString("title"), object.getString("info"),
                        object.getDate("createdAt"), 170));
            }

            datas.clear();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        btn_notice_insert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                ParseObject notice = new ParseObject("Notice");
                notice.put("info", edit_info.getText().toString());
                notice.put("title", edit_title.getText().toString());
                notice.put("toggle", true);
                notice.saveInBackground();
                listView.deferNotifyDataSetChanged();
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setAdapter(adapter);
    }


}
