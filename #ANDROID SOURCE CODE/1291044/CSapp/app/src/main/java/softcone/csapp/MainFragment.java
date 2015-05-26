package softcone.csapp;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by 쟈 on 2015-05-08.
 */
public class MainFragment extends ListFragment {

    private static final String ARG_POSITION = "position";
    private int position;

    public static View v;

    private MainListView listView;
    private ArrayList<ItemData> arrayList;
    private ItemAdapter adapter;

    private LinearLayout layoutBottom;
    private int mQuickReturnHeight;

    private static final int STATE_ONSCREEN = 0;
    private static final int STATE_OFFSCREEN = 1;
    private static final int STATE_RETURNING = 2;
    private int mState = STATE_ONSCREEN;
    private int mScrollY;
    private int mMinRawY = 0;

    private TranslateAnimation anim;

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

        /**
         * @param layoutBottom : 하단바 레이아웃
         */
        layoutBottom = (LinearLayout) v.findViewById(R.id.layout_bottom);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (MainListView) getListView();
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

        listView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        // 하단바 애니메이션을 위해 변경
                        mQuickReturnHeight = layoutBottom.getHeight();
                        listView.computeScrollY();
                    }
                });

        // 리스트뷰 스크롤시 하단바 애니메이션
        listView.setOnScrollListener(new OnScrollListener() {
            @SuppressLint("NewApi")
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                mScrollY = 0;
                int translationY = 0;

                if (listView.scrollYIsComputed()) {
                    mScrollY = listView.getComputedScrollY();
                }

                int rawY = mScrollY;

                switch (mState) {
                    case STATE_OFFSCREEN:
                        if (rawY >= mMinRawY) {
                            mMinRawY = rawY;
                        } else {
                            mState = STATE_RETURNING;
                        }
                        translationY = rawY;
                        break;

                    case STATE_ONSCREEN:
                        if (rawY > mQuickReturnHeight) {
                            mState = STATE_OFFSCREEN;
                            mMinRawY = rawY;
                        }
                        translationY = rawY;
                        break;

                    case STATE_RETURNING:
                        translationY = (rawY - mMinRawY) + mQuickReturnHeight;
                        if (translationY < 0) {
                            translationY = 0;
                            mMinRawY = rawY + mQuickReturnHeight;
                        }
                        if (rawY == 0) {
                            mState = STATE_ONSCREEN;
                            translationY = 0;
                        }
                        if (translationY > mQuickReturnHeight) {
                            mState = STATE_OFFSCREEN;
                            mMinRawY = rawY;
                        }
                        break;
                }

                /** this can be used if the build is below honeycomb **/
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB) {
                    anim = new TranslateAnimation(0, 0, translationY, translationY);
                    anim.setFillAfter(true);
                    anim.setDuration(0);

                    // 하단바 애니메이션을 위해 변경
                    layoutBottom.startAnimation(anim);
                } else {

                    // 하단바 애니메이션을 위해 변경
                    layoutBottom.setTranslationY(translationY);
                }

            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}

        });

    }
}
