package org.cramest.cremasgelateria;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 02/10/2016.
 */

public class GestoreTempo {

    public static String getTime() {
        DateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(Calendar.getInstance().getTime());
    }
    public static String getHour() {
        DateFormat df = new SimpleDateFormat("HH");
        return df.format(Calendar.getInstance().getTime());
    }
    public static String getMinutes() {
        DateFormat df = new SimpleDateFormat("mm");
        return df.format(Calendar.getInstance().getTime());
    }
    public static String getGiornoSett() {
        DateFormat df = new SimpleDateFormat("EEE");
        return df.format(Calendar.getInstance().getTime());
    }
}
