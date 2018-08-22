package com.playingjoy.fanrabbit.ui.fragment.index;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.ui.activity.index.MessageCenterActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.MyGameActivity;
import com.playingjoy.fanrabbit.ui.activity.tribe.TransferTribeActivity;
import com.playingjoy.fanrabbit.ui.presenter.index.IndexPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;

/**
 * Created by Ly on 2018/3/20.
 * 首页的Fragment
 */

public class IndexFragment extends BaseFragment<IndexPresenter> {

    @BindView(R.id.ll_index_search)
    LinearLayout mLlIndexSearch;
    @BindView(R.id.ll_index_download_manager)
    ImageView mLlIndexDownloadManager;
    @BindView(R.id.ll_index_message)
    ImageView mLlIndexMessage;
    @BindView(R.id.vp_index_container)
    ViewPager mVpIndexContainer;

    @BindView(R.id.tb_index_menu)
    TabLayout mTbIndexMenu;

    public static IndexFragment newInstance() {
        Bundle args = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        doInitFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    public IndexPresenter newP() {
        return new IndexPresenter();
    }


    @OnClick({R.id.ll_index_search, R.id.ll_index_download_manager, R.id.ll_index_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_index_search:
                // TODO: 2018-04-17 搜索跳转修改
                TransferTribeActivity.toTransferTribeActivity(getActivity());
//                SearchActivity.toSearchActivity(getActivity());
                break;
            case R.id.ll_index_download_manager:
                MyGameActivity.toMyGamePage(getActivity(), 2);
                break;
            case R.id.ll_index_message:
                MessageCenterActivity.toMessageCenter(getActivity());
                break;
        }
    }

    /**
     * 初始化Fragment适配器
     */
    private void doInitFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(RecommendFragment.newInstance(true));
        fragmentList.add(RankingFragment.newInstance());
        String[] titles = new String[]{getString(R.string.text_recommend), getString(R.string.text_ranking)};
        mVpIndexContainer.setAdapter(new XFragmentAdapter(getChildFragmentManager(),
                fragmentList, titles));
        mTbIndexMenu.setupWithViewPager(mVpIndexContainer);
    }
}
