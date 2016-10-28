package android.zeroh729.com.blueboothfairy.buyers.presenters;

import android.zeroh729.com.blueboothfairy.buyers.data.model.User;
import android.zeroh729.com.blueboothfairy.buyers.interactors.interfaces.RegisterSystemImpl;
import android.zeroh729.com.blueboothfairy.buyers.presenters.base.BasePresenter;
import android.zeroh729.com.blueboothfairy.buyers.presenters.base.SingleDataCallback;
import android.zeroh729.com.blueboothfairy.buyers.ui.main.activities.RegisterActivity;

import com.google.firebase.database.DatabaseError;

public class RegisterPresenter implements BasePresenter {
    private RegisterActivity screen;
    private RegisterSystemImpl system;

    public RegisterPresenter(RegisterActivity screen) {
        this.screen = screen;
        system = new RegisterSystemImpl();
        if(system.isLoggedInBefore()){
            screen.navigateToMainActivity();
        }
    }

    @Override
    public void updateState() {

    }

    @Override
    public void setState(int state) {

    }

    public void onSignUpPressed(User user){
        if(user.getEmail().isEmpty()|| user.getName().isEmpty() || user.getContactNumber().isEmpty()){
            screen.showError("One of the fields is missing!");
            return;
        }
        screen.displayLoading();
        system.saveUser(user, new SingleDataCallback<String>() {
            @Override
            public void run(String error) {
                if(error == null){
                    screen.navigateToMainActivity();
                    screen.showSuccess();
                }else{
                    screen.hideLoading();
                    screen.showError("Error: " + error);
                }
            }
        });
    }

    public interface RegisterScreen{
        void navigateToMainActivity();
        void displayLoading();
        void hideLoading();
        void showError(String message);
        void showSuccess();
    }

    public interface RegisterSystem{
        void saveUser(User user, SingleDataCallback<String> callback);
        boolean isLoggedInBefore();
    }
}
