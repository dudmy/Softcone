package softcone.csapp.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import softcone.csapp.R;

/**
 * Created by YuJin on 2015-04-01.
 */
public class NoticeAdapter extends ArrayAdapter<NoticeData> {

    private Context context;
    ArrayList<NoticeData> arrData;
    private LayoutInflater inflater;

    public NoticeAdapter(Context c, ArrayList<NoticeData> arr){
        super(c, 0, arr);
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        return arrData.size();
    }

    @Override
    public NoticeData getItem(int position) {
        return arrData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_notice, parent, false);
        }

        NoticeData object = arrData.get(position);

        LinearLayout linearLayout = (LinearLayout)(convertView.findViewById(
                R.id.item_linear_layout));
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams
                (AbsListView.LayoutParams.MATCH_PARENT, object.getCollapsedHeight());
        linearLayout.setLayoutParams(linearLayoutParams);

        ToggleButton toogle = (ToggleButton)convertView.findViewById(R.id.toggle);
        toogle.setChecked(object.getToggle());

        TextView notice_title = (TextView)convertView.findViewById(R.id.notice_title);
        notice_title.setText(object.getNotie_title());

        TextView notice_info = (TextView)convertView.findViewById(R.id.notice_info);
        notice_info.setText(object.getNotice_info());

        TextView date = (TextView)convertView.findViewById(R.id.date);
        date.setText((CharSequence) object.getDate());

        convertView.setLayoutParams(new ListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                AbsListView.LayoutParams.WRAP_CONTENT));

        ExpandingLayout expandingLayout = (ExpandingLayout)convertView.findViewById(R.id
                .expanding_layout);
        expandingLayout.setExpandedHeight(object.getExpandedHeight());
        expandingLayout.setSizeChangedListener(object);

        if (!object.isExpanded()) {
            expandingLayout.setVisibility(View.GONE);
        } else {
            expandingLayout.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

}
