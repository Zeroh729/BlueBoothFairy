package android.zeroh729.com.blueboothfairy.exhibitor.utils;

import android.util.Log;
import android.widget.Toast;
import android.zeroh729.com.blueboothfairy.exhibitor.App_;

public class _ {
    public static void showToast(String message){
        Toast.makeText(App_.getInstance().getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void log(String message){
        Log.d("TEST", message);
    }

    public static void logError(String message){
        Log.e("TEST", message);
    }
}
