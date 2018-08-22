package com.playingjoy.fanrabbit.widget;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

/**
 * Author: Ly
 * Data：2018/3/21-14:13
 * Description: 空视图
 */
public class EmptyView extends RelativeLayout {

    ImageView tipImageView;
    TextView tipTextView;

    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);
    }

    private void setupView(Context context) {
        View inflate = inflate(context, R.layout.view_empty, this);
        tipImageView = inflate.findViewById(R.id.iv_tip_pic);
        tipTextView = inflate.findViewById(R.id.tv_tip_text);
    }


    public void setTipText(@NonNull String text) {
        tipTextView.setText(text);
    }

    public void setTipImageViewVisible(boolean isVisible) {
        tipImageView.setVisibility(isVisible ? VISIBLE : GONE);
    }

    public void setTextColor(@ColorInt int color) {
        tipTextView.setTextColor(color);
    }
}



