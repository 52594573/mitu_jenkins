package com.playingjoy.fanrabbit.utils.net;

import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author Ly
 * @date 2017/8/9
 */

public class HttpRequest {
    /**
     * baseUrl
     */
    private static final String API_BASE_URL = "http://metoo.maowan.com:8080/api/";
    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (HttpRequest.class) {
                if (apiService == null) {
                    apiService = XApi.getInstance().getRetrofit(API_BASE_URL, true).create(ApiService.class);
                }
            }
        }
        return apiService;
    }
}
