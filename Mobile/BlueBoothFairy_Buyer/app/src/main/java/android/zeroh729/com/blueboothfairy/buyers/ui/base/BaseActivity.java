package android.zeroh729.com.blueboothfairy.buyers.ui.base;

import android.content.Context;
import android.zeroh729.com.blueboothfairy.buyers.App_;
import android.zeroh729.com.blueboothfairy.buyers.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.zeroh729.com.blueboothfairy.buyers.data.local.SharedPrefHelper;
import android.zeroh729.com.blueboothfairy.buyers.data.model.User;
import android.zeroh729.com.blueboothfairy.buyers.data.model.User_;
import android.zeroh729.com.blueboothfairy.buyers.data.values.Constants;
import android.zeroh729.com.blueboothfairy.buyers.utils.OttoBus;
import android.zeroh729.com.blueboothfairy.buyers.utils.OttoBus_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

@EActivity
public abstract class BaseActivity extends AppCompatActivity{

    @Bean
    OttoBus bus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus = OttoBus_.getInstance_(this);
        bus.register(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(findViewById(R.id.toolbar) != null) {
            setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
            getSupportActionBar().setTitle("");
        }
        SharedPrefHelper prefs = SharedPrefHelper.getInstance(App_.getInstance().getContext());
        boolean isLoggedInBefore = prefs.getBoolean(Constants.USER_DETAILS);
        if(isLoggedInBefore){
            User user = User_.getInstance_(App_.getInstance());
            user.setId(prefs.getString(Constants.USER_ID));
            user.setName(prefs.getString(Constants.USER_NAME));
            user.setEmail(prefs.getString(Constants.USER_EMAIL));
            user.setContactNumber(prefs.getString(Constants.USER_CONTACTNO));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

}
