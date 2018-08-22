package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.dialog.SimpleDialog;
import com.playingjoy.fanrabbit.ui.presenter.mine.MyRadishPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 我的萝卜页面
 *
 * @author Morphine
 * @date 2018-04-11.
 */

public class MyRadishActivity extends BaseActivity<MyRadishPresenter> implements View.OnClickListener {
    @BindView(R.id.tv_radish_value)
    TextView mTvRadishValue;

    SimpleDialog simpleDialog;
    ImageButton mIbAmountAdd;
    ImageButton mIbAmountMinus;
    TextView mTvAmount;
    TextView mTvCanWithdrawAmount;
    TextView mTvWithdrawAll;

    private int canWithdrawMostAmount = 33333333;
    private int withdrawAmount = 0;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBarRightMsg(getString(R.string.text_my_radish), getString(R.string.text_radish_explanation), v -> {
            CoinAndRadishExplanationActivity.toCoinAndRadishExplanationActivity(this, CoinAndRadishExplanationActivity.FLAG_RADISH_EXPLANATION);
        });
        getP().getRadishData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_radish;
    }

    public static void toMyRadishActivity(Activity activity) {
        Router.newIntent(activity).to(MyRadishActivity.class).launch();
    }

    @Override
    public MyRadishPresenter newP() {
        return new MyRadishPresenter();
    }

    @OnClick(R.id.btn_withdraw)
    public void onViewClicked() {
        if (simpleDialog == null) {
            simpleDialog = new SimpleDialog(context);
            View view = View.inflate(context, R.layout.view_withdraw_dialog_content, null);
            mIbAmountAdd = view.findViewById(R.id.ib_amount_add);
            mIbAmountMinus = view.findViewById(R.id.ib_amount_minus);
            mTvAmount = view.findViewById(R.id.tv_amount);
            mTvCanWithdrawAmount = view.findViewById(R.id.tv_can_withdraw_amount);
            mTvWithdrawAll = view.findViewById(R.id.tv_withdraw_all);
            mIbAmountAdd.setOnClickListener(this);
            mIbAmountMinus.setOnClickListener(this);
            mTvWithdrawAll.setOnClickListener(this);
            simpleDialog.setBtnText(getString(R.string.text_confirm))
                    .setTitleText(getString(R.string.text_withdraw))
                    .addContentView(view)
                    .addBtnClickListener(v -> {
                        //确定提现
                        if (withdrawAmount >= 100) {
                            getP().doWithdraw(withdrawAmount);
                        } else {
                            showTs("提现金额需要整百");
                        }
                    });
        }
        mTvCanWithdrawAmount.setText(String.format(getString(R.string.format_can_withdraw_amount), canWithdrawMostAmount));
        if (canWithdrawMostAmount >= 100) {
            withdrawAmount = 100;
        }
        withdrawAmountChange();
        simpleDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_withdraw_all:
                //提现最多
                withdrawAmount = canWithdrawMostAmount / 100 * 100;
                withdrawAmountChange();
                break;
            case R.id.ib_amount_add:
                //提现金额加
                withdrawAmount += 100;
                withdrawAmountChange();
                break;
            case R.id.ib_amount_minus:
                //提现金额减
                withdrawAmount -= 100;
                withdrawAmountChange();
                break;
        }
    }

    private void withdrawAmountChange() {
        mTvAmount.setText(String.valueOf(withdrawAmount));
        mIbAmountAdd.setEnabled(withdrawAmount < canWithdrawMostAmount / 100 * 100);
        mIbAmountAdd.setImageResource(mIbAmountAdd.isEnabled() ? R.drawable.ic_withdraw_add_p : R.drawable.ic_withdraw_add);
        mIbAmountMinus.setEnabled(withdrawAmount > 100);
        mIbAmountMinus.setImageResource(mIbAmountMinus.isEnabled() ? R.drawable.ic_withdraw_minus_p : R.drawable.ic_withdraw_minus);
    }


}
