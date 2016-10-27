package android.zeroh729.com.blueboothfairy.buyers.presenters.base;

import static android.R.attr.data;

public interface SingleDataCallback<T> {
    void run(T data);
}
