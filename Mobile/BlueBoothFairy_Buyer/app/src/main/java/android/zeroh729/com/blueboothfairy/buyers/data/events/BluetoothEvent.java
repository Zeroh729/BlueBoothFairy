package android.zeroh729.com.blueboothfairy.buyers.data.events;

public class BluetoothEvent {
    private String bluetoothId = "";

    public BluetoothEvent(String bluetoothId) {
        this.bluetoothId = bluetoothId;
    }

    public String getBluetoothId() {
        return bluetoothId;
    }
}
