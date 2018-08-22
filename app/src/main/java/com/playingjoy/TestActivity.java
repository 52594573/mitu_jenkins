package com.playingjoy;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.widget.CircularProgressBar;
import com.trello.rxlifecycle2.components.RxActivity;

import java.util.concurrent.TimeUnit;

import cn.droidlover.xdroidmvp.log.XLog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ly on 2018/3/19.
 * 测试activity
 */

public class TestActivity extends RxActivity {
    private CircularProgressBar mCircularProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_game_list);


        mCircularProgressBar = findViewById(R.id.cpb_game_progress);
//        mCircularProgressBar.setTipsMessage("下载");
//        mCircularProgressBar.setDefColor(getResources().getColor(R.color.x_red));
//        mCircularProgressBar.setFontColor(getResources().getColor(R.color.x_yellow));
//        mCircularProgressBar.setTabColor(getResources().getColor(R.color.x_blue));
//        mCircularProgressBar.setProgressColor(getResources().getColor(R.color.colorPrimaryDark));
//        mCircularProgressBar.setProgressWidth(4);
//        mCircularProgressBar.setRectWidth(1);
//        mCircularProgressBar.setTipsFinish("傻逼");
        mCircularProgressBar.setOnClickListener(v -> {
            if (disposable != null) {
                disposable.dispose();
            }
            if (mCircularProgressBar.isInProgress()) {
                mCircularProgressBar.doPauseProgress();
            } else {
                doStartDownLoad();
            }
        });
    }

    private io.reactivex.disposables.Disposable disposable;

    private void doStartDownLoad() {
        mCircularProgressBar.doStartProgress();
        disposable = io.reactivex.Observable.interval(1, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    XLog.e("the progress is " + aLong + 1);
                    if (aLong >= 9) {
                        mCircularProgressBar.doFinishProgress();
                    } else {
                        mCircularProgressBar.setProgress((int) ((aLong + 1) * 10), 100);
                    }
                });
        mCircularProgressBar.setOnProgressListener(disposable::dispose);

    }
}
