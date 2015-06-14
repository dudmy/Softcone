package softcone.csapp.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import softcone.csapp.R;
import softcone.csapp.list.ItemAdapter;

/**
 * Created by 쟈 on 2015-05-08.
 */
public class HomeChildFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int position;

    private ListView listView;
    private ItemAdapter adapter;

    // parse.com 에서 읽어온 object 들을 저장할 List
    private ArrayList<ParseObject> datas = new ArrayList<>();

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

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        listView = (ListView) v.findViewById(R.id.lv_home);
        adapter = new ItemAdapter(v.getContext(), datas);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                alertDelete(position);
                return false;
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 오늘 날짜 가져오기
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        // 서버에 Item class 데이터 요청
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Life");

        switch (position) {
            case 0:
                // 오늘 날짜에 해당하는 것만 검색
                query.whereEqualTo("day", format.format(now));
                break;

            case 1:
                // 해당 주의 마지막 날짜 : 토요일
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                // 오늘 날짜 이후
                query.whereGreaterThanOrEqualTo("day", format.format(now));
                // 이번 주 마지막 날 이전
                query.whereLessThanOrEqualTo("day", format.format(cal.getTime()));
                break;

            case 2:
                // 오늘 날짜 이후
                query.whereGreaterThanOrEqualTo("day", format.format(now));
                break;
        }

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    // 읽어온 데이터를 List 에 저장
                    datas.addAll(parseObjects);

                    listView.setAdapter(adapter);
                }
            }
        });

    }


    private void alertDelete(final int po) {
        SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
        dialog.setTitleText("정말 삭제하시겠습니까?")
                .setContentText("제품을 폐기했는지 확인하세요.")
                .setCancelText("취소")
                .setConfirmText("삭제")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.setTitleText("삭제 취소!")
                                .setContentText("제품의 폐기여부를 확인하세요.")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.setTitleText("삭제 완료!")
                                .setContentText("해당 제품을 삭제했습니다.")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                        // 데이터베이스에서 지우기
                        datas.get(po).deleteInBackground();
                    }
                })
                .show();
    }


}
