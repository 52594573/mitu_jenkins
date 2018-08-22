package com.playingjoy.fanrabbit.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.ui.dialog.SimpleDialog;

/**
 * @author Morphine
 * @date 2018-04-04.
 */

public class GiftsDialogUtil {

    /**
     * 游戏状态
     *
     * @see GameState
     */
    private int gameState;
    /**
     * 已淘次数(淘号状态下)
     */
    private int rushCount;
    /**
     * 礼包码(淘号、领号状态下)
     */
    private String giftsNumber;
    /**
     * 礼包状态
     *
     * @see GiftsConfig
     */
    private int giftsState;
    /**
     * 礼包预订状态
     *
     * @see GiftsConfig
     */
    private int predestineState;

    /**
     * 礼包名称
     */
    private String giftsName;

    /**
     * 兑换礼包所需钻石
     */
    private int giftsDiamond;

    private Context context;


    /**
     * 获取个人礼包相关操作dialog
     *
     * @param predestineState
     * @param giftsState
     * @param giftsNumber     礼包码(淘号、领号状态下)
     * @param gameState       游戏状态
     * @param rushCount       已淘次数(淘号状态下)
     * @return
     */
    public SimpleDialog getPersonalGiftsDialog(Context context, int predestineState, int giftsState, int rushCount, String giftsNumber, int gameState) {
        this.gameState = gameState;
        this.giftsNumber = giftsNumber;
        this.predestineState = predestineState;
        this.rushCount = rushCount;
        this.giftsState = giftsState;
        this.context = context;
        switch (giftsState) {
            case GiftsConfig.GIFTS_STATE_PREDESTINE:
            default:
                return initPredestineContainer();
            case GiftsConfig.GIFTS_STATE_RECEIVE:
            case GiftsConfig.GIFTS_STATE_RUSH:
                return initReceiveOrRushContainer();
        }
    }

    public SimpleDialog getGuildGiftsDialog(Context context, String giftsName, int giftsDiamond) {
        this.giftsName = giftsName;
        this.giftsDiamond = giftsDiamond;
        this.context = context;
        if (!isJoinGuild() || !guildHasTheGifts()) {
            return getGuildTipDialog();
        } else if (1 != 2) {
            return this.getConfirmGiftsDialog();
        } else {
            return this.getPersonalGiftsDialog(context, 0, giftsState, rushCount, giftsNumber, gameState);
        }
    }

    public SimpleDialog getConfirmGiftsDialog() {
        String dialogBtnText = context.getString(R.string.text_confirm);
        String dialogTitle = context.getString(R.string.text_exchange_confirm);
        View inflate = View.inflate(context, R.layout.view_gifts_detail_dialog_container_text, null);
        TextView tvTipContent = inflate.findViewById(R.id.tv_tip_content);
        Spanned spanned = Html.fromHtml(giftsName + "领号 , 需消耗贡献值" + "<font color=\"#9F7EE8\">" + giftsDiamond + "</font>个紫钻");
        tvTipContent.setText(spanned);
        return new SimpleDialog(context).addContentView(inflate)
                .setTitleText(dialogTitle)
                .setBtnText(dialogBtnText);
    }

    /**
     * 解除绑定的dialog
     *
     * @param title
     * @param container
     * @param btnMsg
     * @return
     */
    public SimpleDialog getAccountBindDialog(Context context, String title, String container, String btnMsg, View.OnClickListener listener) {
        View inflate = View.inflate(context, R.layout.view_gifts_detail_dialog_container_text, null);
        TextView tvTipContent = inflate.findViewById(R.id.tv_tip_content);
        tvTipContent.setText(container);
        return new SimpleDialog(context).addContentView(inflate)
                .setTitleText(title)
                .setBtnText(btnMsg).addBtnClickListener(listener);

    }

    /**
     * 更新的dialog
     *
     * @param context
     * @param title
     * @param view
     * @param btnMsg
     * @param listener
     * @return
     */
    public SimpleDialog getUpdateDialog(Context context, String title, View view, String btnMsg, View.OnClickListener listener) {
        return new SimpleDialog(context).addContentView(view)
                .setTitleText(title)
                .setBtnText(btnMsg).addBtnClickListener(listener);
    }

