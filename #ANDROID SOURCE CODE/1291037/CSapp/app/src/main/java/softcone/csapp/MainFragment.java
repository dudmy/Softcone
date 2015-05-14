package softcone.csapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by 쟈 on 2015-05-08.
 */
public class MainFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;

    public static View v;

    private ListView listView;
    private ArrayList<ItemData> arrayList;
    private ItemAdapter adapter;

    public static MainFragment newInstance(int position) {
        MainFragment f = new MainFragment();
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

        v = inflater.inflate(R.layout.fragment_main, container, false);

        listView = (ListView) v.findViewById(R.id.list_item);
        arrayList = new ArrayList<ItemData>();


        listView = (ListView) v.findViewById(R.id.list_item);
        arrayList = new ArrayList<ItemData>();
        adapter = new ItemAdapter(v.getContext(), arrayList);

        arrayList.add(new ItemData(null, "허니버터칩", "D-day", "13:00:00"));
        arrayList.add(new ItemData(null, "허니버터칩2", "D-day", "13:00:00"));
        arrayList.add(new ItemData(null, "허니버터칩3", "D-day", "13:00:00"));
        adapter = new ItemAdapter(v.getContext(), arrayList);

        listView.setAdapter(adapter);

        return v;
    }

}
