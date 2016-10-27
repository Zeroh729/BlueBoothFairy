package android.zeroh729.com.blueboothfairy.buyers.data.events;

import android.zeroh729.com.blueboothfairy.buyers.data.model.Exhibitor;

public class ExhibitorUpdateEvent{
    private Exhibitor exhibitor;

    public ExhibitorUpdateEvent(Exhibitor exhibitor) {
        this.exhibitor = exhibitor;
    }

    public Exhibitor getExhibitor() {
        return exhibitor;
    }
}
