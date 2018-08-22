package com.playingjoy.fanrabbit.ui.activity.index;

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
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.library.flowlayout.FlowLayoutManager;
import com.library.flowlayout.NestedRecyclerView;
import com.library.flowlayout.SpaceItemDecoration;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.search.RecommendGameListAdapter;
import com.playingjoy.fanrabbit.ui.adapter.search.RecommendTribeListAdapter;
import com.playingjoy.fanrabbit.ui.adapter.search.SearchHistoryListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.index.SearchPresenter;
import com.playingjoy.fanrabbit.widget.SimpleNoTitleDialog;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * @author Morphine
 * @date 2018-03-28.
 */

public class SearchActivity extends BaseActivity<SearchPresenter> implements TextView.OnEditorActionListener {
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.tv_search_hint)
    TextView tvSearchHint;
    @BindView(R.id.rlv_recommend_tribe_list)
    XRecyclerView rlvRecommendTribeList;

    SimpleNoTitleDialog simpleNoTitleDialog;
    ImageButton ibSearchHistoryDel;
    NestedRecyclerView rlvSearchHistoryList;
    XRecyclerView rlvRecommendGameList;
    View headerView;

    @Override
    public void initData(Bundle savedInstanceState) {
        RxTextView.afterTextChangeEvents(etKeyword)
                .map(textViewAfterTextChangeEvent -> TextUtils.isEmpty(etKeyword.getText().toString()))
                .subscribe(aBoolean -> tvSearchHint.setVisibility(!aBoolean ? View.GONE : View.VISIBLE));
        //设置软键盘搜索监听事件
        etKeyword.setOnEditorActionListener(this);
        initHeaderView();
        initSearchHistory();
        initRecommendGameList();
        initRecommendTribeList();
    }

    private void initHeaderView() {
        headerView = View.inflate(context, R.layout.header_search_page, null);
        rlvSearchHistoryList = headerView.findViewById(R.id.rlv_search_history_list);
        rlvRecommendGameList = headerView.findViewById(R.id.rlv_recommend_game_list);
        ibSearchHistoryDel = headerView.findViewById(R.id.ib_search_history_del);
        ibSearchHistoryDel.setOnClickListener(v -> {
            if (simpleNoTitleDialog == null)
                simpleNoTitleDialog = new SimpleNoTitleDialog(context);
            simpleNoTitleDialog.setConfirmBtnClickListener(v1 -> getP().deleteSearchHistory()
            ).setCancelBtnClickListener(v1 -> simpleNoTitleDialog.dismiss()).show();
        });
    }

    /**
     * 初始化推荐部落列表
     */
    private void initRecommendTribeList() {
        rlvRecommendTribeList.setLayoutManager(new LinearLayoutManager(context));
        RecommendTribeListAdapter recommendTribeListAdapter = new RecommendTribeListAdapter(context);
        recommendTribeListAdapter.setData(new String[]{"王者荣耀", "问问", "捱三顶五群", "测试", "问问", "捱三顶五群", "全文最新出去啊请问", "阿萨德正常去"});
        rlvRecommendTribeList.setAdapter(recommendTribeListAdapter);
        rlvRecommendTribeList.addHeaderView(headerView);
    }

    /**
     * 初始化推荐游戏列表
     */
    private void initRecommendGameList() {
        rlvRecommendGameList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        RecommendGameListAdapter recommendGameListAdapter = new RecommendGameListAdapter(this);
        rlvRecommendGameList.addItemDecoration(new SpaceItemDecoration((int) Kits.Dimens.dpToPx(context, 6)));
        recommendGameListAdapter.setData(new String[]{"王者荣耀", "问问", "捱三顶五群", "测试", "问问", "捱三顶五群", "全文最新出去啊请问", "阿萨德正常去"});
        rlvRecommendGameList.setAdapter(recommendGameListAdapter);
    }

    /**
     * 初始化搜索历史列表
     */
    private void initSearchHistory() {
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        rlvSearchHistoryList.setLayoutManager(flowLayoutManager);
        rlvSearchHistoryList.addItemDecoration(new SpaceItemDecoration((int) Kits.Dimens.dpToPx(context, 7)));
        SearchHistoryListAdapter searchHistoryListAdapter = new SearchHistoryListAdapter(this);
        searchHistoryListAdapter.setData(new String[]{"测试", "问问", "捱三顶五群", "测试", "问问", "捱三顶五群", "全文最新出去啊请问", "阿萨德正常去"});
        rlvSearchHistoryList.setAdapter(searchHistoryListAdapter);
        searchHistoryListAdapter.setRecItemClick(new RecyclerItemCallback<String, SearchHistoryListAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, String model, int tag, SearchHistoryListAdapter.ViewHolder holder) {
                etKeyword.setText(model);
                SearchResultActivity.toSearchResultActivity(SearchActivity.this, model);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public SearchPresenter newP() {
        return new SearchPresenter();
    }


    @OnClick({R.id.ib_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
        }
    }

    public static void toSearchActivity(Activity activity) {
        Router.newIntent(activity).to(SearchActivity.class).launch();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            // 当按了搜索之后关闭软键盘
            ((InputMethodManager) etKeyword.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    SearchActivity.this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            SearchResultActivity.toSearchResultActivity(this, etKeyword.getText().toString());
            return true;
        }
        return false;
    }
}
