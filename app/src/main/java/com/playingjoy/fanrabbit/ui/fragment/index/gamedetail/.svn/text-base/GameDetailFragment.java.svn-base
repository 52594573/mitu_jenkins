package com.playingjoy.fanrabbit.ui.fragment.index.gamedetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.ui.adapter.index.GameDetailCommentAdapter;
import com.playingjoy.fanrabbit.ui.adapter.index.GameLikerAdapter;
import com.playingjoy.fanrabbit.ui.presenter.gamedetail.GameDetailFragmentPresenter;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * Author: Ly
 * Data：2018/3/28-16:39
 * Description: 游戏详情里面的游戏详情碎片
 */
public class GameDetailFragment extends BaseFragment<GameDetailFragmentPresenter> {
    @BindView(R.id.xlv_game_detail_comment_list)
    XRecyclerContentLayout mRlvGameDetailCommentList;


    private View mHeaderView;
    private GameDetailCommentAdapter mGameDetailCommentAdapter;

    public static GameDetailFragment newInstance() {
        Bundle args = new Bundle();
        GameDetailFragment fragment = new GameDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initHeader();
        mGameDetailCommentAdapter = new GameDetailCommentAdapter(context);
        mRlvGameDetailCommentList.getRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlvGameDetailCommentList.getRecyclerView().setAdapter(mGameDetailCommentAdapter);
        mRlvGameDetailCommentList.getRecyclerView().addHeaderView(mHeaderView);
    }

    private void initHeader() {
        mHeaderView=View.inflate(context,R.layout.header_game_detail_fragment,null);
        RecyclerView rlvLikeMan=mHeaderView.findViewById(R.id.rlv_game_item_like_man);
        LinearLayoutManager llForLikeMan=new LinearLayoutManager(context);
        llForLikeMan.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlvLikeMan.setLayoutManager(llForLikeMan);
        rlvLikeMan.setAdapter(new GameLikerAdapter(context));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_game_detail;
    }

    @Override
    public GameDetailFragmentPresenter newP() {
        return new GameDetailFragmentPresenter();
    }
}
