package android.zeroh729.com.blueboothfairy.buyers.utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.zeroh729.com.blueboothfairy.buyers.App_;

public class _ {
    public static void showToast(String message){
        Toast.makeText(App_.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showSnack(View v, String message){
        Snackbar.make(v, message, Snackbar.LENGTH_SHORT).show();
    }

    public static void log(String message){
        Log.d("TEST", message);
    }

    public static void logError(String message){
        Log.e("TEST", message);
    }
}
