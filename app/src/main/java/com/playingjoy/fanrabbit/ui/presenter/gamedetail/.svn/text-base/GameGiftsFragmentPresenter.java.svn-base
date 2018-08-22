package com.playingjoy.fanrabbit.ui.presenter.gamedetail;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.base.BasePresenter;
import com.playingjoy.fanrabbit.ui.fragment.index.gamedetail.GameGiftsFragment;

import cn.droidlover.xdroidmvp.kit.Kits;

/**
 * Author: Ly
 * Data：2018/3/28-16:43
 * Description:
 */
public class GameGiftsFragmentPresenter extends BasePresenter<GameGiftsFragment> {
    /**
     * 预订礼包
     *
     * @param phoneNumber 手机号
     */
    public void predestineGifts(String phoneNumber) {
        if (Kits.Regular.isPhoneNumber(phoneNumber)) {
            getV().showTs(phoneNumber);
            getV().predestineSuccess();
        } else {
            getV().showTs(getV().getString(R.string.text_phone_number_error));
        }
    }
}
