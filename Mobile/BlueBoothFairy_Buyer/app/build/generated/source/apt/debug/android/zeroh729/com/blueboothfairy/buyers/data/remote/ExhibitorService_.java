//
// DO NOT EDIT THIS FILE.
// Generated using AndroidAnnotations 4.0.0.
// 
// You can create a larger work that contains this file and distribute that work under terms of your choice.
//


package android.zeroh729.com.blueboothfairy.buyers.data.remote;

import android.content.Context;
import android.zeroh729.com.blueboothfairy.buyers.utils.OttoBus_;

public final class ExhibitorService_
    extends ExhibitorService
{
    private Context context_;

    private ExhibitorService_(Context context) {
        context_ = context;
        init_();
    }

    public static ExhibitorService_ getInstance_(Context context) {
        return new ExhibitorService_(context);
    }

    private void init_() {
        this.bus = OttoBus_.getInstance_(context_);
    }

    public void rebind(Context context) {
        context_ = context;
        init_();
    }
}