//
// DO NOT EDIT THIS FILE.
// Generated using AndroidAnnotations 4.0.0.
// 
// You can create a larger work that contains this file and distribute that work under terms of your choice.
//


package android.zeroh729.com.blueboothfairy.buyers.ui.main.views.viewholders;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.zeroh729.com.blueboothfairy.buyers.R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;


/**
 * We use @SuppressWarning here because our java code
 * generator doesn't know that there is no need
 * to import OnXXXListeners from View as we already
 * are in a View.
 * 
 */
@SuppressWarnings("unused")
public final class BasicGridView_
    extends BasicGridView
    implements HasViews, OnViewChangedListener
{
    private boolean alreadyInflated_ = false;
    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();

    public BasicGridView_(Context context) {
        super(context);
        init_();
    }

    public static BasicGridView build(Context context) {
        BasicGridView_ instance = new BasicGridView_(context);
        instance.onFinishInflate();
        return instance;
    }

    /**
     * The alreadyInflated_ hack is needed because of an Android bug
     * which leads to infinite calls of onFinishInflate()
     * when inflating a layout with a parent and using
     * the <merge /> tag.
     * 
     */
    @Override
    public void onFinishInflate() {
        if (!alreadyInflated_) {
            alreadyInflated_ = true;
            inflate(getContext(), R.layout.grid_basic, this);
            onViewChangedNotifier_.notifyViewChanged(this);
        }
        super.onFinishInflate();
    }

    private void init_() {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        this.iv_image = ((ImageView) hasViews.findViewById(R.id.iv_image));
        this.tv_title = ((TextView) hasViews.findViewById(R.id.tv_title));
    }
}
