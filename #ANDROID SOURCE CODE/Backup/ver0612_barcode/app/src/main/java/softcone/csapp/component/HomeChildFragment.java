package softcone.csapp.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import softcone.csapp.R;
import softcone.csapp.list.ItemAdapter;
import softcone.csapp.list.ItemData;

/**
 * Created by 쟈 on 2015-05-08.
 */
public class HomeChildFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int position;

    public static View v;

    private ListView listView;
    private ArrayList<ItemData> arrayList;
    private ItemAdapter adapter;

    public static HomeChildFragment newInstance(int position) {
        HomeChildFragment f = new HomeChildFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (ListView) v.findViewById(R.id.lv_home);
        arrayList = new ArrayList<ItemData>();

        /**
         * 현재는 리스트에 임의의 값을 추가
         * @todo Parse 이용해 편의점 물품 목록을 받아와 추가하기
         */
        arrayList.add(new ItemData(null, "허니버터칩", "D-day", "13:00:00"));
        arrayList.add(new ItemData(null, "허니버터칩2", "D-day", "13:00:00"));
        arrayList.add(new ItemData(null, "허니버터칩3", "D-day", "13:00:00"));
        arrayList.add(new ItemData(null, "허니버터칩3", "D-day", "13:00:00"));
        arrayList.add(new ItemData(null, "허니버터칩3", "D-day", "13:00:00"));
        arrayList.add(new ItemData(null, "허니버터칩3", "D-day", "13:00:00"));
        arrayList.add(new ItemData(null, "허니버터칩3", "D-day", "13:00:00"));
        arrayList.add(new ItemData(null, "허니버터칩3", "D-day", "13:00:00"));
        arrayList.add(new ItemData(null, "허니버터칩3", "D-day", "13:00:00"));
        arrayList.add(new ItemData(null, "허니버터칩3", "D-day", "13:00:00"));
        arrayList.add(new ItemData(null, "허니버터칩3", "D-day", "13:00:00"));
        adapter = new ItemAdapter(v.getContext(), arrayList);

        listView.setAdapter(adapter);
    }

}
