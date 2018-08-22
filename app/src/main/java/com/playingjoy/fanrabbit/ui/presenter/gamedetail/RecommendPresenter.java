package com.playingjoy.fanrabbit.ui.presenter.gamedetail;

import android.content.Context;
import android.text.TextUtils;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.playingjoy.fanrabbit.BuildConfig;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BasePresenter;
import com.playingjoy.fanrabbit.domain.impl.GameListBean;
import com.playingjoy.fanrabbit.ui.fragment.index.RecommendFragment;
import com.playingjoy.fanrabbit.utils.cache.DownLoadInfoManager;
import com.playingjoy.fanrabbit.utils.net.HttpRequest;

import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Author: Ly
 * Data：2018/3/23-14:12
 * Description:
 */
public class RecommendPresenter extends BasePresenter<RecommendFragment> {





    public void getGameList() {
        HttpRequest.getApiService().getGameList()
                .compose(XApi.getScheduler())
                .compose(XApi.getApiTransformer())
                .compose(getV().bindToLifecycle())
                .subscribe(new ApiSubscriber<GameListBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        XLog.e("error" + error.getMessage());
                    }

                    @Override
                    public void onNext(GameListBean gameListBean) {
                        getV().getGameListSuc(gameListBean.getData().getGames());
                    }
                });
    }
}
