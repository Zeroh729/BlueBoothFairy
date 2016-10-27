package android.zeroh729.com.blueboothfairy.buyers.data.remote;

import android.zeroh729.com.blueboothfairy.buyers.data.model.Exhibitor;
import android.zeroh729.com.blueboothfairy.buyers.utils.OttoBus;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

@EBean
public class ExhibitorService {

    @Bean
    OttoBus bus;

    private ArrayList<Exhibitor> getAllExhibitors(){
        ArrayList<Exhibitor> exhibitors = new ArrayList<>();


        return exhibitors;
    }

}
