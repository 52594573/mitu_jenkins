package com.playingjoy.fanrabbit.domain.event;

import cn.droidlover.xdroidmvp.event.IBus;

/**
 * Author: Ly
 * Data：2018/3/31-11:33
 * Description: 微信传递Code
 */
public class WechatCodeEvent implements IBus.IEvent {
    String code;

    public WechatCodeEvent(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int getTag() {
        return 1;
    }
}
