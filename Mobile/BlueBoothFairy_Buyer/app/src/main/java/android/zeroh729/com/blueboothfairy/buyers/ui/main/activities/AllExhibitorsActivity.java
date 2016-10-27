package android.zeroh729.com.blueboothfairy.buyers.ui.main.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.zeroh729.com.blueboothfairy.buyers.R;
import android.zeroh729.com.blueboothfairy.buyers.data.events.ExhibitorUpdateEvent;
import android.zeroh729.com.blueboothfairy.buyers.data.events.NetworkEvent;
import android.zeroh729.com.blueboothfairy.buyers.data.model.Exhibitor;
import android.zeroh729.com.blueboothfairy.buyers.data.model.Model;
import android.zeroh729.com.blueboothfairy.buyers.interactors.BoothNearbySystemImpl;
import android.zeroh729.com.blueboothfairy.buyers.presenters.DataListPresenter;
import android.zeroh729.com.blueboothfairy.buyers.ui.base.BaseActivity;
import android.zeroh729.com.blueboothfairy.buyers.ui.base.BaseAdapterRecyclerView;
import android.zeroh729.com.blueboothfairy.buyers.ui.main.adapters.BasicRowRecyclerAdapter;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_allexhibitors)
public class AllExhibitorsActivity extends BaseActivity implements DataListPresenter.Screen{

    @ViewById(R.id.rv_data)
    RecyclerView rv_data;

    @Bean
    BasicRowRecyclerAdapter adapter;

    @Bean
    BoothNearbySystemImpl firebaseInteractor;

    @AfterViews
    public void afterViews(){
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(adapter);
        adapter.setListener(new BaseAdapterRecyclerView.ClickListener() {
            @Override
            public void onClick(int position) {
                BoothNearbyActivity_.intent(AllExhibitorsActivity.this).extra("isDetailsLocked", true).extra("exhibitorId", adapter.getItems().get(position).getId()).start();
            }
        });
    }

    @Subscribe
    public void onSubscribeExhibitor(ExhibitorUpdateEvent event){
        Exhibitor ex = event.getExhibitor();
        Model model = new Model();
        model.setId(ex.getId());
        model.setImage(ex.getImgUrl());
        model.setTitle(ex.getName());
        adapter.getItems().add(model);
    }

    @Click(R.id.btn_back)
    void onClickBack(){
        finish();
    }

    @Subscribe
    public void subscribeToNetworkEvent(NetworkEvent event){
        Toast.makeText(this, "Connected : " + event.isConnected(), Toast.LENGTH_SHORT).show();
    }
}
