package android.zeroh729.com.blueboothfairy.exhibitor.ui.main.views.viewholders;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.zeroh729.com.blueboothfairy.exhibitor.R;
import android.zeroh729.com.blueboothfairy.exhibitor.data.model.Model;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.row_basic)
public class BasicRowView extends RelativeLayout{
    @ViewById(R.id.tv_title)
    TextView tv_title;

    @ViewById(R.id.tv_subtitle)
    TextView tv_subtitle;

    @ViewById(R.id.iv_image)
    ImageView iv_image;

    public BasicRowView(Context context) {
        super(context);
    }

    public void bind(Model model, OnClickListener listener){
        tv_title.setText(model.getTitle());
        tv_subtitle.setText(model.getSubtitle());
        Glide.with(getContext()).load(Uri.parse(model.getImage())).into(iv_image);
        setOnClickListener(listener);
    }

}
