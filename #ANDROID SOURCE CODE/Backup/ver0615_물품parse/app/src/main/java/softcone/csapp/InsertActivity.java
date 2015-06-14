package softcone.csapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.rey.material.widget.Button;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class InsertActivity extends ActionBarActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private TextView tv_item_code;
    private com.rey.material.widget.EditText et_item_name;
    private Button btn_time;
    private TextView tv_time;
    private Button btn_insert;

    public static final String DATEPICKER_TAG = "datepicker";
    public static final String TIMEPICKER_TAG = "timepicker";

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
        setContentView(R.layout.activity_insert);

        et_item_name = (com.rey.material.widget.EditText)findViewById(R.id.et_item_name);
        tv_item_code = (TextView)findViewById(R.id.tv_item_code);
        tv_item_code.setText(BarcodeActivity.item_code);

        tv_time = (TextView)findViewById(R.id.tv_time);
        btn_time = (Button)findViewById(R.id.btn_time);
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.setVibrate(true);
                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.setCloseOnSingleTapDay(false);
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
            }
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

        btn_insert = (Button)findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                ParseObject life = new ParseObject("Life");
                life.put("barcode", BarcodeActivity.item_code);
                life.put("image", BarcodeActivity.life_object.getParseFile("image"));
                life.put("name", et_item_name.getText().toString());
                life.put("day", life_date);
                life.put("time", life_time);
                life.saveInBackground();
                finish();*/
            }
        });

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
