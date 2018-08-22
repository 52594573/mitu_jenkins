package com.playingjoy.fanrabbit.ui.activity.tribe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.domain.event.SelectGameEvent;
import com.playingjoy.fanrabbit.ui.adapter.tribe.SelectGameAdapter;
import com.playingjoy.fanrabbit.ui.presenter.tribe.SelectGamePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * Created by Ly on 2018/4/18.
 * 选择所属游戏
 */

public class SelectGameActivity extends BaseActivity<SelectGamePresenter> {
    @BindView(R.id.xlv_select_game_list)
    XRecyclerContentLayout xlvSelectGameList;

    private SelectGameAdapter mSelectGameAdapter;
    private View mHeaderView;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBarRightImg(getString(R.string.text_select_game), R.drawable.search, v -> TribeGameSearchActivity.toSelectTribeGame(context));
        initAdapter();
        registerSelectGameEvent();
    }

    /**
     * 注册接受选择事件的eventBus
     */
    private void registerSelectGameEvent() {
        BusProvider.getBus().toFlowable(SelectGameEvent.class)
                .compose(bindToLifecycle())
                .subscribe(loginViewFinishEvent -> finish());
    }

    private void initAdapter() {
        mSelectGameAdapter = new SelectGameAdapter(context);
        xlvSelectGameList.getRecyclerView().setLayoutManager(new LinearLayoutManager(context));
        xlvSelectGameList.getRecyclerView().setAdapter(mSelectGameAdapter);
        setDefConfRecyclerView(xlvSelectGameList);
        mSelectGameAdapter.setRecItemClick(new RecyclerItemCallback<String, SelectGameAdapter.SelectGameHolder>() {
            @Override
            public void onItemClick(int position, String model, int tag, SelectGameAdapter.SelectGameHolder holder) {
                super.onItemClick(position, model, tag, holder);
                sendSelectedGameEvent(null, "决战朝歌", position);
            }
        });
        initHeader();

    }


    /**
     * 选择游戏发送事件
     */
    private void sendSelectedGameEvent(String gamePic, String gameName, int gameID) {
        BusProvider.getBus().post(new SelectGameEvent(gameName, gamePic, gameID));
        finish();
    }

    private void initHeader() {
        mHeaderView = LayoutInflater.from(context).inflate(R.layout.header_select_game, null);
        xlvSelectGameList.getRecyclerView().addHeaderView(mHeaderView);
        mHeaderView.findViewById(R.id.tv_header_game_select).setOnClickListener(v-> sendSelectedGameEvent(null, "决战朝歌", 0));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_game;
    }

    @Override
    public SelectGamePresenter newP() {
        return new SelectGamePresenter();
    }


    public static void toSelectGameActivity(Activity activity) {
        Router.newIntent(activity).to(SelectGameActivity.class).launch();
    }
}
