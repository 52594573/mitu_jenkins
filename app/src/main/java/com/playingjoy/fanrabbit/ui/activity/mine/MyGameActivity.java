package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.fragment.mine.MyGameListFragment;
import com.playingjoy.fanrabbit.ui.presenter.message.MyGamePresenter;
import com.playingjoy.fanrabbit.utils.CommUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Author: Ly
 * Data：2018/3/29-14:56
 * Description:我的游戏
 */
public class MyGameActivity extends BaseActivity<MyGamePresenter> {
    @BindView(R.id.tl_my_game_menu)
    TabLayout mTlMyGameMenu;
    @BindView(R.id.vp_my_game_container)
    ViewPager mVpMyGameContainer;

    /**
     * 默认进入到玩过页面
     */
    private int mFlag = 0;

    @Override
    public void initData(Bundle savedInstanceState) {
        mFlag = getIntent().getIntExtra("flag", 0);
        setTitleBar(getString(R.string.text_my_game));
        initViewPage();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_game;
    }

    @Override
    public MyGamePresenter newP() {
        return new MyGamePresenter();
    }

    /**
     * 初始化viewPager
     */
    private void initViewPage() {
        List<Fragment> fragmentList = new ArrayList<>();
        String[] menuStrs = new String[]{getString(R.string.text_played), getString(R.string.text_collection),
                getString(R.string.text_download), getString(R.string.text_reservation)};
        fragmentList.add(MyGameListFragment.newInstance());
        fragmentList.add(MyGameListFragment.newInstance());
        fragmentList.add(MyGameListFragment.newInstance());
        fragmentList.add(MyGameListFragment.newInstance());
        mVpMyGameContainer.setAdapter(new XFragmentAdapter(getSupportFragmentManager(), fragmentList, menuStrs));
        mTlMyGameMenu.setupWithViewPager(mVpMyGameContainer);
        if (mFlag >= 0 && mFlag <= menuStrs.length) {
            mVpMyGameContainer.setCurrentItem(mFlag, false);
        }
        CommUtils.setIndicator(mTlMyGameMenu,20,20);
    }

    /**
     * @param activity
     * @param flag     0 玩过  1 收藏  2 下载  3预约
     */
    public static void toMyGamePage(Activity activity, int flag) {
        Router.newIntent(activity).to(MyGameActivity.class).putInt("flag", flag).launch();
    }
}
