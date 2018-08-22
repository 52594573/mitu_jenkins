package com.playingjoy.fanrabbit.ui.fragment.comm;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;

import butterknife.BindView;

/**
 * Author: Ly
 * Data：2018/4/11-15:38
 * Description: 大图查看的fragment
 */
public class BigImageFragment extends BaseFragment {
    @BindView(R.id.iv_big_img)
    ImageView mIvBigImageView;


    public static BigImageFragment newInstance(String imgPath) {
        Bundle args = new Bundle();
        args.putString("imgPath", imgPath);
        BigImageFragment fragment = new BigImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String imgPath = bundle.getString("imgPath");
            Glide.with(context).load(imgPath).into(mIvBigImageView);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_big_image;
    }

    @Override
    public Object newP() {
        return null;
    }
}
