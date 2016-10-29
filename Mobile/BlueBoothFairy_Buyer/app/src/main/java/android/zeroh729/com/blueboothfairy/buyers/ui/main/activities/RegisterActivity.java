package android.zeroh729.com.blueboothfairy.buyers.ui.main.activities;

import android.widget.Button;
import android.widget.EditText;
import android.zeroh729.com.blueboothfairy.buyers.R;
import android.zeroh729.com.blueboothfairy.buyers.data.model.User;
import android.zeroh729.com.blueboothfairy.buyers.data.model.User_;
import android.zeroh729.com.blueboothfairy.buyers.presenters.RegisterPresenter;
import android.zeroh729.com.blueboothfairy.buyers.ui.base.BaseActivity;
import android.zeroh729.com.blueboothfairy.buyers.utils._;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity implements RegisterPresenter.RegisterScreen{
    @ViewById(R.id.btn_signup)
    Button btn_signup;

    @ViewById(R.id.et_name)
    EditText et_name;

    @ViewById(R.id.et_email)
    EditText et_email;

    @ViewById(R.id.et_contactno)
    EditText et_contact;

    private RegisterPresenter presenter;

    @AfterViews
    void afterviews(){
        presenter = new RegisterPresenter(this);
    }

    @Override
    public void navigateToMainActivity() {
        BoothNearbyActivity_.intent(this).start();
    }

    @Click(R.id.btn_signup)
    void onClickSignUp(){
        User user = User_.getInstance_(this);
        user.setEmail(et_email.getText().toString());
        user.setName(et_name.getText().toString());
        user.setContactNumber(et_contact.getText().toString());
        presenter.onSignUpPressed(user);
    }

    @Override
    public void displayLoading() {
        btn_signup.setEnabled(false);
        btn_signup.setText("Signing in...");
        et_name.setEnabled(false);
        et_email.setEnabled(false);
        et_contact.setEnabled(false);
    }

    @Override
    public void hideLoading() {
        btn_signup.setEnabled(true);
        btn_signup.setText("Sign In");
        et_name.setEnabled(true);
        et_email.setEnabled(true);
        et_contact.setEnabled(true);
    }

    @Override
    public void showError(String message) {
        _.showToast(message);
    }

    @Override
    public void showSuccess() {
        _.showToast("Success!");
    }
}
