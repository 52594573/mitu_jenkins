package com.playingjoy.fanrabbit.ui.activity.comm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.BigImageAdapter;
import com.playingjoy.fanrabbit.ui.fragment.comm.BigImageFragment;
import com.playingjoy.fanrabbit.ui.presenter.mine.BigImagePresenter;
import com.playingjoy.fanrabbit.utils.StatusBarUtil;
import com.playingjoy.fanrabbit.widget.PhotoViewViewPager;
import com.playingjoy.fanrabbit.widget.SimpleNoTitleDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Author: Ly
 * Data：2018/4/11-15:01
 * Description:查看大图页面
 */
public class BigImageActivity extends BaseActivity<BigImagePresenter> {
    @BindView(R.id.vp_big_container)
    PhotoViewViewPager mVpBigContainer;
    @BindView(R.id.iv_big_delete)
    ImageView mIvBigDelete;
    @BindView(R.id.tv_big_curr)
    TextView mTvBigCurr;

    private ArrayList<String> imgList = new ArrayList<>();
    private BigImageAdapter mFragmentStatePagerAdapter;

    @Override
    public void setUpStatusBar() {
        super.setUpStatusBar();
        StatusBarUtil.setColor(this, getResources().getColor(R.color.main_black));
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getBundleExtra("extra");
        if (bundle != null) {
            imgList = bundle.getStringArrayList("list");
            int index = bundle.getInt("position", 0);
            mTvBigCurr.setText(String.format("%1$s/%2$s", index + 1, String.valueOf(imgList.size())));
            mVpBigContainer.setAdapter(mFragmentStatePagerAdapter = new BigImageAdapter(getSupportFragmentManager(), imgList));
            mVpBigContainer.setCurrentItem(index);
            mVpBigContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    mTvBigCurr.setText(String.format("%1$s/%2$s", String.valueOf(position + 1), String.valueOf(imgList.size())));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_big_image;
    }

    @Override
    public BigImagePresenter newP() {
        return new BigImagePresenter();
    }

    /**
     * @param a
     * @param list     图片路径的ArrayList
     * @param position 当前index
     */
    public static void toBigImageActivity(Activity a, ArrayList<String> list, int position) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("list", list);
        bundle.putInt("position", position);
        Router.newIntent(a).putBundle("extra", bundle).to(BigImageActivity.class).launch();
    }


    @OnClick(R.id.iv_big_delete)
    public void onViewClicked() {
        if (!imgList.isEmpty()) {
            showAlert2Delete();
        } else {
            finish();
        }
    }


    /**
     * 显示确认删除的dialog
     */
    private void showAlert2Delete() {
        SimpleNoTitleDialog simpleNoTitleDialog = new SimpleNoTitleDialog(this)
                .setTipText("确定删除该图片吗?")
                .setConfirmBtnClickListener(v -> {
                    imgList.remove(mVpBigContainer.getCurrentItem());
                    mFragmentStatePagerAdapter.updateData(imgList);
                    mTvBigCurr.setText(String.format("%1$s/%2$s", String.valueOf(mVpBigContainer.getCurrentItem() + 1), String.valueOf(imgList.size())));
                    if (imgList.isEmpty()) {
                        finish();
                    }
                });
        simpleNoTitleDialog.show();


//        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setTitle("确定删除")
//                .setMessage("确定删除该图片吗？")
//                .setNegativeButton("取消", (dialog1, which) -> {
//
//                })
//                .setPositiveButton("确定", (dialog1, which) -> {
//                    imgList.remove(mVpBigContainer.getCurrentItem());
//                    mFragmentStatePagerAdapter.updateData(imgList);
//                    mTvBigCurr.setText(String.format("%1$s/%2$s", String.valueOf(mVpBigContainer.getCurrentItem() + 1), String.valueOf(imgList.size())));
//                    if (imgList.isEmpty()){
//                        finish();
//                    }
//                })
//                .create();
////        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.second_color));
////        dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.second_color));
//        dialog.show();
    }
}
