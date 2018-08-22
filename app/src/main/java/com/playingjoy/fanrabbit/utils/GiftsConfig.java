package com.playingjoy.fanrabbit.utils;

import android.content.Context;
import android.graphics.Color;

import com.hyphenate.util.DensityUtil;
import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.widget.TextDrawable;

/**
 * 礼包相关配置属性
 *
 * @author Morphine
 * @date 2018-04-04.
 */

public class GiftsConfig {

    /**
     * 礼包类型key字符串
     */
    public static final String GIFTS_TYPE = "gifts_type";


    /**
     * 礼包类型:个人礼包
     */
    public static final int GIFTS_TYPE_PERSONAL = 1;

    /**
     * 礼包类型:工会礼包
     */
    public static final int GIFTS_TYPE_TRIBE = 2;

    /**
     * 礼包状态:1-预订
     */
    public static final int GIFTS_STATE_PREDESTINE = 1;

    /**
     * 礼包状态:2-领号
     */
    public static final int GIFTS_STATE_RECEIVE = 2;
    /**
     * 礼包状态:3-淘号
     */
    public static final int GIFTS_STATE_RUSH = 3;

    /**
     * 预订状态 1-未预定
     */
    public static final int PREDESTINE_STATE_NOT = 1;
    /**
     * 预订状态 2-预定成功
     */
    public static final int PREDESTINE_STATE_SUCCESS = 2;

    /**
     * 预订状态 3-已经预定
     */
    public static final int PREDESTINE_STATE_ALREADY = 3;


    public static TextDrawable getGiftsDrawableFlag(Context context, int giftsType) {
        int colorResId = giftsType == GIFTS_TYPE_TRIBE ? R.color.gifts_type_color_tribe : R.color.gifts_type_color_personal;
        String text = context.getString(giftsType == GIFTS_TYPE_TRIBE ? R.string.text_tribe : R.string.text_personal);
        TextDrawable textDrawable = TextDrawable.builder()
                .beginConfig()
                .withBorder(DensityUtil.dip2px(context, 1), context.getResources().getColor(colorResId))
                .textColor(context.getResources().getColor(colorResId))
                .fontSize(DensityUtil.dip2px(context, 11))
                .endConfig()
                .buildRoundRect(text, Color.WHITE, DensityUtil.dip2px(context, 2));
        textDrawable.setBounds(0, 0, DensityUtil.dip2px(context, 31), DensityUtil.dip2px(context, 17));
        return textDrawable;
    }

}
