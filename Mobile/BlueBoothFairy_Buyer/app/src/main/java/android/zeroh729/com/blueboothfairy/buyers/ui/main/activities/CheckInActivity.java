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

    @Click(R.id.btn_claimfreebie)
    void onClickClaimFreebie(){
        SharedPrefHelper.getInstance(this).putBoolean(Constants.FREEBIE_CLAIMED, true);
        finish();
    }

}
