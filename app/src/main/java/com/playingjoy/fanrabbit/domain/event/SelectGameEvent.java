package com.playingjoy.fanrabbit.domain.event;

import cn.droidlover.xdroidmvp.event.IBus;

/**
 * Created by Ly on 2018/4/18.
 * 选择所属游戏的事件
 */

public class SelectGameEvent implements IBus.IEvent {
    @Override
    public int getTag() {
        return 1;
    }

    private String gameName;
    private String gamePic;
    private int gameId;


    public SelectGameEvent(String gameName, String gamePic, int gameId) {
        this.gameName = gameName;
        this.gamePic = gamePic;
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGamePic() {
        return gamePic;
    }

    public int getGameId() {
        return gameId;
    }
}
