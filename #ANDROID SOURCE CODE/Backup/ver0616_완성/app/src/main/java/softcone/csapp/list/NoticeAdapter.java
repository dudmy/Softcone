package softcone.csapp.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import softcone.csapp.R;

/**
 * Created by YuJin on 2015-04-01.
 */
public class NoticeAdapter extends ArrayAdapter<NoticeData> {

    private Context context;
    ArrayList<NoticeData> arrData;
    private LayoutInflater inflater;
    private String object_id="";

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

        final NoticeData object = arrData.get(position);

        LinearLayout linearLayout = (LinearLayout)(convertView.findViewById(
                R.id.item_linear_layout));
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams
                (AbsListView.LayoutParams.MATCH_PARENT, object.getCollapsedHeight());
        linearLayout.setLayoutParams(linearLayoutParams);

        final ToggleButton toggle = (ToggleButton)convertView.findViewById(R.id.toggle);
        toggle.setChecked(object.isToggle());
        /*
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Notice");
                query.whereEqualTo("title", object.getNotice_title());
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject po, ParseException e) {
                        if (e == null) {
                            object_id = po.getString("objectId");
                        }
                    }
                });

                // Retrieve the object by id
                query.getInBackground(object_id, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, com.parse.ParseException e) {
                        if (e == null) {
                            // Now let's update it with some new data. In this case, only cheatMode and score
                            // will get sent to the Parse Cloud. playerName hasn't changed.
                            parseObject.put("toggle", toggle.isChecked());
                            parseObject.saveInBackground();
                        }
                    }
                });
            }
        });*/

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Notice");

                    // Retrieve the object by id
                    query.getInBackground(object.getObjectId(), new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, com.parse.ParseException e) {
                            if (e == null) {
                                // Now let's update it with some new data. In this case, only cheatMode and score
                                // will get sent to the Parse Cloud. playerName hasn't changed.
                                parseObject.put("toggle", true);
                                parseObject.saveInBackground();
                            }
                        }
                    });
                } else {
                    // The toggle is disabled
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Notice");

                    // Retrieve the object by id
                    query.getInBackground(object.getObjectId(), new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, com.parse.ParseException e) {
                            if (e == null) {
                                // Now let's update it with some new data. In this case, only cheatMode and score
                                // will get sent to the Parse Cloud. playerName hasn't changed.
                                parseObject.put("toggle", false);
                                parseObject.saveInBackground();
                            }
                        }
                    });
                }
            }
        });

        TextView notice_title = (TextView)convertView.findViewById(R.id.notice_title);
        notice_title.setText(object.getNotice_title());

        TextView notice_info = (TextView)convertView.findViewById(R.id.notice_info);
        notice_info.setText(object.getNotice_info());

        TextView date = (TextView)convertView.findViewById(R.id.date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date.setText(format.format(object.getDate()));

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
