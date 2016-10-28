package android.zeroh729.com.blueboothfairy.exhibitor.ui.base;

import android.content.Context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.zeroh729.com.blueboothfairy.exhibitor.R;
import android.zeroh729.com.blueboothfairy.exhibitor.data.model.Exhibitor;
import android.zeroh729.com.blueboothfairy.exhibitor.data.remote.ExhibitorRepo;
import android.zeroh729.com.blueboothfairy.exhibitor.ui.main.activities.MainActivity;
import android.zeroh729.com.blueboothfairy.exhibitor.ui.main.activities.MainActivity_;
import android.zeroh729.com.blueboothfairy.exhibitor.utils.OttoBus;
import android.zeroh729.com.blueboothfairy.exhibitor.utils.OttoBus_;
import android.zeroh729.com.blueboothfairy.exhibitor.utils._;

import com.afollestad.materialdialogs.MaterialDialog;

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    protected void showSelectDialog(){
        new MaterialDialog.Builder(this)
                .title("Select Company ")
                .items(ExhibitorRepo.getNames())
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        String exhibitorName = ExhibitorRepo.getNames().get(position);
                        MainActivity_.intent(BaseActivity.this).extra("exhibitor", ExhibitorRepo.getExhibitorOfName(exhibitorName)).start();
                    }
                })
                .show();
    }
}
