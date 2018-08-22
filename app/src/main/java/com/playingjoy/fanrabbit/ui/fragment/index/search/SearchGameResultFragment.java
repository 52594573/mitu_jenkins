package com.playingjoy.fanrabbit.ui.fragment.index.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.ui.adapter.search.SearchResultGameListAdapter;
import com.playingjoy.fanrabbit.ui.presenter.index.SearchGameResultPresenter;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * 搜索结果游戏页面
 *
 * @author Morphine
 * @date 2018-04-10.
 */

public class SearchGameResultFragment extends BaseFragment<SearchGameResultPresenter> {

    @BindView(R.id.xlv_search_result_list)
    XRecyclerContentLayout mXlvSearchResultList;
    String keyword;

    public static SearchGameResultFragment newInstance(String keyword) {
        Bundle args = new Bundle();
        args.putString("keyword", keyword);
        SearchGameResultFragment fragment = new SearchGameResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        keyword = getArguments().getString("keyword");
        initResultList();
        getP().getSearchGameResult(keyword);
    }

    /**
     * 初始化搜索游戏列表
     */
    private void initResultList() {
        mXlvSearchResultList.getRecyclerView().setLayoutManager(new LinearLayoutManager(context));
        mXlvSearchResultList.getRecyclerView().setAdapter(new SearchResultGameListAdapter(context));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search_result_simple;
    }

    @Override
    public SearchGameResultPresenter newP() {
        return new SearchGameResultPresenter();
    }

}
