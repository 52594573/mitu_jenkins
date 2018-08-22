package com.playingjoy.fanrabbit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.playingjoy.fanrabbit.R;

/**
 * Author: Ly
 * Data：2018/3/28-10:31
 * Description:
 */
public class FriendEmptyView extends RelativeLayout {

    public FriendEmptyView(Context context) {
        this(context, null);
    }

    public FriendEmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FriendEmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);
    }

    private void setupView(Context context){
        inflate(context, R.layout.view_friends_empty,this);
    }
}