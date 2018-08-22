package com.playingjoy.fanrabbit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.playingjoy.fanrabbit.R;

/**
 * Author: Ly
 * Data：2018/3/21-14:12
 * Description:错误视图
 */
public class ErrView extends RelativeLayout {

    public ErrView(Context context) {
        this(context, null);
    }

    public ErrView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ErrView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);
    }

    private void setupView(Context context){
        inflate(context, R.layout.view_error,this);
    }
}




