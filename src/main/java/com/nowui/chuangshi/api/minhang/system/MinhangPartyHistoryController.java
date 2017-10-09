package com.nowui.chuangshi.api.minhang.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/minhang/party/history")
public class MinhangPartyHistoryController extends Controller {

    @ActionKey("/system/minhang/party/history/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/party/history/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/party/history/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/party/history/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/minhang/party/history/delete")
    public void delete() {

        renderSuccessJson();
    }

}