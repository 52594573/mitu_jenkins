package com.playingjoy.fanrabbit.ui.activity.tribe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Button;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BaseActivity;
import com.playingjoy.fanrabbit.ui.activity.index.GiftsDetailActivity;
import com.playingjoy.fanrabbit.ui.activity.mine.MyGiftPackageActivity;
import com.playingjoy.fanrabbit.ui.adapter.tribe.TribeShopAdapter;
import com.playingjoy.fanrabbit.ui.dialog.SimpleDialog;
import com.playingjoy.fanrabbit.ui.presenter.tribe.TribeShopPresenter;
import com.playingjoy.fanrabbit.utils.GameState;
import com.playingjoy.fanrabbit.utils.GiftsConfig;
import com.playingjoy.fanrabbit.utils.GiftsDialogUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

/**
 * Author: Ly
 * Data：2018/4/4-14:10
 * Description: 部落商店
 */
public class TribeShopActivity extends BaseActivity<TribeShopPresenter> {
    @BindView(R.id.xlv_tribe_shop_list)
    XRecyclerContentLayout mXlvTribeShopList;
    TribeShopAdapter tribeShopAdapter;
    GiftsDialogUtil giftsDialogUtil;
    SimpleDialog guildGiftsDialog;
    SimpleDialog giftsDialog;
    private int gameState = 1;

    @Override
    public void initData(Bundle savedInstanceState) {
        giftsDialogUtil = new GiftsDialogUtil();
        setTitleBarRightMsg(getString(R.string.text_tribe_shop), getString(R.string.text_my_gifts), v -> MyGiftPackageActivity.toMyGiftPackageActivity(this));
        setDefConfRecyclerView(mXlvTribeShopList);
        mXlvTribeShopList.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        tribeShopAdapter = new TribeShopAdapter(this);
        mXlvTribeShopList.getRecyclerView().setAdapter(tribeShopAdapter);

        tribeShopAdapter.setRecItemClick(new RecyclerItemCallback<String, TribeShopAdapter.TribeShopHolder>() {
            @Override
            public void onItemClick(int position, String model, int tag, TribeShopAdapter.TribeShopHolder holder) {
                GiftsDetailActivity.toGiftsDetailActivity(TribeShopActivity.this, GiftsConfig.GIFTS_TYPE_TRIBE);
            }
        });

        tribeShopAdapter.setOnGetNumberBtnClickListener(position -> {
            guildGiftsDialog = giftsDialogUtil.getGuildGiftsDialog(context, "测试测试", position);
            guildGiftsDialog.addBtnClickListener(v -> {
                if (!giftsDialogUtil.isJoinGuild()) {
                    showTs("查找工会");
                } else if (!giftsDialogUtil.guildHasTheGifts()) {
                    showTs("联系会长");
                } else {
                    showPersonalGiftsDialog(position % 2 + 2, position, "123456");
                }
                guildGiftsDialog.dismiss();
            }).show();
        });
    }

    private void showPersonalGiftsDialog(int giftsState, int rushCount, String giftsNumber) {
        giftsDialog = giftsDialogUtil.getPersonalGiftsDialog(context, GiftsConfig.PREDESTINE_STATE_ALREADY, giftsState, rushCount, giftsNumber, gameState);
        giftsDialog.addBtnClickListener(v -> onDialogBtnClick(giftsDialog)).show();
    }

    private void onDialogBtnClick(SimpleDialog detailDialog) {
        if (gameState == GameState.GAME_STATE_NOT_DOWNLOAD) {
            showTs("下载成功");
            gameState = GameState.GAME_STATE_NOT_INSTALL;
            ((Button) detailDialog.findViewById(R.id.btn_confirm)).setText(R.string.text_install_game);
        } else if (gameState == GameState.GAME_STATE_NOT_INSTALL) {
            showTs("安装游戏");
            gameState = GameState.GAME_STATE_INSTALLED;
            ((Button) detailDialog.findViewById(R.id.btn_confirm)).setText(R.string.text_go_in_the_game);
        } else {
            detailDialog.dismiss();
            showTs("打开游戏");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tribe_shop;
    }

    @Override
    public TribeShopPresenter newP() {
        return new TribeShopPresenter();
    }

    public static void toTribeShopActivity(Activity activity) {
        Router.newIntent(activity).to(TribeShopActivity.class).launch();
    }

}
