package com.pucmm.app.aip;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;
import com.pucmm.app.R;

import java.io.IOException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
//import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CONTACTS;

public class PermissionActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    private static final int PERMISSION_REQUEST_CODE = 200;
    private View view;
    private UtilsAIP utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        utils = new UtilsAIP(this);

        Button btnStorage = findViewById(R.id.btnStorage);
        Button btnLocation = findViewById(R.id.btnLocation);
        Button btnCamara = findViewById(R.id.btnCamara);
        Button btnPhone = findViewById(R.id.btnPhone);
        Button btnContacts = findViewById(R.id.btnContacts);

        btnStorage.setOnTouchListener(this);
        btnLocation.setOnTouchListener(this);
        btnCamara.setOnTouchListener(this);
        btnPhone.setOnTouchListener(this);
        btnContacts.setOnTouchListener(this);

        btnStorage.setOnClickListener(this);
        btnLocation.setOnClickListener(this);
        btnCamara.setOnClickListener(this);
        btnPhone.setOnClickListener(this);
        btnContacts.setOnClickListener(this);
    }


    public void onClick(View view) {
        String access;
        switch (view.getId()) {
            case R.id.btnStorage:
                access = READ_EXTERNAL_STORAGE;
                break;
            case R.id.btnLocation:
                access = ACCESS_FINE_LOCATION;
                break;
            case R.id.btnCamara:
                access = CAMERA;
                break;
            case R.id.btnPhone:
                access = CALL_PHONE;
                break;
            case R.id.btnContacts:
                access = READ_CONTACTS;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
        if (utils.checkSelfPermission(access)) {
            Snackbar.make(view, "Permission already granted.", Snackbar.LENGTH_LONG)
                    .setAction("Open", (v) -> {
                        Intent intent;
                        switch (access) {
                            case READ_EXTERNAL_STORAGE:
                                intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                                intent.addCategory(Intent.CATEGORY_OPENABLE);
                                intent.setType("application/pdf");
                                startActivity(intent);
                                break;
                            case CAMERA:
                                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivity(intent);
                                break;
                            case CALL_PHONE:
                                String uri = "tel:18298202626" ;
                                 intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse(uri));
                                startActivity(intent);
                                break;
                            case READ_CONTACTS:
                                intent = new Intent(Intent.ACTION_VIEW);
                                Uri contactUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, "");
                                intent.setData(contactUri);
                                startActivity(intent);
                                break;
                            case ACCESS_FINE_LOCATION:
                                //Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
                                Uri gmmIntentUri = Uri.parse("geo:0,0?q=restaurants");
                                intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                intent.setPackage("com.google.android.apps.maps");
                                startActivity(intent);
                                break;
                        }
                    })
                    .show();
        } else {
            Snackbar.make(view, "Please request permission.", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        return utils.onTouch(view, event);
    }

}