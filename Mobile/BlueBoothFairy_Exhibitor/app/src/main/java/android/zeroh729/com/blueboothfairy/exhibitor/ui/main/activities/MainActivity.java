package android.zeroh729.com.blueboothfairy.exhibitor.ui.main.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.zeroh729.com.blueboothfairy.exhibitor.R;
import android.zeroh729.com.blueboothfairy.exhibitor.data.events.NetworkEvent;
import android.zeroh729.com.blueboothfairy.exhibitor.presenters.DataListPresenter;
import android.zeroh729.com.blueboothfairy.exhibitor.ui.base.BaseActivity;
import android.widget.Toast;
import android.zeroh729.com.blueboothfairy.exhibitor.utils._;

import com.squareup.otto.Subscribe;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BeaconTransmitter;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;

import me.alexrs.wavedrawable.WaveDrawable;


@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements DataListPresenter.Screen{
    private WaveDrawable waveDrawable;

    @ViewById(R.id.iv_ripple)
    View ripple_bg;

    @ViewById(R.id.tv_exhibitorname)
    TextView tv_exhibitorname;

    private static final int REQUEST_ENABLE_BT = 1111;

    @AfterViews
    public void afterViews(){
        waveDrawable = new WaveDrawable(Color.parseColor("#6addea"), 500);
        waveDrawable.setWaveInterpolator(new LinearInterpolator());
        ripple_bg.setBackground(waveDrawable);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            _.showToast("Get a new phone that supports bluetooth.");
            finish();
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }else{
            startTransmitting();
        }
    }

    private void startTransmitting(){
        Beacon beacon = new Beacon.Builder()
                .setId1("2f234454-cf6d-4a0f-adf2-f4911ba9ffa6")
                .setId2("1")
                .setId3("2")
                .setManufacturer(0x0118) // Radius Networks.  Change this for other beacon layouts
                .setTxPower(-59)
                .setDataFields(Arrays.asList(new Long[] {0l})) // Remove this for beacon layouts without d: fields
                .build();

        // Change the layout below for other beacon types
        BeaconParser beaconParser = new BeaconParser()
                .setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25");
        BeaconTransmitter beaconTransmitter = new BeaconTransmitter(getApplicationContext(), beaconParser);
        beaconTransmitter.startAdvertising(beacon, new AdvertiseCallback() {
            @Override
            public void onStartFailure(int errorCode) {
                _.showToast("Advertisement start failed with code: "+errorCode);
            }

            @Override
            public void onStartSuccess(AdvertiseSettings settingsInEffect) {
                _.showToast("Advertisement start succeeded.");
                prepareWaveAnimation();
            }
        });
    }


    @OnActivityResult(REQUEST_ENABLE_BT)
    void onBluetoothIntent(int resultCode, Intent intent){
        if(resultCode == RESULT_OK){
            startTransmitting();
        }else{
            _.showToast("Try again! Turn on Bluetooth.");
        }
    }


    @Background(delay = 1000)
    void prepareWaveAnimation(){
        startWaveAnimation();
    }

    @UiThread
    void startWaveAnimation(){
        waveDrawable.startAnimation();
    }

    @Subscribe
    public void subscribeToNetworkEvent(NetworkEvent event){
        Toast.makeText(this, "Connected : " + event.isConnected(), Toast.LENGTH_SHORT).show();
    }
}
