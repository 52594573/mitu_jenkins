package com.playingjoy.fanrabbit.ui.fragment.index.gamedetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.ui.adapter.search.RecommendTribeListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.gamedetail.GameTribeFragmentPresenter;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * Author: Ly
 * Data：2018/3/28-16:41
 * Description: 游戏详情里面的部落
 */
public class GameTribeFragment extends BaseFragment<GameTribeFragmentPresenter> {

    @BindView(R.id.rlv_game_tribe_list)
    XRecyclerContentLayout rlvGameTribeList;
    TextView tvCreateTribe;
    RecommendTribeListAdapter tribeListAdapter;

    public static GameTribeFragment newInstance() {
        Bundle args = new Bundle();
        GameTribeFragment fragment = new GameTribeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        View emptyView = View.inflate(context, R.layout.view_game_detail_tribe_empty, null);
        tvCreateTribe = emptyView.findViewById(R.id.tv_create_tribe);
        tvCreateTribe.setOnClickListener(v -> rlvGameTribeList.showContent());
        rlvGameTribeList.emptyView(emptyView);
        rlvGameTribeList.getRecyclerView().setLayoutManager(new LinearLayoutManager(context));
        tribeListAdapter = new RecommendTribeListAdapter(context);
        rlvGameTribeList.getRecyclerView().setAdapter(tribeListAdapter);
        tribeListAdapter.setData(new String[]{"王者荣耀", "问问", "捱三顶五群", "测试", "问问", "捱三顶五群", "全文最新出去啊请问", "阿萨德正常去"});
        rlvGameTribeList.showEmpty();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_game_tribe;
    }

    @Override
    public GameTribeFragmentPresenter newP() {
        return new GameTribeFragmentPresenter();
    }
}
