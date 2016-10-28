package android.zeroh729.com.blueboothfairy.buyers.ui.main.activities;

import android.zeroh729.com.blueboothfairy.buyers.R;
import android.zeroh729.com.blueboothfairy.buyers.data.local.SharedPrefHelper;
import android.zeroh729.com.blueboothfairy.buyers.data.values.Constants;
import android.zeroh729.com.blueboothfairy.buyers.ui.base.BaseActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_checkin)
public class CheckInActivity extends BaseActivity {
    private static boolean isShowing = false;

    @Click(R.id.btn_claimfreebie)
    void onClickClaimFreebie(){
        SharedPrefHelper.getInstance(this).putBoolean(Constants.FREEBIE_CLAIMED, true);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isShowing = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isShowing = false;
    }

    public static boolean isShowing() {
        return isShowing;
    }
}
