package android.zeroh729.com.blueboothfairy.buyers.interactors;

import android.support.annotation.Nullable;
import android.zeroh729.com.blueboothfairy.buyers.App;
import android.zeroh729.com.blueboothfairy.buyers.App_;
import android.zeroh729.com.blueboothfairy.buyers.data.events.ExhibitorUpdateEvent;
import android.zeroh729.com.blueboothfairy.buyers.data.local.SharedPrefHelper;
import android.zeroh729.com.blueboothfairy.buyers.data.model.Exhibitor;
import android.zeroh729.com.blueboothfairy.buyers.data.model.User_;
import android.zeroh729.com.blueboothfairy.buyers.data.values.Constants;
import android.zeroh729.com.blueboothfairy.buyers.data.values.DbConstants;
import android.zeroh729.com.blueboothfairy.buyers.presenters.BoothNearbyPresenter;
import android.zeroh729.com.blueboothfairy.buyers.utils.OttoBus;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.HashMap;

@EBean
public class BoothNearbySystemImpl implements BoothNearbyPresenter.BoothNearbySystem{
    private DatabaseReference ref;
    private ArrayList<Exhibitor> exhibitors;
    private String exhibitorId;
    private Exhibitor selectedExhibitor;
    private SharedPrefHelper prefs;

    @Bean
    OttoBus bus;

    public BoothNearbySystemImpl() {
        ref = FirebaseDatabase.getInstance().getReference();
        prefs = SharedPrefHelper.getInstance(App_.getInstance().getContext());
        exhibitors = new ArrayList<>();
        fetchExhibitors();
    }

    private void fetchExhibitors(){
        final DatabaseReference exhbitorRef = ref.child(DbConstants.CHILD_EXHIBITORS);
        exhbitorRef.keepSynced(true);
        exhbitorRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateExhibitors(getExhibitorFromDatasnapshot(dataSnapshot));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for(Exhibitor exhibitor : exhibitors){
                    if(exhibitor.getId().equals(dataSnapshot.getKey())){
                        updateExhibitors(getExhibitorFromDatasnapshot(dataSnapshot));
                    }
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private Exhibitor getExhibitorFromDatasnapshot(DataSnapshot dataSnapshot) {
        HashMap map = (HashMap)dataSnapshot.getValue();
        Exhibitor exhibitor = new Exhibitor();
        exhibitor.setId(dataSnapshot.getKey());
        exhibitor.setAddress(map.get(DbConstants.FIELD_ADDRESS).toString());
        exhibitor.setContact_person(map.get(DbConstants.FIELD_CONTACT_PERSON).toString());
        exhibitor.setContact_person_role(map.get(DbConstants.FIELD_CONTACT_PERSON_ROLE).toString());
        exhibitor.setDescription(map.get(DbConstants.FIELD_DESCRIPTION).toString());
        exhibitor.setEmail(map.get(DbConstants.FIELD_EMAIL).toString());
        exhibitor.setImgUrl(map.get(DbConstants.FIELD_IMG_URL).toString());
        exhibitor.setMobile(map.get(DbConstants.FIELD_MOBILE).toString());
        exhibitor.setName(map.get(DbConstants.FIELD_NAME).toString());
        exhibitor.setProducts((ArrayList<String>) map.get(DbConstants.FIELD_PRODUCTS));
        HashMap temp = (HashMap)map.get(DbConstants.FIELD_INTERESTED_BUYERS);
        String userId = User_.getInstance_(App_.getInstance()).getId();
        exhibitor.setSubscribed(temp.containsKey(userId));
        if(exhibitor.isSubscribed()) {
            exhibitor.setSubscribedProducts((ArrayList<String>)temp.get(userId));
        }
        return exhibitor;
    }

    public void updateExhibitors(Exhibitor exhibitor){
        exhibitors.add(exhibitor);
        bus.post(new ExhibitorUpdateEvent(exhibitor));
        if(selectedExhibitor == null) {
            setExhibitor(exhibitorId);
        }
    }

    @Override
    public void setExhibitor(String exhibitorId) {
        this.exhibitorId = exhibitorId;
        for(Exhibitor exhibitor : exhibitors){
            if(exhibitor.getId().equals(exhibitorId)){
                selectedExhibitor = exhibitor;
            }
        }
    }

    @Override
    @Nullable
    public Exhibitor getExhibitor() {
        return selectedExhibitor;
    }

    @Override
    public void subscribeToExhibitor(ArrayList<String> products) {
        ref.child(DbConstants.CHILD_EXHIBITORS).child(selectedExhibitor.getId()).child(DbConstants.FIELD_INTERESTED_BUYERS).child(User_.getInstance_(App_.getInstance().getContext()).getId()).setValue(products);
    }

    @Override
    public boolean isFreebieClaimed() {
        return prefs.getBoolean(Constants.FREEBIE_CLAIMED);
    }

    public ArrayList<Exhibitor> getExhibitors() {
        return exhibitors;
    }
}
