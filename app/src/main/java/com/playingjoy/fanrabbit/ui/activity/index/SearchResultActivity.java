package com.playingjoy.fanrabbit.ui.activity.index;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.base.BasePresenter;
import com.playingjoy.fanrabbit.ui.fragment.index.search.SearchGameResultFragment;
import com.playingjoy.fanrabbit.ui.fragment.index.search.SearchTribeResultFragment;
import com.playingjoy.fanrabbit.ui.fragment.index.search.SearchUserResultFragment;
import com.playingjoy.fanrabbit.utils.CommUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 搜索结果页面
 *
 * @author Morphine
 * @date 2018-04-10.
 */

public class SearchResultActivity extends BaseActivity {

    String keyword;
    @BindView(R.id.ib_back)
    ImageButton mIbBack;
    @BindView(R.id.tv_keyword)
    TextView mTvKeyword;
    @BindView(R.id.tl_search_result)
    TabLayout mTlSearchResult;
    @BindView(R.id.vp_search_result)
    ViewPager mVpSearchResult;

    @Override
    public void initData(Bundle savedInstanceState) {
        keyword = getIntent().getStringExtra("keyword");
        mTvKeyword.setText(keyword);
        initResultListPager();
    }

    /**
     * 初始化搜索结果列表页面
     */
    private void initResultListPager() {
        String[] titles = new String[]{getString(R.string.text_game), getString(R.string.text_tribe), getString(R.string.text_user)};
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(SearchGameResultFragment.newInstance(keyword));
        fragments.add(SearchTribeResultFragment.newInstance(keyword));
        fragments.add(SearchUserResultFragment.newInstance(keyword));
        mVpSearchResult.setAdapter(new XFragmentAdapter(getSupportFragmentManager(), fragments, titles));
        mTlSearchResult.setupWithViewPager(mVpSearchResult);
        CommUtils.setIndicator(mTlSearchResult);
    }

    public static void toSearchResultActivity(Activity activity, String keyword) {
        Router.newIntent(activity).putString("keyword", keyword).to(SearchResultActivity.class).launch();
    }

    @OnClick(R.id.tv_keyword)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_keyword:
                finish();
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_result;
    }

    @Override
    public BasePresenter newP() {
        return null;
    }

}
