package android.vadera.nachiketa.pen_paper;

import android.util.Log;

public class Logger {

    private static String tag = "Logger@PenAndPaper";

    public String getTag() {
        return Logger.tag;
    }

    public void setTag(String tag) {
        Logger.tag = tag;
    }

    public void debug(String message) {
        Log.d(tag, message);
    }

    public void information(String message) {
        Log.i(tag, message);
    }

    public void error(String message) {
        Log.e(tag, message);
    }

    public void verbose(String message) {
        Log.v(tag, message);
    }

    public void warn(String message) {
        Log.w(tag, message);
    }

}
