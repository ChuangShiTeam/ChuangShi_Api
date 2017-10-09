package com.nowui.chuangshi.api.minhang.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/minhang/party/song")
public class MinhangPartySongController extends Controller {

    @ActionKey("/system/minhang/party/song/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/party/song/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/party/song/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/party/song/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/party/song/delete")
    public void delete() {

        renderSuccessJson();
    }

}