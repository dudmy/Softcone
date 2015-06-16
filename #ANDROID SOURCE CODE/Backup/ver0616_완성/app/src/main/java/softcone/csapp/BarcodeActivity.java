package softcone.csapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import softcone.csapp.android.integration.IntentIntegrator;
import softcone.csapp.android.integration.IntentResult;

public class BarcodeActivity extends Activity {

    public static String item_code="";
    public static ParseObject life_object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        IntentIntegrator.initiateScan(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // QR 코드, 바코드를 스캔한 결과 값을 가져옵니다.
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        item_code = result.getContents();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Item");

        query.whereEqualTo("barcode", item_code);

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    startActivity(new Intent(getApplicationContext(), InsertActivity.class));
                } else {
                    life_object = object;
                    startActivity(new Intent(getApplicationContext(), InsertActivity2.class));
                }
            }
        });

        this.finish();

    }


}
