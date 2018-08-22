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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.tribe.TribeAddedRecordsListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.tribe.TribeBlacklistSearchPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 部落黑名单搜索页面
 *
 * @author Morphine
 * @date 2018-04-18.
 */

public class TribeBlacklistSearchActivity extends BaseActivity<TribeBlacklistSearchPresenter> implements TextView.OnEditorActionListener, TribeAddedRecordsListAdapter.OnSelectChangeListener {
    @BindView(R.id.et_keyword)
    EditText mEtKeyword;
    @BindView(R.id.tv_search_hint)
    TextView mTvSearchHint;
    @BindView(R.id.xlv_search)
    XRecyclerView mXlvSearch;
    @BindView(R.id.btn_confirm)
    Button mBtnConfirm;
    String keyword;
    TribeAddedRecordsListAdapter blacklistListAdapter;

    private List<Integer> selectList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        mBtnConfirm.setText(R.string.text_relieve_blacklist);
        RxTextView.afterTextChangeEvents(mEtKeyword)
                .compose(bindToLifecycle())
                .subscribe(textViewAfterTextChangeEvent ->
                        mTvSearchHint.setVisibility(TextUtils.isEmpty(mEtKeyword.getText().toString()) ?
                                View.VISIBLE : View.GONE));
        mEtKeyword.setOnEditorActionListener(this);
        initList();
    }

    private void initList() {
        mXlvSearch.setLayoutManager(new LinearLayoutManager(context));
        blacklistListAdapter = new TribeAddedRecordsListAdapter(context, true);

        blacklistListAdapter.setOnSelectChangeListener(this);
        mXlvSearch.setAdapter(blacklistListAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tribe_simple_search;
    }

    @Override
    public TribeBlacklistSearchPresenter newP() {
        return new TribeBlacklistSearchPresenter();
    }


    @OnClick({R.id.ib_back, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.btn_confirm:
                if (selectList == null || selectList.size() == 0) {
                    showTs("请选择需要解除的成员");
                    return;
                }
                getP().relieveBlacklist();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            // 当按了搜索之后关闭软键盘
            ((InputMethodManager) mEtKeyword.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    TribeBlacklistSearchActivity.this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            keyword = mEtKeyword.getText().toString();
            getP().searchBlacklist(keyword);
            return true;
        }
        return false;
    }

    public static void toTribeBlacklistSearchActivity(Activity activity){
        Router.newIntent(activity).to(TribeBlacklistSearchActivity.class).launch();
    }


    public void setSearchResult(List<String> list) {
        blacklistListAdapter.setKeyword(keyword);
        blacklistListAdapter.setData(list);
    }

    @Override
    public void onSelectChange(List<Integer> selectList) {
        this.selectList = selectList;
    }
}
