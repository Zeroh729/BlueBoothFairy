package android.zeroh729.com.blueboothfairy.buyers.ui.main.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.zeroh729.com.blueboothfairy.buyers.data.model.Model;
import android.zeroh729.com.blueboothfairy.buyers.ui.base.BaseAdapterRecyclerView;
import android.zeroh729.com.blueboothfairy.buyers.ui.base.ViewWrapper;
import android.zeroh729.com.blueboothfairy.buyers.ui.main.views.viewholders.BasicGridView;
import android.zeroh729.com.blueboothfairy.buyers.ui.main.views.viewholders.BasicGridView_;
import android.zeroh729.com.blueboothfairy.buyers.utils._;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class BasicGridRecyclerAdapter extends BaseAdapterRecyclerView<Model, BasicGridView>{
    @RootContext
    Context context;

    @Override
    protected BasicGridView onCreateItemView(ViewGroup parent, int viewType) {
        return BasicGridView_.build(context);
    }

    @Override
    public void onBindViewHolder(final ViewWrapper<BasicGridView> holder, final int position) {
        holder.getView().bind(items.get(position), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(holder.getAdapterPosition());
            }
        });
    }
}