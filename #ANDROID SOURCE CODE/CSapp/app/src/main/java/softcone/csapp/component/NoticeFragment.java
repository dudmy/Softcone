package softcone.csapp.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

<<<<<<< HEAD
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

=======
>>>>>>> 5f4edfe2180e73ed9e9db8481d13ac3e8f04b105
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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_notice, container, false);

        listView = (ExpandingListView) v.findViewById(R.id.lv_notice);
        arrayList = new ArrayList<NoticeData>();
<<<<<<< HEAD
        adapter = new NoticeAdapter(getActivity(), arrayList);

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
=======


        arrayList.add(new NoticeData(true, "택배기계 고장", "월요일 즈음에 고쳐주신다고 합니다.", "2015.06.12", 170));
        arrayList.add(new NoticeData(true, "일할 때 핸드폰 만지지 말 것", "걸리면 자름^___^", "2015.06.14", 170));

        adapter = new NoticeAdapter(getActivity(), arrayList);

        listView.setAdapter(adapter);
>>>>>>> 5f4edfe2180e73ed9e9db8481d13ac3e8f04b105

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setAdapter(adapter);
    }
}
