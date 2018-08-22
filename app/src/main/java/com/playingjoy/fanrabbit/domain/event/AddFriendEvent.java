package com.playingjoy.fanrabbit.domain.event;

import cn.droidlover.xdroidmvp.event.IBus;

/**
 * @author Morphine
 * @date 2018-04-10.
 * 添加好友事件
 */

public class AddFriendEvent implements IBus.IEvent {

    public AddFriendEvent(String model) {
        this.model = model;
    }

    public AddFriendEvent() {
    }

    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int getTag() {
        return 0;
    }
}
