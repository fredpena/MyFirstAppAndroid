package com.pucmm.app.aip;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;
import com.pucmm.app.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
//import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CONTACTS;

public class ActivitiesIntentPermissionsActivity extends AppCompatActivity implements View.OnTouchListener, CompoundButton.OnCheckedChangeListener {

    private static final int PERMISSION_REQUEST_CODE = 200;
    private View view;
    private UtilsAIP utils;

    private Button btnContinue;
    private Button btnCancel;

    private Switch swStorage;
    private Switch swLocation;
    private Switch swCamara;
    private Switch swPhone;
    private Switch swContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_intent_permissions);
        utils = new UtilsAIP(this);

        swStorage = findViewById(R.id.swStorage);
        swLocation = findViewById(R.id.swLocation);
        swCamara = findViewById(R.id.swCamara);
        swPhone = findViewById(R.id.swPhone);
        swContacts = findViewById(R.id.swContacts);

        swStorage.setOnCheckedChangeListener(this);
        swLocation.setOnCheckedChangeListener(this);
        swCamara.setOnCheckedChangeListener(this);
        swPhone.setOnCheckedChangeListener(this);
        swContacts.setOnCheckedChangeListener(this);

        swStorage.setChecked(utils.checkSelfPermission(READ_EXTERNAL_STORAGE));
        swLocation.setChecked(utils.checkSelfPermission(ACCESS_FINE_LOCATION));
        swCamara.setChecked(utils.checkSelfPermission(CAMERA));
        swPhone.setChecked(utils.checkSelfPermission(CALL_PHONE));
        swContacts.setChecked(utils.checkSelfPermission(READ_CONTACTS));


        btnContinue = findViewById(R.id.btnContinue);
        btnCancel = findViewById(R.id.btnCancel);
        btnContinue.setOnTouchListener(this);
        btnCancel.setOnTouchListener(this);

        btnCancel.setOnClickListener(v -> {
            finish();
            System.exit(0);
        });


        btnContinue.setOnClickListener(v -> {
            requestPermission();
        });
    }


    private void requestPermission() {
        final String[] access = getAccess();
        ActivityCompat.requestPermissions(this, access, PERMISSION_REQUEST_CODE);
    }




    private String[] getAccess() {
        ArrayList<String> list = new ArrayList<>();
        list.add(swStorage.isChecked() ? READ_EXTERNAL_STORAGE : "");
        list.add(swLocation.isChecked() ? ACCESS_FINE_LOCATION : "");
        list.add(swCamara.isChecked() ? CAMERA : "");
        list.add(swPhone.isChecked() ? CALL_PHONE : "");
        list.add(swContacts.isChecked() ? READ_CONTACTS : "");

        list.stream().filter(f -> !f.isEmpty()).collect(Collectors.toList());

        return list.toArray(new String[list.size()]);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intent intent = new Intent(getApplicationContext(), PermissionActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        return utils.onTouch( view, event);
    }

    @Override
    public void onCheckedChanged(CompoundButton v, boolean b) {

        Switch view;
        String access;
        switch (v.getId()) {
            case R.id.swStorage:
                view = findViewById(R.id.swStorage);
                access = READ_EXTERNAL_STORAGE;
                break;
            case R.id.swLocation:
                view = findViewById(R.id.swLocation);
                access = ACCESS_FINE_LOCATION;
                break;
            case R.id.swCamara:
                view = findViewById(R.id.swCamara);
                access = CAMERA;
                break;
            case R.id.swPhone:
                view = findViewById(R.id.swPhone);
                access = CALL_PHONE;
                break;
            case R.id.swContacts:
                view = findViewById(R.id.swContacts);
                access = READ_CONTACTS;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

        if (!view.isChecked() && utils.checkSelfPermission(access)) {
            view.setChecked(true);
            Snackbar.make(view, "Permission Granted!!!", Snackbar.LENGTH_LONG).show();
        }
    }


}