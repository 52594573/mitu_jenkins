package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.presenter.mine.MyPursePresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 我的钱包页面
 *
 * @author Morphine
 * @date 2018-04-11.
 */

public class MyPurseActivity extends BaseActivity<MyPursePresenter> {
    @BindView(R.id.tv_coin_value)
    TextView mTvCoinValue;
    @BindView(R.id.tv_radish_value)
    TextView mTvRadishValue;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_my_purse));
        getP().getPurseData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_purse;
    }

    @Override
    public MyPursePresenter newP() {
        return new MyPursePresenter();
    }

    public static void toMyPurseActivity(Activity activity) {
        Router.newIntent(activity).to(MyPurseActivity.class).launch();
    }

    @OnClick({R.id.tv_coin_last, R.id.tv_coin_transaction_record, R.id.tv_radish_last, R.id.tv_radish_transaction_record, R.id.rl_coin_layout, R.id.rl_radish_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_coin_last:
                //兔币说明
                CoinAndRadishExplanationActivity.toCoinAndRadishExplanationActivity(this, CoinAndRadishExplanationActivity.FLAG_COIN_EXPLANATION);
                break;
            case R.id.tv_coin_transaction_record:
                //兔币交易记录
                CoinAndRadishRecordActivity.toCoinAndRadishRecordActivity(this, CoinAndRadishRecordActivity.FLAG_COIN_RECORD);
                break;
            case R.id.tv_radish_last:
                //萝卜说明
                CoinAndRadishExplanationActivity.toCoinAndRadishExplanationActivity(this, CoinAndRadishExplanationActivity.FLAG_RADISH_EXPLANATION);
                break;
            case R.id.tv_radish_transaction_record:
                //萝卜交易记录
                CoinAndRadishRecordActivity.toCoinAndRadishRecordActivity(this, CoinAndRadishRecordActivity.FLAG_RADISH_RECORD);
                break;
            case R.id.rl_coin_layout:
//                我的兔币
                MyCoinActivity.toMyCoinActivity(this);
                break;
            case R.id.rl_radish_layout:
                //我的萝卜
                MyRadishActivity.toMyRadishActivity(this);
                break;
        }
    }
}
