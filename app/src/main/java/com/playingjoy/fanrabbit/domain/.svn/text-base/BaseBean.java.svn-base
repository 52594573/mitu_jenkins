package com.playingjoy.fanrabbit.domain;

import java.io.Serializable;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by Ly on 2018/3/19.
 */

public class BaseBean implements IModel, Serializable {
    private String status;
    private String message;
    private int code;

    @Override
    public String toString() {
        return "BaseBean{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isAuthError() {
        return false;
    }

    @Override
    public boolean isBizError() {
        return false;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }
}
