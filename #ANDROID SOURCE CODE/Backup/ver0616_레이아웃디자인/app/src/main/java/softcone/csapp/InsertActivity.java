package softcone.csapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.rey.material.widget.Button;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

/**
 * 제품 없을 경우 액티비티
 */
public class InsertActivity extends ActionBarActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, View.OnClickListener {

    public static final String DATEPICKER_TAG = "datepicker";
    public static final String TIMEPICKER_TAG = "timepicker";

    private int TAKE_CAMERA = 1; // 카메라 리턴 코드값 설정
    private int TAKE_GALLERY = 2; // 앨범선택에 대한 리턴 코드값 설정

    private ParseImageView img_camera;
    private TextView tv_item_code;
    private TextView tv_time;
    private com.rey.material.widget.EditText et_item_name;
    private ParseFile img_file;

    private Button btn_time;
    private Button btn_insert;
    private Button btn_camera;

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
        btn_insert = (Button)findViewById(R.id.btn_insert);
        btn_camera = (Button)findViewById(R.id.btn_camera);

        btn_time.setOnClickListener(this);
        btn_insert.setOnClickListener(this);
        btn_camera.setOnClickListener(this);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_CAMERA) {
                if( data != null )
                {
                    Log.e("Test", "result = " + data);
                    Bitmap thumbnail = (Bitmap)data.getExtras().get("data");
                    thumbnail = Bitmap.createScaledBitmap(thumbnail, 100, 120, true);
                    if( thumbnail != null )
                    {
                        ParseImageView Imageview = (ParseImageView) findViewById(R.id.item_img);
                        Imageview.setImageBitmap(thumbnail);

                        ByteArrayOutputStream blob = new ByteArrayOutputStream();
                        thumbnail.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */,blob);

                        byte[] imgArray = blob.toByteArray();

                        //Assign Byte array to ParseFile
                        img_file = new ParseFile(BarcodeActivity.item_code + ".png", imgArray);
                    }
                }

            } else if (requestCode == TAKE_GALLERY) {
                if( data != null )
                {
                    Log.e("Test", "result = " + data);

                    Uri thumbnail = data.getData();
                    if( thumbnail != null )
                    {
                        ParseImageView Imageview = (ParseImageView) findViewById(R.id.item_img);
                        Imageview.setImageURI(thumbnail);
                    }
                }
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

                ParseObject item = new ParseObject("Item");
                item.put("barcode", BarcodeActivity.item_code);
                item.put("image", img_file);
                item.put("name", et_item_name.getText().toString());
                item.saveInBackground();

                ParseObject life = new ParseObject("Life");
                life.put("barcode", BarcodeActivity.item_code);
                life.put("image", img_file);
                life.put("name", et_item_name.getText().toString());
                life.put("day", life_date);
                life.put("time", life_time);
                life.saveInBackground();
                finish();

                break;

            case R.id.btn_camera:
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_CAMERA);
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
