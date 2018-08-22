package com.playingjoy.fanrabbit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.playingjoy.fanrabbit.R;

/**
 * Author: Ly
 * Data：2018/3/21-14:13
 * Description: 加载视图
 */
public class LoadView extends RelativeLayout {

    public LoadView(Context context) {
        this(context, null);
    }

    public LoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);
    }

    private void setupView(Context context){
        inflate(context, R.layout.view_loading,this);
    }
}



