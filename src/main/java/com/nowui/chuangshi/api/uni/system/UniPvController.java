package com.nowui.chuangshi.api.uni.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/uni/pv")
public class UniPvController extends Controller {

    @ActionKey("/system/uni/pv/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/pv/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/pv/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/pv/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/uni/pv/delete")
    public void delete() {

        renderSuccessJson();
    }

}