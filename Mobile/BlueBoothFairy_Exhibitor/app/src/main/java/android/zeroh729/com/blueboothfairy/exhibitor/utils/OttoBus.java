package android.zeroh729.com.blueboothfairy.exhibitor.utils;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import org.androidannotations.annotations.EBean;

@EBean(scope = EBean.Scope.Singleton)
public class OttoBus extends Bus{

    public OttoBus(){
        super(ThreadEnforcer.ANY);
    }

}
