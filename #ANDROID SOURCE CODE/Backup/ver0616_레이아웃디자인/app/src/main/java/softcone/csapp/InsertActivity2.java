package softcone.csapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.rey.material.widget.Button;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class InsertActivity2 extends ActionBarActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, View.OnClickListener {

    public static final String DATEPICKER_TAG = "datepicker";
    public static final String TIMEPICKER_TAG = "timepicker";

    private TextView tv_item_code;
    private TextView tv_item_name;
    private TextView tv_time;
    private Button btn_time;
    private Button btn_insert;
    private ParseImageView item_img;

    private String text_date="";
    private String text_time="";
    private String life_date="";
    private String life_time="";

    final Calendar calendar = Calendar.getInstance();

    final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);
    final TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), false, false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert2);

        tv_item_name = (TextView)findViewById(R.id.tv_item_name);
        tv_item_name.setText(BarcodeActivity.life_object.getString("name"));
        tv_item_code = (TextView)findViewById(R.id.tv_item_code);
        tv_item_code.setText(BarcodeActivity.item_code);

        tv_time = (TextView)findViewById(R.id.tv_time);
        btn_time = (Button)findViewById(R.id.btn_time);
        btn_insert = (Button)findViewById(R.id.btn_insert);

        btn_time.setOnClickListener(this);
        btn_insert.setOnClickListener(this);

        item_img = (ParseImageView)findViewById(R.id.item_img);

        item_img.setParseFile(BarcodeActivity.life_object.getParseFile("image"));
        item_img.loadInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {}
        });

        if (savedInstanceState != null) {
            DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener(this);
            }

            TimePickerDialog tpd = (TimePickerDialog) getSupportFragmentManager().findFragmentByTag(TIMEPICKER_TAG);
            if (tpd != null) {
                tpd.setOnTimeSetListener(this);
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_time:
                datePickerDialog.setVibrate(true);
                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.setCloseOnSingleTapDay(false);
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
                break;

            case R.id.btn_insert:
                ParseObject life = new ParseObject("Life");
                life.put("barcode", BarcodeActivity.life_object.getString("barcode"));
                life.put("image", BarcodeActivity.life_object.getParseFile("image"));
                life.put("name", BarcodeActivity.life_object.getString("name"));
                life.put("day", life_date);
                life.put("time", life_time);
                life.saveInBackground();
                finish();
                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        text_date = year + "년 " + (month+1) + "월 " + day + "일  ";
        if(month<10) life_date = year + "-0" + (month+1) + "-" + day;
        else life_date = year + (month+1) + "-" + day;

        timePickerDialog.setVibrate(true);
        timePickerDialog.setCloseOnSingleTapMinute(false);
        timePickerDialog.show(getSupportFragmentManager(), TIMEPICKER_TAG);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        text_time = hourOfDay + "시 " + minute + "분";
        life_time = hourOfDay + ":" + minute + ":00";
        tv_time.setText(text_date + text_time);
    }

}
