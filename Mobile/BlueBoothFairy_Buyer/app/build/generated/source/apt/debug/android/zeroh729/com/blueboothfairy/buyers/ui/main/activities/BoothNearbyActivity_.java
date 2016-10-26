//
// DO NOT EDIT THIS FILE.
// Generated using AndroidAnnotations 4.0.0.
// 
// You can create a larger work that contains this file and distribute that work under terms of your choice.
//


package android.zeroh729.com.blueboothfairy.buyers.ui.main.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.zeroh729.com.blueboothfairy.buyers.R;
import android.zeroh729.com.blueboothfairy.buyers.data.events.BluetoothEvent;
import android.zeroh729.com.blueboothfairy.buyers.data.events.ExhibitorUpdateEvent;
import android.zeroh729.com.blueboothfairy.buyers.presenters.BoothNearbyPresenter_;
import com.squareup.otto.Subscribe;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class BoothNearbyActivity_
    extends BoothNearbyActivity
    implements HasViews, OnViewChangedListener
{
    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    public final static String IS_DETAILS_LOCKED_EXTRA = "isDetailsLocked";
    public final static String EXHIBITOR_ID_EXTRA = "exhibitorId";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(R.layout.activity_boothnearby);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        this.presenter = BoothNearbyPresenter_.getInstance_(this);
        injectExtras_();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static BoothNearbyActivity_.IntentBuilder_ intent(Context context) {
        return new BoothNearbyActivity_.IntentBuilder_(context);
    }

    public static BoothNearbyActivity_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new BoothNearbyActivity_.IntentBuilder_(fragment);
    }

    public static BoothNearbyActivity_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new BoothNearbyActivity_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        this.iv_header = ((ImageView) hasViews.findViewById(R.id.iv_header));
        this.tv_descripion = ((TextView) hasViews.findViewById(R.id.tv_descripion));
        this.tv_exhibitorname = ((TextView) hasViews.findViewById(R.id.tv_exhibitorname));
        this.tv_contactdetails = ((TextView) hasViews.findViewById(R.id.tv_contactdetails));
        this.btn_savecontact = ((ImageButton) hasViews.findViewById(R.id.btn_savecontact));
        this.rg_productlist = ((RadioGroup) hasViews.findViewById(R.id.rg_productlist));
        this.layout_empty = ((ViewGroup) hasViews.findViewById(R.id.layout_empty));
        View view_btn_allexhibitors = hasViews.findViewById(R.id.btn_allexhibitors);

        if (view_btn_allexhibitors!= null) {
            view_btn_allexhibitors.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    BoothNearbyActivity_.this.onClickAllExhibitors();
                }
            }
            );
        }
        if (this.btn_savecontact!= null) {
            this.btn_savecontact.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    BoothNearbyActivity_.this.onClickSaveToContacts();
                }
            }
            );
        }
        afterInject();
    }

    private void injectExtras_() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_!= null) {
            if (extras_.containsKey(IS_DETAILS_LOCKED_EXTRA)) {
                this.isDetailsLocked = extras_.getBoolean(IS_DETAILS_LOCKED_EXTRA);
            }
            if (extras_.containsKey(EXHIBITOR_ID_EXTRA)) {
                this.exhibitorId = extras_.getString(EXHIBITOR_ID_EXTRA);
            }
        }
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        injectExtras_();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case  1001 :
            {
                BoothNearbyActivity_.this.onBluetoothIntent(resultCode, data);
                break;
            }
        }
    }

    @Override
    @Subscribe
    public void onBluetoothEvent(final BluetoothEvent event) {
        BoothNearbyActivity_.super.onBluetoothEvent(event);
    }

    @Override
    @Subscribe
    public void onExhibitorEvent(final ExhibitorUpdateEvent event) {
        BoothNearbyActivity_.super.onExhibitorEvent(event);
    }

    public static class IntentBuilder_
        extends ActivityIntentBuilder<BoothNearbyActivity_.IntentBuilder_>
    {
        private android.app.Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            super(context, BoothNearbyActivity_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            super(fragment.getActivity(), BoothNearbyActivity_.class);
            fragment_ = fragment;
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            super(fragment.getActivity(), BoothNearbyActivity_.class);
            fragmentSupport_ = fragment;
        }

        @Override
        public PostActivityStarter startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent, requestCode);
            } else {
                if (fragment_!= null) {
                    fragment_.startActivityForResult(intent, requestCode, lastOptions);
                } else {
                    if (context instanceof Activity) {
                        Activity activity = ((Activity) context);
                        ActivityCompat.startActivityForResult(activity, intent, requestCode, lastOptions);
                    } else {
                        context.startActivity(intent, lastOptions);
                    }
                }
            }
            return new PostActivityStarter(context);
        }

        /**
         * 
         * @param isDetailsLocked
         *     the value for this extra
         * @return
         *     the IntentBuilder to chain calls
         */
        public BoothNearbyActivity_.IntentBuilder_ isDetailsLocked(boolean isDetailsLocked) {
            return super.extra(IS_DETAILS_LOCKED_EXTRA, isDetailsLocked);
        }

        /**
         * 
         * @param exhibitorId
         *     the value for this extra
         * @return
         *     the IntentBuilder to chain calls
         */
        public BoothNearbyActivity_.IntentBuilder_ exhibitorId(String exhibitorId) {
            return super.extra(EXHIBITOR_ID_EXTRA, exhibitorId);
        }
    }
}