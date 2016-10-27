package android.zeroh729.com.blueboothfairy.exhibitor.ui.main.views.viewholders;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.zeroh729.com.blueboothfairy.exhibitor.R;
import android.zeroh729.com.blueboothfairy.exhibitor.data.model.Model;
import android.zeroh729.com.blueboothfairy.exhibitor.ui.main.views.SquareLayout;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import static java.security.AccessController.getContext;

@EViewGroup(R.layout.grid_basic)
public class BasicGridView extends SquareLayout {
    @ViewById(R.id.iv_image)
    ImageView iv_image;

    @ViewById(R.id.tv_title)
    TextView tv_title;

    public BasicGridView(Context context) {
        super(context);
    }

    public void bind(Model model, View.OnClickListener listener){
        Glide.with(getContext()).load(Uri.parse(model.getImage())).into(iv_image);
        tv_title.setText(model.getTitle());
        setOnClickListener(listener);
    }
}
