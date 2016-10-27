package android.zeroh729.com.blueboothfairy.buyers.presenters;

import android.zeroh729.com.blueboothfairy.buyers.interactors.DataListSystem;
import android.zeroh729.com.blueboothfairy.buyers.presenters.base.BasePresenter;

public class DataListPresenter implements BasePresenter {
    private Screen screen;
    private System system;

    public DataListPresenter(Screen screen) {
        this.screen = screen;
        system = new DataListSystem();
    }

    @Override
    public void updateState() {

    }

    @Override
    public void setState(int state) {

    }

    public interface Screen {

    }

    public interface System {

    }
}
