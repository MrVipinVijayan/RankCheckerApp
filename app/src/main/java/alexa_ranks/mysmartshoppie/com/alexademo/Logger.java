package alexa_ranks.mysmartshoppie.com.alexademo;

import android.util.Log;

public class Logger {
    public static Boolean DEBUG = Boolean.valueOf(false);

    public Logger() {
    }

    public static void logInfo(String tag, String message) {
        if(DEBUG.booleanValue()) {
            if(message != null) {
                Log.i(tag, message);
            } else {
                Log.i(tag, "<NULL VALUE>");
            }
        }

    }

    public static void logError(String tag, String message) {
        if(DEBUG.booleanValue()) {
            if(message != null) {
                Log.e(tag, message);
            } else {
                Log.e(tag, "<NULL VALUE>");
            }
        }

    }
}
