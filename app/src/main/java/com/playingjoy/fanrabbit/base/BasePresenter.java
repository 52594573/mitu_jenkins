package com.playingjoy.fanrabbit.base;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.playingjoy.fanrabbit.BuildConfig;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.utils.cache.DownLoadInfoManager;

import org.reactivestreams.Publisher;

import java.io.File;

import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.IView;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.IModel;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 作者：Ly
 * 时间：2018/3/9  10:58
 * 描述：这是一个类，用于基准类
 */

public abstract class BasePresenter<V extends IView> extends XPresent<V> {
    /**
     * 默认的下载文件夹
     * Dir: /CatPlayDownload
     */
    private final File mDownloadDir = new File(Environment.getExternalStorageDirectory(), "MeToo");

    public File getDownLoadPath() {
        return mDownloadDir;
    }

    public <T extends IModel> FlowableTransformer<T, T> showLoadingDialog() {
        return this.showLoadingDialog(null, false);
    }

    public <T extends IModel> FlowableTransformer<T, T> showLoadingDialog(boolean isCanable) {
        return this.showLoadingDialog(null, isCanable);
    }

    public <T extends IModel> FlowableTransformer<T, T> showLoadingDialog(String loadingText, boolean isCanable) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.compose(XApi.getApiTransformer())
                        .compose(XApi.getScheduler())
                        .doOnSubscribe(subscription -> {
                            getV().showLoadingDialog(loadingText, isCanable);
                        }).subscribeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 下载任务
     *
     * @param context 上下文
     * @param flag    flag
     * @param url     下载地址
     * @param apkName apk的名字
     * @param path    文件缓存路径（全路径）
     */
    public void doLoadLoad(Context context, String flag, String url, String apkName, String path, FileDownloadListener fileDownloadListener) {
        XLog.e("the path is " + path);
        BaseDownloadTask downloadTask = FileDownloader.getImpl().create(url).setPath(path).setListener(fileDownloadListener);
        if (TextUtils.equals(flag, context.getResources().getString(R.string.text_download_finish))) {
//             下载完成
            Kits.Package.installNormal(context, BuildConfig.APPLICATION_ID, downloadTask.getPath());
        } else if (TextUtils.equals(flag, context.getResources().getString(R.string.text_download_goon)) || TextUtils.equals(flag, context.getResources().getString(R.string.text_start_download))
                || TextUtils.equals(flag, context.getResources().getString(R.string.text_download_error))) {
            // 下载暂停 下载还没开始  下载错误&重试
            int downloadId = downloadTask.start();
            XLog.e("the download id is" + downloadId);
            XLog.e("the getTotalBytes id is" + downloadTask.getTotalBytes());
            XLog.e("the getSmallFileTotalBytes id is" + downloadTask.getSmallFileTotalBytes());
            XLog.e("the getLargeFileTotalBytes id is" + downloadTask.getLargeFileTotalBytes());
            DownLoadInfoManager.saveOrUpdate(downloadId, apkName+".apk", path, downloadTask.getSoFarBytes(), downloadTask.getTotalBytes());
        } else {
            // 其他情况 下载暂停
            downloadTask.pause();
        }
    }
}