//
// DO NOT EDIT THIS FILE.
// Generated using AndroidAnnotations 4.0.0.
// 
// You can create a larger work that contains this file and distribute that work under terms of your choice.
//


package android.zeroh729.com.blueboothfairy.buyers.interactors;

import android.content.Context;

public final class BluetoothSystemImpl_
    extends BluetoothSystemImpl
{
    private Context context_;

    private BluetoothSystemImpl_(Context context) {
        super(context);
        context_ = context;
        init_();
    }

    public static BluetoothSystemImpl_ getInstance_(Context context) {
        return new BluetoothSystemImpl_(context);
    }

    private void init_() {
        this.context = context_;
    }

    public void rebind(Context context) {
        context_ = context;
        init_();
    }
}
