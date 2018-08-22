package com.playingjoy.fanrabbit.ui.activity.tribe;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.tribe.TransferTribeMemberListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.tribe.TransferTribeSearchPresenter;
import com.playingjoy.fanrabbit.utils.TipDialogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 转让部落搜索页面
 *
 * @author Morphine
 * @date 2018-04-18.
 */

public class TransferTribeSearchActivity extends BaseActivity<TransferTribeSearchPresenter> implements TextView.OnEditorActionListener, TransferTribeMemberListAdapter.OnMemberSelectListener {
    @BindView(R.id.et_keyword)
    EditText mEtKeyword;
    @BindView(R.id.tv_search_hint)
    TextView mTvSearchHint;
    @BindView(R.id.xlv_search)
    XRecyclerView mXlvSearch;
    TransferTribeMemberListAdapter tribeMemberListAdapter;
    String keyword;
    int choosePosition;
    TipDialogUtil tipDialogUtil;

    @Override
    public void initData(Bundle savedInstanceState) {
        RxTextView.afterTextChangeEvents(mEtKeyword)
                .compose(bindToLifecycle())
                .subscribe(textViewAfterTextChangeEvent -> {
                    mTvSearchHint.setVisibility(TextUtils.isEmpty(mEtKeyword.getText().toString()) ? View.VISIBLE : View.GONE);
                });
        mEtKeyword.setOnEditorActionListener(this);
        initSearchList();
    }

    private void initSearchList() {
        mXlvSearch.setLayoutManager(new LinearLayoutManager(context));
        tribeMemberListAdapter = new TransferTribeMemberListAdapter(context);
        mXlvSearch.setAdapter(tribeMemberListAdapter);
        tribeMemberListAdapter.setOnMemberSelectListener(this);
    }

    public void setSearchResult(List<String> result) {
        tribeMemberListAdapter.setChoosePosition(-1);
        tribeMemberListAdapter.setKeyword(keyword);
        tribeMemberListAdapter.setData(result);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            // 当按了搜索之后关闭软键盘
            ((InputMethodManager) mEtKeyword.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    TransferTribeSearchActivity.this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            keyword = mEtKeyword.getText().toString();
            getP().searchUser(keyword);
            return true;
        }
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tribe_simple_search;
    }

    public static void toTransferTribeSearchActivity(Activity activity) {
        Router.newIntent(activity).to(TransferTribeSearchActivity.class).launch();
    }


    @Override
    public TransferTribeSearchPresenter newP() {
        return new TransferTribeSearchPresenter();
    }

    @OnClick({R.id.ib_back, R.id.btn_confirm})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                finish();
                break;
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
        choosePosition = position;
    }

    public void TransferSuccess() {
        if (tipDialogUtil == null) {
            tipDialogUtil = new TipDialogUtil(context);
        }
        tipDialogUtil.show("成功转让部落", "您的部落“悠游天下”已成功转让给部 落成员“兔纸”。", "去部落", v -> {
            // TODO: 2018-04-18 跳去部落详情页
        });
    }
}
