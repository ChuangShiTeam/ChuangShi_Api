package com.nowui.chuangshi.runnable;

import com.jfinal.weixin.sdk.api.AccessTokenApi;

public class WeChatRunnable implements Runnable {

    @Override
    public void run() {
        AccessTokenApi.refreshAccessToken();
    }
}
