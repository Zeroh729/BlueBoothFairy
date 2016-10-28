package android.zeroh729.com.blueboothfairy.buyers.interactors;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.RemoteException;
import android.zeroh729.com.blueboothfairy.buyers.App_;
import android.zeroh729.com.blueboothfairy.buyers.interactors.interfaces.BluetoothSystem;
import android.zeroh729.com.blueboothfairy.buyers.presenters.base.SingleCallback;
import android.zeroh729.com.blueboothfairy.buyers.presenters.base.SingleDataCallback;
import android.zeroh729.com.blueboothfairy.buyers.utils._;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.Collection;

@EBean
public class BluetoothSystemImpl implements BluetoothSystem {
    @RootContext
    Context context;

    private BluetoothAdapter bluetoothAdapter;
    private BeaconManager beaconManager;
    private BeaconConsumer beaconConsumer;
    private SingleDataCallback<String> singleDataCallback;

    public BluetoothSystemImpl(Context context) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            _.showToast("Get a new phone that supports bluetooth.");
            System.exit(0);
        }
    }

    @Override
    public boolean isBluetoothOn() {
        return bluetoothAdapter.isEnabled();
    }

    @Override
    public void listenToTransmissions(SingleDataCallback<String> singleDataCallback) {
        this.singleDataCallback = singleDataCallback;
    }

    @Override
    public void bind(BeaconConsumer consumer) {
        this.beaconConsumer = consumer;
        beaconManager = BeaconManager.getInstanceForApplication(consumer.getApplicationContext());
        beaconManager.bind(beaconConsumer);
    }

    @Override
    public void unbind() {
        beaconManager.unbind(beaconConsumer);
    }

    @Override
    public void subscribeToBluetoothEvent() {
        beaconManager.addMonitorNotifier(new BootstrapNotifier() {
            @Override
            public Context getApplicationContext() {
                return App_.getInstance().getApplicationContext();
            }

            @Override
            public void didEnterRegion(Region region) {
                _.log("Entered! "+ region.getId1().toString());
                broadcastCallback(region.getId1().toString());
            }

            @Override
            public void didExitRegion(Region region) {
                _.log("Exited!");
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {
                _.log("determining...!");
            }
        });
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
                ArrayList<Beacon> bacons = new ArrayList<Beacon>(collection);
                if(!bacons.isEmpty()) {
                    _.log("ranging...! " + bacons.get(0).getId1() + " size: " + collection.size());
                    broadcastCallback(bacons.get(0).getId1().toString());
                    int position = 0;
                    for (int i = 1; i < bacons.size(); i++) {
                        if (bacons.get(i).getDistance() != -1) {
                            if (bacons.get(position).getDistance() > bacons.get(i).getDistance()) {
                                position = i;
                            }
                        }
                    }
                    _.log("Distance is " + bacons.get(position).getDistance());

                    if(bacons.get(position).getDistance() < 2) {
                        broadcastCallback(bacons.get(position).getId1().toString());
                        return;
                    }
                }
                broadcastCallback("");
            }
        });
        beaconManager.setForegroundScanPeriod(1000);
        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {    }
    }

    @UiThread
    void broadcastCallback(String id){
        singleDataCallback.run(id);
    }
}
