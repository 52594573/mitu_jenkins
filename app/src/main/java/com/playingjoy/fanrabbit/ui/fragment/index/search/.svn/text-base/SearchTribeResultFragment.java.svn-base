package com.playingjoy.fanrabbit.ui.fragment.index.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.ui.adapter.search.SearchResultTribeListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.index.SearchTribeResultPresenter;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * 搜索结果部落页面
 *
 * @author Morphine
 * @date 2018-04-10.
 */

public class SearchTribeResultFragment extends BaseFragment<SearchTribeResultPresenter> {

    @BindView(R.id.xlv_search_result_list)
    XRecyclerContentLayout mXlvSearchResultList;
    String keyword;

    public static SearchTribeResultFragment newInstance(String keyword) {
        Bundle args = new Bundle();
        args.putString("keyword", keyword);
        SearchTribeResultFragment fragment = new SearchTribeResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        keyword = getArguments().getString("keyword");
        initResultList();
        getP().getSearchTribeResult(keyword);
    }

    /**
     * 初始化搜索结果列表
     */
    private void initResultList() {
        mXlvSearchResultList.getRecyclerView().setLayoutManager(new LinearLayoutManager(context));
        mXlvSearchResultList.getRecyclerView().setAdapter(new SearchResultTribeListAdapter(context));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search_result_simple;
    }

    @Override
    public SearchTribeResultPresenter newP() {
        return new SearchTribeResultPresenter();
    }

}
