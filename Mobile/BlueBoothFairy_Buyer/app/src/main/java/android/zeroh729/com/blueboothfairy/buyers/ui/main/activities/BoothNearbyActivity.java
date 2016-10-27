package android.zeroh729.com.blueboothfairy.buyers.ui.main.activities;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.zeroh729.com.blueboothfairy.buyers.R;
import android.zeroh729.com.blueboothfairy.buyers.data.events.BluetoothEvent;
import android.zeroh729.com.blueboothfairy.buyers.data.events.ExhibitorUpdateEvent;
import android.zeroh729.com.blueboothfairy.buyers.data.model.Exhibitor;
import android.zeroh729.com.blueboothfairy.buyers.presenters.BoothNearbyPresenter;
import android.zeroh729.com.blueboothfairy.buyers.ui.base.BaseActivity;
import android.zeroh729.com.blueboothfairy.buyers.ui.main.views.PaddedCheckbox;
import android.zeroh729.com.blueboothfairy.buyers.utils._;

import com.bumptech.glide.Glide;
import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import static android.zeroh729.com.blueboothfairy.buyers.data.values.Constants.REQUEST_ENABLE_BT;

@EActivity(R.layout.activity_boothnearby)
public class BoothNearbyActivity extends BaseActivity implements BoothNearbyPresenter.BoothNearbyScreen {
    @ViewById(R.id.parent_view)
    ViewGroup parent_view;

    @ViewById(R.id.iv_header)
    ImageView iv_header;

    @ViewById(R.id.tv_descripion)
    TextView tv_descripion;

    @ViewById(R.id.tv_exhibitorname)
    TextView tv_exhibitorname;

    @ViewById(R.id.tv_contactdetails)
    TextView tv_contactdetails;

    @ViewById(R.id.btn_savecontact)
    ImageButton btn_savecontact;

    @ViewById(R.id.btn_givecard)
    Button btn_givecard;

    @ViewById(R.id.rg_productlist)
    RadioGroup rg_productlist;

    @ViewById(R.id.layout_empty)
    ViewGroup layout_empty;

    @Extra
    boolean isDetailsLocked = false;

    @Bean
    BoothNearbyPresenter presenter;

    @Extra
    String exhibitorId;

    private boolean isBluetoothDialogShowing;
    private final String DISABLED_MESSAGE = "Business Card Sent";

    @AfterViews
    void afterviews(){
        presenter.setup(this);
        if(exhibitorId != null){
            presenter.setExhibitorId(exhibitorId);
        }
        isBluetoothDialogShowing = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbindListeners();
    }

    @Subscribe
    public void onBluetoothEvent(BluetoothEvent event){
        exhibitorId = event.getBluetoothId();
        presenter.setExhibitorId(event.getBluetoothId());
    }

    @Subscribe
    public void onExhibitorEvent(ExhibitorUpdateEvent event){
        presenter.setExhibitorId(exhibitorId);
    }

    @OnActivityResult(REQUEST_ENABLE_BT)
    void onBluetoothIntent(int resultCode, Intent intent){
//        presenter.onListenToBluetoothStatus();
        if(resultCode != RESULT_OK){
            _.showToast("Try again! Turn on bluetooth");
        }
        isBluetoothDialogShowing = false;
    }

    @Override
    public void onClickGiveBusinessCard() {
        ArrayList<String> products = new ArrayList<>();
        presenter.onClickGiveBusinessCard(products);
    }

    @Override
    @Click(R.id.btn_allexhibitors)
    public void onClickAllExhibitors(){
        AllExhibitorsActivity_.intent(this).start();
    }

    @Click(R.id.btn_allexhibitors_inverse)
    public void onClickAllExhibitors2(){
        AllExhibitorsActivity_.intent(this).start();
    }

    @Override
    public void onClickTurnOnBluetooth() {
        presenter.onClickTurnOnBluetooth();
    }

    @Click(R.id.btn_givecard)
    public void onClickGiveCard(){
        ArrayList<String> products = new ArrayList<>();
        for(int i = 0; i < rg_productlist.getChildCount(); i++) {
            PaddedCheckbox cb = (PaddedCheckbox) rg_productlist.getChildAt(i);
            if (cb.getCheckBox().isChecked()) {
                products.add(cb.getCheckBox().getText().toString());
            }
        }
        presenter.onClickGiveBusinessCard(products);
    }

    @Override
    @Click(R.id.btn_savecontact)
    public void onClickSaveToContacts() {
        presenter.onClickSaveToContacts();
    }

    @Override
    public void displayTurnOnBlueTooth() {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        isBluetoothDialogShowing = true;
    }

    @Override
    public void displayEmptyExhibitor() {
        layout_empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayExhibitor(Exhibitor exhibitor) {
        tv_exhibitorname.setText(exhibitor.getName());
        tv_contactdetails.setText(exhibitor.getContact_person() + ", " + exhibitor.getContact_person_role()
                                + "\n" + exhibitor.getMobile()
                                + "\n" + exhibitor.getEmail());
        tv_descripion.setText(exhibitor.getDescription());
        rg_productlist.removeAllViews();
        for(String product : exhibitor.getProducts()){
            PaddedCheckbox cb = new PaddedCheckbox(this);
            cb.getCheckBox().setText(product);
            rg_productlist.addView(cb);
            cb.getCheckBox().setEnabled(!exhibitor.isSubscribed());
            cb.getCheckBox().setChecked(exhibitor.getSubscribedProducts().contains(product));
        }
        Glide.with(this).load(exhibitor.getImgUrl()).into(iv_header);
        if(exhibitor.isSubscribed()){
            btn_givecard.setEnabled(false);
            btn_givecard.setText(DISABLED_MESSAGE);
        }
    }

    @Override
    public void displaySuccessGiveBusinessCard() {
        _.showSnack(parent_view, "Business card sent!");
    }

    @Override
    public void displayCheckInScreen() {
        CheckInActivity_.intent(this).start();
    }

    @Override
    public void hideEmptyExhibitor() {
        layout_empty.setVisibility(View.GONE);
    }

    @Override
    public void navigateToAllExhibitorsScreen() {
        AllExhibitorsActivity_.intent(this).start();
    }

    @Override
    public boolean isDetailsLocked() {
        return isDetailsLocked;
    }

    @Override
    public boolean isBluetoothDialogShowing() {
        return isBluetoothDialogShowing;
    }

    @Override
    public void enableGiveCardElements(boolean isEnabled) {
        if(isEnabled){
            btn_givecard.setText("Give Business Card");
        }else{
            btn_givecard.setText(DISABLED_MESSAGE);
        }
        btn_givecard.setEnabled(isEnabled);
        for(int i = 0; i < rg_productlist.getChildCount(); i++) {
            ((PaddedCheckbox) rg_productlist.getChildAt(i)).getCheckBox().setEnabled(isEnabled);
        }
    }

    @Override
    public void setContactsSaved(boolean isContactSaved) {
        if(isContactSaved){
            btn_savecontact.setImageResource(R.drawable.btn_saved);
            _.showSnack(parent_view, "Contact saved!");
        }else{
            btn_savecontact.setImageResource(R.drawable.btn_save);
        }
    }

    @Override
    public void showMessage(String message) {
        _.showToast(message);
    }

    @Override
    public void onBeaconServiceConnect() {
        presenter.subscribeToBluetoothEvents();
    }
}
