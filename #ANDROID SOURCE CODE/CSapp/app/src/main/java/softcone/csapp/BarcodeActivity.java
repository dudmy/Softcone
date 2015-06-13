package softcone.csapp;

import android.app.Activity;
<<<<<<< HEAD
=======
import android.app.AlertDialog;
import android.content.DialogInterface;
>>>>>>> 5f4edfe2180e73ed9e9db8481d13ac3e8f04b105
import android.content.Intent;
import android.os.Bundle;

import softcone.csapp.android.integration.IntentIntegrator;
<<<<<<< HEAD

public class BarcodeActivity extends Activity
{

=======
import softcone.csapp.android.integration.IntentResult;

public class BarcodeActivity extends Activity
{
>>>>>>> 5f4edfe2180e73ed9e9db8481d13ac3e8f04b105
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

<<<<<<< HEAD
        IntentIntegrator.initiateScan(this);
=======
        IntentIntegrator.initiateScan(BarcodeActivity.this);
>>>>>>> 5f4edfe2180e73ed9e9db8481d13ac3e8f04b105
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
<<<<<<< HEAD
        /*
=======
>>>>>>> 5f4edfe2180e73ed9e9db8481d13ac3e8f04b105
        // QR코드/바코드를 스캔한 결과 값을 가져옵니다.
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        // 결과값 출력
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(result.getContents() + " [" + result.getFormatName() + "]")
                .setPositiveButton("확인", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                })
<<<<<<< HEAD
                .show();*/

        startActivity(new Intent(this, InsertActivity.class));
        this.finish();
=======
                .show();
>>>>>>> 5f4edfe2180e73ed9e9db8481d13ac3e8f04b105
    }
}
