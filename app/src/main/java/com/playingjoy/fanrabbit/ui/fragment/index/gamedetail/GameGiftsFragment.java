package com.playingjoy.fanrabbit.ui.fragment.index.gamedetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseFragment;
import com.playingjoy.fanrabbit.ui.activity.index.GiftsDetailActivity;
import com.playingjoy.fanrabbit.ui.activity.tribe.TribeShopActivity;
import com.playingjoy.fanrabbit.ui.adapter.index.GameGiftsListAdapter;
import com.playingjoy.fanrabbit.ui.dialog.SimpleDialog;
import com.playingjoy.fanrabbit.ui.presenter.gamedetail.GameGiftsFragmentPresenter;
import com.playingjoy.fanrabbit.utils.GameState;
import com.playingjoy.fanrabbit.utils.GiftsConfig;
import com.playingjoy.fanrabbit.utils.GiftsDialogUtil;
import com.playingjoy.fanrabbit.utils.cache.UserInfoManager;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * Author: Ly
 * Data：2018/3/28-16:43
 * Description:游戏详情里面的游戏礼包碎片
 */
public class GameGiftsFragment extends BaseFragment<GameGiftsFragmentPresenter> implements GameGiftsListAdapter.OnButtonClickListener {
    @BindView(R.id.xlv_gift_list)
    XRecyclerContentLayout xlvGiftGuildList;

    GameGiftsListAdapter gameGiftsGuildPackageListAdapter;
    XRecyclerView xlvGiftPersonList;
    GameGiftsListAdapter gameGiftsPersonPackageListAdapter;
    View headView;
    GiftsDialogUtil giftsDialogUtil;
    private int gameState = 1;
    int predestineState = 1, giftsState = 1, rushCount = 10;
    String giftsNumber = "1122";
    SimpleDialog personalGiftsDialog;
    SimpleDialog guildGiftsDialog;

    public static GameGiftsFragment newInstance() {
        Bundle args = new Bundle();
        GameGiftsFragment fragment = new GameGiftsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initHeadView();
        giftsDialogUtil = new GiftsDialogUtil();
        xlvGiftGuildList.getRecyclerView().setLayoutManager(new LinearLayoutManager(context));
        View emptyView = View.inflate(context, R.layout.view_game_gifts_list_empty_tip, null);
        xlvGiftGuildList.emptyView(emptyView);
        gameGiftsGuildPackageListAdapter = new GameGiftsListAdapter(GiftsConfig.GIFTS_TYPE_TRIBE, context);
        xlvGiftGuildList.getRecyclerView().setAdapter(gameGiftsGuildPackageListAdapter);
        xlvGiftGuildList.getRecyclerView().addHeaderView(headView);
        gameGiftsGuildPackageListAdapter.setData(new String[]{"王者荣耀", "问问", "捱三顶五群", "测试", "问问", "捱三顶五群", "全文最新出去啊请问", "阿萨德正常去"});
//        xlvGiftGuildList.showEmpty();
        gameGiftsGuildPackageListAdapter.setOnButtonClickListener(this);
        gameGiftsGuildPackageListAdapter.setRecItemClick(new RecyclerItemCallback<String, RecyclerView.ViewHolder>() {
            @Override
            public void onItemClick(int position, String model, int tag, RecyclerView.ViewHolder holder) {
                // TODO: 2018-03-31 跳转礼包详情页面传值待定
               GiftsDetailActivity.toGiftsDetailActivity(getActivity(),GiftsConfig.GIFTS_TYPE_TRIBE);
            }
        });
    }

    /**
     * 初始化个人礼包
     */
    private void initHeadView() {
        headView = View.inflate(context, R.layout.header_game_gift_list, null);
        xlvGiftPersonList = headView.findViewById(R.id.xlv_gift_list);
        xlvGiftPersonList.setLayoutManager(new LinearLayoutManager(context));
        gameGiftsPersonPackageListAdapter = new GameGiftsListAdapter(GiftsConfig.GIFTS_TYPE_PERSONAL, context);
        xlvGiftPersonList.setAdapter(gameGiftsPersonPackageListAdapter);
        gameGiftsPersonPackageListAdapter.setData(new String[]{"王者荣耀(个人)", "问问(个人)", "捱三顶五群(个人)", "测试(个人)", "问问(个人)", "捱三顶五群(个人)", "全文最新出去啊请问(个人)", "阿萨德正常去(个人)"});
        gameGiftsPersonPackageListAdapter.setRecItemClick(new RecyclerItemCallback<String, RecyclerView.ViewHolder>() {
            @Override
            public void onItemClick(int position, String model, int tag, RecyclerView.ViewHolder holder) {
                // TODO: 2018-03-31 跳转礼包详情页面传值待定
                GiftsDetailActivity.toGiftsDetailActivity(getActivity(),GiftsConfig.GIFTS_TYPE_PERSONAL);
            }
        });
        gameGiftsPersonPackageListAdapter.setOnButtonClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_game_gift;
    }

    @Override
    public GameGiftsFragmentPresenter newP() {
        return new GameGiftsFragmentPresenter();
    }

    @Override
    public void onBtnClick(int giftsType, int position) {
        gameState = GameState.GAME_STATE_NOT_DOWNLOAD;
        if (giftsType == GiftsConfig.GIFTS_TYPE_PERSONAL) {
            //个人礼包
            giftsState = position % 3 + 1;
            showPersonalGiftsDialog();
        } else {
            //工会礼包
            giftsState = position % 3 + 1;
            if (giftsState == 1) {
                giftsState += 1;
            }
            if (UserInfoManager.isJoinTribe()){
                showGuildGiftsDialog();
            }else{
                TribeShopActivity.toTribeShopActivity(getActivity());
            }
        }
    }

    private void showGuildGiftsDialog() {
        guildGiftsDialog = giftsDialogUtil.getGuildGiftsDialog(context, "",1);
        guildGiftsDialog.addBtnClickListener(v -> {
            if (!giftsDialogUtil.isJoinGuild()) {
                showTs("查找工会");
            } else if (!giftsDialogUtil.guildHasTheGifts()) {
                showTs("联系会长");
            }
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

    /**
     * 预订成功
     */
    public void predestineSuccess() {
        predestineState = GiftsConfig.PREDESTINE_STATE_SUCCESS;
        personalGiftsDialog.dismiss();
        showPersonalGiftsDialog();
    }


}
