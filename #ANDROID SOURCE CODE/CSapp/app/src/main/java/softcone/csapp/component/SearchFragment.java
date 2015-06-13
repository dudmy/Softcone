package softcone.csapp.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

import softcone.csapp.R;
import softcone.csapp.list.SearchAdapter;

/**
 * Created by YuJin on 2015-06-09.
 */
public class SearchFragment extends Fragment {

    private SearchView search;

    private ListView listView;
    private ArrayList<ParseObject> datas = new ArrayList<>();
    public SearchAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_search, container, false);

        search = (SearchView) v.findViewById(R.id.searchView);
        search.setQueryHint("제품명 검색");
        search.setOnQueryTextListener(queryListener);

        listView = (ListView) v.findViewById(R.id.lv_search);
        adapter = new SearchAdapter(getActivity(), datas);

        return v;
    }

    private SearchView.OnQueryTextListener queryListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {

            try {

                // 서버에 Item class 데이터 요청
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Item");

                // 서버에 이름이 포함되는 데이터만 검색
                parseQuery.whereContains("name", query);

                // 읽어온 데이터를 List 에 저장
                datas.addAll(parseQuery.find());

                listView.setAdapter(adapter);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

}
