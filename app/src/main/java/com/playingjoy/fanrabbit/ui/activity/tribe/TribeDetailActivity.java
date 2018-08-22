package com.playingjoy.fanrabbit.ui.activity.tribe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.tribe.detail.TribeCorpsAdapter;
import com.playingjoy.fanrabbit.ui.adapter.tribe.detail.TribeDevelopmentAdapter;
import com.playingjoy.fanrabbit.ui.adapter.tribe.detail.TribeGamesAdapter;
import com.playingjoy.fanrabbit.ui.adapter.tribe.detail.TribeMemberAdapter;
import com.playingjoy.fanrabbit.ui.presenter.tribe.TribeDetailPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Author: Ly
 * Data：2018/4/17-14:28
 * Description: 部落详情页
 */
public class TribeDetailActivity extends BaseActivity<TribeDetailPresenter> {
    @BindView(R.id.ll_tribe_title_back)
    LinearLayout llTribeTitleBack;
    @BindView(R.id.tv_tribe_title_name)
    TextView tvTribeTitleName;
    @BindView(R.id.tv_tribe_title_message_num)
    TextView tvTribeTitleMessageNum;
    @BindView(R.id.fl_tribe_title_message)
    FrameLayout flTribeTitleMessage;
    @BindView(R.id.iv_tribe_title_more)
    ImageView ivTribeTitleMore;
    @BindView(R.id.ll_tribe_title_bar)
    LinearLayout llTribeTitleBar;
    @BindView(R.id.iv_tribe_pic)
    ImageView ivTribePic;
    @BindView(R.id.tv_tribe_id)
    TextView tvTribeId;
    @BindView(R.id.rb_tribe_rating)
    RatingBar rbTribeRating;
    @BindView(R.id.tv_tribe_desc)
    TextView tvTribeDesc;
    @BindView(R.id.ll_mine_info)
    LinearLayout llMineInfo;
    @BindView(R.id.iv_tribe_signIn)
    ImageView ivTribeSignIn;
    @BindView(R.id.rl_tribe_info)
    RelativeLayout rlTribeInfo;
    @BindView(R.id.tv_tribe_member_count)
    TextView tvTribeMemberCount;
    @BindView(R.id.tv_tribe_contribution_count)
    TextView tvTribeContributionCount;
    @BindView(R.id.tv_tribe_active_num)
    TextView tvTribeActiveNum;
    @BindView(R.id.tv_tribe_introduction)
    TextView tvTribeIntroduction;
    @BindView(R.id.tv_tribe_announcement)
    TextView tvTribeAnnouncement;
    @BindView(R.id.tv_tribe_announcement_more)
    TextView tvTribeAnnouncementMore;
    @BindView(R.id.tv_tribe_honor)
    TextView tvTribeHonor;
    @BindView(R.id.tv_tribe_shop)
    TextView tvTribeShop;
    @BindView(R.id.tv_tribe_task)
    TextView tvTribeTask;
    @BindView(R.id.tv_tribe_member)
    TextView tvTribeMember;
    @BindView(R.id.tv_tribe_member_more)
    TextView tvTribeMemberMore;
    @BindView(R.id.rlv_tribe_member_list)
    RecyclerView rlvTribeMemberList;
    @BindView(R.id.tv_tribe_games)
    TextView tvTribeGames;
    @BindView(R.id.rlv_tribe_games_list)
    RecyclerView rlvTribeGamesList;
    @BindView(R.id.tv_tribe_legion)
    TextView tvTribeLegion;
    @BindView(R.id.tv_tribe_legion_more)
    TextView tvTribeLegionMore;
    @BindView(R.id.rlv_tribe_legion_list)
    RecyclerView rlvTribeLegionList;
    @BindView(R.id.rlv_tribe_development_list)
    RecyclerView rlvTribeDevelopmentList;

    // 成员管理适配器
    private TribeMemberAdapter mTribeMemberAdapter;
    // 游戏管理适配器
    private TribeGamesAdapter mTribeGamesAdapter;
    //    游戏军团管理适配器
    private TribeCorpsAdapter mTribeCorpsAdapter;
    //    游戏历程适配器
    private TribeDevelopmentAdapter mTribeDevelopmentAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        initMember();
        initGames();
        initGameLegion();
        initDevelopment();
    }

    /**
     * 初始化工会路程
     */
    private void initDevelopment() {
        mTribeDevelopmentAdapter = new TribeDevelopmentAdapter(this);
        rlvTribeDevelopmentList.setLayoutManager(new LinearLayoutManager(this));
        rlvTribeDevelopmentList.setAdapter(mTribeDevelopmentAdapter);
        rlvTribeDevelopmentList.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化游戏军团
     */
    private void initGameLegion() {
        mTribeCorpsAdapter = new TribeCorpsAdapter(this);
        rlvTribeLegionList.setLayoutManager(new LinearLayoutManager(this));
        rlvTribeLegionList.setAdapter(mTribeCorpsAdapter);
        rlvTribeLegionList.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化我们的游戏
     */
    private void initGames() {
        mTribeGamesAdapter = new TribeGamesAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlvTribeGamesList.setLayoutManager(linearLayoutManager);
        rlvTribeGamesList.setAdapter(mTribeGamesAdapter);
        rlvTribeGamesList.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化工会成员
     */
    private void initMember() {
        mTribeMemberAdapter = new TribeMemberAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlvTribeMemberList.setLayoutManager(linearLayoutManager);
        rlvTribeMemberList.setAdapter(mTribeMemberAdapter);
        rlvTribeMemberList.setNestedScrollingEnabled(false);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_tribe_detail;
    }

    @Override
    public TribeDetailPresenter newP() {
        return new TribeDetailPresenter();
    }

    public static void toTribeDetailActivity(Activity activity) {
        Router.newIntent(activity).to(TribeDetailActivity.class).launch();
    }


    @OnClick({R.id.ll_tribe_title_back, R.id.fl_tribe_title_message, R.id.iv_tribe_title_more, R.id.iv_tribe_signIn, R.id.tv_tribe_announcement, R.id.tv_tribe_announcement_more, R.id.tv_tribe_honor, R.id.tv_tribe_shop, R.id.tv_tribe_task, R.id.tv_tribe_member_more, R.id.tv_tribe_legion_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tribe_title_back:
//                返回
                finish();
                break;
            case R.id.fl_tribe_title_message:
//                消息提醒
                break;
            case R.id.iv_tribe_title_more:
//                titlebar上面的更多
                break;
            case R.id.iv_tribe_signIn:

//                签到
                break;
            case R.id.tv_tribe_announcement:
//                最新的那个公告
                break;
            case R.id.tv_tribe_announcement_more:
//                更多公告
                break;
            case R.id.tv_tribe_honor:
//                荣誉墙
                break;
            case R.id.tv_tribe_shop:
                TribeShopActivity.toTribeShopActivity(context);
//                部落商店
                break;
            case R.id.tv_tribe_task:
//                部落任务
                break;
            case R.id.tv_tribe_member_more:
//                更多 工会成员
                break;
            case R.id.tv_tribe_legion_more:
//                更多 游戏军团
                break;
            default:
                break;
        }
    }
}
