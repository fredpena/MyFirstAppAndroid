package com.pucmm.app;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class Utils {

    private Utils() {
    }

    public static List<String> getGenders() {
        return Arrays.asList("Masculino", "Femenino");
    }

    public static String format(Context context, Date date) {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        return dateFormat.format(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String valueFromArray(List<String> list) {
        final String[] value = {""};
        list.stream().forEach(obj ->{
            value[0] += (obj + ", ");
        });
        return value[0];
    }
}
