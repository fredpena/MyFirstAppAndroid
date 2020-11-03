package com.pucmm.app;

import android.app.Activity;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxUtils {
    private final Activity view;
    private CheckBox cbJava;
    private CheckBox cbPython;
    private CheckBox cbJS;
    private CheckBox cbGo;
    private CheckBox cbCCpp;
    private CheckBox cbCSharp;
    private List<String> languajes;

    public CheckBoxUtils(Activity view) {
        this.view = view;
        cbJava = view.findViewById(R.id.cbJava);
        cbPython = view.findViewById(R.id.cbPython);
        cbJS = view.findViewById(R.id.cbJS);
        cbGo = view.findViewById(R.id.cbGo);
        cbCCpp = view.findViewById(R.id.cbCCpp);
        cbCSharp = view.findViewById(R.id.cbCSharp);

        languajes = new ArrayList<>();
        ;
    }


    public void enabled(boolean enabled) {
        cbJava.setEnabled(enabled);
        cbPython.setEnabled(enabled);
        cbJS.setEnabled(enabled);
        cbGo.setEnabled(enabled);
        cbCCpp.setEnabled(enabled);
        cbCSharp.setEnabled(enabled);

    }

    public void checked(boolean checked) {
        cbJava.setChecked(checked);
        cbPython.setChecked(checked);
        cbJS.setChecked(checked);
        cbGo.setChecked(checked);
        cbCCpp.setChecked(checked);
        cbCSharp.setChecked(checked);
    }

    public List<String> getLanguajes() {
        return languajes;
    }

    public void checkSelected() {
        if (cbJava.isChecked()) {
            languajes.add(view.getResources().getString(R.string.lan_java));
        }
        if (cbPython.isChecked()) {
            languajes.add(view.getResources().getString(R.string.lan_python));
        }
        if (cbJS.isChecked()) {
            languajes.add(view.getResources().getString(R.string.lan_js));
        }
        if (cbGo.isChecked()) {
            languajes.add(view.getResources().getString(R.string.lan_go));
        }
        if (cbCCpp.isChecked()) {
            languajes.add(view.getResources().getString(R.string.lan_c_cpp));
        }
        if (cbCSharp.isChecked()) {
            languajes.add(view.getResources().getString(R.string.lan_csharp));
        }

    }
}
