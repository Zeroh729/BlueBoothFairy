package android.zeroh729.com.blueboothfairy.exhibitor.presenters.base;


public interface BasePresenter {
    void setup();
    void updateState();
    void setState(int state);

    interface SystemInterface{

    }

    interface ScreenInterface{

    }
}
