//
// DO NOT EDIT THIS FILE.
// Generated using AndroidAnnotations 4.0.0.
// 
// You can create a larger work that contains this file and distribute that work under terms of your choice.
//


package android.zeroh729.com.blueboothfairy.exhibitor.ui.main.adapters;

import android.content.Context;

public final class BasicRowRecyclerAdapter_
    extends BasicRowRecyclerAdapter
{
    private Context context_;

    private BasicRowRecyclerAdapter_(Context context) {
        context_ = context;
        init_();
    }

    public static BasicRowRecyclerAdapter_ getInstance_(Context context) {
        return new BasicRowRecyclerAdapter_(context);
    }

    private void init_() {
        this.context = context_;
    }

    public void rebind(Context context) {
        context_ = context;
        init_();
    }
}
