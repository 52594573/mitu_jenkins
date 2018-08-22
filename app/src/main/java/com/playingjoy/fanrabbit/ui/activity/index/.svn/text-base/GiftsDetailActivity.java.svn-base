package com.playingjoy.fanrabbit.ui.activity.index;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.util.DensityUtil;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.adapter.GiftsDetailRecommendGamesListAdapter;
import com.playingjoy.fanrabbit.ui.dialog.SimpleDialog;
import com.playingjoy.fanrabbit.ui.presenter.gamedetail.GiftsDetailPresenter;
import com.playingjoy.fanrabbit.utils.GiftsConfig;
import com.playingjoy.fanrabbit.utils.GiftsDialogUtil;
import com.playingjoy.fanrabbit.widget.FilletProgressBarView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 礼包详情页
 *
 * @author Morphine
 * @date 2018-03-31.
 */

public class GiftsDetailActivity extends BaseActivity<GiftsDetailPresenter> {
    @BindView(R.id.iv_game_icon)
    ImageView mIvGameIcon;
    @BindView(R.id.tv_gifts_name)
    TextView mTvGiftsName;
    @BindView(R.id.tv_predestine_count)
    TextView mTvPredestineCount;
    @BindView(R.id.btn_predestine)
    TextView mBtnPredestine;
    @BindView(R.id.tv_gifts_content)
    TextView mTvGiftsContent;
    @BindView(R.id.tv_exchange_period)
    TextView mTvExchangePeriod;
    @BindView(R.id.tv_used_method)
    TextView mTvUsedMethod;
    @BindView(R.id.tv_games_recommend_tip)
    TextView mTvGamesRecommendTip;
    @BindView(R.id.xlv_recommend_games_list)
    XRecyclerView mXlvRecommendGamesList;
    @BindView(R.id.iv_change_recommend)
    ImageView ivChangeRecommend;
    @BindView(R.id.rl_gifts_info_personal)
    RelativeLayout mRlGiftsInfoPersonal;
    @BindView(R.id.iv_game_icon_guild)
    ImageView mIvGameIconGuild;
    @BindView(R.id.tv_gifts_name_guild)
    TextView mTvGiftsNameGuild;
    @BindView(R.id.pb_gifts_last)
    FilletProgressBarView mPbGiftsLast;
    @BindView(R.id.tv_contribution_value)
    TextView mTvContributionValue;
    @BindView(R.id.btn_predestine_guild)
    TextView mBtnPredestineGuild;
    @BindView(R.id.rl_gifts_info_guild)
    RelativeLayout mRlGiftsInfoGuild;
    @BindView(R.id.tv_predestine_count_guild)
    TextView mTvPredestineCountGuild;

    GiftsDetailRecommendGamesListAdapter recommendGamesListAdapter;

    /**
     * 礼包状态 1-预订,2-领号,3-淘号
     */
    private int giftsState;
    /**
     * 预订状态 1-未预定,2-预订成功,3-已预订
     */
    private int predestineState = GiftsConfig.PREDESTINE_STATE_NOT;

    /**
     * 游戏状态 1-未下载,2-下载中,3-未安装,4-已安装
     */
    private int gameState = 1;

    /**
     * 礼包码
     */
    private String giftsNumber;

    /**
     * 已淘次数(淘号状态下)
     */
    private int rushCount = 222;
    /**
     * 礼包名称
     */
    private String giftsName;

    /**
     * 兑换礼包所需钻石
     */
    private int exchangeDiamond;


    Animation changeRecommendAnim;
    GiftsDialogUtil giftsDialogUtil;
    SimpleDialog personalGiftsDialog;
    int giftsType;
    SimpleDialog guildGiftsDialog;

    @Override
    public void initData(Bundle savedInstanceState) {
        giftsType = getIntent().getIntExtra(GiftsConfig.GIFTS_TYPE, GiftsConfig.GIFTS_TYPE_PERSONAL);
        initGiftsType();

        initRecommendGamesList();
        initChangeRecommendAnim();
        getP().initGiftsDetailData();
        getP().getRecommendGame();
        giftsDialogUtil = new GiftsDialogUtil();
    }

    private void initGiftsType() {
        mRlGiftsInfoGuild.setVisibility(giftsType == GiftsConfig.GIFTS_TYPE_PERSONAL ? View.GONE : View.VISIBLE);

        mTvGiftsName.setCompoundDrawables(null, null, GiftsConfig.getGiftsDrawableFlag(getApplicationContext(), GiftsConfig.GIFTS_TYPE_PERSONAL), null);
        mTvGiftsName.setCompoundDrawablePadding(DensityUtil.dip2px(context, 8));

        mTvGiftsNameGuild.setCompoundDrawablePadding(DensityUtil.dip2px(context, 8));
        mTvGiftsNameGuild.setCompoundDrawables(null, null,  GiftsConfig.getGiftsDrawableFlag(getApplicationContext(), GiftsConfig.GIFTS_TYPE_TRIBE), null);
        mRlGiftsInfoPersonal.setVisibility(giftsType == GiftsConfig.GIFTS_TYPE_PERSONAL ? View.VISIBLE : View.GONE);
    }

