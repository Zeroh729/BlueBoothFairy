package android.zeroh729.com.blueboothfairy.buyers.interactors;

import android.support.annotation.Nullable;
import android.zeroh729.com.blueboothfairy.buyers.App;
import android.zeroh729.com.blueboothfairy.buyers.App_;
import android.zeroh729.com.blueboothfairy.buyers.data.events.ExhibitorUpdateEvent;
import android.zeroh729.com.blueboothfairy.buyers.data.model.Exhibitor;
import android.zeroh729.com.blueboothfairy.buyers.data.model.User_;
import android.zeroh729.com.blueboothfairy.buyers.data.values.DbConstants;
import android.zeroh729.com.blueboothfairy.buyers.presenters.BoothNearbyPresenter;
import android.zeroh729.com.blueboothfairy.buyers.utils.OttoBus;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.HashMap;

@EBean
public class FirebaseInteractor implements BoothNearbyPresenter.BoothNearbySystem{
    private DatabaseReference ref;
    private ArrayList<Exhibitor> exhibitors;
    private String exhibitorId;
    private Exhibitor selectedExhibitor;

    @Bean
    OttoBus bus;

    public FirebaseInteractor() {
        ref = FirebaseDatabase.getInstance().getReference();
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
                for(Exhibitor exhibitor : exhibitors){
                    if(exhibitor.getId().equals(dataSnapshot.getKey())){
                        exhibitors.remove(exhibitor);
                    }
                }
                updateExhibitors(getExhibitorFromDatasnapshot(dataSnapshot));
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

    public ArrayList<Exhibitor> getExhibitors() {
        return exhibitors;
    }
}
