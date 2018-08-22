package com.playingjoy.fanrabbit.ui.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.ui.adapter.mine.MyGameAdapter;
import com.playingjoy.fanrabbit.ui.presenter.message.MyGameListPresenter;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * Author: Ly
 * Data：2018/3/29-15:19
 * Description:我的游戏列表
 */
public class MyGameListFragment extends BaseFragment<MyGameListPresenter> {
    private MyGameAdapter mMyGameAdapter;
    @BindView(R.id.xlv_my_game_list)
    XRecyclerContentLayout mXlvMyGameList;


    public static MyGameListFragment newInstance() {
        Bundle args = new Bundle();
        MyGameListFragment fragment = new MyGameListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mMyGameAdapter = new MyGameAdapter(context);
        setDefConfRecyclerView(mXlvMyGameList);
        mXlvMyGameList.getRecyclerView().setLayoutManager(new LinearLayoutManager(context));
        mXlvMyGameList.getRecyclerView().setAdapter(mMyGameAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_game_list;
    }

    @Override
    public MyGameListPresenter newP() {
        return new MyGameListPresenter();
    }

}