    private void initChangeRecommendAnim() {
        changeRecommendAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        changeRecommendAnim.setDuration(1000);
        changeRecommendAnim.setFillBefore(true);
        changeRecommendAnim.setRepeatMode(Animation.REVERSE);
        changeRecommendAnim.setRepeatCount(Animation.INFINITE);
    }

    /**
     * 初始化推荐游戏列表
     */
    private void initRecommendGamesList() {
        mXlvRecommendGamesList.setRefreshEnabled(false);
        mXlvRecommendGamesList.setLayoutManager(new GridLayoutManager(context, 4));
        recommendGamesListAdapter = new GiftsDetailRecommendGamesListAdapter(context);
        mXlvRecommendGamesList.setAdapter(recommendGamesListAdapter);
    }

    /**
     * 设置相关游戏推荐
     *
     * @param gameList
     */
    public void setRecommendGameList(List<String> gameList) {
        recommendGamesListAdapter.setData(gameList);
        if (changeRecommendAnim != null) {
            changeRecommendAnim.cancel();
        }
    }

    /**
     * 设置礼包类型
     */
    public void setGiftsState(int giftsState) {
        this.giftsState = giftsState;
        String btnStateText = "";
        switch (giftsState) {
            case GiftsConfig.GIFTS_STATE_PREDESTINE:
                btnStateText = "预订";
                break;
            case GiftsConfig.GIFTS_STATE_RECEIVE:
                btnStateText = "领号";
                break;
            case GiftsConfig.GIFTS_STATE_RUSH:
                btnStateText = "淘号";
                break;
            default:
                break;
        }
        mBtnPredestine.setText(btnStateText);
        mBtnPredestineGuild.setText(btnStateText);
    }

    /**
     * 设置兑换礼包需要的贡献值
     *
     * @param contributionValue 兑换礼包需要的贡献值
     */
    public void setContributionValue(int contributionValue) {
        this.exchangeDiamond = contributionValue;
        mTvContributionValue.setText(String.valueOf(exchangeDiamond));
    }

    /**
     * 领号、淘号成功显示礼包码
     *
     * @param giftsNumber 礼包码
     */
    public void showGiftsNumber(String giftsNumber) {
        this.giftsNumber = giftsNumber;
        showPersonalGiftsDialog();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_gifts_detail;
    }

    @Override
    public GiftsDetailPresenter newP() {
        return new GiftsDetailPresenter();
    }

    /**
     * 设置礼包名称
     *
     * @param giftsName 礼包名称
     */
    public void setGiftsName(String giftsName) {
        mTvGiftsName.setText(giftsName);
        mTvGiftsNameGuild.setText(giftsName);
        this.giftsName = giftsName;
    }

