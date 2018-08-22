package com.playingjoy.fanrabbit.ui.activity.tribe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.tribe.TransferTribeMemberListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.tribe.TribeMemberManagementPresenter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 部落成员管理页面
 *
 * @author Morphine
 * @date 2018-04-18.
 */

public class TribeMemberManagementActivity extends BaseActivity<TribeMemberManagementPresenter> {
    @BindView(R.id.xlv_tribe_member)
    XRecyclerView mXlvTribeMember;
    TransferTribeMemberListAdapter tribeMemberListAdapter;
    XRecyclerView mXlvTribeManager;
    TransferTribeMemberListAdapter tribeManagerListAdapter;
    View headerView;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBarRightImg(getString(R.string.text_tribe_member_management), R.drawable.search, v -> {
            TribeMemberManagementSearchActivity.toTribeMemberManagementSearchActivity(this);
        });
        initHeaderView();
        initMemberList();
    }

    /**
     * 初始化headerView
     */
    private void initHeaderView() {
        headerView = View.inflate(context, R.layout.header_tribe_member_management, null);
        mXlvTribeManager = headerView.findViewById(R.id.xlv_tribe_manager);
        mXlvTribeManager.setFocusableInTouchMode(false);
        headerView.findViewById(R.id.tv_added_records).setOnClickListener(v -> {
            TribeAddedRecordsActivity.toTribeAddedRecordsActivity(this);
        });
        headerView.findViewById(R.id.tv_black_list).setOnClickListener(v -> {
            TribeBlacklistActivity.toBlacklistActivity(this);
        });
        initManagerList();
    }

    /**
     * 初始化部落管理列表
     */
    private void initManagerList() {
        mXlvTribeManager.setLayoutManager(new LinearLayoutManager(context));
        tribeManagerListAdapter = new TransferTribeMemberListAdapter(false, true, context);
        mXlvTribeManager.setAdapter(tribeManagerListAdapter);
        tribeManagerListAdapter.setData(new String[]{"1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3"});
    }

    /**
     * 初始化成员列表
     */
    private void initMemberList() {
        mXlvTribeMember.setLayoutManager(new LinearLayoutManager(context));
        tribeMemberListAdapter = new TransferTribeMemberListAdapter(false, false, context);
        mXlvTribeMember.setAdapter(tribeMemberListAdapter);
        tribeMemberListAdapter.setData(new String[]{"1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3"});
        mXlvTribeMember.addHeaderView(headerView);
    }


    public static void toTribeMemberManagerActivity(Activity activity) {
        Router.newIntent(activity).to(TribeMemberManagementActivity.class).launch();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_tribe_member_management;
    }

    @Override
    public TribeMemberManagementPresenter newP() {
        return new TribeMemberManagementPresenter();
    }

}
