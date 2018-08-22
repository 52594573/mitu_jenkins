package com.playingjoy.fanrabbit.ui.fragment.tribe;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.ui.activity.tribe.CreateGroupActivity;
import com.playingjoy.fanrabbit.ui.activity.tribe.TribeDetailActivity;
import com.playingjoy.fanrabbit.ui.adapter.tribe.ShareFriendsActivity;
import com.playingjoy.fanrabbit.ui.presenter.tribe.TribePresenter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;

/**
 * Author: Ly
 * Data：2018/4/10-15:20
 * Description: 部落页面
 */
public class TribeFragment extends BaseFragment<TribePresenter> {
    @BindView(R.id.iv_test)
    ImageView ivTest;


    public static TribeFragment newInstance() {
        Bundle args = new Bundle();
        TribeFragment fragment = new TribeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        ivTest.setOnClickListener(v -> TribeDetailActivity.toTribeDetailActivity(context));
        context.findViewById(R.id.bt_tribe_create_group).setOnClickListener(v -> CreateGroupActivity.toCreateGroupActivity(context));
        context.findViewById(R.id.bt_tribe_share).setOnClickListener(v -> ShareFriendsActivity.toInviteJoin(context));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tribe;
    }

    @Override
    public TribePresenter newP() {
        return new TribePresenter();
    }
}
