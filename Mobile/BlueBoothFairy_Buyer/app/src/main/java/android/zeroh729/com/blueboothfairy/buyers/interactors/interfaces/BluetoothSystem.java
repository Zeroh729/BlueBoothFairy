package android.zeroh729.com.blueboothfairy.buyers.interactors.interfaces;

import android.content.Context;
import android.zeroh729.com.blueboothfairy.buyers.presenters.base.SingleCallback;
import android.zeroh729.com.blueboothfairy.buyers.presenters.base.SingleDataCallback;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;

public interface BluetoothSystem {
    boolean isBluetoothOn();

    void listenToTransmissions(SingleDataCallback<String> singleDataCallback);

    void bind(BeaconConsumer context);

    void unbind();

    void subscribeToBluetoothEvent();
}
