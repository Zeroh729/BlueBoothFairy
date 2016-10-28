package android.zeroh729.com.blueboothfairy.exhibitor.data.remote;

import android.zeroh729.com.blueboothfairy.exhibitor.data.model.Exhibitor;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExhibitorRepo {
    private static ArrayList<Exhibitor> exhibitorList;
    private static ArrayList<String> names = new ArrayList<>();


    public static void fetchExhibitors(final Callback callback){
        exhibitorList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("exhibitors");
        ref.keepSynced(true);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap exhibitors = (HashMap)dataSnapshot.getValue();
                Iterator it = exhibitors.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<String, HashMap> pair = (Map.Entry<String, HashMap>)it.next();
                    Exhibitor exhibitor = new Exhibitor();
                    exhibitor.setId(pair.getKey());
                    exhibitor.setName((String)pair.getValue().get("name"));
                    exhibitor.setImageUrl((String)pair.getValue().get("img_url"));
                    exhibitorList.add(exhibitor);
                }
                Exhibitor registrationBooth = new Exhibitor();
                registrationBooth.setId("d18f5361-2726-4ac4-87c5-60673962321c");
                registrationBooth.setName("Manila Fame Registration Booth");
                registrationBooth.setImageUrl("https://firebasestorage.googleapis.com/v0/b/blue-booth-fairy.appspot.com/o/products%2Fmanila_fame.jpg?alt=media&token=6169337d-f9c3-450f-a080-f9d61228407e");
                exhibitorList.add(0, registrationBooth);

                callback.onSuccess(exhibitorList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onFailure("Error: " + databaseError.getMessage());
            }
        });
    }

    public static ArrayList<Exhibitor> getExhibitors(){
        return exhibitorList;
    }

    public static ArrayList<String> getNames(){
        if(names.isEmpty()) {
            for (Exhibitor e : exhibitorList) {
                names.add(e.getName());
            }
        }
        return names;
    }

    public static Exhibitor getExhibitorOfName(String name){
        for(Exhibitor e : exhibitorList){
            if(e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public interface Callback{
        void onSuccess(ArrayList<Exhibitor> exhibitors);
        void onFailure(String message);
    }
}
