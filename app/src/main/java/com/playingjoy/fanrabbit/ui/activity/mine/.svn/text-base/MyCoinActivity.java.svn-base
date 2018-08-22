package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hyphenate.util.DensityUtil;
import com.library.flowlayout.SpaceItemDecoration;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.mine.MyGlodRechargeListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.mine.MyCoinPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 我的兔币页面
 *
 * @author Morphine
 * @date 2018-04-11.
 */

public class MyCoinActivity extends BaseActivity<MyCoinPresenter> {
    @BindView(R.id.tv_coin_value)
    TextView mTvCoinValue;
    @BindView(R.id.xrl_recharge_list)
    XRecyclerView mXrlRechargeList;
    @BindView(R.id.cb_wechat)
    CheckBox mCbWechat;
    @BindView(R.id.cb_alipay)
    CheckBox mCbAlipay;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBarRightMsg(getString(R.string.text_my_coin), getString(R.string.text_coin_explanation), v -> {
            CoinAndRadishExplanationActivity.toCoinAndRadishExplanationActivity(this, CoinAndRadishExplanationActivity.FLAG_COIN_EXPLANATION);
        });
        initRechargeList();
        getP().getRechargeData();
    }

    /**
     * 初始化充值选项列表
     */
    private void initRechargeList() {
        mXrlRechargeList.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(context, 7)));
        mXrlRechargeList.setAdapter(new MyGlodRechargeListAdapter(context));
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_coin;
    }

    @Override
    public MyCoinPresenter newP() {
        return new MyCoinPresenter();
    }

    public static void toMyCoinActivity(Activity activity) {
        Router.newIntent(activity).to(MyCoinActivity.class).launch();
    }

    @OnClick({R.id.cb_wechat, R.id.cb_alipay, R.id.btn_confirm, R.id.ll_alipay, R.id.ll_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_wechat:
            case R.id.ll_wechat:
                mCbWechat.setChecked(true);
                mCbAlipay.setChecked(false);
                break;
            case R.id.cb_alipay:
            case R.id.ll_alipay:
                mCbWechat.setChecked(false);
                mCbAlipay.setChecked(true);
                break;
            case R.id.btn_confirm:
                break;
        }
    }

}
