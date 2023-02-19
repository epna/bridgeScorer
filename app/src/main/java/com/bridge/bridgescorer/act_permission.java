package com.bridge.bridgescorer;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class act_permission extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 200;

    public act_permission() {
    }

    public  boolean checkPermission(View view) {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission ( view.getContext (), WRITE_EXTERNAL_STORAGE );
        int permission2 = ContextCompat.checkSelfPermission ( view.getContext (), READ_EXTERNAL_STORAGE );
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(Activity xxThis) {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions ( xxThis, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult ( requestCode, permissions, grantResults );

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {

                } else {
                    Toast.makeText ( this, "Permission Denied.", Toast.LENGTH_SHORT ).show ();
                    finish ();
                }
            }
        }
    }
}
