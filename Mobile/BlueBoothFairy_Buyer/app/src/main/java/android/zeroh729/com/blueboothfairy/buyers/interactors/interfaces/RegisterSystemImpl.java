package android.zeroh729.com.blueboothfairy.buyers.interactors.interfaces;

import android.support.annotation.NonNull;
import android.zeroh729.com.blueboothfairy.buyers.App;
import android.zeroh729.com.blueboothfairy.buyers.App_;
import android.zeroh729.com.blueboothfairy.buyers.data.local.SharedPrefHelper;
import android.zeroh729.com.blueboothfairy.buyers.data.model.User;
import android.zeroh729.com.blueboothfairy.buyers.data.model.User_;
import android.zeroh729.com.blueboothfairy.buyers.data.values.Constants;
import android.zeroh729.com.blueboothfairy.buyers.presenters.RegisterPresenter;
import android.zeroh729.com.blueboothfairy.buyers.presenters.base.SingleDataCallback;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Boolean.getBoolean;

public class RegisterSystemImpl implements RegisterPresenter.RegisterSystem {
    private SharedPrefHelper prefs;

    public RegisterSystemImpl() {
        prefs = SharedPrefHelper.getInstance(App_.getInstance().getContext());
    }

    @Override
    public void saveUser(final User user, final SingleDataCallback<String> callback) {
        DatabaseReference buyersRef = FirebaseDatabase.getInstance().getReference().child("buyers").push();
        user.setId(buyersRef.getKey());
        buyersRef.setValue(user.toHash()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    prefs.putString(Constants.USER_ID, user.getId());
                    prefs.putString(Constants.USER_NAME, user.getName());
                    prefs.putString(Constants.USER_EMAIL, user.getEmail());
                    prefs.putString(Constants.USER_CONTACTNO, user.getContactNumber());
                    prefs.putBoolean(Constants.USER_DETAILS, true);
                    User_.getInstance_(App_.getInstance()).setUser(user);
                    callback.run(null);
                }else{
                    callback.run(task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public boolean isLoggedInBefore() {
        boolean isLoggedInBefore = prefs.getBoolean(Constants.USER_DETAILS);

        if(isLoggedInBefore){
            User user = User_.getInstance_(App_.getInstance());
            user.setId(prefs.getString(Constants.USER_ID));
            user.setName(prefs.getString(Constants.USER_NAME));
            user.setEmail(prefs.getString(Constants.USER_EMAIL));
            user.setContactNumber(prefs.getString(Constants.USER_CONTACTNO));
        }

        return isLoggedInBefore;
    }




}
