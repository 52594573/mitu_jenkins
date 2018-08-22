package com.playingjoy.fanrabbit.ui.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.ui.activity.index.MessageCenterActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.AccountBindActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.ApplyPromoterActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.FeedbackActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.MyFriendActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.MyGameActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.MyGiftPackageActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.MyPurseActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.MyTaskActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.SettingActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.UserInfoActivity;
import com.playingjoy.fanrabbit.ui.adapter.mine.MineGamePlayedAdapter;
import com.playingjoy.fanrabbit.ui.presenter.mine.MinePresenter;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: Ly
 * Data：2018/4/3-11:03
 * Description:个人中心
 */
public class MineFragment extends BaseFragment<MinePresenter> {
    @BindView(R.id.rlv_mine_game_played)
    RecyclerView mRlvMineGamePlayed;
    @BindView(R.id.iv_mine_pic)
    CircleImageView mIvMinePic;
    @BindView(R.id.tv_mine_name)
    TextView mTvMineName;
    @BindView(R.id.iv_mine_sex)
    ImageView mIvMineSex;
    @BindView(R.id.tv_mine_level)
    TextView mTvMineLevel;
    @BindView(R.id.tv_mine_id)
    TextView mTvMineId;
    @BindView(R.id.tv_mine_attention)
    TextView mTvMineAttention;
    @BindView(R.id.tv_mine_fans)
    TextView mTvMineFans;
    @BindView(R.id.tv_mine_desc)
    TextView mTvMineDesc;
    @BindView(R.id.ll_mine_info)
    LinearLayout mLlMineInfo;
    @BindView(R.id.tv_mine_my_task)
    TextView mTvMineMyTask;
    @BindView(R.id.tv_mine_my_tribe)
    TextView mTvMineMyTribe;
    @BindView(R.id.tv_mine_my_message)
    TextView mTvMineMymessage;
    @BindView(R.id.tv_mine_my_friends)
    TextView mTvMineMyFriends;
    @BindView(R.id.tv_mine_download_num)
    TextView mTvMineDownloadNum;
    @BindView(R.id.tv_mine_like_num)
    TextView mTvMineLikeNum;
    @BindView(R.id.tv_mine_book_num)
    TextView mTvMineBookNum;
    @BindView(R.id.tv_mine_played_num)
    TextView mTvMinePlayedNum;
    @BindView(R.id.rl_mine_promotion)
    RelativeLayout mRlMinePromotion;
    @BindView(R.id.rl_mine_gifts)
    RelativeLayout mRlMineGifts;
    @BindView(R.id.rl_mine_purse)
    RelativeLayout mRlMinePurse;
    @BindView(R.id.rl_mine_account)
    RelativeLayout mRlMineAccount;
    @BindView(R.id.rl_mine_settings)
    RelativeLayout mRlMineSettings;

    private MineGamePlayedAdapter mMineGamePlayedAdapter;

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mMineGamePlayedAdapter = new MineGamePlayedAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRlvMineGamePlayed.setLayoutManager(linearLayoutManager);
        mRlvMineGamePlayed.setAdapter(mMineGamePlayedAdapter);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public MinePresenter newP() {
        return new MinePresenter();
    }


    @OnClick({R.id.tv_mine_my_game, R.id.tv_mine_my_message, R.id.tv_mine_promotion, R.id.iv_mine_pic, R.id.tv_mine_attention,
            R.id.tv_mine_fans, R.id.tv_mine_desc, R.id.tv_mine_my_task, R.id.tv_mine_my_tribe, R.id.tv_mine_my_friends,
            R.id.ll_mine_download_num, R.id.ll_mine_like_num, R.id.ll_mine_book_num, R.id.rl_mine_promotion,
            R.id.rl_mine_gifts, R.id.rl_mine_purse, R.id.rl_mine_account, R.id.rl_mine_settings, R.id.rl_mine_feedback, R.id.ll_mine_played_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_pic:
//                个人头像
                UserInfoActivity.toUserInfoActivity(context);
                break;
            case R.id.tv_mine_attention:
//                我的关注
                break;
            case R.id.tv_mine_fans:
//                我的粉丝
                break;
            case R.id.tv_mine_desc:
//                我的简介
                break;
            case R.id.tv_mine_my_task:
//                我的任务
                MyTaskActivity.toMyTaskActivity(getActivity());
                break;
            case R.id.tv_mine_my_tribe:
//                我的部落
                break;
            case R.id.tv_mine_my_message:
                MessageCenterActivity.toMessageCenter(context);
//                消息中心
                break;
            case R.id.tv_mine_my_friends:
                MyFriendActivity.toMyFriendActivity(context);
//                我的好友
                break;
            case R.id.ll_mine_download_num:
//                我的下载
                MyGameActivity.toMyGamePage(context, 2);
                break;
            case R.id.ll_mine_like_num:
//                我的喜欢
                MyGameActivity.toMyGamePage(context, 1);
                break;
            case R.id.tv_mine_promotion:
//                申请推广员
                ApplyPromoterActivity.toApplyPromoterActivity(context);
                break;
            case R.id.ll_mine_book_num:
//                我的预定
                MyGameActivity.toMyGamePage(context, 3);
                break;
            case R.id.rl_mine_promotion:
//                我的推广
                break;
            case R.id.rl_mine_gifts:
//                我的礼包
                MyGiftPackageActivity.toMyGiftPackageActivity(context);
                break;
            case R.id.rl_mine_purse:
//                我的钱包
                MyPurseActivity.toMyPurseActivity(getActivity());
                break;
            case R.id.rl_mine_account:
//                绑定账号
                AccountBindActivity.toAccountBindActivity(context);
                break;
            case R.id.rl_mine_settings:
//                设置
                SettingActivity.toSettingActivity(context);
                break;
            case R.id.tv_mine_my_game:
//                我的游戏
//                MyGameActivity.toMyGamePage(context, 0);
                break;
            case R.id.rl_mine_feedback:
                FeedbackActivity.toFeedBackActivity(context);
                break;
            case R.id.ll_mine_played_num:
                MyGameActivity.toMyGamePage(context, 0);
                break;
            default:
                break;
        }
    }
}
