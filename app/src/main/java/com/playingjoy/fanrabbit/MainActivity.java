package com.playingjoy.fanrabbit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.liulishuo.filedownloader.FileDownloader;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.domain.entity.DownLoadEntity;
import com.playingjoy.fanrabbit.gen.DaoSession;
import com.playingjoy.fanrabbit.ui.fragment.index.IndexFragment;
import com.playingjoy.fanrabbit.ui.fragment.mine.MineFragment;
import com.playingjoy.fanrabbit.ui.fragment.tribe.TribeFragment;
import com.playingjoy.fanrabbit.ui.presenter.MainPresenter;
import com.playingjoy.fanrabbit.utils.StatusBarUtil;
import com.playingjoy.fanrabbit.utils.cache.DownLoadInfoManager;
import com.playingjoy.fanrabbit.utils.db.DbHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 主页
 */
public class MainActivity extends BaseActivity<MainPresenter> {


    @BindView(R.id.vp_main_container)
    ViewPager mVpMainContainer;
    @BindView(R.id.rdoBtn_main_index)
    RadioButton mRdoBtnMainIndex;
    @BindView(R.id.rdoBtn_main_tribe)
    RadioButton mRdoBtnMainTribe;
    @BindView(R.id.rdoBtn_main_dynamic)
    RadioButton mRdoBtnMainDynamic;
    @BindView(R.id.rdoBtn_main_mine)
    RadioButton mRdoBtnMainMine;
    @BindView(R.id.rdoG_main_menu)
    RadioGroup mRdoGMainMenu;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter newP() {
        return new MainPresenter();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initFragment();
    }

    @Override
    public void setUpStatusBar() {
        super.setUpStatusBar();
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }

    /**
     * viewPager 初始化
     */
    private void initFragment() {
        mRdoBtnMainDynamic.setVisibility(View.GONE);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(IndexFragment.newInstance());
        fragmentList.add(TribeFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());
        mVpMainContainer.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        mVpMainContainer.setOffscreenPageLimit(fragmentList.size() - 1);
        mRdoGMainMenu.setOnCheckedChangeListener((group, checkedId) -> {
            switch (group.getCheckedRadioButtonId()) {
                case R.id.rdoBtn_main_index:
                    mVpMainContainer.setCurrentItem(0, false);
                    break;
                case R.id.rdoBtn_main_tribe:
                    mVpMainContainer.setCurrentItem(1, false);
                    break;
                case R.id.rdoBtn_main_dynamic:
                    mVpMainContainer.setCurrentItem(2, false);
                    break;
                case R.id.rdoBtn_main_mine:
                    mVpMainContainer.setCurrentItem(3, false);
                    break;
                default:
                    break;
            }
        });
    }


    public static void toMainActivity(Activity activity) {
        Router.newIntent(activity).to(MainActivity.class).launch();
    }


    /**
     * 记录用户首次点击返回键的时间
     */
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long exitTime = 2000;
            if (System.currentTimeMillis() - firstTime > exitTime) {
                Toast.makeText(MainActivity.this, R.string.text_click_more_to_exit, Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                FileDownloader.getImpl().pauseAll();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
