package com.playingjoy.fanrabbit.ui.activity.tribe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.tribe.TransferTribeMemberListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.tribe.TransferTribePresenter;
import com.playingjoy.fanrabbit.utils.TipDialogUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 转让部落页面
 *
 * @author Morphine
 * @date 2018-04-17.
 */

public class TransferTribeActivity extends BaseActivity<TransferTribePresenter> implements TransferTribeMemberListAdapter.OnMemberSelectListener {
    @BindView(R.id.xlv_tribe_member)
    XRecyclerView mXlvTribeMember;

    TransferTribeMemberListAdapter memberListAdapter;
    XRecyclerView mXlvTribeManagerList;
    TransferTribeMemberListAdapter managerListAdapter;
    View headerView;
    private boolean isManager;
    private int choosePosition;
    TipDialogUtil tipDialogUtil;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBarRightImg(getString(R.string.text_transfer_tribe), R.drawable.search, v -> TransferTribeSearchActivity.toTransferTribeSearchActivity(this));
        initHeader();
        initMemberList();
    }

    /**
     * 初始化头部
     */
    private void initHeader() {
        headerView = View.inflate(context, R.layout.head_transfer_tribe, null);
        headerView.setFocusable(false);
        mXlvTribeManagerList = headerView.findViewById(R.id.xlv_tribe_manager);
        mXlvTribeManagerList.setFocusableInTouchMode(false);
        mXlvTribeManagerList.setLayoutManager(new LinearLayoutManager(context));
        managerListAdapter = new TransferTribeMemberListAdapter(context, true);
        managerListAdapter.setOnMemberSelectListener(this);
        mXlvTribeManagerList.setAdapter(managerListAdapter);
        managerListAdapter.setData(new String[]{"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"});
    }

    /**
     * 初始化成员列表
     */
    private void initMemberList() {
        mXlvTribeMember.setLayoutManager(new LinearLayoutManager(context));
        memberListAdapter = new TransferTribeMemberListAdapter(context, false);
        mXlvTribeMember.setAdapter(memberListAdapter);
        mXlvTribeMember.setFocusableInTouchMode(false);
        memberListAdapter.setData(new String[]{"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"});
        memberListAdapter.setOnMemberSelectListener(this);
        mXlvTribeMember.addHeaderView(headerView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_transfer_tribe;
    }

    @Override
    public TransferTribePresenter newP() {
        return new TransferTribePresenter();
    }

    public static void toTransferTribeActivity(Activity activity) {
        Router.newIntent(activity).to(TransferTribeActivity.class).launch();
    }

    @OnClick(R.id.btn_confirm)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                transferTribe();
                break;
        }
    }


    private void transferTribe() {
        if (choosePosition == -1) {
            showTs("请选择要转让的成员");
        } else {
            getP().transferTribe();
        }
    }

    @Override
    public void onMemberSelect(boolean isManager, int position) {
        this.isManager = isManager;
        this.choosePosition = position;
        if (isManager && position > -1) {
            memberListAdapter.setChoosePosition(-1);
        } else if (!isManager && position > -1) {
            managerListAdapter.setChoosePosition(-1);
        }
    }

    /**
     * 转让成功
     */
    public void transferSuccess() {
        if (tipDialogUtil == null) {
            tipDialogUtil = new TipDialogUtil(context);
        }
        tipDialogUtil.show("成功转让部落", "您的部落“悠游天下”已成功转让给部 落成员“兔纸”。", "去部落", v -> {
            // TODO: 2018-04-18 跳去部落详情页
            TribeMemberManagementActivity.toTribeMemberManagerActivity(context);
        });
    }
}
