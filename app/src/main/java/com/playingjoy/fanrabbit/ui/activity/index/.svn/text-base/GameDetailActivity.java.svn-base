package com.playingjoy.fanrabbit.ui.activity.index;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.index.GameTagAdapter;
import com.playingjoy.fanrabbit.ui.dialog.PromotionAuthTipDialog;
import com.playingjoy.fanrabbit.ui.dialog.ShareDialog;
import com.playingjoy.fanrabbit.ui.fragment.index.gamedetail.GameDetailFragment;
import com.playingjoy.fanrabbit.ui.fragment.index.gamedetail.GameGiftsFragment;
import com.playingjoy.fanrabbit.ui.fragment.index.gamedetail.GameTribeFragment;
import com.playingjoy.fanrabbit.ui.popupwindow.GameDetailCommentPpw;
import com.playingjoy.fanrabbit.ui.presenter.gamedetail.GameDetailPresenter;
import com.playingjoy.fanrabbit.utils.CommUtils;
import com.playingjoy.fanrabbit.utils.StatusBarUtil;
import com.playingjoy.fanrabbit.widget.ImageViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Author: Ly
 * Data：2018/3/28-13:50
 * Description: 游戏详情页面
 */
public class GameDetailActivity extends BaseActivity<GameDetailPresenter> {
    @BindView(R.id.view_need_offset)
    AppBarLayout mViewNeedOffset;
    @BindView(R.id.rlv_game_detail_tag)
    RecyclerView mRlvGameDetailGameTag;
    @BindView(R.id.br_game_detail_banner)
    ConvenientBanner mBrGameDetailBanner;
    @BindView(R.id.iv_game_detail_pic)
    ImageView mIvGameDetailPic;
    @BindView(R.id.tv_game_detail_name)
    TextView mTvGameDetailName;
    @BindView(R.id.tv_game_detail_size)
    TextView mTvGameDetailSize;
    @BindView(R.id.tv_game_detail_connection_num)
    TextView mTvGameDetailConnectionNum;
    @BindView(R.id.tv_game_detail_share_num)
    TextView mTvGameDetailShareNum;
    @BindView(R.id.tv_game_detail_comment_num)
    TextView mTvGameDetailCommentNum;
    @BindView(R.id.tl_game_detail_menu)
    TabLayout mTlGameDetailMenu;
    @BindView(R.id.vp_game_detail_container)
    ViewPager mVpGameDetailContainer;
    @BindView(R.id.iv_collection)
    ImageView ivCollection;


    private GameTagAdapter mGameTagAdapter;
    GameDetailCommentPpw gameDetailCommentPpw;
    PromotionAuthTipDialog promotionAuthTipDialog;

    @Override
    public void initData(Bundle savedInstanceState) {
        doInitBanner();
        doInitGameTag();
        doInitFragment();
    }


    @Override
    public void setUpStatusBar() {
        StatusBarUtil.setTranslucentForImageView(GameDetailActivity.this, mViewNeedOffset);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_game_detail;
    }

    @Override
    public GameDetailPresenter newP() {
        return new GameDetailPresenter();
    }

    public static void toGameDetailActivity(Activity activity) {
        Router.newIntent(activity).to(GameDetailActivity.class).launch();
    }

    /**
     * 初始化banner
     */
    private void doInitBanner() {
        List<Integer> imgList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            imgList.add(R.drawable.bg_monkey_king);
        }
        mBrGameDetailBanner.startTurning(4000);
        mBrGameDetailBanner.setPages(ImageViewHolder::new, imgList);
    }

    /**
     * 初始化游戏标签
     */
    private void doInitGameTag() {
        LinearLayoutManager layoutManagerForGameTag = new LinearLayoutManager(this);
        layoutManagerForGameTag.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRlvGameDetailGameTag.setLayoutManager(layoutManagerForGameTag);
        mGameTagAdapter = new GameTagAdapter(this);
        mRlvGameDetailGameTag.setAdapter(mGameTagAdapter);
    }

    /**
     * 初始化游戏菜单
     */
    private void doInitFragment() {
        String[] titles = new String[]{getResources().getString(R.string.text_detail),
                getResources().getString(R.string.text_tribe), getResources().getString(R.string.text_gift)};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(GameDetailFragment.newInstance());
        fragments.add(GameTribeFragment.newInstance());
        fragments.add(GameGiftsFragment.newInstance());
        mVpGameDetailContainer.setAdapter(new XFragmentAdapter(getSupportFragmentManager(),
                fragments, titles));
        mTlGameDetailMenu.setupWithViewPager(mVpGameDetailContainer);
        CommUtils.setIndicator(mTlGameDetailMenu);
    }


    @OnClick({R.id.ll_collection, R.id.ll_comment, R.id.ll_share, R.id.ll_promotion, R.id.ll_download, R.id.iv_game_detail_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_collection:
                getP().collectionClick();
                break;
            case R.id.ll_comment:
                showCommentPopupWindow();
                break;
            case R.id.ll_share:
                ShareDialog.showDialog(this, getString(R.string.text_share_to))
                        .setOnItemClickListener((shareMenuBean, message) -> XLog.e("xxxxx", shareMenuBean.toString()));
                break;
            case R.id.ll_promotion:
                if (promotionAuthTipDialog == null) {
                    promotionAuthTipDialog = new PromotionAuthTipDialog(context);
                    promotionAuthTipDialog.setCancelBtnClickListener(v -> promotionAuthTipDialog.dismiss());
                    // TODO: 2018-03-29 跳推广者认证
                    promotionAuthTipDialog.setConfirmBtnClickListener(v -> showTs("跳去推广者认证"));
                }
                promotionAuthTipDialog.show();
                break;
            case R.id.ll_download:
                break;
            case R.id.iv_game_detail_back:
                finish();
            default:
                break;
        }
    }

    private void showCommentPopupWindow() {
        if (gameDetailCommentPpw == null) {
            gameDetailCommentPpw = new GameDetailCommentPpw(context, v -> {
                String message = ((EditText) v).getText().toString();
                //发送事件处理
                showTs(message);
            });
        }
        gameDetailCommentPpw.showAtLocation(findViewById(R.id.root_view), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    /**
     * 更改收藏状态UI
     */
    public void updateCollectionState(boolean isCollection) {
        Drawable drawable = getResources().getDrawable(isCollection ? R.drawable.big_collection_p : R.drawable.big_collection);
        ivCollection.setImageDrawable(drawable);
    }
}
