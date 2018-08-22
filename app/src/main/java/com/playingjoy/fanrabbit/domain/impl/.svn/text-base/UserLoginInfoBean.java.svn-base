package com.playingjoy.fanrabbit.domain.impl;

import com.playingjoy.fanrabbit.domain.BaseBean;

import java.io.Serializable;

/**
 * Author: Ly
 * Data：2018/3/31-18:04
 * Description:
 */
public class UserLoginInfoBean extends BaseBean {

    /**
     * data : {"center_id":"5","avatar":"http://baidu.com/1.jpeg","username":"maowan","uid":"100000","updated_at":"2018-03-30 20:09:12","created_at":"2018-03-30 20:09:12","id":"14","token":"fc462b2d83a6774a20e5c177ebd42432"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "status='" + super.getStatus() + '\'' +
                ",data="+data.toString()+
                '}';
    }

    public static class DataBean implements Serializable {
        /**
         * center_id : 5
         * avatar : http://baidu.com/1.jpeg
         * username : maowan
         * uid : 100000
         * updated_at : 2018-03-30 20:09:12
         * created_at : 2018-03-30 20:09:12
         * id : 14
         * token : fc462b2d83a6774a20e5c177ebd42432
         */

        private String center_id;
        private String avatar;
        private String username;
        private String uid;
        private String updated_at;
        private String created_at;
        private String id;
        private String token;

        public String getCenter_id() {
            return center_id;
        }

        public void setCenter_id(String center_id) {
            this.center_id = center_id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "{" +
                    "center_id='" + center_id + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", username='" + username + '\'' +
                    ", uid='" + uid + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    ", created_at='" + created_at + '\'' +
                    ", id='" + id + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }
}
