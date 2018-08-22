package com.playingjoy.fanrabbit.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.utils.StatusBarUtil;
import com.playingjoy.fanrabbit.widget.EmptyView;
import com.playingjoy.fanrabbit.widget.ErrView;
import com.playingjoy.fanrabbit.widget.LoadView;
import com.playingjoy.fanrabbit.widget.LoadingDialog;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * 作者：Ly
 * 时间：2018/3/9  10:58
 * 描述：这是一个类，用于基准类
 */

public abstract class BaseActivity<P extends IPresent> extends XActivity<P> {
    @Nullable
    @BindView(R.id.ll_title_left)
    LinearLayout mLlTitleLeft;
    @Nullable
    @BindView(R.id.tv_title_msg)
    TextView mTvTitleMsg;
    @Nullable
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @Nullable
    @BindView(R.id.ll_title_container)
    LinearLayout mLlTitleContainer;
    @Nullable
    @BindView(R.id.ib_title_right)
    ImageButton mIbTitleRight;

    LoadingDialog loadingDialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mLlTitleLeft != null) {
            mLlTitleLeft.setOnClickListener(v -> finish());
        }
    }

    /**
     * 设置标题
     *
     * @param message
     */
    protected void setTitleBar(String message) {
        if (mTvTitleMsg != null) {
            mTvTitleMsg.setText(message);
        }
    }

    /**
     * 设置titleBar 背景颜色
     *
     * @param color
     */
    protected void setTitleBarColor(int color) {
        if (mLlTitleContainer != null) {
            mLlTitleContainer.setBackgroundColor(color);
        }
    }

    /**
     * 设置右边的提示文案以及点击事件
     *
     * @param message
     * @param rightMsg
     * @param v
     */
    protected void setTitleBarRightMsg(String message, String rightMsg, View.OnClickListener v) {
        if (mTvTitleMsg != null) {
            mTvTitleMsg.setText(message);
        }
        if (mTvTitleRight != null) {
            mTvTitleRight.setText(rightMsg);
            mTvTitleRight.setOnClickListener(v);
            mTvTitleRight.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置右边的按钮图片以及点击事件
     *
     * @param message
     * @param v
     */
    protected void setTitleBarRightImg(String message, @DrawableRes int rightImg, View.OnClickListener v) {
        if (mTvTitleMsg != null) {
            mTvTitleMsg.setText(message);
        }
        if (mIbTitleRight != null) {
            mIbTitleRight.setImageResource(rightImg);
            mIbTitleRight.setOnClickListener(v);
            mIbTitleRight.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setUpStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.main_color));
    }

    /**
     * 显示Toast
     *
     * @param message
     */
    @Override
    public void showTs(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null && v != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }

    /**
     * 是否该关闭软键盘
     *
     * @param v
     * @param event
     * @return
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }


    /**
     * 获取app权限的方法 跳到app信息页面
     *
     * @param message
     */
    protected void showAlert2AppInfo(String message) {
        new AlertDialog.Builder(this)
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
    public Dialog showLoadingDialog() {
        return this.showLoadingDialog(false);
    }

    @Override
    public Dialog showLoadingDialog(boolean isCancelable) {
        return this.showLoadingDialog(null, isCancelable);
    }

    @Override
    public Dialog showLoadingDialog(String loadingText, boolean isCancelable) {
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
     * 设置空，错误，加载view
     *
     * @param xRecyclerContentLayout
     */
    protected void setDefConfRecyclerView(XRecyclerContentLayout xRecyclerContentLayout) {
        xRecyclerContentLayout.errorView(new ErrView(this));
        xRecyclerContentLayout.loadingView(new LoadView(this));
        xRecyclerContentLayout.emptyView(new EmptyView(this));
    }

    public void showLoadding() {
    }
}
