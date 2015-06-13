package softcone.csapp.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseImageView;
import com.parse.ParseObject;

import java.util.ArrayList;

import softcone.csapp.R;

public class ItemAdapter extends BaseAdapter{

	@SuppressWarnings("unused")
	private Context context;
	ArrayList<ParseObject> arrData;
	private LayoutInflater inflater;

	public ItemAdapter(Context c, ArrayList<ParseObject> arr){
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
			convertView = inflater.inflate(R.layout.list_item, parent, false);
		}

        ParseObject object = arrData.get(position);

        ParseImageView item_img = (ParseImageView)convertView.findViewById(R.id.item_img);
        TextView item_name = (TextView)convertView.findViewById(R.id.item_name);
        TextView item_day = (TextView)convertView.findViewById(R.id.item_day);
        TextView item_time = (TextView)convertView.findViewById(R.id.item_time);

        item_img.setParseFile(object.getParseFile("image"));
        item_img.loadInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {

            }
        });

        item_name.setText(object.getString("name"));
        item_day.setText(object.getString("day"));
        item_time.setText(object.getString("time"));

		return convertView;
	}

}

