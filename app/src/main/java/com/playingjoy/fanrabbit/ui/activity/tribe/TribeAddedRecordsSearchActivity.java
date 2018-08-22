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
import com.playingjoy.fanrabbit.ui.presenter.tribe.TribeAddedRecordsSearchPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 部落加入记录搜索页面
 *
 * @author Morphine
 * @date 2018-04-18.
 */

public class TribeAddedRecordsSearchActivity extends BaseActivity<TribeAddedRecordsSearchPresenter> implements TextView.OnEditorActionListener {
    @BindView(R.id.et_keyword)
    EditText mEtKeyword;
    @BindView(R.id.tv_search_hint)
    TextView mTvSearchHint;
    @BindView(R.id.xlv_search)
    XRecyclerView mXlvSearch;
    @BindView(R.id.btn_confirm)
    Button mBtnConfirm;
    TribeAddedRecordsListAdapter tribeAddedRecordsListAdapter;
    String keyword;

    @Override
    public void initData(Bundle savedInstanceState) {
        RxTextView.afterTextChangeEvents(mEtKeyword)
                .compose(bindToLifecycle())
                .subscribe(textViewAfterTextChangeEvent -> mTvSearchHint.setVisibility(TextUtils.isEmpty(mEtKeyword.getText().toString()) ? View.VISIBLE : View.GONE));
        mBtnConfirm.setVisibility(View.GONE);
        mEtKeyword.setOnEditorActionListener(this);
        initList();
    }

    private void initList() {
        mXlvSearch.setLayoutManager(new LinearLayoutManager(context));
        tribeAddedRecordsListAdapter = new TribeAddedRecordsListAdapter(context, false);
        mXlvSearch.setAdapter(tribeAddedRecordsListAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tribe_simple_search;
    }

    @Override
    public TribeAddedRecordsSearchPresenter newP() {
        return new TribeAddedRecordsSearchPresenter();
    }

    @OnClick(R.id.ib_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            // 当按了搜索之后关闭软键盘
            ((InputMethodManager) mEtKeyword.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    TribeAddedRecordsSearchActivity.this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            keyword = mEtKeyword.getText().toString();
            getP().searchUser(keyword);
            return true;
        }
        return false;
    }

    /**
     * 设置搜索结果
     *
     * @param list
     */
    public void setSearchResult(List<String> list) {
        tribeAddedRecordsListAdapter.setKeyword(keyword);
        tribeAddedRecordsListAdapter.setData(list);
    }

    public static void toTribeAddedRecordsSearchActivity(Activity activity) {
        Router.newIntent(activity).to(TribeAddedRecordsSearchActivity.class).launch();
    }
}
