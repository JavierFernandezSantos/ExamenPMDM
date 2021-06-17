package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_TO_CALL = 0x00001;

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.inputLoteria);
        CharSequence texto = et.getText();
    }

    public void onClickLanzarIntent (View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnLlamar:
                clickCall(v);
                break;
            case R.id.btnWebView:
                webView();
                break;
            case R.id.btnToast:
                toast();
                break;
            case R.id.btnSms:
                enviarSMS();
                break;
            default:
                startActivity(new Intent(this, LoteriaActivity.class));
        }
    }

    public void llamar () {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:666666666"));
        startActivity(i);
    }

    public void clickCall(View v){
        /*
         */

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            llamar();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] { Manifest.permission.CALL_PHONE }, REQUEST_CODE_TO_CALL); // Marca única que me invento
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_TO_CALL: // Marca única que recibo
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    llamar();

                }  else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.

                    Toast.makeText(this, "Si no das permisos no hay llamada.", Toast.LENGTH_LONG).show();

                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }

    public void webView () {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.selae.es/es/web-corporativa/quienes-somos"));
        startActivity(i);
    }

    public void toast () {
        int leng = et.getText().length();
        CharSequence txtEditText;
        if (leng >= 5)
        {
            txtEditText = "Mandar a pasarela pago Compra " + et.getText();
            Toast.makeText(this, txtEditText, Toast.LENGTH_LONG).show();
        }
        else
        {
            txtEditText = "Error el boleto no contiene 5 dígitos";
            Toast.makeText(this, txtEditText, Toast.LENGTH_LONG).show();
        }

    }

    public void enviarSMS() {
        CharSequence mensaje = "Compra boleto con numero: " + et.getText();
        int leng = et.getText().length();


        if (leng >= 5)
        {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("sms:5555555"));
            i.putExtra("sms_body", mensaje);
            startActivity(i);
        }
        else
        {
            mensaje = "Error el boleto no contiene 5 dígitos";
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        }
    }
}