    private SimpleDialog getGuildTipDialog() {
        String dialogBtnText = isJoinGuild() ? context.getString(R.string.text_contact_president) : context.getString(R.string.text_seek_guild);
        String dialogTitle = isJoinGuild() ? context.getString(R.string.text_friendly_tips) : context.getString(R.string.text_please_join_guild);
        View inflate = View.inflate(context, R.layout.view_gifts_detail_dialog_container_text, null);
        TextView tvTipContent = inflate.findViewById(R.id.tv_tip_content);
        tvTipContent.setText(isJoinGuild() ? "您的部落暂未申请该礼包，请联系会长申请" : "该礼包需先加入游戏部落才可领取");
        return new SimpleDialog(context).addContentView(inflate)
                .setTitleText(dialogTitle)
                .setBtnText(dialogBtnText);
    }


    /**
     * 工会是否有此礼包
     *
     * @return
     */
    public boolean guildHasTheGifts() {
        return true;
    }

    /**
     * 是否加入工会
     *
     * @return
     */
    public boolean isJoinGuild() {
        return true;
    }

    private SimpleDialog initPredestineContainer() {
        int resId = predestineState == GiftsConfig.PREDESTINE_STATE_NOT ?
                R.layout.view_gifts_detail_dialog_container_predestine : R.layout.view_gifts_detail_dialog_container_text;
        String dialogBtnText = predestineState == GiftsConfig.PREDESTINE_STATE_NOT ? context.getString(R.string.text_now_predestine) : context.getString(R.string.text_show_predestine);
        String dialogTitle = context.getString(predestineState == GiftsConfig.PREDESTINE_STATE_NOT ? R.string.text_gifts_predestine :
                predestineState == GiftsConfig.PREDESTINE_STATE_SUCCESS ? R.string.text_gifts_predestine_success : R.string.text_predestine_repeat);
        return new SimpleDialog(context).addContentView(View.inflate(context, resId, null))
                .setTitleText(dialogTitle)
                .setBtnText(dialogBtnText);
    }

    /**
     * 初始化领号或淘号状态下Dialog内容
     *
     * @return
     */
    private SimpleDialog initReceiveOrRushContainer() {
        String dialogTitle = context.getString(giftsState == GiftsConfig.GIFTS_STATE_RECEIVE ? R.string.text_receive_number_success : R.string.text_rush_number_success);
        View inflate = View.inflate(context, R.layout.view_gifts_detail_dialog_container_receive, null);
        inflate.findViewById(R.id.tv_copy).setOnClickListener(v -> {
            //获取剪贴板管理器
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", giftsNumber);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            Toast.makeText(context, R.string.text_copy_success, Toast.LENGTH_SHORT).show();
        });
        ((TextView) inflate.findViewById(R.id.tv_gifts_number)).setText(giftsNumber);
        ((TextView) inflate.findViewById(R.id.tv_second_tip)).setText(giftsState == 2 ?
                R.string.text_gifts_detail_receive_second_tip : R.string.text_gifts_detail_rush_second_tip);
        TextView tvRushCount = inflate.findViewById(R.id.tv_rush_count);
        tvRushCount.setVisibility(giftsState == GiftsConfig.GIFTS_STATE_RECEIVE ? View.GONE : View.VISIBLE);
        inflate.findViewById(R.id.ll_third_tip).setVisibility(giftsState == GiftsConfig.GIFTS_STATE_RECEIVE ? View.VISIBLE : View.GONE);
        SpannableString spannableString = new SpannableString("已淘" + rushCount + "次");
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.yellow_color)),
                2, 2 + String.valueOf(rushCount).length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvRushCount.setText(spannableString);
        String btnText = initDownBtnText();
        return new SimpleDialog(context).addContentView(inflate)
                .setTitleText(dialogTitle)
                .setBtnText(btnText);
    }

    private String initDownBtnText() {
        String dialogBtnText = "";
        switch (gameState) {
            case 1:
                dialogBtnText = context.getString(R.string.text_download_game);
                break;
            case 2:
                dialogBtnText = context.getString(R.string.text_downloading);
                break;
            case 3:
                dialogBtnText = context.getString(R.string.text_install_game);
                break;
            case 4:
                dialogBtnText = context.getString(R.string.text_go_in_the_game);
                break;
        }
        return dialogBtnText;
    }


}
