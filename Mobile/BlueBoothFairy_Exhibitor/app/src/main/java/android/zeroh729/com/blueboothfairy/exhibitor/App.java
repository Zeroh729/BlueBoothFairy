package android.zeroh729.com.blueboothfairy.exhibitor;

import android.app.Application;
import android.content.Context;

import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.androidannotations.annotations.EApplication;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@EApplication
public class App extends Application{
    private Context context;
    private BackgroundPowerSaver backgroundPowerSaver;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/FinenessProRegular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        backgroundPowerSaver = new BackgroundPowerSaver(this);
    }

    public Context getContext(){
        return context;
    }
}
