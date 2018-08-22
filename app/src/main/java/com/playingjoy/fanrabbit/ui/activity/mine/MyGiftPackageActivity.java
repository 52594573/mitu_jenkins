package com.playingjoy.fanrabbit.ui.activity.mine;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.fragment.mine.MyGiftsFragment;
import com.playingjoy.fanrabbit.ui.presenter.mine.MyGiftPackagePresenter;
import com.playingjoy.fanrabbit.utils.CommUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Author: Ly
 * Data：2018/4/4-15:26
 * Description:我的礼包
 */
public class MyGiftPackageActivity extends BaseActivity<MyGiftPackagePresenter> {
    @BindView(R.id.tl_my_gifts_menu)
    TabLayout mTlMyGiftsMenu;
    @BindView(R.id.vp_my_gifts_container)
    ViewPager mVpMyGiftsContainer;

    @Override
    public void initData(Bundle savedInstanceState) {
        setTitleBar(getString(R.string.text_my_gifts));
        initViewPager();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        String[] title = new String[]{getString(R.string.text_all), getString(R.string.text_already_book),
                getString(R.string.text_already_received), getString(R.string.text_already_expired)};
        List<android.support.v4.app.Fragment> list = new ArrayList<>();
        list.add(MyGiftsFragment.newInstance());
        list.add(MyGiftsFragment.newInstance());
        list.add(MyGiftsFragment.newInstance());
        list.add(MyGiftsFragment.newInstance());
        mVpMyGiftsContainer.setAdapter(new XFragmentAdapter(getSupportFragmentManager(), list, title));
        mTlMyGiftsMenu.setupWithViewPager(mVpMyGiftsContainer);
        CommUtils.setIndicator(mTlMyGiftsMenu,20,20);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_game_gifts;
    }

    @Override
    public MyGiftPackagePresenter newP() {
        return new MyGiftPackagePresenter();
    }

    public static void toMyGiftPackageActivity(Activity activity) {
        Router.newIntent(activity).to(MyGiftPackageActivity.class).launch();
    }

}
