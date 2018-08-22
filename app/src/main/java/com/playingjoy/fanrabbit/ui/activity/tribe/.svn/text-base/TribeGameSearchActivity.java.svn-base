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
import com.playingjoy.fanrabbit.domain.event.LoginViewFinishEvent;
import com.playingjoy.fanrabbit.domain.event.SelectGameEvent;
import com.playingjoy.fanrabbit.ui.adapter.tribe.SelectGameAdapter;
import com.playingjoy.fanrabbit.ui.presenter.tribe.TribeGameSearchPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * Created by Ly on 2018/4/18.
 * 所属游戏搜索列表
 */

public class TribeGameSearchActivity extends BaseActivity<TribeGameSearchPresenter> implements TextView.OnEditorActionListener {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.tv_search_hint)
    TextView tvSearchHint;
    @BindView(R.id.xlv_search)
    XRecyclerView xlvSearch;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;


    private SelectGameAdapter mSelectGameAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        initKeyWord();
        initRecyclerView();

    }

    private void initRecyclerView() {
        mSelectGameAdapter = new SelectGameAdapter(context);
        xlvSearch.setLayoutManager(new LinearLayoutManager(this));
        mSelectGameAdapter.setRecItemClick(new RecyclerItemCallback<String, SelectGameAdapter.SelectGameHolder>() {
            @Override
            public void onItemClick(int position, String model, int tag, SelectGameAdapter.SelectGameHolder holder) {
                super.onItemClick(position, model, tag, holder);
                switch (tag) {
                    case 0:
                        sendSelectedGameEvent(null, "决战朝歌", position);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 选择游戏发送事件
     */
    private void sendSelectedGameEvent(String gamePic, String gameName, int gameID) {
        BusProvider.getBus().post(new SelectGameEvent(gameName, gamePic, gameID));
        finish();
    }

    private void initKeyWord() {
        btnConfirm.setVisibility(View.GONE);
        RxTextView.afterTextChangeEvents(etKeyword)
                .map(textViewAfterTextChangeEvent -> TextUtils.isEmpty(etKeyword.getText().toString()))
                .subscribe(aBoolean -> tvSearchHint.setVisibility(!aBoolean ? View.GONE : View.VISIBLE));
        //设置软键盘搜索监听事件
        etKeyword.setOnEditorActionListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tribe_simple_search;
    }

    @Override
    public TribeGameSearchPresenter newP() {
        return new TribeGameSearchPresenter();
    }

    public static void toSelectTribeGame(Activity activity) {
        Router.newIntent(activity).to(TribeGameSearchActivity.class).launch();
    }


    @OnClick(R.id.ib_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            // 当按了搜索之后关闭软键盘
            ((InputMethodManager) etKeyword.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    TribeGameSearchActivity.this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            xlvSearch.setAdapter(mSelectGameAdapter);
            return true;
        }
        return false;
    }
}
