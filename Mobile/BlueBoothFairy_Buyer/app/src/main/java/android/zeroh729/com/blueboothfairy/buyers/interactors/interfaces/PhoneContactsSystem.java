package android.zeroh729.com.blueboothfairy.buyers.interactors.interfaces;

import android.zeroh729.com.blueboothfairy.buyers.data.model.ContactProfile;
import android.zeroh729.com.blueboothfairy.buyers.presenters.base.SingleCallback;

public interface PhoneContactsSystem {
    void saveToContacts(ContactProfile contactProfile, SingleCallback callback);

    boolean isContactSaved(String displayName);
}
