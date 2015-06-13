package softcone.csapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import softcone.csapp.android.integration.IntentIntegrator;
import softcone.csapp.android.integration.IntentResult;

public class BarcodeActivity extends Activity
{

    public static String item_code="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        IntentIntegrator.initiateScan(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        // QR코드/바코드를 스캔한 결과 값을 가져옵니다.
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        item_code = result.getContents();

        startActivity(new Intent(this, InsertActivity.class));
        this.finish();
    }
}
