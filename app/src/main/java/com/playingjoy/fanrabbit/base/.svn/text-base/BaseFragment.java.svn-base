package com.playingjoy.fanrabbit.base;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.widget.EmptyView;
import com.playingjoy.fanrabbit.widget.ErrView;
import com.playingjoy.fanrabbit.widget.LoadView;
import com.playingjoy.fanrabbit.widget.LoadingDialog;
import com.umeng.socialize.UMShareAPI;

import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * 作者：Ly
 * 时间：2018/3/9  11:01
 * 描述：这是一个类，用于基准的Fragment
 */

public abstract class BaseFragment<P extends IPresent> extends XFragment<P> {
    private LoadingDialog loadingDialog;

    /**
     * 设置空，错误，加载view
     *
     * @param xRecyclerContentLayout
     */
    protected void setDefConfRecyclerView(XRecyclerContentLayout xRecyclerContentLayout) {
        xRecyclerContentLayout.errorView(new ErrView(getActivity()));
        xRecyclerContentLayout.loadingView(new LoadView(getActivity()));
        xRecyclerContentLayout.emptyView(new EmptyView(getActivity()));
    }

    @Override
    public void showTs(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public LoadingDialog showLoadingDialog() {
       return this.showLoadingDialog(false);
    }

    @Override
    public LoadingDialog showLoadingDialog(boolean isCancelable) {
       return this.showLoadingDialog(null, isCancelable);
    }

    @Override
    public LoadingDialog showLoadingDialog(String loadingText, boolean isCancelable) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
        }
       return loadingDialog.show(loadingText, isCancelable);
    }

    @Override
    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 获取app权限的方法 跳到app信息页面
     *
     * @param message
     */
    protected void showAlert2AppInfo(String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(R.string.text_next, (dialog, which) -> {
                    Intent localIntent = new Intent();
                    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                    startActivity(localIntent);
                }).setNegativeButton(R.string.text_cancel, (dialog, which) -> {

        }).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(context).release();
    }
}
