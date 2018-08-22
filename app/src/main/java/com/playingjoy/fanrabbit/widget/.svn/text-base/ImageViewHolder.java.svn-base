package com.playingjoy.fanrabbit.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Author: Ly
 * Data：2018/3/28-16:33
 * Description: banner holder
 */
public class ImageViewHolder implements Holder<Integer> {
    ImageView mImageView;

    @Override
    public View createView(Context context) {
        mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setImageResource(data);
    }
}