package softcone.csapp.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;

import softcone.csapp.R;

/**
 * Created by YuJin on 2015-06-13.
 */
public class SearchAdapter extends BaseAdapter {

    @SuppressWarnings("unused")
    private Context context;
    ArrayList<ParseObject> arrData;
    private LayoutInflater inflater;

    public SearchAdapter(Context c, ArrayList<ParseObject> arr){
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        return arrData.size();
    }

    @Override
    public Object getItem(int position) {
        return arrData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_search, parent, false);
        }

        ParseObject object = arrData.get(position);

        TextView item_name = (TextView)convertView.findViewById(R.id.search_item_name);
        TextView item_day = (TextView)convertView.findViewById(R.id.search_item_day);
        TextView item_time = (TextView)convertView.findViewById(R.id.search_item_time);

        item_name.setText(object.getString("name"));
        item_day.setText(object.getString("day"));
        item_time.setText(object.getString("time"));

        return convertView;
    }

}
