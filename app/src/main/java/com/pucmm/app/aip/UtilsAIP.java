package com.pucmm.app.aip;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.core.content.ContextCompat;
import com.pucmm.app.R;

public final class UtilsAIP {

    private final Context context;

    public UtilsAIP(Context context) {
        this.context = context;

    }

    public boolean checkSelfPermission(String access) {
        int result = ContextCompat.checkSelfPermission(context, access);

        return result == PackageManager.PERMISSION_GRANTED;
    }

    public boolean onTouch(View view, MotionEvent event) {
        Button button = (Button) view;
        if (event.getAction() == MotionEvent.ACTION_UP) {
            button.setBackgroundColor(ContextCompat.getColor(context, R.color.backgroundMain));
        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            button.setBackgroundColor(ContextCompat.getColor(context, R.color.btnPress));
        }
        return false;
    }
}
