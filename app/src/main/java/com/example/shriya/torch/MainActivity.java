package com.example.shriya.torch;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    ImageButton btn;
    private Camera camera;
    Camera.Parameters parameters;
    private boolean ison = false;
    private boolean isflash = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (ImageButton) findViewById(R.id.btn);
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            camera = Camera.open();
            parameters = camera.getParameters();
            isflash = true;
        }
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isflash) {
                    if (!ison) {
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                        camera.startPreview();
                        ison = true;
                    } else {
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(parameters);
                        camera.stopPreview();
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("ERROR.....");
                    builder.setMessage("FlashLight is not available on this device...");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    AlertDialog alertdialog = builder.create();
                    alertdialog.show();
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
    }

// See https://g.co/AppIndexing/AndroidStudio for more information.
    client=new GoogleApiClient.Builder(this).

    addApi(AppIndex.API)

    .

    build();


    @Override
    protected void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        if (camera != null) {
            camera.release();
            camera = null;
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }
}

