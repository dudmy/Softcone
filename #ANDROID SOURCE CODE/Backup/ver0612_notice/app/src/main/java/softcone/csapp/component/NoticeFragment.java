package softcone.csapp.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


        arrayList.add(new NoticeData(true, "택배기계 고장", "월요일 즈음에 고쳐주신다고 합니다.", "2015.06.12", 170));
        arrayList.add(new NoticeData(true, "일할 때 핸드폰 만지지 말 것", "걸리면 자름^___^", "2015.06.14", 170));

        adapter = new NoticeAdapter(getActivity(), arrayList);

        listView.setAdapter(adapter);

        return v;
    }

}
