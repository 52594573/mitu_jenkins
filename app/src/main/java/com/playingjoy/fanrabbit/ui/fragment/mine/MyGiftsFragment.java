package com.playingjoy.fanrabbit.ui.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.ui.adapter.mine.MyGiftsAdapter;
import com.playingjoy.fanrabbit.ui.presenter.mine.MyGiftsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * Author: Ly
 * Data：2018/4/4-16:15
 * Description:
 */
public class MyGiftsFragment extends BaseFragment<MyGiftsPresenter> {
    @BindView(R.id.xlv_my_gifts_list)
    XRecyclerContentLayout mXlvMyGiftsList;

    private MyGiftsAdapter mMyGiftsAdapter;


    public static MyGiftsFragment newInstance() {
        Bundle args = new Bundle();
        MyGiftsFragment fragment = new MyGiftsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initData(Bundle savedInstanceState) {
        setDefConfRecyclerView(mXlvMyGiftsList);
        mXlvMyGiftsList.getRecyclerView().setLayoutManager(new LinearLayoutManager(context));
        mMyGiftsAdapter=new MyGiftsAdapter(context);
        mXlvMyGiftsList.getRecyclerView().setAdapter(mMyGiftsAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_gifts;
    }

    @Override
    public MyGiftsPresenter newP() {
        return new MyGiftsPresenter();
    }

}
