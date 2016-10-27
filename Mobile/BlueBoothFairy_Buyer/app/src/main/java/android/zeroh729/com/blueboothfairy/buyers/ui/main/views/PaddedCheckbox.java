package android.zeroh729.com.blueboothfairy.buyers.ui.main.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.zeroh729.com.blueboothfairy.buyers.R;
import android.zeroh729.com.blueboothfairy.buyers.utils.ScreenUtil;

import org.androidannotations.annotations.EView;
import org.androidannotations.annotations.EViewGroup;

@EViewGroup(R.layout.view_checkbox)
public class PaddedCheckbox extends FrameLayout{
    private CheckBox checkBox;

    public PaddedCheckbox(Context context) {
        super(context);
        init();
    }

    public PaddedCheckbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaddedCheckbox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PaddedCheckbox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.view_checkbox, this);
        checkBox = (CheckBox)findViewById(R.id.cb_checkbox);
        checkBox.setLayoutParams(new FrameLayout.LayoutParams(ScreenUtil.getScreenWidth(), FrameLayout.LayoutParams.MATCH_PARENT));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.toggle();
            }
        });
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