    /**
     * 设置已预订数量
     *
     * @param predestineCount 预订人数
     */
    public void setPredestineCount(String predestineCount) {
        SpannableString spannableString = new SpannableString(String.format(context.getString(R.string.format_predestine_person_count), predestineCount));
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.yellow_color));
        spannableString.setSpan(colorSpan, 2, 3 + predestineCount.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mTvPredestineCount.setText(spannableString);
    }

    /**
     * 设置剩余礼包数量
     *
     * @param progress 已领百分比
     */
    public void setGiftsLast(int progress) {
        if (giftsState == GiftsConfig.GIFTS_STATE_RECEIVE) {
            if (giftsType == GiftsConfig.GIFTS_TYPE_TRIBE) {
                mPbGiftsLast.setProgress(progress);
                mPbGiftsLast.setVisibility(giftsState == GiftsConfig.GIFTS_STATE_RECEIVE ? View.VISIBLE : View.GONE);
                mTvPredestineCountGuild.setVisibility(giftsState == GiftsConfig.GIFTS_STATE_RECEIVE ? View.GONE : View.VISIBLE);
            } else {
            }
        }
    }

    /**
     * 设置已淘号次数
     *
     * @param rushCount
     */
    public void setRushCount(int rushCount) {
        this.rushCount = rushCount;
        SpannableString spannableString = new SpannableString(String.format(context.getString(R.string.format_rush_count), rushCount));
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.yellow_color));
        spannableString.setSpan(colorSpan, 2, 2 + String.valueOf(rushCount).length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        if (giftsState == GiftsConfig.GIFTS_STATE_RUSH) {
            if (giftsType == GiftsConfig.GIFTS_TYPE_TRIBE) {
                mTvPredestineCountGuild.setText(spannableString);
            } else {
                mTvPredestineCount.setText(spannableString);
            }
        }
    }

    /**
     * 设置游戏名称
     *
     * @param gameName
     */
    public void setGameName(String gameName) {
        setTitleBar(gameName);
        SpannableString spannableString =
                new SpannableString(String.format(getString(R.string.text_gifts_games_recommend_format), gameName));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue_4787fc)),
                2, 2 + gameName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvGamesRecommendTip.setText(spannableString);
    }

    /**
     * 设置礼包内容
     *
     * @param giftsContent 礼包内容
     */
    public void setGiftsContent(String giftsContent) {
        mTvGiftsContent.setText(giftsContent);
    }

    /**
     * 设置兑换期限
     *
     * @param exchangePeriod 兑换期限
     */
    public void setExchangePeriod(String exchangePeriod) {
        mTvExchangePeriod.setText(exchangePeriod);
    }

    /**
     * 设置使用方法
     *
     * @param usedMethod 使用方法
     */
    public void setUsedMethod(String usedMethod) {
        mTvUsedMethod.setText(usedMethod);
    }


    @OnClick({R.id.btn_predestine, R.id.ll_change_recommend, R.id.btn_predestine_guild})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_predestine:
                if (giftsState == GiftsConfig.GIFTS_STATE_PREDESTINE) {
                    showPersonalGiftsDialog();
                } else if (giftsState == GiftsConfig.GIFTS_STATE_RECEIVE) {
                    getP().receiveNumber();
                } else if (giftsState == GiftsConfig.GIFTS_STATE_RUSH) {
                    getP().rushNumber();
                }
                break;
            case R.id.ll_change_recommend:
                ivChangeRecommend.startAnimation(changeRecommendAnim);
                getP().getRecommendGame();
                break;
            case R.id.btn_predestine_guild:
                //部落礼包dialog
                showGuildGiftsDialog();
                break;
        }
    }

    private void showGuildGiftsDialog() {
        guildGiftsDialog = giftsDialogUtil.getGuildGiftsDialog(context, giftsName, exchangeDiamond);
        guildGiftsDialog.addBtnClickListener(v -> {
            if (!giftsDialogUtil.isJoinGuild()) {
                showTs("查找工会");
            } else if (!giftsDialogUtil.guildHasTheGifts()) {
                showTs("联系会长");
            } else {
                showPersonalGiftsDialog();
            }
            guildGiftsDialog.dismiss();
        }).show();
    }

    private void showPersonalGiftsDialog() {
        personalGiftsDialog = giftsDialogUtil.getPersonalGiftsDialog(context, predestineState, giftsState, rushCount, giftsNumber, gameState);
        personalGiftsDialog.addBtnClickListener(v -> onDialogBtnClick(personalGiftsDialog)).show();
    }

    /**
     * 领号、淘号、预订弹窗内按钮点击事件
     *
     * @param detailDialog
     */
    private void onDialogBtnClick(SimpleDialog detailDialog) {
        if (giftsState == GiftsConfig.GIFTS_STATE_PREDESTINE) {
            if (predestineState == GiftsConfig.PREDESTINE_STATE_NOT) {
                getP().predestineGifts(((EditText) detailDialog.findViewById(R.id.et_phone)).getText().toString());
            } else {
                // TODO: 2018-03-31 跳转我的预订界面
                showTs("跳我的预订界面");
                detailDialog.dismiss();
            }
        } else {
            if (gameState == 1) {
                showTs("下载成功");
                gameState = 3;
                ((Button) detailDialog.findViewById(R.id.btn_confirm)).setText(R.string.text_install_game);
            } else if (gameState == 3) {
                showTs("安装游戏");
                gameState = 4;
                ((Button) detailDialog.findViewById(R.id.btn_confirm)).setText(R.string.text_go_in_the_game);
            } else {
                detailDialog.dismiss();
                showTs("打开游戏");
            }
        }
    }

    public static void toGiftsDetailActivity(Activity activity, int giftsType) {
        Router.newIntent(activity).to(GiftsDetailActivity.class)
                .putInt(GiftsConfig.GIFTS_TYPE, giftsType).launch();
    }


    /**
     * 预订成功操作
     */
    public void predestineSuccess() {
        personalGiftsDialog.dismiss();
        predestineState = GiftsConfig.PREDESTINE_STATE_SUCCESS;
        showPersonalGiftsDialog();
    }
}
