package softcone.csapp.component;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

import softcone.csapp.R;
import softcone.csapp.list.ItemAdapter;

/**
 * Created by 쟈 on 2015-05-08.
 */
public class HomeChildFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int position;

    public static View v;

    private ListView listView;

    // parse.com 에서 읽어온 object 들을 저장할 List
    ArrayList<ParseObject> datas = new ArrayList<>();

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

        listView = (ListView) v.findViewById(R.id.lv_home);
        adapter = new ItemAdapter(v.getContext(), datas);

        try {

            // 서버에 Item class 데이터 요청
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Item");

            // 읽어온 데이터를 List 에 저장
            datas.addAll(query.find());

            listView.setAdapter(adapter);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return v;
    }

}
