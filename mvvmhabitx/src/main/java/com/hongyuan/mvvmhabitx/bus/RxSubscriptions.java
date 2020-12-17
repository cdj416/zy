package com.hongyuan.mvvmhabitx.bus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 管理 CompositeSubscription
 * demo
 * @author cdj
 * @date 2020/12/10
 */
public class RxSubscriptions {
    private static CompositeDisposable mSubscriptions = new CompositeDisposable ();

    public static boolean isDisposed() {
        return mSubscriptions.isDisposed();
    }

    public static void add(Disposable s) {
        if (s != null) {
            mSubscriptions.add(s);
        }
    }

    public static void remove(Disposable s) {
        if (s != null) {
            mSubscriptions.remove(s);
        }
    }

    public static void clear() {
        mSubscriptions.clear();
    }

    public static void dispose() {
        mSubscriptions.dispose();
    }

}
