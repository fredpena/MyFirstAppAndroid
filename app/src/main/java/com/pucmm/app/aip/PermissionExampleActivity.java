package com.pucmm.app.aip;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;
import com.pucmm.app.R;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CONTACTS;

public class PermissionExampleActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 200;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_example);

//        Button checkPermission = findViewById(R.id.check_permission);
//        Button requestPermission = findViewById(R.id.request_permission);

//        checkPermission.setOnClickListener(v -> {
//            view = v;
//            if (checkPermission()) {
//                Snackbar.make(view, "Permission already granted.", Snackbar.LENGTH_LONG).show();
//            } else {
//                Snackbar.make(view, "Please request permission.", Snackbar.LENGTH_LONG).show();
//            }
//         });
//
//        requestPermission.setOnClickListener(v -> {
//            view = v;
//            if (!checkPermission()) {
//                requestPermission();
//            } else {
//                Snackbar.make(view, "Permission already granted.", Snackbar.LENGTH_LONG).show();
//            }
//        });
    }

    public void methodCheckPermission(View v) {
        view = v;

        if (checkPermission()) {
            Snackbar.make(view, "Permission already granted.", Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(view, "Please request permission.", Snackbar.LENGTH_LONG).show();
        }
    }

    public void methodRequestPermission(View v) {
        view = v;
        if (checkPermission()) {
            Snackbar.make(view, "Permission already granted.", Snackbar.LENGTH_LONG).show();
        } else {
            requestPermission();
        }
    }

    private boolean checkPermission() {
        int location = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int camera = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int callPhone = ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE);
        int readContacts = ContextCompat.checkSelfPermission(getApplicationContext(), READ_CONTACTS);

        return location == PackageManager.PERMISSION_GRANTED && camera == PackageManager.PERMISSION_GRANTED &&
        callPhone == PackageManager.PERMISSION_GRANTED && readContacts == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA, ACCESS_FINE_LOCATION, CALL_PHONE, READ_CONTACTS}, 300);
        //  ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, CAMERA, ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0) {
//
//                    boolean locationAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//
//                    if (locationAccepted && cameraAccepted)
//                        Snackbar.make(view, "Permission Granted, Now you can access location data and camera.", Snackbar.LENGTH_LONG).show();
//                    else {
//
//                        Snackbar.make(view, "Permission Denied, You cannot access location data and camera.", Snackbar.LENGTH_LONG).show();
//
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
//                                showMessageOKCancel("You need to allow access to both the permissions",
//                                        new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION, CAMERA},
//                                                            PERMISSION_REQUEST_CODE);
//                                                }
//                                            }
//                                        });
//                                return;
//                            }
//                        }
//
//                    }
//                }
//
//
//                break;
//        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(PermissionExampleActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


}