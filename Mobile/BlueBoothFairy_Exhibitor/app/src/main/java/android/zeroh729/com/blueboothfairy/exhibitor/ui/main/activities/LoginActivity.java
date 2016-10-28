package android.zeroh729.com.blueboothfairy.exhibitor.ui.main.activities;

import android.widget.Button;
import android.zeroh729.com.blueboothfairy.exhibitor.R;
import android.zeroh729.com.blueboothfairy.exhibitor.data.model.Exhibitor;
import android.zeroh729.com.blueboothfairy.exhibitor.data.remote.ExhibitorRepo;
import android.zeroh729.com.blueboothfairy.exhibitor.ui.base.BaseActivity;
import android.zeroh729.com.blueboothfairy.exhibitor.utils._;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewById(R.id.btn_login)
    Button btn_login;

    private ArrayList<Exhibitor> exhibitorList;

    @AfterViews
    void afterviews(){
        btn_login.setEnabled(false);
        btn_login.setText("Loading companies...");
        ExhibitorRepo.fetchExhibitors(new ExhibitorRepo.Callback() {
            @Override
            public void onSuccess(ArrayList<Exhibitor> exhibitors) {
                exhibitorList = exhibitors;
                btn_login.setText("Select Your Company");
                btn_login.setEnabled(true);
            }

            @Override
            public void onFailure(String message) {
                _.showToast(message);
            }
        });
    }

    @Click(R.id.btn_login)
    void onClickLogin(){
//        MainActivity_.intent(this).start();
        showSelectDialog();
    }
}
