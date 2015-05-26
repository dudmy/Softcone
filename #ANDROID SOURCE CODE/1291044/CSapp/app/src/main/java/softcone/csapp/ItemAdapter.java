package softcone.csapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter{

	@SuppressWarnings("unused")
	private Context context;
	ArrayList<ItemData> arrData;
	private LayoutInflater inflater;
	
	public ItemAdapter(Context c, ArrayList<ItemData> arr){
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

        ImageView item_img = (ImageView)convertView.findViewById(R.id.item_img);
        TextView item_name = (TextView)convertView.findViewById(R.id.item_name);
        TextView item_day = (TextView)convertView.findViewById(R.id.item_day);
        TextView item_time = (TextView)convertView.findViewById(R.id.item_time);

        item_name.setText(arrData.get(position).getItem_name());
        item_day.setText(arrData.get(position).getItem_day());
        item_time.setText(arrData.get(position).getItem_time());

		return convertView;
	}

}

