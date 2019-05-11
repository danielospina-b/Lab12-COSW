package edu.eci.cosw.APIApp.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StringUtils {
    public static boolean isValidEmail( CharSequence target ) {
        return ( !TextUtils.isEmpty( target ) && Patterns.EMAIL_ADDRESS.matcher( target ).matches() );
    }

    public static String getFormattedDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        long milis = Long.parseLong(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milis);
        return simpleDateFormat.format(calendar.getTime());
    }

}
