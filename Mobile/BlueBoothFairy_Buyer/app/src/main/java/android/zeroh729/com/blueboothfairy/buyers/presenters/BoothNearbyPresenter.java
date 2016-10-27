package android.zeroh729.com.blueboothfairy.buyers.presenters;

import android.zeroh729.com.blueboothfairy.buyers.data.events.BluetoothEvent;
import android.zeroh729.com.blueboothfairy.buyers.data.model.ContactProfile;
import android.zeroh729.com.blueboothfairy.buyers.data.model.Exhibitor;
import android.zeroh729.com.blueboothfairy.buyers.interactors.BluetoothSystemImpl;
import android.zeroh729.com.blueboothfairy.buyers.interactors.FirebaseInteractor;
import android.zeroh729.com.blueboothfairy.buyers.interactors.PhoneContactsSystemImpl;
import android.zeroh729.com.blueboothfairy.buyers.interactors.interfaces.BluetoothSystem;
import android.zeroh729.com.blueboothfairy.buyers.interactors.interfaces.PhoneContactsSystem;
import android.zeroh729.com.blueboothfairy.buyers.presenters.base.BasePresenter;
import android.zeroh729.com.blueboothfairy.buyers.presenters.base.SingleCallback;
import android.zeroh729.com.blueboothfairy.buyers.presenters.base.SingleDataCallback;
import android.zeroh729.com.blueboothfairy.buyers.ui.main.activities.BoothNearbyActivity;
import android.zeroh729.com.blueboothfairy.buyers.utils.OttoBus;

import org.altbeacon.beacon.BeaconConsumer;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

@EBean
public class BoothNearbyPresenter implements BasePresenter{
    private final int STATE_EMPTY = 0, STATE_BOOTH_NEARBY = 1, STATE_BLUETOOTH_OFF = 2, STATE_DETAILS_LOCKED = 3;
    private BoothNearbyActivity screen;

    @Bean
    FirebaseInteractor system;

    @Bean
    BluetoothSystemImpl bluetoothSystem;

    @Bean
    PhoneContactsSystemImpl phoneContactsSystem;

    @Bean
    OttoBus bus;

    public void setup(BoothNearbyActivity screen) {
        this.screen = screen;
        bluetoothSystem.bind(screen);
        bluetoothSystem.listenToTransmissions(new SingleDataCallback<String>(){
            @Override
            public void run(String data) {
                bus.post(new BluetoothEvent(data));
            }
        });
    }

    public void unbindListeners(){
        bluetoothSystem.unbind();
    }

    public void subscribeToBluetoothEvents(){
        bluetoothSystem.subscribeToBluetoothEvent();
    }

    public void setExhibitorId(String id){
        system.setExhibitor(id);
        updateState();
        updateContactToggle();
    }

    private void updateContactToggle() {
        if (system.getExhibitor() != null) {
            String displayName = system.getExhibitor().getName();
            screen.setContactsSaved(phoneContactsSystem.isContactSaved(displayName));
        }
    }

    @Override
    public void updateState() {
        if(screen.isDetailsLocked()){
            setState(STATE_DETAILS_LOCKED);
        }else if(!bluetoothSystem.isBluetoothOn()){
            setState(STATE_BLUETOOTH_OFF);
        }else if(system.getExhibitor() == null){
            setState(STATE_EMPTY);
        }else{
            setState(STATE_BOOTH_NEARBY);
        }
    }

    @Override
    public void setState(int state) {
        switch (state){
            case STATE_EMPTY:
                screen.displayEmptyExhibitor();
                break;
            case STATE_DETAILS_LOCKED:
            case STATE_BOOTH_NEARBY:
                screen.hideEmptyExhibitor();
                screen.displayExhibitor(system.getExhibitor());
                break;
            case STATE_BLUETOOTH_OFF:
                screen.hideEmptyExhibitor();
                screen.displayTurnOnBlueTooth();
                break;
        }
    }

    public void onClickGiveBusinessCard(ArrayList<String> products){
        system.subscribeToExhibitor(products);
        screen.displaySuccessGiveBusinessCard();
    }

    public void onClickAllExhibitors(){
        screen.navigateToAllExhibitorsScreen();
    }

    public void onClickTurnOnBluetooth(){
        screen.displayTurnOnBlueTooth();
    }

    public void onClickSaveToContacts(){
        Exhibitor exhibitor = system.getExhibitor();
        ContactProfile contactProfile = new ContactProfile(exhibitor.getName(), exhibitor.getMobile());
        contactProfile.address = exhibitor.getAddress();
        contactProfile.email = exhibitor.getEmail();
        contactProfile.phoneticName = exhibitor.getContact_person();
        contactProfile.jobTitle = exhibitor.getContact_person_role();
        contactProfile.website = exhibitor.getWebsite();

        phoneContactsSystem.saveToContacts(contactProfile, new SingleCallback(){
            @Override
            public void run() {
                updateContactToggle();
            }
        });

    }

    public void onListenToBluetoothStatus() {
        updateState();
    }

    public interface BoothNearbySystem{
        void setExhibitor(String exhibitorId);

        Exhibitor getExhibitor();

        void subscribeToExhibitor(ArrayList<String> products);
    }

    public interface BoothNearbyScreen extends BeaconConsumer{
        void onClickGiveBusinessCard();

        void onClickAllExhibitors();

        void onClickTurnOnBluetooth();

        void onClickSaveToContacts();

        void displayTurnOnBlueTooth();

        void displayEmptyExhibitor();

        void displayExhibitor(Exhibitor exhibitor);

        void displaySuccessGiveBusinessCard();

        void hideEmptyExhibitor();

        void navigateToAllExhibitorsScreen();

        void setContactsSaved(boolean contactSaved);

        boolean isDetailsLocked();
    }
}
