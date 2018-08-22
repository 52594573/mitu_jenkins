package com.playingjoy.fanrabbit.ui.presenter.gamedetail;

import com.playingjoy.fanrabbit.base.BasePresenter;
import com.playingjoy.fanrabbit.ui.activity.index.GiftsDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 礼包详情控制中心
 *
 * @author Morphine
 * @date 2018-03-31.
 */

public class GiftsDetailPresenter extends BasePresenter<GiftsDetailActivity> {

    private int x = 0;

    /**
     * 获取礼包详情数据
     */
    public void initGiftsDetailData() {
        getV().setGiftsState(2);
        getV().setPredestineCount("1234");
        getV().setGameName("王者荣耀");
        getV().setRushCount(3333);
        getV().setContributionValue(333);
        getV().setGiftsLast(30);
        getV().setGiftsName("王者荣耀情人节礼包");
        getV().setGiftsContent("1 妲己情人节皮肤、安琪拉皮肤\n2 钻石100、铭文10\n3 得李白、刘邦、钟馗任一英雄");
        getV().setExchangePeriod("2018年03月01日0:00至2018年04月01日23:59");
        getV().setUsedMethod("点击奖励大厅-激活码兑换处，填入兑换码领取礼包");
    }

    /**
     * 获取相关游戏推荐
     */
    public void getRecommendGame() {
        x++;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(x + "相关游戏" + i);
        }
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .compose(getV().bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> getV().setRecommendGameList(list));
    }

    /**
     * 预订礼包
     *
     * @param phone 手机号码
     */
    public void predestineGifts(String phone) {
        getV().showTs(phone);
        getV().predestineSuccess();
    }

    /**
     * 领号
     */
    public void receiveNumber() {
        getV().showGiftsNumber("ABCDEFG1234");
    }

    /**
     * 淘号
     */
    public void rushNumber() {
        getV().showGiftsNumber("QWERTYU4321");
    }
}
