package com.playingjoy.fanrabbit.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;


import com.playingjoy.fanrabbit.R;

import java.lang.ref.WeakReference;


/**
 * @author Ly
 * @date 2017/11/10
 */

public class CountDownUtils extends CountDownTimer {
    private WeakReference<TextView> mTextViewWeakReference;
    private boolean isRunning = false;
    private WeakReference<Context> mContextWeakReference;

    /**
     * @param millisInFuture    倒计时总时长---毫秒数
     * @param countDownInterval 倒计时间隔长---毫秒数
     * @param mView             倒计时要显示的View---目前为TextView
     */
    public CountDownUtils(Context context, long millisInFuture, long countDownInterval, TextView mView) {
        super(millisInFuture, countDownInterval);
        this.mContextWeakReference = new WeakReference<Context>(context);
        this.mTextViewWeakReference = new WeakReference<TextView>(mView);
    }

    public CountDownUtils(Context context, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mContextWeakReference = new WeakReference<Context>(context);
    }

    public void setView(TextView view) {
        this.mTextViewWeakReference = new WeakReference<TextView>(view);
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void onTick(long l) {
        isRunning = true;
        if (mTextViewWeakReference.get() != null) {
            mTextViewWeakReference.get().setEnabled(false);
            mTextViewWeakReference.get().setText(getText(l));

//            mTextViewWeakReference.get().setText(String.format((l / 1000) + "s"));
        }
    }

    private Spanned getText(long l) {
        return Html.fromHtml("<font color='#e30808'><big>" + (l / 1000) + "s</font><font></big><small>重新获取</small></font>");
    }

    @Override
    public void onFinish() {
        isRunning = false;
        if (mTextViewWeakReference.get() != null && mContextWeakReference.get() != null) {
            mTextViewWeakReference.get().setEnabled(true);
            mTextViewWeakReference.get().setClickable(true);
            mTextViewWeakReference.get().setText(
                    mContextWeakReference.get().getString(R.string.text_get_verification_code));
            mTextViewWeakReference.get().setBackgroundResource(R.drawable.bg_fillet_login);
            mTextViewWeakReference.get().setTextColor(mContextWeakReference.get().getResources().getColor(R.color.main_black));
            ;
        }
    }

    public void doFinish(String tips) {
        cancel();
        isRunning = false;
        if (mTextViewWeakReference.get() != null) {
            mTextViewWeakReference.get().setEnabled(true);
            mTextViewWeakReference.get().setClickable(true);
            mTextViewWeakReference.get().setText(tips);
        }
    }
}
