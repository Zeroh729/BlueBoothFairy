package android.zeroh729.com.blueboothfairy.exhibitor.ui.main.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.zeroh729.com.blueboothfairy.exhibitor.R;
import android.zeroh729.com.blueboothfairy.exhibitor.ui.base.BaseActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @Click(R.id.btn_login)
    void onClickLogin(){
        MainActivity_.intent(this).start();
    }
}
