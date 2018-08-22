package com.playingjoy.fanrabbit.ui.activity.tribe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.tribe.TribeAddedRecordsListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.tribe.TribeBlacklistPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 部落黑名单页面
 *
 * @author Morphine
 * @date 2018-04-18.
 */

public class TribeBlacklistActivity extends BaseActivity<TribeBlacklistPresenter> implements TribeAddedRecordsListAdapter.OnSelectChangeListener {
    @BindView(R.id.xlv_blacklist)
    XRecyclerView mXlvBlacklist;
    TribeAddedRecordsListAdapter blacklistAdapter;

    private List<Integer> selectList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBarRightImg(getString(R.string.blacklist), R.drawable.search, v -> {
            TribeBlacklistSearchActivity.toTribeBlacklistSearchActivity(this);
        });
        initList();
        getP().getBlacklist();
    }

    private void initList() {
        mXlvBlacklist.setLayoutManager(new LinearLayoutManager(context));
        blacklistAdapter = new TribeAddedRecordsListAdapter(context, true);
        blacklistAdapter.setOnSelectChangeListener(this);
        mXlvBlacklist.setAdapter(blacklistAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tribe_blacklist;
    }

    @Override
    public TribeBlacklistPresenter newP() {
        return new TribeBlacklistPresenter();
    }

    @OnClick(R.id.btn_relieve)
    public void onViewClicked() {
        if (selectList == null || selectList.size() == 0) {
            showTs("请选择需要解除的成员");
            return;
        }
        getP().relieveBlacklist();
    }

    public static void toBlacklistActivity(Activity activity) {
        Router.newIntent(activity).to(TribeBlacklistActivity.class).launch();
    }

    public void setBlacklist(List<String> list) {
        blacklistAdapter.setData(list);
    }

    @Override
    public void onSelectChange(List<Integer> selectList) {
        this.selectList = selectList;
    }
}
