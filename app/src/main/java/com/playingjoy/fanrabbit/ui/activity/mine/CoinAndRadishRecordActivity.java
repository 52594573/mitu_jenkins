package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.mine.TransactionRecordListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.mine.CoinAndRadishRecordPresenter;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * 兔币和萝卜交易记录页面
 *
 * @author Morphine
 * @date 2018-04-11.
 */

public class CoinAndRadishRecordActivity extends BaseActivity<CoinAndRadishRecordPresenter> {
    @BindView(R.id.xrl_content)
    XRecyclerContentLayout mXrlContent;

    /**
     * 交易记录标识-兔币
     */
    public static final int FLAG_COIN_RECORD = 1;
    /**
     * 交易记录标识-萝卜
     */
    public static final int FLAG_RADISH_RECORD = 2;
    int flag;

    @Override
    public void initData(Bundle savedInstanceState) {
        flag = getIntent().getIntExtra("flag", FLAG_COIN_RECORD);
        setTitleBar(getString(flag == FLAG_COIN_RECORD ? R.string.text_coin : R.string.text_radish) + getString(R.string.text_transaction_record));
        initList();
    }

    private void initList() {
        mXrlContent.getRecyclerView().setLayoutManager(new LinearLayoutManager(context));
        mXrlContent.getRecyclerView().setAdapter(new TransactionRecordListAdapter(context));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_simple_recycler;
    }

    /**
     * @param activity
     * @param flag     1-兔币交易记录,2-萝卜交易记录
     */
    public static void toCoinAndRadishRecordActivity(Activity activity, int flag) {
        Router.newIntent(activity).putInt("flag", flag).to(CoinAndRadishRecordActivity.class).launch();
    }

    @Override
    public CoinAndRadishRecordPresenter newP() {
        return new CoinAndRadishRecordPresenter();
    }

}
