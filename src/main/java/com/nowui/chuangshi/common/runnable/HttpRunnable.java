package com.nowui.chuangshi.common.runnable;

import com.nowui.chuangshi.model.Http;
import com.nowui.chuangshi.service.HttpService;

public class HttpRunnable implements Runnable {

    private Http http;
    private final HttpService httpService = new HttpService();

    public HttpRunnable(Http http) {
        this.http = http;
    }

    @Override
    public void run() {
        httpService.save(http.getHttp_id(), http.getApp_id(), http.getHttp_url(), http.getHttp_code(), http.getHttp_request(), http.getHttp_response(), http.getHttp_token(), http.getHttp_platform(), http.getHttp_version(), http.getHttp_ip_address(), http.getHttp_run_time(), http.getSystem_create_user_id());
    }

}
