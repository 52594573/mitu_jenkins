package com.playingjoy.fanrabbit.ui.presenter.mine;

import com.playingjoy.fanrabbit.base.BasePresenter;
import com.playingjoy.fanrabbit.ui.activity.mine.MyTaskActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Morphine
 * @date 2018-04-09.
 */

public class MyTaskPresenter extends BasePresenter<MyTaskActivity> {
    public void getMyTaskData() {
        Flowable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .compose(getV().bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> getV().setOnLineTime(aLong.intValue()));

    }
}
